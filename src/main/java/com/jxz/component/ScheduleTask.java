package com.jxz.component;

import com.jxz.dao.BlogRepository;
import com.jxz.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Project: blog
 * @Package: com.jxz.handler
 * @ClassName: ScheduleTaskHandler
 * @Description: TODO
 * @Author: jiangxinze
 * @Date: 2021/4/14
 * @Version 1.0
 */
@Component
public class ScheduleTask {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0 0/2 * * * ?")
    public void updateViewsFromRedisToMySQl() {
        Map<String, Integer> views = redisTemplate.opsForHash().entries("views");
        for(Map.Entry<String,Integer> view : views.entrySet()){
            Long blogId = Long.parseLong(view.getKey());
            int viewCount = view.getValue();
            blogRepository.updateViews(blogId,viewCount);
        }
    }

}
