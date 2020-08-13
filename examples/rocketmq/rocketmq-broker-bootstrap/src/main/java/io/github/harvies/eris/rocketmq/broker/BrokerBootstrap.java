/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.harvies.eris.rocketmq.broker;

import org.apache.rocketmq.broker.BrokerStartup;
import org.apache.rocketmq.common.MixAll;

/**
 * @author harvies
 */
public class BrokerBootstrap {

    public static void main(String[] args) {
        System.setProperty(MixAll.ROCKETMQ_HOME_PROPERTY, "examples/rocketmq");
        System.setProperty(MixAll.NAMESRV_ADDR_PROPERTY, "localhost:9876");

        args = new String[2];
        args[0] = "-c";
        args[1] = "examples/rocketmq/conf/broker.conf";
        BrokerStartup.main(args);
    }

}
