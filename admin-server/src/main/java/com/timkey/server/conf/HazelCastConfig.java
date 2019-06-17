package com.timkey.server.conf;

import com.hazelcast.config.*;
import com.hazelcast.map.merge.PutIfAbsentMapMergePolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        MapConfig mapConfig = new MapConfig("spring-boot-admin-event-store").setInMemoryFormat(InMemoryFormat.OBJECT)
                .setBackupCount(1)
                .setEvictionPolicy(EvictionPolicy.NONE)
                .setMergePolicyConfig(new MergePolicyConfig(
                        PutIfAbsentMapMergePolicy.class.getName(),
                        100
                ));
        return new Config().setProperty("hazelcast.jmx", "true").addMapConfig(mapConfig);
    }
}
