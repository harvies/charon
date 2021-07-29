package io.github.harvies.charon.zookeeper.bootstrap.cluster;

import io.github.harvies.charon.util.FileUtils;
import org.apache.zookeeper.server.quorum.QuorumPeerMain;

public class ZookeeperClusterNode1Bootstrap {
    public static void main(String[] args) {
        args = new String[1];
        args[0] = FileUtils.getClassPathFileAbsolutePath("conf/cluster/zoo-node1.cfg");
        QuorumPeerMain.main(args);
    }
}
