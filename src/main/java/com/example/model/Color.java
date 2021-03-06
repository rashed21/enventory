package com.example.model;
// Generated Apr 23, 2018 12:41:34 PM by Hibernate Tools 3.6.0


import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Color generated by hbm2java
 */
@Entity
@Table(name="color"
    ,catalog="`SPRING-SECURITY-TUTORIAL`"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Color  implements Serializable {


     private Integer id;
     private User user;
     private String code;
     private String name;
     private Date entryDate;

    public Color() {
    }

	
    public Color(User user, String code, String name, Date entryDate) {
        this.user = user;
        this.code = code;
        this.name = name;
        this.entryDate = entryDate;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="entry_user", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    
    @Column(name="code", nullable=false, length=5)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="name", nullable=false, length=45)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="entry_date", nullable=false, length=19)
    public Date getEntryDate() {
        return this.entryDate;
    }
    
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }



}


