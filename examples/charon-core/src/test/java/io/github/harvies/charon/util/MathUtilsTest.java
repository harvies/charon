package io.github.harvies.charon.util;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    @Test
    void decimalToBinary() {
        assertThat(MathUtils.decimalToBinary(53), is("110101"));
    }

    @Test
    void binaryToDecimal() {
        assertThat(MathUtils.binaryToDecimal("110101"), is(53));
    }
}