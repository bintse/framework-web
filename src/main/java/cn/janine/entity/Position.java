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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 岗位实体类
 * 
 *
 */
@Entity
@Table(name = "sys_position")
public class Position extends BaseEntity {
    private static final long serialVersionUID = 3785994122644836069L;

    private String name;// 岗位名称
    private String description;// 描述
    private Position parent;// 上级部门
    private List<Position> children = new ArrayList<Position>();// 下级部门
    private List<Role> roles = new ArrayList<Role>();// 该岗位拥有的角色
    private List<User> users = new ArrayList<User>();// 该岗位下的用户

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
     * @return the parent
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public Position getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Position parent) {
        this.parent = parent;
    }

    /**
     * @return the children
     */
    @OneToMany(mappedBy = "parent")
    public List<Position> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(List<Position> children) {
        this.children = children;
    }

    /**
     * @return the roles
     */
    @ManyToMany
    @JoinTable(name = "sys_position_role", joinColumns = { @JoinColumn(name = "position_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") })
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
    @ManyToMany(mappedBy = "positions")
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
