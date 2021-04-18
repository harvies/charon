package io.github.harvies.charon.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class SnowFlakeTest {

    @Test
    void nextId() {
        SnowFlake snowFlake = new SnowFlake(1, 1);
        for (int i = 0; i < 16; i++) {
            long id = snowFlake.nextId();
            print(id);
        }
    }

    @Test
    void name() {
        print(590644812790083585L);
    }

    private void print(long id) {
        log.info("id:[{}] db_{} table_{}", id, id % 4 / 2, id % 4);
    }
}
