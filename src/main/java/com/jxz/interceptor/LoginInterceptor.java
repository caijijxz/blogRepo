package com.jxz.interceptor;

import com.jxz.po.TokenInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/admin");
            return false;
        }
        return true;
        // TokenInfo userToken = this.getUserToken(request);
        // if (StringUtils.isBlank(userToken.getAdminId()) && StringUtils.isBlank(userToken.getToken())) {
        //     System.out.println("token为空");
        //     response.sendRedirect("/login");
        //     return false;
        // }
        // try {
        //     String id = userToken.getAdminId();
        //     System.out.println(id);
        //     String token = (String) redisTemplate.opsForValue().get(id);
        //     if (token != null && token.equals(userToken.getToken())) {
        //         System.out.println("token校验成功");
        //         return true;
        //     } else {
        //         System.out.println("token校验失败");
        //         response.sendRedirect("/login");
        //         return false;
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     System.out.println("token校验出现异常");
        //     return false;
        // }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }


    private TokenInfo getUserToken(HttpServletRequest request) {
        TokenInfo info = new TokenInfo();
        String userToken = request.getHeader("token");
        System.out.println(userToken);
        String[] userIdAndToken = userToken.split("-");
        String adminId = userIdAndToken[0];
        String token = userIdAndToken[1];
        if (StringUtils.isNotBlank(adminId) && StringUtils.isNotBlank(token)) {
            info.setAdminId(adminId);
            info.setToken(token);
        }
        return info;
    }
}
