package com.itz.gst.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "GstType") @Data
public class GstType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    private Integer id = 0;
    @Column(name = "CompanyName")
    private String companyName;
    @Column(name = "Type")
    private String type;
    @Column(name = "Email")
    private String email;
    @Column(name = "IpAddress")
    private String ipAddress;
    @Column(name = "ClientId")
    private String clientId;
    @Column(name = "ClientSecret")
    private String clientSecret;
    @Column(name = "TotalHits")
    private Integer totalHits;
    @Column(name = "PurchasedDate")
    private Date purchasedDate;
    @Column(name = "ExpiryDate")
    private Date expiryDate;
    @Column(name = "Url")
    private String url;
}
