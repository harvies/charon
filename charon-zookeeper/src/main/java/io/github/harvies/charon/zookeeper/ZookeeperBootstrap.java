package io.github.harvies.charon.zookeeper;

import io.github.harvies.charon.util.FileUtils;
import org.apache.zookeeper.server.quorum.QuorumPeerMain;

public class ZookeeperBootstrap {
    public static void main(String[] args) {
        args = new String[1];
        args[0] = FileUtils.getClassPathFileAbsolutePath("conf/zoo.cfg");
        QuorumPeerMain.main(args);
    }
}
