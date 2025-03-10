package com.liu.weibocomment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.weibocomment.entity.User;
import com.liu.weibocomment.service.RoleService;
import com.liu.weibocomment.service.UserService;
import com.liu.weibocomment.vo.DataView;
import com.liu.weibocomment.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/toUser")
    public String toUser(){
        return "user/user";
    }
    @RequestMapping("/toChangePassword")
    public String toChangePassword(){
        return "user/changepassword";
    }

    /**
     * 分页查询所有用户
     * @param userVo
     * @return
     */
    @RequestMapping("/loadAllUser")
    @ResponseBody
    public DataView loadAllUser(UserVo userVo){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        IPage<User> page = new Page<>(userVo.getPage(),userVo.getLimit());
        queryWrapper.like(StringUtils.isNotBlank(userVo.getUsername()),"username",userVo.getUsername());
        queryWrapper.eq(StringUtils.isNotBlank(userVo.getPhone()),"phone",userVo.getPhone());
        IPage<User> iPage = userService.page(page, queryWrapper);
        return new DataView(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * 新增用户操作
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public DataView addUser(User user){
        boolean save = userService.save(user);
        DataView dataView = new DataView();
        if(save){
            dataView.setCode(200);
            dataView.setMsg("添加用户成功");
        }{
            dataView.setCode(100);
            dataView.setMsg("添加用户失败");
        }
        return dataView;
    }

    /**
     * 修改用户
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    public DataView updateUser(User user){
        boolean b = userService.updateById(user);
        DataView dataView = new DataView();
        if(b){
            dataView.setCode(200);
            dataView.setMsg("修改用户成功");
        }else{
            dataView.setCode(100);
            dataView.setMsg("修改用户失败");
        }
        return dataView;
    }

    /**
     * 删除用户
     */
    @RequestMapping("/deleteUser/{id}")
    @ResponseBody
    public DataView deleteUser(@PathVariable("id") Integer id){
        boolean b = userService.removeById(id);
        DataView dataView = new DataView();
        if(b) {
            dataView.setCode(200);
            dataView.setMsg("删除用户成功");
        }else{
            dataView.setCode(100);
            dataView.setMsg("删除用户失败");
        }
        return dataView;
    }


    /**
     * 重置密码操作
     */
    @RequestMapping("/resetPwd/{id}")
    @ResponseBody
    public DataView resetPwd(@PathVariable("id") Integer id){
        User user = new User();
        user.setId(id);
        user.setPassword("123456");
        userService.updateById(user);
        DataView dataView = new DataView();
        dataView.setMsg("用户重置密码成功");
        dataView.setCode(200);
        return dataView;
    }

    /**
     * 点击分配角色时候 初始化用户角色
     * 根据id查询所拥有的角色
     */
    @RequestMapping("/initRoleByUserId")
    @ResponseBody
    public DataView initRoleByUserId(Integer id){
        //1、查询所有角色
        List<Map<String, Object>> listMaps = roleService.listMaps();
        //2、查询当前登录用户所拥有的角色
        List<Integer> currentUserRoleIds = roleService.queryUserRoleById(id);
        //3、让你的前端 变为选中状态
        for(Map<String,Object> map : listMaps){
            Boolean LAY_CHECKED = false;
            Integer roleId = (Integer) map.get("id");
            for(Integer rid : currentUserRoleIds){
                if(rid.equals(roleId)){
                    LAY_CHECKED = true;
                    break;
                }
            }
            map.put("LAY_CHECKED",LAY_CHECKED);
        }
        return new DataView(Long.valueOf(listMaps.size()),listMaps);
    }

    /**
     * 保存用户与角色之间的关系 1：m
     * 先删除再保存
     */
    @RequestMapping("/saveUserRole")
    @ResponseBody
    private DataView saveUserRole(Integer uid,Integer[] ids){
        userService.saveUserRole(uid,ids);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("用户角色分配成功");
        return dataView;
    }

}
