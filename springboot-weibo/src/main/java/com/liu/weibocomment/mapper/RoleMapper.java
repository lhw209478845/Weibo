package com.liu.weibocomment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.weibocomment.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    @Select("select mid from role_menu where rid = #{roleId}")
    List<Integer> queryMidByRid(@Param("roleId") Integer roleId);

    @Delete("delete from role_menu where rid = #{rid}")
    void deleteRoleByRid(@Param("rid") Integer rid);

    @Insert("insert into role_menu(rid,mid) values(#{rid},#{mid})")
    void saveRoleMenu(@Param("rid") Integer rid, @Param("mid") Integer mid);

    //根据用户id查询所有的角色
    @Select("select rid from user_role where uid = #{id}")
    List<Integer> queryUserRoleById(@Param("id") Integer id);

    //1、先删除之前的用户和角色关系
    @Delete("delete from user_role where uid = #{uid}")
    void deleteRoleUserByUid(@Param("uid") Integer uid);

    //2、保存分配的用户和角色之间的关系
    @Insert("insert into user_role(uid,rid) values(#{uid},#{rid})")
    void saveUserRole(@Param("uid") Integer uid, @Param("rid") Integer rid);
}
