package cn.janine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.janine.entity.Position;
import cn.janine.exception.NotExistedException;
import cn.janine.exception.ParamsException;
import cn.janine.exception.UndeletableException;
import cn.janine.repository.PositionRepository;

/**
 * 岗位服务类
 * 
 *
 */
@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;
    // @Autowired
    // private ShiroDBRealm shiroDBRealm;

    /**
     * 创建岗位
     * 
     * @param position 岗位对象
     * @return Response
     */
    public Position create(Position position) {
        Assert.isNull(position.getId());// 断言，新增时ID为空
        return positionRepository.save(position);
    }

    /**
     * 更新岗位
     * 
     * @param position 岗位对象
     * @return Response
     */
    public Position update(Position position) {
        if (position.getId() == null) {
            throw new ParamsException("更新岗位失败，岗位ID不能为空！");
        }
        // 清除全部授权缓存
        // shiroDBRealm.clearAllCachedAuthorizationInfo();
        return positionRepository.save(position);
    }

    /**
     * 删除岗位
     * 
     * @param id 岗位ID
     * @return Response
     */
    public void delete(String id) {
        Position position = positionRepository.findOne(id);
        if (position == null) {
            throw new NotExistedException("删除异常，该岗位不存在！");
        }
        if (position.getChildren().size() > 0) {
            throw new UndeletableException("该岗位下存在子岗位，不能被删除！");
        }
        // TODO 处理关联数据
        // 清除全部授权缓存
        // shiroDBRealm.clearAllCachedAuthorizationInfo();
        positionRepository.delete(id);
    }

}
