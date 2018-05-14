package com.example.model;
// Generated Apr 23, 2018 12:41:34 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Voucher generated by hbm2java
 */
@Entity
@Table(name="voucher"
    ,catalog="`SPRING-SECURITY-TUTORIAL`"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Voucher  implements java.io.Serializable {


     private Integer id;
     private User user;
     private Wholeseller wholeseller;
     private Integer totalPair;
     private String catagory;
     private Double totalValue;
     private Double payment;
     private Double due;
     private String voucher;
     private Date date;
     private Date entryDate;

    public Voucher() {
    }

    public Voucher(User user, Wholeseller wholeseller, Integer totalPair, String catagory, Double totalValue, Double payment, Double due, String voucher, Date date, Date entryDate) {
       this.user = user;
       this.wholeseller = wholeseller;
       this.totalPair = totalPair;
       this.catagory = catagory;
       this.totalValue = totalValue;
       this.payment = payment;
       this.due = due;
       this.voucher = voucher;
       this.date = date;
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
    @JoinColumn(name="entry_user")
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="whole_id")
    public Wholeseller getWholeseller() {
        return this.wholeseller;
    }
    
    public void setWholeseller(Wholeseller wholeseller) {
        this.wholeseller = wholeseller;
    }

    
    @Column(name="total_pair")
    public Integer getTotalPair() {
        return this.totalPair;
    }
    
    public void setTotalPair(Integer totalPair) {
        this.totalPair = totalPair;
    }

    
    @Column(name="catagory", length=100)
    public String getCatagory() {
        return this.catagory;
    }
    
    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    
    @Column(name="total_value", precision=22, scale=0)
    public Double getTotalValue() {
        return this.totalValue;
    }
    
    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    
    @Column(name="payment", precision=22, scale=0)
    public Double getPayment() {
        return this.payment;
    }
    
    public void setPayment(Double payment) {
        this.payment = payment;
    }

    
    @Column(name="due", precision=22, scale=0)
    public Double getDue() {
        return this.due;
    }
    
    public void setDue(Double due) {
        this.due = due;
    }

    
    @Column(name="voucher", length=20)
    public String getVoucher() {
        return this.voucher;
    }
    
    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date", length=19)
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="entry_date", length=19)
    public Date getEntryDate() {
        return this.entryDate;
    }
    
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }


}


