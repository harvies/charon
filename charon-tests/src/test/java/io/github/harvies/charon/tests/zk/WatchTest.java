package io.github.harvies.charon.tests.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.locks.LockSupport;

import static io.github.harvies.charon.tests.zk.ZookeeperTest.testPath;

@Slf4j
public class WatchTest {
    public static void main(String[] args) throws Exception {
        CuratorWatcher curatorWatcher = WatchTest::process;
        ZkUtils.getClient().watchers().add().usingWatcher(curatorWatcher).forPath(testPath);
        LockSupport.park();
    }

    private static void process(WatchedEvent event) {
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
        }
    }
}
