package chapter7;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes=MainApplication.class,webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CacheTest {
    @Resource private CacheManager cacheManager;
    @Test
    public void cacheTest(){
        String res=cacheManager.getCacheNames().stream().collect(joining(",","",""));
        log.debug("{}",res);
        Cache cache=cacheManager.getCache("passwordRetryCache");
        cache.put("key","123");
        log.debug("cache success");
        String res2=cache.get("key",String.class);
        log.debug("from cache",res2);
        assertEquals("123",res2);
    }
    @Test
    public void testToEhCacheManager(){
        EhCacheCacheManager cacheCacheManager=(EhCacheCacheManager) cacheManager;
        net.sf.ehcache.CacheManager ehcacheManager=cacheCacheManager.getCacheManager();
        net.sf.ehcache.Cache cache=ehcacheManager.getCache("passwordRetryCache");
        cache.put(new Element("lisi",new AtomicInteger(0)));
        Element ele=cache.get("lisi");
        AtomicInteger atomicInteger=(AtomicInteger) ele.getObjectValue();
        assertEquals(0,atomicInteger.get());

    }
}
