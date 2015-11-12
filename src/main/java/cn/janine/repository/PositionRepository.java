package cn.janine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.janine.entity.Position;

/**
 * 岗位实体类DAO操作接口
 * 
 *
 */
public interface PositionRepository extends JpaRepository<Position, String> {

}
