package com.ch.ch11.controller.admin;


import com.ch.ch11.NoLoginException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminBaseController {
    @ModelAttribute
    public void isLogin(HttpSession session){
        if(session.getAttribute("auser") == null){
            throw new NoLoginException("没有登录");
        }
    }
}
