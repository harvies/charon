## mybatis执行流程

### 1.创建sqlSessionFactory
#### get Builder
```java
public class SqlSessionFactoryBuilder {
  public SqlSessionFactory build(InputStream inputStream, String environment, Properties properties) {
    try {
      XMLConfigBuilder parser = new XMLConfigBuilder(inputStream, environment, properties);
      return build(parser.parse());
    } catch (Exception e) {
      throw ExceptionFactory.wrapException("Error building SqlSession.", e);
    } finally {
      ErrorContext.instance().reset();
      try {
        inputStream.close();
      } catch (IOException e) {
        // Intentionally ignore. Prefer previous error.
      }
    }
}
}
```
#### build
```java
  public class DefaultSqlSessionFactory implements SqlSessionFactory {
  
    private final Configuration configuration;
  
    public DefaultSqlSessionFactory(Configuration configuration) {
      this.configuration = configuration;
    }
}
```
### 2.获取sqlSession
```java
public class DefaultSqlSessionFactory implements SqlSessionFactory {
  private SqlSession openSessionFromDataSource(ExecutorType execType, TransactionIsolationLevel level, boolean autoCommit) {
    Transaction tx = null;
    try {
      final Environment environment = configuration.getEnvironment();
      final TransactionFactory transactionFactory = getTransactionFactoryFromEnvironment(environment);
      tx = transactionFactory.newTransaction(environment.getDataSource(), level, autoCommit);
      final Executor executor = configuration.newExecutor(tx, execType);
      return new DefaultSqlSession(configuration, executor, autoCommit);
    } catch (Exception e) {
      closeTransaction(tx); // may have fetched a connection so lets call close()
      throw ExceptionFactory.wrapException("Error opening session.  Cause: " + e, e);
    } finally {
      ErrorContext.instance().reset();
    }
  }
  }
  public class DefaultSqlSession implements SqlSession {
  
    private Configuration configuration;
    private Executor executor;
  
    private boolean autoCommit;
    private boolean dirty;
    private List<Cursor<?>> cursorList;
  
    public DefaultSqlSession(Configuration configuration, Executor executor, boolean autoCommit) {
      this.configuration = configuration;
      this.executor = executor;
      this.dirty = false;
      this.autoCommit = autoCommit;
    }
}
```
> executor 默认 CachingExecutor



### 3.getMapper
> 创建 Mapper的代理对象,使用jdk动态代理 
> new MapperProxy(sqlSession,mapperInterface,methodCache)
```java
public class MapperRegistry {

  private final Configuration config;
  private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<Class<?>, MapperProxyFactory<?>>();

  public MapperRegistry(Configuration config) {
    this.config = config;
  }

  @SuppressWarnings("unchecked")
  public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
    final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
    if (mapperProxyFactory == null) {
      throw new BindingException("Type " + type + " is not known to the MapperRegistry.");
    }
    try {
      return mapperProxyFactory.newInstance(sqlSession);
    } catch (Exception e) {
      throw new BindingException("Error getting mapper instance. Cause: " + e, e);
    }
  }
}
```

### 4.执行sql

#### 1.根据Method去methodCache里获取是否有缓存
```java
public class MapperProxy<T> implements InvocationHandler, Serializable {
  private MapperMethod cachedMapperMethod(Method method) {
    MapperMethod mapperMethod = methodCache.get(method);
    if (mapperMethod == null) {
      mapperMethod = new MapperMethod(mapperInterface, method, sqlSession.getConfiguration());
      methodCache.put(method, mapperMethod);
    }
    return mapperMethod;
  }
  }
```
#### 2.执行sql
```java
public class MapperProxy<T> implements InvocationHandler, Serializable {
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    try {
      if (Object.class.equals(method.getDeclaringClass())) {
        return method.invoke(this, args);
      } else if (isDefaultMethod(method)) {
        return invokeDefaultMethod(proxy, method, args);
      }
    } catch (Throwable t) {
      throw ExceptionUtil.unwrapThrowable(t);
    }
    final MapperMethod mapperMethod = cachedMapperMethod(method);
    return mapperMethod.execute(sqlSession, args);
  }
  }
  public class MapperMethod {
   public Object execute(SqlSession sqlSession, Object[] args) {
      Object result;
      switch (command.getType()) {
        case INSERT: {
      	Object param = method.convertArgsToSqlCommandParam(args);
          result = rowCountResult(sqlSession.insert(command.getName(), param));
          break;
        }
        case UPDATE: {
          Object param = method.convertArgsToSqlCommandParam(args);
          result = rowCountResult(sqlSession.update(command.getName(), param));
          break;
        }
        case DELETE: {
          Object param = method.convertArgsToSqlCommandParam(args);
          result = rowCountResult(sqlSession.delete(command.getName(), param));
          break;
        }
        case SELECT:
          if (method.returnsVoid() && method.hasResultHandler()) {
            executeWithResultHandler(sqlSession, args);
            result = null;
          } else if (method.returnsMany()) {
            result = executeForMany(sqlSession, args);
          } else if (method.returnsMap()) {
            result = executeForMap(sqlSession, args);
          } else if (method.returnsCursor()) {
            result = executeForCursor(sqlSession, args);
          } else {
            Object param = method.convertArgsToSqlCommandParam(args);
            result = sqlSession.selectOne(command.getName(), param);
          }
          break;
        case FLUSH:
          result = sqlSession.flushStatements();
          break;
        default:
          throw new BindingException("Unknown execution method for: " + command.getName());
      }
      if (result == null && method.getReturnType().isPrimitive() && !method.returnsVoid()) {
        throw new BindingException("Mapper method '" + command.getName() 
            + " attempted to return null from a method with a primitive return type (" + method.getReturnType() + ").");
      }
      return result;
    }
    }
```
