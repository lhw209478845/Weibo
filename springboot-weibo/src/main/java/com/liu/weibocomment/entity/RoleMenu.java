package com.liu.weibocomment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role_menu")
public class RoleMenu {
    private Integer rid; //角色ID
    private Integer mid; //菜单ID
}
