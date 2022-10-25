import io.github.harvies.charon.jcef.CefHttpClient;
import io.github.harvies.charon.model.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.cef.network.CefRequest;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@Slf4j
public class TestMain {
    private final static ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
    static {
        ses.scheduleWithFixedDelay(() -> {
            CefRequest cefRequest = CefRequest.create();
            cefRequest.setURL("https://www.baidu.com");
            ApiResult<String> execute = CefHttpClient.INSTANCE.execute(cefRequest, null);
            log.info("execute",execute);
        },10,10, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        BaiduFrame.main(args);
    }
}
