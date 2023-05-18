package com.itz.gst.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FIN_EIN_UserGstDetails") @Data
public class UserGstDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    private Integer id = 0;
    @Column(name = "UserMasterId")
    private Integer userMasterId;
    @Column(name = "Gstin")
    private String gstin;
    @Column(name = "UserName")
    private String userName;
    @Column(name = "Password")
    private String password;
    @Column(name = "Email")
    private String email;
    @Column(name = "TotalHits")
    private Integer totalHits;
    @Column(name = "AuthToken")
    private String authToken;
    @Column(name = "TokenExpiry")
    private Date tokenExpiry;
}
