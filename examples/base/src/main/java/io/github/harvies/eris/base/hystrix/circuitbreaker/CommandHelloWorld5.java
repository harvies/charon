package io.github.harvies.eris.base.hystrix.circuitbreaker;

import com.netflix.hystrix.*;

import java.util.concurrent.TimeUnit;

/**
 * 测试4-熔断测试
 */
public class CommandHelloWorld5 extends HystrixCommand<String> {
    private Integer id;

    public CommandHelloWorld5(Integer id) {
        super(setter());
        this.id = id;
    }

    private static Setter setter() {
        return ApiSetter.setter("getNum");

    }

    @Override
    protected String run() throws Exception {
        System.out.print("id:" + id);
        if (id % 2 == 0 && id <= 10) { //让小于等于10的偶数返回
            return "running run():" + id;
        } else { //让奇数或大于10的数进入fallback
            TimeUnit.MILLISECONDS.sleep(200);
            return " XXX ";
        }
    }

    @Override
    protected String getFallback() {
        return " ====== CircuitBreaker fallback" + id + " ,是否进入熔断:" + super.isCircuitBreakerOpen();
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 30; i++) {
            System.out.println(new CommandHelloWorld5(i).execute());
//			/*Future<String> future =  new CommandHelloWorld5(i).queue();
//        	System.out.println(future.get());*/
        }
    }

    private static class ApiSetter {

        /**
         * @param commandKeyName    服务标识名称
         * @param threadPoolKeyName 线程程名称
         * @return
         */
        public static Setter setter(String commandKeyName, String threadPoolKeyName) {
            return setter("ApiGroup", commandKeyName, threadPoolKeyName);
        }

        /**
         * @param commandKeyName 服务标识名称
         * @return
         */
        public static Setter setter(String commandKeyName) {
            return setter(commandKeyName, "Api-Pool");
        }

        /**
         * @param groupKeyName      服务分组名
         * @param commandKeyName    服务标识名称
         * @param threadPoolKeyName 线程池名称
         * @return
         * @description 相关参数设置
         */
        public static Setter setter(String groupKeyName, String commandKeyName, String threadPoolKeyName) {
            HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey(groupKeyName);            // 服务分组
            HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey(commandKeyName);                    // 服务标识
            HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey(threadPoolKeyName);        // 线程池名称
            HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties.Setter()    // 线程配置
                    .withCoreSize(25)                        // 设置核心线程池的大小,默认为10
                    .withKeepAliveTimeMinutes(5)            // 设置存活时间，单位分钟。如果coreSize小于maximumSize，那么该属性控制一个线程从实用完成到被释放的时间。默认为1
                    .withMaxQueueSize(Integer.MAX_VALUE)    // 设置BlockingQueue最大的队列值,默认值为-1
                    //设置队列拒绝的阈值——一个为设置的拒绝访问的最大队列值，即使maxQueueSize还没有达到。
                    //当将一个线程放入队列等待执行时，HystrixCommand使用该属性
                    //注意：如果maxQueueSize设置为-1，该属性不可用 ,默认为5
                    .withQueueSizeRejectionThreshold(10000);

            //命令属性的配置
            HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
                    .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                    //设置HystrixCommand.run()的执行是否有超时限制。默认是true
                    .withExecutionTimeoutEnabled(true)

                    //使用线程隔离时,是否对命令执行超时的线程调用中断(Thread.interrupt())操作,默认是:true
                    //即：设置HystrixCommand.run()的执行是否在超时发生时被中断。
                    .withExecutionIsolationThreadInterruptOnTimeout(true)

                    //设置调用者等待命令执行的超时限制，超过此时间，HystrixCommand被标记为TIMEOUT，并执行回退逻辑。默认是1000ms
                    //注意：超时会作用在HystrixCommand.queue()，即使调用者没有调用get()去获得Future对象。
                    .withExecutionTimeoutInMilliseconds(100)

                    //使用线程隔离时，调用超时时间，默认:1秒  ,该方法已经不建议使用！
//	                .withExecutionIsolationThreadTimeoutInMilliseconds(3000)

                    //失败率达到20%熔断器启动,默认值是50
                    .withCircuitBreakerErrorThresholdPercentage(10)

                    // 置为true时，所有请求都将被拒绝，直接到fallback
                    //.withCircuitBreakerForceOpen(true)

                    //10秒内请求超过5个的话才会启动熔断器,
                    //熔断器在设置在一个滚动窗口中，打开断路器的最少请求数。
                    //默认20。也就是10秒钟内至少请求20次，熔断器才发挥起作用 ,
                    //比如：如果值是20,在一个窗口内(比如10s),收到19个请求,即使这19个请求都失败了，熔断器也不会打开
                    //.withCircuitBreakerRequestVolumeThreshold(5)

                    //是否启用熔断器,默认true. 启动
                    .withCircuitBreakerEnabled(true)

                    //设置HystrixCommand.getCacheKey()是否启用，
                    //由HystrixRequestCache通过请求缓存提供去重复数据功能[默认为true]
                    .withRequestCacheEnabled(false);

            //返回
            return HystrixCommand.Setter
                    .withGroupKey(groupKey)                                // 服务分组
                    .andCommandKey(commandKey)                                // 服务标识
                    .andThreadPoolKey(threadPoolKey)                        // 线程池名称
                    .andThreadPoolPropertiesDefaults(threadPoolProperties)    // 线程配置
                    .andCommandPropertiesDefaults(commandProperties);        // 命令属性的配置
        }

        /**
         * ☆参数说明：
         1.HystrixCommandGroupKey：服务分组，以上pydyn分组就包括多个服务，必填选项
         2.HystrixCommandKey：服务的名称，唯一标识，如果不配置，则默认是类名
         3.HystrixThreadPoolKey：线程池的名称，相同线程池名称的线程池是同一个，如果不配置，默认为分组名
         4.HystrixThreadPoolProperties：线程池的配置，
         coreSize配置核心线程池的大小，
         maxQueueSize线程池队列的最大大小，
         queueSizeRejectionThreshold，限制当前队列的大小，
         实际队列大小由这个参数决定，即到达队列里面条数到达10000，则都会被拒绝。
         5.HystrixCommandProperties：配置命令的一些参数，
         如executionIsolationStrategy，配置执行隔离策略，默认是使用线程隔离，THREAD即为线程池隔离，
         ExecutionIsolationThreadInterruptOnTimeout 使用线程隔离时,是否对命令执行超时的线程调用中断操作.默认：true
         和ExecutionTimeoutInMilliseconds配置了启用超时和最大执行时间，这里为3s，
         circuitBreakerErrorThresholdPercentage失败率配置，默认为50%，这里配置的为25%，即失败率到达25%触发熔断
         */

    }
}
