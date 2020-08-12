import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ZookeeperTest {
    private static final String zookeeperConnectionString = "zk.dev.kikera.top:2181";
    private static final String testPath = "/test";
    private static CuratorFramework client = null;

    @BeforeAll
    public static void before() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder()
                .connectString(zookeeperConnectionString)
                //设置重试策略
                .retryPolicy(retryPolicy)
                .build();
        //Start the client. Most mutator methods will not work until the client is started
        client.start();
    }

    /**
     * 获取根目录下所有节点
     *
     * @throws Exception
     */
    @Test
    public void getChildren() throws Exception {
        List<String> strings = client.getChildren().forPath("/");
        System.err.println(strings);
    }

    /**
     * 创建一个节点并设置数据
     *
     * @throws Exception
     */
    @Test
    public void create() throws Exception {
        String test = client.create().forPath(testPath, "testData".getBytes("UTF-8"));
        System.err.println(test);
    }

    /**
     * 获取指定节点下的数据
     *
     * @throws Exception
     */
    @Test
    public void getData() throws Exception {
        byte[] bytes = client.getData().forPath(testPath);
        System.err.println(new String(bytes, "UTF-8"));
    }

    /**
     * 删除指定节点
     *
     * @throws Exception
     */
    @AfterAll
    public static void delete() throws Exception {
        client.delete().forPath(testPath);
    }
}
