package cn.janine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.janine.entity.Organization;

/**
 * 组织机构实体类DAO操作接口
 * 
 *
 */
public interface OrganizationRepository extends JpaRepository<Organization, String> {

    /**
     * 根据组织机构名称查询组织机构信息
     * 
     * @param name 组织机构名称
     * @return Organization
     */
    Organization findByName(String name);

    /**
     * 根据组织机构编码查询组织机构信息
     * 
     * @param code 组织机构编码
     * @return Organization
     */
    Organization findByCode(String code);

}
