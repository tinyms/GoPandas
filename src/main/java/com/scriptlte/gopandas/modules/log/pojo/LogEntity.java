package com.scriptlte.gopandas.modules.log.pojo;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 平台操作日志记录: 日志实体
 */
//@Entity
@Data
@Entity
public class LogEntity {
    @Id
    @GeneratedValue(generator = "uuid-jpa-LogEntity")
    @GenericGenerator(strategy = "uuid",name = "uuid-jpa-LogEntity")
    private String id;
    /**
     * 进行该动作的用户的用户名
     */
    @Column(nullable = false)
    private String operaUserName;
    /**
     * 操作对应的功能名称
     */
    @Column(nullable = false)
    private String operaFunctionName;
    /**
     * 操作描述
     */
    @Column(nullable = false)
    private String operaDescription;
    /**
     * 操作所属的模块
     */
    @Column(nullable = false)
    private String operaModule;
    /**
     * 操作时间
     */
    @Column(nullable = false)
    private Long operaTime;
    /**
     * 被调用的方法名,可为null
     */
    private String calledMethod;

    public LogEntity() {
    }
    public LogEntity(String operaUserName, String operaFunctionName, String operaDescription, String operaModule, Long operaTime, String calledMethod) {
        this.operaUserName = operaUserName;
        this.operaFunctionName = operaFunctionName;
        this.operaDescription = operaDescription;
        this.operaModule = operaModule;
        this.operaTime = operaTime;
        this.calledMethod = calledMethod;
    }

    public LogEntity setId(String id) {
        this.id = id;
        return this;
    }

    public LogEntity setOperaUserName(String operaUserName) {
        this.operaUserName = operaUserName;
        return this;
    }

    public LogEntity setOperaDescription(String operaDescription) {
        this.operaDescription = operaDescription;
        return this;
    }

    public LogEntity setOperaModule(String operaModule) {
        this.operaModule = operaModule;
        return this;
    }

    public LogEntity setOperaTime(Long operaTime) {
        this.operaTime = operaTime;
        return this;
    }

    public LogEntity setOperaFunctionName(String operaFunctionName) {
        this.operaFunctionName = operaFunctionName;
        return this;
    }

    public LogEntity setCalledMethod(String calledMethod) {
        this.calledMethod = calledMethod;
        return this;
    }
}
