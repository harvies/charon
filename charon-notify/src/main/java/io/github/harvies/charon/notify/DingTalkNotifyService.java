package io.github.harvies.charon.notify;

import io.github.harvies.charon.util.JsonUtils;
import net.dongliu.requests.Requests;

import java.util.HashMap;
import java.util.Map;


public class DingTalkNotifyService implements NotifyService {
    private DingTalkProperties dingTalkProperties;

    public DingTalkNotifyService(DingTalkProperties dingTalkProperties) {
        this.dingTalkProperties = dingTalkProperties;
    }

    @Override
    public boolean send(String title, String text) {
        Map<String, Object> data = new HashMap<>();
        data.put("msgtype", "markdown");
        Map<String, Object> textObject = new HashMap<>();
        textObject.put("title", title);
        textObject.put("text", text);
        data.put("markdown", textObject);
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        String result = Requests.post(dingTalkProperties.getWebHookUrl())
                .body(JsonUtils.toJSONString(data))
                .headers(headerMap)
                .send().readToText();
        return JsonUtils.parseObject(result).getInteger("errcode") == 0;
    }
}
