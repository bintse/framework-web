package cn.janine.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 组织机构实体类
 * 
 *
 */
@Entity
@Table(name = "sys_organization", indexes = { @Index(columnList = "name", name = "i_sys_organization_name"),
        @Index(columnList = "code", name = "i_sys_organization_code"), @Index(columnList = "type", name = "i_sys_organization_type") })
public class Organization extends BaseEntity {
    private static final long serialVersionUID = 3166670762705453129L;

    private String name;// 机构名称
    private String description;// 描述
    private String code;// 机构编码
    private String type;// 机构类型
    private String tel;// 联系电话
    private String fax;// 传真
    private String address;// 地址
    private String email;// 邮箱
    private Organization parent;// 上级部门
    private List<Organization> children = new ArrayList<Organization>();// 下级部门
    private List<Role> roles = new ArrayList<Role>();// 该部门拥有的角色
    private List<User> users = new ArrayList<User>();// 该部门下的用户

    /**
     * @return the name
     */
    @Column(name = "name", length = 64, nullable = false)
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    @Column(name = "description", length = 256)
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the code
     */
    @Column(name = "code", length = 40)
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the type
     */
    @Column(name = "type", length = 16)
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the tel
     */
    @Column(name = "tel", length = 20)
    public String getTel() {
        return tel;
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the fax
     */
    @Column(name = "fax", length = 20)
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the address
     */
    @Column(name = "address", length = 255)
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
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
     * @return the parent
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public Organization getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Organization parent) {
        this.parent = parent;
    }

    /**
     * @return the children
     */
    @OneToMany(mappedBy = "parent")
    public List<Organization> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(List<Organization> children) {
        this.children = children;
    }

    /**
     * @return the roles
     */
    @ManyToMany
    @JoinTable(name = "sys_organization_role", joinColumns = { @JoinColumn(name = "organization_id", referencedColumnName = "id") }, inverseJoinColumns = {
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

    /**
     * @return the users
     */
    @OneToMany(mappedBy = "organization")
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
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
