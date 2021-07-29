package io.github.harvies.charon.tests.other;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class DocTest {
    public static void main(String[] args) {
        String doc = "/**\n" +
                "     * Causes this character sequence to be replaced by the reverse of\n" +
                "     * the sequence. If there are any surrogate pairs included in the\n" +
                "     * sequence, these are treated as single characters for the\n" +
                "     * reverse operation. Thus, the order of the high-low surrogates\n" +
                "     * is never reversed.\n" +
                "     *\n" +
                "     * Let <i>n</i> be the character length of this character sequence\n" +
                "     * (not the length in {@code char} values) just prior to\n" +
                "     * execution of the {@code reverse} method. Then the\n" +
                "     * character at index <i>k</i> in the new character sequence is\n" +
                "     * equal to the character at index <i>n-k-1</i> in the old\n" +
                "     * character sequence.\n" +
                "     *\n" +
                "     * <p>Note that the reverse operation may result in producing\n" +
                "     * surrogate pairs that were unpaired low-surrogates and\n" +
                "     * high-surrogates before the operation. For example, reversing\n" +
                "     * \"\\u005CuDC00\\u005CuD800\" produces \"\\u005CuD800\\u005CuDC00\" which is\n" +
                "     * a valid surrogate pair.\n" +
                "     *\n" +
                "     * @return  a reference to this object.\n" +
                "     */";
        String[] split = StringUtils.split(doc, "\n");
        int first = 0;
        int end = 0;
        for (int i = 0; i < split.length; i++) {
            if (StringUtils.startsWith(split[i], "/**")) {
                first = i;
            } else if (StringUtils.endsWith(split[i], "*/")) {
                end = i;
            } else {
                if (i > first) {
                    split[i] = StringUtils.substring(split[i], split[i].indexOf("*") + 1, split[i].length());
                }
            }
        }
        System.out.println(first);
        System.out.println(end);
        String[] strings = Arrays.copyOfRange(split, 1, split.length - 1);
        System.out.println(Joiner.on("\n").join(strings));
    }
}
