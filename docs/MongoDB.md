
## 命令

### 查询

#### 排序(-1倒序 1顺序) limit
```bash
db.getCollection('proxy').find().sort({'stability':-1}).limit(10)
```
### 返回指定列(1展示 -1隐藏)
> https://docs.mongodb.com/manual/reference/method/db.collection.find/
```bash
db.getCollection('github_repo').find({},{name:1,starNum:1}).sort({'starNum':-1}).limit(10)
```