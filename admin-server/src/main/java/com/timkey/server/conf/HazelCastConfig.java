package com.timkey.server.conf;

import com.hazelcast.config.*;
import com.hazelcast.map.merge.PutIfAbsentMapMergePolicy;
import com.hazelcast.spi.MemberAddressProvider;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

/**
 * @author tangfeng
 * @fileName HazelcastConfig
 * @date 2019/6/6 17:32
 * @description xxxx
 */
@Configuration
public class HazelCastConfig {

    @Bean
    public Config hazelcastConfig() {

        MapConfig mapConfig = new MapConfig("spring-boot-admin-event-store")
                .setInMemoryFormat(InMemoryFormat.OBJECT)
                .setBackupCount(1)
                .setEvictionPolicy(EvictionPolicy.NONE)
                .setMergePolicyConfig(new MergePolicyConfig(
                        PutIfAbsentMapMergePolicy.class.getName(),
                        100
                ));
        Config config = new Config();
        config.getNetworkConfig().setPort(5701);
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getEurekaConfig()
                .setEnabled(true)
                .setProperty("self-registration", "true")
                .setProperty("namespace", "hazelcast");
        config.setProperty("hazelcast.jmx", "true").addMapConfig(mapConfig);
        return config;
    }
}
