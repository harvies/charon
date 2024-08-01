package io.github.harvies.charon.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomValueTest {

    @Test
    void getAddress() {

        for (int i = 0; i < 100; i++) {
            System.out.println(RandomValue.getAddress());
//          System.out.println(getEmailName(6,9));  
        }
    }
}
