package io.github.harvies.charon.util;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class StringEscapeUtilsTest {

    @Test
    public void test() {
        String orig = "【遂茗旗舰店】&nbsp;中秋特惠！本店明前特级绿茶限时 中秋大礼包回馈活动 老客户尊享买半斤实发一斤 自留或是中秋送礼首选哦！详细请咨询（遂茗旗舰店）客服或vx:18923711111，预祝亲中秋快乐！ https://c.tb.cn/c.0Uj124&nbsp;退订回T";
        String result = "【遂茗旗舰店】 中秋特惠！本店明前特级绿茶限时 中秋大礼包回馈活动 老客户尊享买半斤实发一斤 自留或是中秋送礼首选哦！详细请咨询（遂茗旗舰店）客服或vx:18923711111，预祝亲中秋快乐！ https://c.tb.cn/c.0Uj124 退订回T";
        assertThat(StringEscapeUtils.unescapeHtml4(orig), is(result));
    }
}