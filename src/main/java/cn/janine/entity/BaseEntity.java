package cn.janine.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import cn.janine.common.Constants;

/**
 * 实体类基类
 * 
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 4053532656813051791L;
    private String id;
    private Date updateTime = new Date();// 默认为当前系统时间
    private Date createTime = new Date();// 默认为当前系统时间

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(generator = "sys-uuid2")
    @GenericGenerator(name = "sys-uuid2", strategy = "uuid2")
    @Column(name = "id", length = 36)
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the updateTime
     */
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = Constants.DEFAULT_DATE_TIME_FORMAT)
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the createTime
     */
    @Column(name = "create_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = Constants.DEFAULT_DATE_TIME_FORMAT)
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
