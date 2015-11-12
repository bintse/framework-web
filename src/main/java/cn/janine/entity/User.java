package cn.janine.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 用户实体类
 * 
 *
 */
@Entity
@Table(name = "sys_user")
public class User extends BaseEntity {
    private static final long serialVersionUID = 4476711331047735513L;
    private String userName;// 用户名
    private String password;// 密码
    private String confirmedPwd;// 验证密码
    private String salt;// 密码盐
    private String realName;// 真实名称
    private String nickName;// 昵称
    private String phone;// 电话
    private String email;// 邮箱
    private String status;// 状态
    private Organization organization;// 组织机构
    private List<Position> positions = new ArrayList<Position>();// 岗位
    private List<Role> roles = new ArrayList<Role>();// 该用户拥有的角色

    /**
     * @return the userName
     */
    @Column(name = "user_name", length = 30, nullable = false, unique = true)
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    @Column(name = "password", length = 256, nullable = false)
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the confirmedPwd
     */
    @Transient
    public String getConfirmedPwd() {
        return confirmedPwd;
    }

    /**
     * @param confirmedPwd the confirmedPwd to set
     */
    public void setConfirmedPwd(String confirmedPwd) {
        this.confirmedPwd = confirmedPwd;
    }

    /**
     * @return the salt
     */
    @Column(name = "salt", length = 256)
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return the realName
     */
    @Column(name = "real_name", length = 20)
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName the realName to set
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return the phone
     */
    @Column(name = "phone", length = 20)
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    @Column(name = "email", length = 40)
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the status
     */
    @Column(name = "status", length = 16)
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the organization
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    public Organization getOrganization() {
        return organization;
    }

    /**
     * @param organization the organization to set
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     * @return the positions
     */
    @ManyToMany
    @JoinTable(name = "sys_user_position", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "position_id", referencedColumnName = "id") })
    public List<Position> getPositions() {
        return positions;
    }

    /**
     * @param positions the positions to set
     */
    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    /**
     * @return the roles
     */
    @ManyToMany
    @JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id") })
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /* (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode() */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /* (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object) */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
