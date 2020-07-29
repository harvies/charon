package io.github.harvies.charon.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author harvies
 */
@Slf4j
public class ApolloTest {
    @Test
    public void get() {
        //load other properties
        System.setProperty("charon.env", "dev");
        Config appConfig = ConfigService.getConfig("dev-common");
        Assertions.assertTrue(appConfig.getPropertyNames().size() != 0);
    }

}
