package io.github.harvies.eris.base.algorithm.leetcode.reverseinteger;

/**
 * 整数反转
 *
 * @author harvies
 */
public class ReverseIntegerImpl implements ReverseInteger {
    @Override
    public int reverse(int x) {
        //1.原值
        System.out.println("x:" + x);
        System.out.println("int max:" + Integer.MAX_VALUE);
        System.out.println("int min:" + Integer.MIN_VALUE);
        //2.反转后的值
        int rev = 0;
        while (x != 0) {
            //3.取模得到个位(弹栈)
            int pop = x % 10;
            //4.除以10得到十位以上的数
            x /= 10;

            System.out.println("pop:" + pop);
            /**
             * rev*10+pop会溢出,分别判断rev和pop导致的溢出
             * 1.rev不能大于int最大值/10,不能小于int最小值/10
             * 2.pop不能大于int最大值(2147483647)的个位
             * 3.pop不能小于int最小值(-2147483648)的个位
             */
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            //5.从个位开始依次压栈
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
