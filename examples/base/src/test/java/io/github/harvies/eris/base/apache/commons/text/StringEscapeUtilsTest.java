package io.github.harvies.eris.base.apache.commons.text;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;

public class StringEscapeUtilsTest {
    @Test
    public void test() {
        String orig = "【遂茗旗舰店】&nbsp;中秋特惠！本店明前特级绿茶限时 中秋大礼包回馈活动 老客户尊享买半斤实发一斤 自留或是中秋送礼首选哦！详细请咨询（遂茗旗舰店）客服或vx:18923713456，预祝亲中秋快乐！ https://c.tb.cn/c.0Ujb24&nbsp;退订回T";
        String html4 = StringEscapeUtils.unescapeHtml4(orig);
        System.err.println(orig);
        System.err.println(html4);
    }
}
