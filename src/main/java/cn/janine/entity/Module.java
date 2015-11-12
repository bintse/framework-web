package cn.janine.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 模块实体类
 * 
 *
 */
@Entity
@Table(name = "sys_module")
public class Module extends BaseEntity implements Resources, Comparable<Module> {
    private static final long serialVersionUID = -8188249946835893566L;
    private String name;// 模块名称
    private String url;// 模块入口
    private String description;// 模块描述
    private String sn;// 标识符，用于授权名称（类似module:save）
    private Integer priority = 999;// 模块的排序号，越小优先级越高
    private Module parent;// 父模块
    private List<Module> children = new ArrayList<Module>();// 子模块
    private List<Permission> permissions = new ArrayList<Permission>();// 权限

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
     * @return the url
     */
    @Column(name = "url", length = 256, nullable = false)
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
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
     * @return the sn
     */
    @Column(name = "sn", length = 32, nullable = false, unique = true)
    public String getSn() {
        return sn;
    }

    /**
     * @param sn the sn to set
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * @return the priority
     */
    @Column(name = "priority", nullable = false)
    public Integer getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * @return the parent
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public Module getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Module parent) {
        this.parent = parent;
    }

    /**
     * @return the children
     */
    @OneToMany(mappedBy = "parent", cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE })
    @OrderBy("priority ASC")
    public List<Module> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(List<Module> children) {
        this.children = children;
    }

    /**
     * @return the permissions
     */
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Permission> getPermissions() {
        // 因为hibernate更新使用的是merge方法，会自动新增关联的瞬时对象，如果在此配置CascadeType.MERGE，会插入两条数据
        return permissions;
    }

    /**
     * @param permissions the permissions to set
     */
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public int compareTo(Module o) {
        if (o == null) {
            return -1;
        } else if (o == this) {
            return 0;
        } else if (this.priority < o.getPriority()) {
            return -1;
        } else if (this.priority > o.getPriority()) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
