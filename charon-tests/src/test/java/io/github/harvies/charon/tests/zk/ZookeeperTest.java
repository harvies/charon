package io.github.harvies.charon.tests.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
public class ZookeeperTest {
    public static final String testPath = "/test";
    private static CuratorFramework client = null;

    @BeforeAll
    public static void before() {
        client = ZkUtils.getClient();
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

    @Test
    public void update() throws Exception {
        String test = client.create().orSetData().forPath(testPath, "testDataUpdate11".getBytes("UTF-8"));
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
    @Test
    public void delete() throws Exception {
        client.delete().forPath(testPath);
    }

}
