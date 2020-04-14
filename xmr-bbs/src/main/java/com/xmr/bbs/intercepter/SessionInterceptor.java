package com.xmr.bbs.intercepter;

import com.xmr.bbs.dao.NotificationMapper;
import com.xmr.bbs.modal.NotificationExample;
import com.xmr.bbs.modal.User;
import com.xmr.bbs.myenums.AdType;
import com.xmr.bbs.myenums.CommentStatus;
import com.xmr.bbs.service.AdService;
import com.xmr.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor implements HandlerInterceptor{
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private AdService adService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //广告

        for (AdType adType : AdType.values()) {
            request.getSession().setAttribute(adType.name(),adService.listAds(adType.name()));
        }
        //用户登入的cookies
        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length>0){
            for (Cookie cookie : cookies) {
                if("token".equals(cookie.getName())){
                    String token = cookie.getValue();
                    User user=userService.findUserByToken(token);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                        break;
                    }
                }
            }
        }
        User user = (User) request.getSession().getAttribute("user");
        if(user!=null){
            //未读信息数
            NotificationExample notificationExample = new NotificationExample();
            NotificationExample.Criteria criteria = notificationExample.createCriteria();
            criteria.andReceiverEqualTo((long) user.getId());
            criteria.andStatusEqualTo(CommentStatus.UN_READ.getCode());
            Integer unreadcount = notificationMapper.countByExample(notificationExample);
            request.getSession().setAttribute("unreadcount",unreadcount);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
