package cn.janine.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.janine.entity.Module;
import cn.janine.entity.Permission;
import cn.janine.exception.ExistedException;
import cn.janine.exception.NotExistedException;
import cn.janine.exception.ParamsException;
import cn.janine.exception.UndeletableException;
import cn.janine.repository.ModuleRepository;

/**
 * 模块服务类
 * 
 *
 */
@Service
public class ModuleService {
    private static final Logger LOG = LoggerFactory.getLogger(ModuleService.class);
    @Autowired
    private ModuleRepository moduleRepository;

    /**
     * 检查模块标识是否唯一,如果不唯一则抛出RuntimeException异常
     * 
     * @param module 模块对象
     */
    private void checkSnUnique(Module module) {
        Module dbModule = moduleRepository.findBySn(module.getSn());
        if (dbModule != null && !dbModule.getId().equals(module.getId())) {
            throw new ExistedException("保存模块失败，已经存在sn为[" + module.getSn() + "]的模块！");
        }
    }

    /**
     * 设置权限的模块属性
     * 
     * @param module 模块对象
     */
    private void initPermissionModule(Module module) {
        List<Permission> permList = module.getPermissions();
        for (Permission permission : permList) {
            if (permission.getModule() == null) {
                permission.setModule(module);
            }
        }
    }

    /**
     * 添加模块的属性信息
     * 
     * @param moduleMap 模块Map
     * @param module 模块
     * @param attrKey attribute的键名称
     */
    private void addModuleAttribute(Map<String, Object> moduleMap, Module module, String attrKey) {
        Map<String, Object> attrMap = new HashMap<String, Object>();
        attrMap.put("sn", module.getSn());
        attrMap.put("description", module.getDescription());
        attrMap.put("priority", module.getPriority());
        attrMap.put("url", module.getUrl());
        List<Map<String, Object>> permMapList = new ArrayList<Map<String, Object>>();
        for (Permission perm : module.getPermissions()) {
            Map<String, Object> permMap = new HashMap<String, Object>();
            permMap.put("sn", perm.getSn());
            permMap.put("name", perm.getName());
            permMap.put("id", perm.getId());
            permMap.put("description", perm.getDescription());
            permMapList.add(permMap);
        }
        attrMap.put("permissions", permMapList);
        moduleMap.put(attrKey, attrMap);
    }

    /**
     * 添加子模块
     * 
     * @param idKey id的键名称
     * @param textKey text的键名称
     * @param childrenKey children的键名称
     * @param parentMap 父节模块
     * @param childrenList 子模块列表
     */
    private void addChildrenModule(String idKey, String textKey, String childrenKey, String attrKey, Map<String, Object> parentMap, List<Module> childrenList) {
        List<Map<String, Object>> childMapList = new ArrayList<Map<String, Object>>();
        for (Module m : childrenList) {
            Map<String, Object> childMap = new HashMap<String, Object>();
            childMap.put(idKey, m.getId());
            childMap.put(textKey, m.getName());
            addModuleAttribute(childMap, m, attrKey);// 添加属性信息
            if (m.getChildren().size() > 0) {
                addChildrenModule(idKey, textKey, childrenKey, attrKey, childMap, m.getChildren());
            }
            childMapList.add(childMap);
        }
        if (childMapList.size() > 0) {
            parentMap.put(childrenKey, childMapList);
        }
    }

    /**
     * 根据模块id查询模块信息
     * 
     * @param id 模块id
     * @return Module || null
     */
    public Module findById(String id) {
        return moduleRepository.findOne(id);
    }

    /**
     * 创建模块
     * 
     * @param module 模块对象
     * @return Module 创建后的模块对象
     */
    public Module create(Module module) {
        initPermissionModule(module);// 设置权限的模块参数
        LOG.debug("create--------->{}", module);
        Assert.isNull(module.getId());
        // 检查模块标识是否唯一,如果不唯一则抛出RuntimeException异常
        checkSnUnique(module);
        return moduleRepository.save(module);
    }

    /**
     * 更新模块
     * 
     * @param module 模块对象
     * @return Module 更新后的模块对象
     */
    public Module update(Module module) {
        initPermissionModule(module);// 设置权限的模块参数
        LOG.debug("update--------->{}", module);
        if (module.getId() == null) {
            throw new ParamsException("更新模块失败，模块ID不能为空！");
        }
        // 检查模块标识是否唯一,如果不唯一则抛出RuntimeException异常
        checkSnUnique(module);
        return moduleRepository.save(module);
    }

    /**
     * 删除模块
     * 
     * @param id 模块ID
     * @return Response
     */
    public void delete(String id) {
        Module module = moduleRepository.findOne(id);
        if (module == null) {
            throw new NotExistedException("删除异常，该模块不存在！");
        }
        if (!module.getChildren().isEmpty()) {
            throw new UndeletableException("该模块下存在子模块，不能被删除！");
        }
        // TODO 处理关联数据
        moduleRepository.delete(id);
    }

    /**
     * 获取模块树
     * 
     * @param idKey id的键名称
     * @param textKey text的键名称
     * @param childrenKey children的键名称
     * @param attrKey attribute的键名称
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> tree(String idKey, String textKey, String childrenKey, String attrKey) {
        List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
        List<Module> mList = moduleRepository.findAll();
        for (Module m : mList) {
            // 查询根节点
            if (m.getParent() == null) {
                Map<String, Object> rootMap = new HashMap<String, Object>();
                rootMap.put(idKey, m.getId());
                rootMap.put(textKey, m.getName());
                addModuleAttribute(rootMap, m, attrKey);// 添加属性信息
                if (m.getChildren().size() > 0) {
                    addChildrenModule(idKey, textKey, childrenKey, attrKey, rootMap, m.getChildren());
                }
                treeList.add(rootMap);
            }
        }
        return treeList;
    }

}
