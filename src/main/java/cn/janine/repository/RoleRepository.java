package cn.janine.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cn.janine.entity.Role;

/**
 * 角色实体类DAO操作接口
 * 
 *
 */
public interface RoleRepository extends PagingAndSortingRepository<Role, String> {

    /**
     * 根据角色名称查询角色
     * 
     * @param name 角色
     * @return Role
     */
    Role findByName(String name);

}
