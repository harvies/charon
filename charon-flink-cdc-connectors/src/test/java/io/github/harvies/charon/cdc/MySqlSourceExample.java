package io.github.harvies.charon.cdc;

import io.github.harvies.charon.util.PropertiesUtils;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import com.ververica.cdc.connectors.mysql.source.MySqlSource;

public class MySqlSourceExample {
  public static void main(String[] args) throws Exception {
    MySqlSource<String> mySqlSource = MySqlSource.<String>builder()
            .hostname(PropertiesUtils.getDefaultProperty("charon.mysql.url"))
            .port(3306)
            .databaseList("blazars") // set captured database
            .tableList("blazars.*") // set captured table
            .username("charon.mysql.username")
            .password("charon.mysql.password")
            .deserializer(new JsonDebeziumDeserializationSchema()) // converts SourceRecord to JSON String
            .build();

    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

    // enable checkpoint
    env.enableCheckpointing(3000);

    env
      .fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "MySQL Source")
      // set 4 parallel source tasks
      .setParallelism(4)
      .print().setParallelism(1); // use parallelism 1 for sink to keep message ordering

    env.execute("Print MySQL Snapshot + Binlog");
  }
}