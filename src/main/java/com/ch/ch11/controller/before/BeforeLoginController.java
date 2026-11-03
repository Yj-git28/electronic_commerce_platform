package com.ch.ch11.controller.before;

import com.ch.ch11.entity.Busertable;
import com.ch.ch11.repository.BusertableMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/before")
public class BeforeLoginController {
    @Autowired
    private BusertableMapper busertableMapper;

    @GetMapping("/login")
    public String toLogin(){
        return "before/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String bemail,
                        @RequestParam String bpwd,
                        @RequestParam String validateCode,
                        HttpSession session){
        // 校验验证码
        String sessionCode = (String) session.getAttribute("validateCode");
        if(sessionCode == null || !sessionCode.equalsIgnoreCase(validateCode)){
            return "redirect:/before/login";
        }
        // 校验账号密码
        Busertable user = busertableMapper.findByBemail(bemail);
        if(user != null && user.getBpwd().equals(bpwd)){
            session.setAttribute("user", user);
            return "redirect:/before/index";
        }
        return "redirect:/before/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/before/login";
    }
}
