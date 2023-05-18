package com.itz.gst.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FIN_EIN_UserMaster") @Data
public class UserMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    private Integer id = 0;
    @Column(name = "UserId")
    private String userId;
    @Column(name = "Password")
    private String password;
    @Column(name = "Designation")
    private String designation;
    @Column(name = "CompanyCode")
    private String companyCode;
    @Column(name = "GstTypeId")
    private Integer gstTypeId;
    @Column(name = "TotalHits")
    private Integer totalHits;
    @Column(name = "PurchasedDate")
    private Date purchasedDate;
    @Column(name = "ExpiryDate")
    private Date expiryDate;
    @Column(name = "IsActive")
    private Boolean isActive;
}