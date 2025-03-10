package com.liu.weibocomment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.weibocomment.entity.Menu;
import com.liu.weibocomment.entity.User;
import com.liu.weibocomment.service.MenuService;
import com.liu.weibocomment.service.RoleService;
import com.liu.weibocomment.utils.TreeNode;
import com.liu.weibocomment.utils.TreeNodeBuilder;
import com.liu.weibocomment.vo.DataView;
import com.liu.weibocomment.vo.MenuVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    @RequestMapping("/toMenu")
    public String toMenu(){
        return "menu/menu";
    }

    /**
     * 加载所有的菜单
     * @param menuVo
     * @return
     */
    @RequestMapping("/loadAllMenu")
    @ResponseBody
    public DataView loadAllMenu(MenuVo menuVo){
        IPage<Menu> page = new Page<>(menuVo.getPage(), menuVo.getLimit());
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(menuVo.getTitle()),"title", menuVo.getTitle());
        queryWrapper.orderByAsc("ordernum");
        menuService.page(page);
        return new DataView(page.getTotal(), page.getRecords());
    }

    /**
     * 加载下拉菜单数据 初始化下拉树
     */
    @RequestMapping("loadMenuManagerLeftTreeJson")
    @ResponseBody
    public DataView loadMenuManagerLeftTreeJson(){
        List<Menu> list = menuService.list();
        List<TreeNode> treeNodes = new ArrayList<>();
        for(Menu menu:list){
            boolean open = menu.getOpen() == 1?true:false;
            treeNodes.add(new TreeNode(menu.getId(), menu.getPid(), menu.getTitle(), open));

        }
        return new DataView(treeNodes);
    }

    /**
     * 加载赋值最大的排序码+1
     * 条件查询： 倒序排序，取一条数据
     */
    @RequestMapping("/loadMenuMaxOrderNum")
    @ResponseBody
    public Map<String,Object> loadMenuMaxOrderNum(){
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        IPage<Menu> page = new Page<>(1,1);
        List<Menu> list = menuService.page(page, queryWrapper).getRecords();

        map.put("value", list.get(0).getOrdernum()+1);
        return map;
    }

    /**
     * 新增菜单
     */
    @RequestMapping("/addMenu")
    @ResponseBody
    public DataView addMenu(Menu menu){
        DataView dataView = new DataView();
        menu.setType("menu");
        boolean save = menuService.save(menu);

        if(!save){
            dataView.setMsg("数据插入失败");
            dataView.setCode(100);
        }
        dataView.setMsg("菜单插入成功！");
        dataView.setCode(200);
        return dataView;
    }

    /**
     * 更新菜单
     */
    @RequestMapping("/updateMenu")
    @ResponseBody
    public DataView updateMenu(Menu menu){
        menuService.updateById(menu);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("更新菜单成功!");
        return dataView;
    }

    /**
     * 判断有没有子类ID
     * 没有子类ID，可以删除
     */
    @RequestMapping("/checkMenuHasChildrenNode")
    @ResponseBody
    public Map<String, Object> checkMenuHasChildrenNode(Menu menu){
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", menu.getId());
        List<Menu> list = menuService.list(queryWrapper);
        if(list.size() > 0){
            map.put("value", true);
        }else{
            map.put("value", false);
        }
        return map;
    }

    /**
     * 删除菜单
     */
    @RequestMapping("/deleteMenu")
    @ResponseBody
    public DataView deleteMenu(Menu menu){
        menuService.removeById(menu.getId());
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("删除 菜单成功!");
        return dataView;
    }

    /**
     * 加载主页面index的菜单栏，带有层级关系
     * [不同的用户登录看到不同的菜单栏] 加查询条件
     */
    @RequestMapping("/loadIndexLeftMenuJson")
    @ResponseBody
    public DataView loadIndexLeftMenuJson(Menu menu, HttpSession session){
        //查询菜单栏 按照条件查询[管理员 学生 老师[条件查询]]
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        List<Menu> list = null;

        //1、取出session中的用户ID
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();

        if(user.getUsername().equals("admin") || StringUtils.equals(user.getUsername(), "admin")){
            list = menuService.list();
        }else{
            //2、根据用户ID查询角色
            List<Integer> currentUserRoleIds = roleService.queryUserRoleById(userId);
            //3去重
            Set<Integer> mids = new HashSet<>();
            for(Integer rid:currentUserRoleIds){
                //3.1、根据角色ID查询菜单ID
                List<Integer> permissionIds = roleService.queryAllPermissionByRid(rid);
                //3.2、菜单栏ID和角色ID去重
                mids.addAll(permissionIds);
            }
            //4根据角色ID查询菜单
            if(mids.size() > 0){
                queryWrapper.in("id",mids);
                list = menuService.list(queryWrapper);
            }
        }
        //构造层级关系
        List<TreeNode> treeNodes = new ArrayList<>();
        for(Menu m:list){
            Integer id = m.getId();
            Integer pid = m.getPid();
            String title = m.getTitle();
            String icon = m.getIcon();
            String href = m.getHref();
            Boolean open = m.getOpen().equals(1)?true:false;
            treeNodes.add(new TreeNode(id,pid,title,icon,href,open));
        }
        //构造层级关系
        List<TreeNode> nodeList = TreeNodeBuilder.build(treeNodes, 0);
        return new DataView(nodeList);
    }
}
