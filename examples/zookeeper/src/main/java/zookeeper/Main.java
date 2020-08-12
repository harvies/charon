package zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author harvies
 */
public class Main {
    private static final String zookeeperConnectionString = "zk.dev.kikera.top:2181";

    public static void main(String[] args) throws Exception {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 1);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
        client.start();
        String path = "/aaaa";
        Void aVoid = client.delete().forPath(path);
        System.err.println(aVoid);
        System.err.println(client.create().forPath(path, "aaaa".getBytes()));
        System.err.println(new String(client.getData().forPath(path)));
    }
}
