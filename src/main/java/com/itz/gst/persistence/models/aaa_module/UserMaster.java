package com.itz.gst.persistence.models.aaa_module;

import com.itz.gst.persistence.models.common.Auditable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "UserMaster")
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@NoArgsConstructor
public class UserMaster extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    private Integer id = 0;
    @Column(name = "UserName")
    private String userName;
    @Column(name = "UserId")
    private String userId;
    @Column(name = "Password")
    private String password;
    @Column(name = "Designation")
    private String designation;
    @Column(name = "IsActive")
    private Boolean isActive;
    @Column(name = "CrBy")
    private String crBy;
    @Column(name = "UpBy")
    private String upBy;
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    @Column(name = "Email")
    private String email;

    private Boolean isDelete = false;

    private Integer roleMasterId;

//    @JsonManagedReference
//    @OneToOne(fetch = FetchType.EAGER, mappedBy = "userMaster", cascade = CascadeType.ALL)
//    private UserRole userRole;


}