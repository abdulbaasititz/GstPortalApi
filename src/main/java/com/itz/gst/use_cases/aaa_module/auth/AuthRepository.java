package com.itz.gst.use_cases.aaa_module.auth;

import com.itz.gst.entity.UserMaster;
import com.itz.gst.use_cases.aaa_module.auth.dao.ClaimsDetDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<UserMaster, Long> {
    //    @Query(value = "select TOP 1 * " +
//            "from userInfo where userId = :username",nativeQuery = true)
    UserMaster findByUserId(String username);

    UserMaster findByUserIdAndIsActive(String usrId, boolean b);

    @Query(value = "select usr.Id as usr,usr.UserId as sub,usr.CompanyName as plt " +
            ",gst.Gstin as gst from UserMaster usr join UserGstDetails gst " +
            " on usr.Id = gst.UserMasterId " +
            " where usr.UserId = :usrId and gst.Gstin = :gst and usr.IsActive = 1",nativeQuery = true)
    ClaimsDetDao getClaimsDetails(String usrId, String gst);
}