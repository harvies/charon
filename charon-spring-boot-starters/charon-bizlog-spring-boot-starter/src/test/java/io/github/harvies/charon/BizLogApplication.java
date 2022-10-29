package io.github.harvies.charon;

import com.mzt.logapi.starter.annotation.EnableLogRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableLogRecord(tenant = "1")
public class BizLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BizLogApplication.class, args);
    }
}
