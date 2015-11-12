package cn.janine.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.janine.entity.User;
import cn.janine.exception.ExistedException;
import cn.janine.exception.NotEqualException;
import cn.janine.exception.NotExistedException;
import cn.janine.exception.ParamsException;
import cn.janine.repository.UserRepository;

/**
 * 用户服务类
 * 
 *
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    // @Autowired
    // private ShiroDBRealm shiroDBRealm;
    @Autowired
    private PasswordService passwordService;

    /**
     * 检查用户名唯一性，如果不通过则抛出RuntimeException
     * 
     * @param user 用户对象
     */
    private void checkUserNameUnique(User user) {
        User dbUser = userRepository.findByUserName(user.getUserName());
        if (dbUser != null && !dbUser.getId().equals(user.getId())) {
            throw new ExistedException("保存失败，已经存在名称为[" + user.getUserName() + "]的用户！");
        }
    }

    /**
     * 创建用户
     * 
     * @param user 用户对象
     * @return User
     */
    public User create(User user) {
        Assert.isNull(user.getId());
        if (StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getPassword())) {
            throw new NotExistedException("用户名、密码不能为空！");
        }
        if (!StringUtils.equals(user.getPassword(), user.getConfirmedPwd())) {
            throw new NotEqualException("两次密码不一致！");
        }
        // 检查用户名唯一性，如果不通过则抛出RuntimeException
        checkUserNameUnique(user);
        // 清除授权缓存
        // shiroDBRealm.clearCachedAuthorizationInfo(user.getUserName());
        user.setPassword(passwordService.encryptPassword(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * 更新用户
     * 
     * @param user 用户对象
     * @return User
     */
    public User update(User user) {
        if (user.getId() == null) {
            throw new ParamsException("更新用户失败，用户ID不能为空！");
        }
        if (StringUtils.isBlank(user.getUserName())) {
            throw new NotExistedException("用户名不能为空！");
        }
        User dbUser = userRepository.findOne(user.getId());
        if (dbUser == null) {
            throw new NotExistedException("更新用户失败，该用户不存在！");
        }
        user.setPassword(dbUser.getPassword());// 密码不在此处更新，单独更新用户密码
        // 检查用户名唯一性，如果不通过则抛出RuntimeException
        checkUserNameUnique(user);
        // 清除授权缓存
        // shiroDBRealm.clearCachedAuthorizationInfo(user.getUserName());
        return userRepository.save(user);
    }

    /**
     * 更新用户(不包含用户角色)
     * 
     * @param user 用户对象
     * @return User
     */
    public User updateWithoutRole(User user) {
        if (user.getId() == null) {
            throw new ParamsException("更新失败，用户ID不能为空！");
        }
        if (StringUtils.isBlank(user.getUserName())) {
            throw new NotExistedException("用户名不能为空！");
        }
        User dbUser = userRepository.findOne(user.getId());
        if (dbUser == null) {
            throw new NotExistedException("更新失败，该用户不存在！");
        }
        // 检查用户名唯一性，如果不通过则抛出RuntimeException
        checkUserNameUnique(user);
        user.setPassword(dbUser.getPassword());// 密码不在此处更新，单独更新用户密码
        user.setRoles(dbUser.getRoles());
        // 清除授权缓存
        // shiroDBRealm.clearCachedAuthorizationInfo(user.getUserName());
        return userRepository.save(user);
    }

    /**
     * 更新用户角色
     * 
     * @param user 用户对象，只需用户id和角色信息
     * @return User
     */
    public User updateUserRole(User user) {
        if (user.getId() == null) {
            throw new ParamsException("更新用户角色失败，用户ID不能为空！");
        }
        User dbUser = userRepository.findOne(user.getId());
        if (dbUser == null) {
            throw new NotExistedException("更新用户角色失败，该用户不存在！");
        }
        dbUser.setRoles(user.getRoles());
        // 清除授权缓存
        // shiroDBRealm.clearCachedAuthorizationInfo(dbUser.getUserName());
        return userRepository.save(dbUser);
    }

    /**
     * 更新用户密码
     * 
     * @param id 用户id
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @param confirmedPwd 新密码确认
     */
    public void updatePassword(String id, String oldPwd, String newPwd, String confirmedPwd) {
        if (id == null) {
            throw new ParamsException("更新用户密码失败，用户ID不能为空！");
        }
        if (StringUtils.isBlank(oldPwd)) {
            throw new NotExistedException("旧密码不能为空！");
        }
        if (StringUtils.isBlank(newPwd) || StringUtils.isBlank(confirmedPwd)) {
            throw new NotExistedException("新密码及新密码确认不能为空！");
        }
        if (!StringUtils.equals(newPwd, confirmedPwd)) {
            throw new NotEqualException("新密码两次值不一致！");
        }
        User dbUser = userRepository.findOne(id);
        if (dbUser == null) {
            throw new NotExistedException("更新用户密码失败，该用户不存在！");
        }
        if (StringUtils.equals(passwordService.encryptPassword(oldPwd), dbUser.getPassword())) {
            userRepository.updatePassword(id, passwordService.encryptPassword(newPwd));
        } else {
            throw new NotEqualException("旧密码不正确！");
        }
    }

    /**
     * 根据用户id删除用户
     * 
     * @param id 用户id
     */
    public void delete(String id) {
        User dbUser = userRepository.findOne(id);
        if (dbUser == null) {
            throw new NotExistedException("删除异常，该用户不存在！");
        }
        // TODO 处理关联数据
        // 清除授权缓存,从shiro中注销
        // shiroDBRealm.clearCachedAuthorizationInfo(dbUser.getUserName());
        userRepository.delete(id);
    }

    /**
     * 通过用户名查询用户信息
     * 
     * @param userName 用户名
     * @return User
     */
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    /**
     * 通过用户Id查询用户信息
     * 
     * @param id 用户Id
     * @return User
     */
    public User findByUserId(String id) {
        return userRepository.findOne(id);
    }

}
