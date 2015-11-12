package cn.janine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.janine.entity.Permission;

/**
 * 权限实体类DAO操作接口
 * 
 *
 */
public interface PermissionRepository extends JpaRepository<Permission, String> {

}
