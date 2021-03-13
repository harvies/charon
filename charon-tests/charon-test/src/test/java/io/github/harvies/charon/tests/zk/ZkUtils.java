package io.github.harvies.charon.tests.zk;

import io.github.harvies.charon.util.PropertiesUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ZkUtils {

    public static CuratorFramework getClient() {
        return getClient(PropertiesUtils.getDefaultProperty("charon.zk.url"));
    }

    public static CuratorFramework getClient(String zookeeperConnectionString) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(zookeeperConnectionString)
                //设置重试策略
                .retryPolicy(retryPolicy)
                .build();
        //Start the client. Most mutator methods will not work until the client is started
        curatorFramework.start();
        return curatorFramework;
    }
}
