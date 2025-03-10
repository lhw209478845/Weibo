package com.liu.weibocomment.controller;

import com.liu.weibocomment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommonController {

    @Autowired
    private UserService userService;

    //定义目标路径
    @Value("${Ceps.path}")
    private String BASE_PATH;


    @RequestMapping(value = "/uploadImage",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(HttpServletRequest servletRequest, @RequestParam("file") MultipartFile file)
            throws IOException {
        // 如果文件内容不为空，则写入上传路径
        if (!file.isEmpty()) {
            //取得原文件名字
            String fileName = file.getOriginalFilename();
            //取得文件扩展名
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            //提取系统时间作为新文件名
            String prefix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime());
            //保存路径
            // 上传文件名
            String filename = prefix+suffix;
            File filepath = new File(BASE_PATH, filename);
            // 判断路径是否存在,没有创建
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            // 将上传文件保存到一个目标文档中
            File file1 = new File(BASE_PATH + File.separator + filename);
            file.transferTo(file1);
            HashMap<String, Object> res = new HashMap<>();
            // 返回的是一个url对象,图片名称
            res.put("url", filename);
            return res;
        } else {
            return null;
        }
    }
    @RequestMapping(value = "/uploadUserImage",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadUserImage(HttpServletRequest servletRequest, @RequestParam("file") MultipartFile file, HttpSession session)
            throws IOException {
        // 如果文件内容不为空，则写入上传路径
        if (!file.isEmpty()) {
            //取得原文件名字
            String fileName = file.getOriginalFilename();
            //取得文件扩展名
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            //图片名为更改用户的姓名+更改时间
            String prefix = (String) session.getAttribute("username") + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime());
            // 上传文件名
            String filename = prefix + suffix;
            File filepath = new File(BASE_PATH, filename);
            // 判断路径是否存在,没有创建
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            // 将上传文件保存到一个目标文档中
            File file1 = new File(BASE_PATH + File.separator + filename);
            file.transferTo(file1);
            HashMap<String, Object> res = new HashMap<>();
            // 返回的是一个url对象,图片名称
            filename = "image/user/" + prefix + suffix;
            res.put("url", filename);
            return res;
        } else {
            return null;
        }
    }
}
