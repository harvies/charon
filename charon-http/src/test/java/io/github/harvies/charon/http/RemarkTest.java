package io.github.harvies.charon.http;

import com.overzealous.remark.Remark;
import net.dongliu.requests.Requests;
import org.junit.jupiter.api.Test;

public class RemarkTest {
    @Test
    public void test() {
        Remark remark = new Remark();
        String convert = remark.convert(Requests.get("https://dbaplus.cn/index.php?m=content&c=index&a=show&catid=248&id=4061").send().readToText());
        System.out.println(convert);
    }
}
