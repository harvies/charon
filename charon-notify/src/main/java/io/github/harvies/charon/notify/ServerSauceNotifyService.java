package io.github.harvies.charon.notify;

import io.github.harvies.charon.util.JsonUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.dongliu.requests.Requests;

import java.util.HashMap;
import java.util.Map;

/**
 * server酱通知服务
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServerSauceNotifyService implements NotifyService {

    private ServerSauceProperties serverSauceProperties;

    public ServerSauceNotifyService(ServerSauceProperties serverSauceProperties) {
        this.serverSauceProperties = serverSauceProperties;
    }

    @Override
    public boolean send(String title, String text) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("text", title);
        paramMap.put("desp", text);
        String result = Requests.post(serverSauceProperties.getWebHookUrl())
                .params(paramMap)
                .send().readToText();
        //{"errno":0,"errmsg":"success","dataset":"done"}
        return JsonUtils.parseObject(result).getInteger("errno") == 0;
    }
}
