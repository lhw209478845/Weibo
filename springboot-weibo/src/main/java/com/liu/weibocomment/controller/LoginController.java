package com.liu.weibocomment.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.liu.weibocomment.entity.User;
import com.liu.weibocomment.service.UserService;
import com.liu.weibocomment.vo.DataView;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }


    /**
     * 验证码的逻辑
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping("/login/getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        //1、验证码对象 HuTool定义图形验证码的长和宽，验证码的位数，干扰线的条数
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(116, 36, 4, 10);

        //2、放入到session
        session.setAttribute("code",captcha.getCode());

        //3、输出验证码
        ServletOutputStream outputStream = response.getOutputStream();
        captcha.write(outputStream);

        //4、关闭输出流
        outputStream.close();
    }

    /**
     * 具体的登录逻辑
     * @param session
     * @return
     */
    @RequestMapping("/login/login")
    @ResponseBody
    public DataView login(@RequestBody Map<String,String> loginUser, HttpSession session){
        DataView dataView = new DataView();
        String username = loginUser.get("username");
        String password = loginUser.get("password");
        String code = loginUser.get("code");

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token =new UsernamePasswordToken(username,password);
        subject.login(token);
        User user = (User) subject.getPrincipal();

        String sessionCode = (String) session.getAttribute("code");
        if(StringUtils.isNotBlank(code) && sessionCode.equals(code)){
            if(user != null){
                //存入session
                session.setAttribute("user",user);
                dataView.setMsg("登录成功！");
                dataView.setCode(200);
            }else{
                dataView.setMsg("用户名或密码错误，请重新尝试！");
                dataView.setCode(100);
            }
        }else{
            dataView.setMsg("验证码输入错误，请重新输入！");
            dataView.setCode(100);
        }
        return dataView;
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/login/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
