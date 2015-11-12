package cn.janine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.janine.entity.Module;

/**
 * 模块实体类DAO操作接口
 * 
 *
 */
public interface ModuleRepository extends JpaRepository<Module, String> {
    /**
     * 根据模块标识查询模块信息
     * 
     * @param sn 模块标识
     * @return Module
     */
    Module findBySn(String sn);

}
