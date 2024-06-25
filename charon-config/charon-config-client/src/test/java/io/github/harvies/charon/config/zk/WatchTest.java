package io.github.harvies.charon.config.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.locks.LockSupport;

import static io.github.harvies.charon.config.zk.ZookeeperTest.testPath;

@Slf4j
public class WatchTest {

    private static final CuratorFramework client = ZkUtils.getClient();

    public static void main(String[] args) throws Exception {
        CuratorWatcher curatorWatcher = WatchTest::process;
        client.watchers().add().usingWatcher(curatorWatcher).forPath(testPath);
        LockSupport.park();
    }

    private static void process(WatchedEvent event) throws Exception {
        log.info("event:[{}]", event);
        if (event.getType().getIntValue() == Watcher.Event.EventType.NodeCreated.getIntValue()) {
            log.info("节点[{}]被创建", event.getPath());
            return;
        }
        if (event.getType().getIntValue() == Watcher.Event.EventType.NodeDeleted.getIntValue()) {
            log.info("节点[{}]被删除", event.getPath());
            return;
        }
        if (event.getType().getIntValue() == Watcher.Event.EventType.NodeDataChanged.getIntValue()) {
            log.info("节点[{}]被更新", event.getPath());
            byte[] bytes = client.getData().forPath(testPath);
            log.info("getData:[{}]", new String(bytes, StandardCharsets.UTF_8));
        }
    }
}
