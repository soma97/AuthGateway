package org.unibl.etf.master.security.anno.gateway.aop.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfiguration {
    private static final String cacheName = "cookies";
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(cacheName);
    }

    // clear cache every 5 minutes
    @CacheEvict(allEntries = true, value = {cacheName})
    @Scheduled(fixedDelay = 5 * 60 * 1000 ,  initialDelay = 500)
    public void reportCacheEvict() {
        System.out.println("Gateway authentication cache flushed");
    }
}
