package com.example.model;
// Generated Apr 23, 2018 12:41:34 PM by Hibernate Tools 3.6.0


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Payment generated by hbm2java
 */
@Entity
@Table(name="payment"
    ,catalog="`SPRING-SECURITY-TUTORIAL`"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Payment  implements java.io.Serializable {


     private Integer id;
     private User user;
     private Wholeseller wholeseller;
     private Double payable;
     private Double payment;
     private Double due;
     private Double note;
     private Date entryDate;

    public Payment() {
    }

    public Payment(User user, Wholeseller wholeseller, Double payable, Double payment, Double due, Double note, Date entryDate) {
       this.user = user;
       this.wholeseller = wholeseller;
       this.payable = payable;
       this.payment = payment;
       this.due = due;
       this.note = note;
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

    
    @Column(name="payable", precision=22, scale=0)
    public Double getPayable() {
        return this.payable;
    }
    
    public void setPayable(Double payable) {
        this.payable = payable;
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

    
    @Column(name="note", precision=22, scale=0)
    public Double getNote() {
        return this.note;
    }
    
    public void setNote(Double note) {
        this.note = note;
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


