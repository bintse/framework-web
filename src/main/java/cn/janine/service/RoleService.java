package cn.janine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.janine.entity.Role;
import cn.janine.exception.ExistedException;
import cn.janine.exception.ParamsException;
import cn.janine.repository.RoleRepository;

/**
 * 角色服务类
 * 
 *
 */
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    // @Autowired
    // private ShiroDBRealm shiroDBRealm;

    /**
     * 创建角色
     * 
     * @param role
     * @return
     */
    public Role create(Role role) {
        Assert.isNull(role.getId());
        if (role.getPermissions() == null || role.getPermissions().isEmpty()) {
            throw new ParamsException("创建角色失败：角色权限不能为空！");
        }
        if (roleRepository.findByName(role.getName()) != null) {
            throw new ExistedException("创建角色失败：该角色名称已经存在！");
        }
        return roleRepository.save(role);
    }

    /**
     * 更新角色
     * 
     * @param role 角色对象
     * @return Response
     */
    public Role update(Role role) {
        if (role == null || role.getId() == null) {
            throw new ParamsException("更新角色失败：角色权限ID不能为空！");
        }
        Role dbRole = roleRepository.findByName(role.getName());
        if (dbRole != null && !dbRole.getId().equals(role.getId())) {
            throw new ExistedException("更新角色失败：该角色名称已经存在！");
        }
        // 清除全部授权缓存
        // shiroDBRealm.clearAllCachedAuthorizationInfo();
        return roleRepository.save(role);
    }

    /**
     * 删除角色
     * 
     * @param id 角色id
     */
    public void delete(String id) {
        if (id == null) {
            throw new ParamsException("删除角色失败：角色权限ID不能为空！");
        }
        // TODO 更新关联数据
        // 清除全部授权缓存
        // shiroDBRealm.clearAllCachedAuthorizationInfo();
        roleRepository.delete(id);
    }
}
