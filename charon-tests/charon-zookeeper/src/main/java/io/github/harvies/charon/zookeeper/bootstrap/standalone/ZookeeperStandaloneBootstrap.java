package io.github.harvies.charon.zookeeper.bootstrap.standalone;

import io.github.harvies.charon.util.FileUtils;
import org.apache.zookeeper.server.quorum.QuorumPeerMain;

public class ZookeeperStandaloneBootstrap {
    public static void main(String[] args) {
        args = new String[1];
        args[0] = FileUtils.getClassPathFileAbsolutePath("conf/standalone/zoo.cfg");
        QuorumPeerMain.main(args);
    }
}
