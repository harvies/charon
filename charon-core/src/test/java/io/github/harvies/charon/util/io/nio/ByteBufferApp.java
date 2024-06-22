package io.github.harvies.charon.util.io.nio;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

public class ByteBufferApp {
    @Test
    public void testBuffer() {
        // 初始化一个大小为6的ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(6);
        print(buffer);  // 初始状态：position: 0, limit: 6, capacity: 6

        // 往buffer中写入3个字节的数据
        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.put((byte) 3);
        print(buffer);  // 写入之后的状态：position: 3, limit: 6, capacity: 6

        System.out.println("************** after flip **************");
        buffer.flip();
        print(buffer);  // 切换为读取模式之后的状态：position: 0, limit: 3, capacity: 6

        buffer.get();
        buffer.get();
        print(buffer);  // 读取两个数据之后的状态：position: 2, limit: 3, capacity: 6
    }

    @Test
    public void testMark() {
        ByteBuffer buffer = ByteBuffer.allocate(6);
        // position: 0, limit: 6, capacity: 6

        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.put((byte) 3);
        // position: 3, limit: 6, capacity: 6

        buffer.mark();  // 写入三个字节数据后进行标记
        // position: 3, limit: 6, capacity: 6

        buffer.put((byte) 4); // 再次写入一个字节数据
        // position: 4, limit: 6, capacity: 6

        buffer.reset(); // 对buffer进行重置，此时将恢复到Mark时的状态
        // position: 3, limit: 6, capacity: 6

        buffer.flip();  // 切换为读取模式，此时有三个数据可供读取
        // position: 0, limit: 3, capacity: 6

        buffer.get(); // 读取一个字节数据之后进行标记
        buffer.mark();
        // position: 1, limit: 3, capacity: 6

        buffer.get(); // 继续读取一个字节数据
        // position: 2, limit: 3, capacity: 6

        buffer.reset(); // 进行重置之后，将会恢复到mark的状态
        // position: 1, limit: 3, capacity: 6
    }

    @Test
    public void testRewind() {
        ByteBuffer buffer = ByteBuffer.allocate(6);
        // position: 0, limit: 6, capacity: 6

        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.put((byte) 3);
        // position: 3, limit: 6, capacity: 6

        buffer.rewind();  // 调用rewind()方法之后，buffer状态将会重置
        // position: 0, limit: 6, capacity: 6
    }

    @Test
    public void testCompact() {
        ByteBuffer buffer = ByteBuffer.allocate(6);
        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.put((byte) 3);
        buffer.put((byte) 4);
        buffer.put((byte) 5);
        buffer.put((byte) 6); // 初始化一个写满的buffer

        buffer.flip();
        // position: 0, limit: 6, capacity: 6  -- 切换为读取模式

        buffer.get();
        buffer.get();
        // position: 2, limit: 6, capacity: 6  -- 读取两个字节后，还剩余四个字节

        buffer.compact();
        // position: 4, limit: 6, capacity: 6  -- 进行压缩之后将从第五个字节开始

        buffer.put((byte) 7);
        // position: 5, limit: 6, capacity: 6  -- 写入一个字节数据的状态
    }

    private void print(ByteBuffer buffer) {
        System.out.printf("position: %d, limit: %d, capacity: %d\n",
                buffer.position(), buffer.limit(), buffer.capacity());
    }
}