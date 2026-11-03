package com.ch.ch11.controller.before;

import com.ch.ch11.NoLoginException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.servlet.http.HttpSession;

@Controller
public class BeforeBaseController {
    @ModelAttribute
    public void isLogin(HttpSession session) throws NoLoginException {
        // 前台用户session存的键，这里用user，和后台管理员auser区分开
        if(session.getAttribute("user") == null){
            throw new NoLoginException("没有登录");
        }
    }
}
