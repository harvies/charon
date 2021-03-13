package io.github.harvies.charon.tests.base.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;
import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

public class CommandHelloWorld extends HystrixCommand<String> {

	private final String name;

	public CommandHelloWorld(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("HelloWorld"))
                //.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("HelloWorldPool")
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionIsolationStrategy(ExecutionIsolationStrategy.THREAD)));
        this.name = name;
    }

	@Override
	protected String run() {
		return "Hello " + name + "!";
	}

	public static class UnitTest {

		@Test
		public void testSynchronous() {
			//assertEquals("Hello World!", new CommandHelloWorld("World").execute());
			//assertEquals("Hello Bob!", new CommandHelloWorld("Bob").execute());
			System.out.println(new CommandHelloWorld("World").execute());
			System.out.println(new CommandHelloWorld("Bob").execute());

		}

		@Test
		public void testAsynchronous1() throws Exception {
			assertEquals("Hello World!", new CommandHelloWorld("World").queue().get());
			assertEquals("Hello Bob!", new CommandHelloWorld("Bob").queue().get());
		}

		@Test
		public void testAsynchronous2() throws Exception {

			Future<String> fWorld = new CommandHelloWorld("World").queue();
			Future<String> fBob = new CommandHelloWorld("Bob").queue();

			assertEquals("Hello World!", fWorld.get());
			assertEquals("Hello Bob!", fBob.get());
		}

		@Test
		public void testObservable() throws Exception {

			/**
	         * 返回的是Hot Observable,HotObservable，不论 “事件源” 是否有“订阅者”
	         * 都会在创建后对事件进行发布。所以对于Hot Observable的每一个“订阅者”都有
	         * 可能从“事件源”的中途开始的，并可能只是看到了整个操作的局部过程
	         */
			Observable<String> fWorld = new CommandHelloWorld("World").observe();
			Observable<String> fBob = new CommandHelloWorld("Bob").observe();

			// blocking
			assertEquals("Hello World!", fWorld.toBlocking().single());
			assertEquals("Hello Bob!", fBob.toBlocking().single());

			// non-blocking
			// - this is a verbose anonymous inner-class approach and doesn't do
			// assertions
			fWorld.subscribe(new Observer<String>() {

				@Override
				public void onCompleted() {
					// nothing needed here
				}

				@Override
				public void onError(Throwable e) {
					e.printStackTrace();
				}

				@Override
				public void onNext(String v) {
					System.out.println("onNext: " + v);
				}

			});

		    fWorld.subscribe((v) -> {
		        System.out.println("onNext: " + v);
		    }, (exception) -> {
		        exception.printStackTrace();
		    });

			// non-blocking
			// - also verbose anonymous inner-class
			// - ignore errors and onCompleted signal
			fBob.subscribe(new Action1<String>() {

				@Override
				public void call(String v) {
					System.out.println("onNext: " + v);
				}

			});
		}

	    @Test
	    public void testToObservable() {
	        /**
	         * Cold Observable在没有 “订阅者” 的时候并不会发布事件，
	         * 而是进行等待，知道有 “订阅者” 之后才发布事件，所以对于
	         * Cold Observable的订阅者，它可以保证从一开始看到整个操作的全部过程。
	         */
	        Observable<String> co = new CommandHelloWorld("World").toObservable();
	        System.out.println(co.toBlocking().single());
	    }
	}
}
