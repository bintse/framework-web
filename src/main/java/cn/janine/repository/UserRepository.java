package cn.janine.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.janine.entity.User;

/**
 * 用户实体类DAO操作接口
 * 
 *
 */
public interface UserRepository extends PagingAndSortingRepository<User, String>, JpaSpecificationExecutor<User> {

    /**
     * 根据用户名查询用户信息
     * 
     * @param userName 用户名
     * @return User
     */
    User findByUserName(String userName);

    /**
     * 更新用户密码
     * 
     * @param id 用户id
     * @param password 新密码
     */
    @Modifying
    @Query("UPDATE User u set u.password=?2 where u.id =?1")
    void updatePassword(String id, String password);

}
