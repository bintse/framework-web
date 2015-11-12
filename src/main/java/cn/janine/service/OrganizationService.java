package cn.janine.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.janine.entity.Organization;
import cn.janine.exception.ExistedException;
import cn.janine.exception.NotExistedException;
import cn.janine.exception.ParamsException;
import cn.janine.exception.UndeletableException;
import cn.janine.repository.OrganizationRepository;

/**
 * 组织结构服务类
 * 
 *
 */
@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;
    // @Autowired
    // private ShiroDBRealm shiroDBRealm;

    /**
     * 检查组织机构名称和编码的唯一性,如果不唯一会抛出RuntimeException异常
     * 
     * @param org 组织机构
     */
    private void checkNameAndCodeUnique(Organization org) {
        Organization dbOrg = organizationRepository.findByName(org.getName());
        if (dbOrg != null && !dbOrg.getId().equals(org.getId())) {
            throw new ExistedException("保存失败，已经存在名称为[" + org.getName() + "]的组织机构！");
        }
        if (StringUtils.isNotBlank(org.getCode())) {
            dbOrg = organizationRepository.findByCode(org.getCode());
            if (dbOrg != null && !dbOrg.getId().equals(org.getId())) {
                throw new ExistedException("保存失败，已经存在编码为[" + org.getCode() + "]的组织机构！");
            }
        }
    }

    /**
     * 创建组织机构
     * 
     * @param org 组织机构对象
     * @return 创建后的组织机构对象
     */
    public Organization create(Organization org) {
        Assert.isNull(org.getId());
        // 检查组织机构名称和编码的唯一性,如果不唯一会抛出RuntimeException异常
        checkNameAndCodeUnique(org);
        return organizationRepository.save(org);
    }

    /**
     * 更新组织机构
     * 
     * @param org 组织机构
     * @return 更新后的组织机构对象
     */
    public Organization update(Organization org) {
        if (org.getId() == null) {
            throw new ParamsException("更新组织机构失败，组织机构ID不能为空！");
        }
        // 检查组织机构名称和编码的唯一性,如果不唯一会抛出RuntimeException异常
        checkNameAndCodeUnique(org);
        // 清除全部授权缓存
        // shiroDBRealm.clearAllCachedAuthorizationInfo();
        return organizationRepository.save(org);
    }

    /**
     * 删除组织机构
     * 
     * @param id 组织机构ID
     * @return Response
     */
    public void delete(String id) {
        Organization org = organizationRepository.findOne(id);
        if (org == null) {
            throw new NotExistedException("删除异常，该组织机构不存在！");
        }
        if (org.getChildren().size() > 0) {
            throw new UndeletableException("该组织机构下存在子组织机构，不能被删除！");
        }
        // TODO 处理关联数据
        // 清除全部授权缓存
        // shiroDBRealm.clearAllCachedAuthorizationInfo();
        organizationRepository.delete(id);
    }

}
