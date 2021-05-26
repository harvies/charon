package io.github.harvies.charon.util;

import java.math.BigInteger;

public class MathUtils {

    /**
     * 十进制转换成二进制
     */
    public static String decimalToBinary(int decimalSource) {
        BigInteger bi = new BigInteger(String.valueOf(decimalSource)); //转换成BigInteger类型，默认是十进制
        return bi.toString(2); //参数2指定的是转化成二进制
    }

    /**
     * 二进制转换成十进制
     */
    public static int binaryToDecimal(String binarySource) {
        BigInteger bi = new BigInteger(binarySource, 2);  //转换为BigInteger类型，参数2指定的是二进制
        return Integer.parseInt(bi.toString());     //默认转换成十进制
    }
}
