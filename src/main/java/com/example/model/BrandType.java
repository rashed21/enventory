package com.example.model;
// Generated Apr 16, 2018 5:45:20 PM by Hibernate Tools 3.6.0


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * BrandType generated by hbm2java
 */
@Entity
@Table(name="brand_type")
public class BrandType  implements java.io.Serializable {


     private Integer bTypeId;
     private User user;
     private String bTypeName;
     private String bTypeCode;
     private String bTypeDesc;

    public BrandType() {
    }

    public BrandType(User user, String bTypeName, String bTypeCode, String bTypeDesc) {
       this.user = user;
       this.bTypeName = bTypeName;
       this.bTypeCode = bTypeCode;
       this.bTypeDesc = bTypeDesc;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="b_type_id", unique=true, nullable=false)
    public Integer getBTypeId() {
        return this.bTypeId;
    }
    
    public void setBTypeId(Integer bTypeId) {
        this.bTypeId = bTypeId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="b_entry_user")
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    
    @Column(name="b_type_name", length=45)
    public String getBTypeName() {
        return this.bTypeName;
    }
    
    public void setBTypeName(String BTypeName) {
        this.bTypeName = BTypeName;
    }

    
    @Column(name="b_type_code", length=5)
    public String getBTypeCode() {
        return this.bTypeCode;
    }
    
    public void setBTypeCode(String BTypeCode) {
        this.bTypeCode = BTypeCode;
    }

    
    @Column(name="b_type_desc", length=100)
    public String getBTypeDesc() {
        return this.bTypeDesc;
    }
    
    public void setBTypeDesc(String BTypeDesc) {
        this.bTypeDesc = BTypeDesc;
    }




}

