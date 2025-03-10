package com.liu.weibocomment.vo;

import com.liu.weibocomment.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RoleVo extends Role {
    private Integer limit;
    private Integer page;
}
