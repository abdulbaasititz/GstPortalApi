package com.itz.gst.use_cases.gst_module.portal;

import com.itz.gst.entity.UserGstDetails;
import com.itz.gst.use_cases.gst_module.portal.dao.GstAuthDetDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GstPortalRepository extends JpaRepository<UserGstDetails, Long> {

    @Query(value = "select usr.UserId,typ.Email,typ.ClientId,typ.ClientSecret,typ.IpAddress,typ.Url, " +
            "   gst.UserName,gst.Password,gst.Gstin,DATEDIFF(ss,GETDATE(),gst.TokenExpiry)as diff ,gst.AuthToken as tkn " +
            "   from FIN_EIN_GstType typ,FIN_EIN_UserMaster usr,FIN_EIN_UserGstDetails gst where " +
            "   usr.GstTypeId= typ.Id and usr.Id = gst.UserMasterId and " +
            "   usr.UserId = :usrId and gst.Gstin = :gst",nativeQuery = true)
    GstAuthDetDao getGstAuthDet(String usrId,String gst);

    @Query(value = "select DATEDIFF(ss,GETDATE(),TokenExpiry)as diff " +
            " from FIN_EIN_UserGstDetails gst where gst.Gstin = :gst",nativeQuery = true)
    Integer checkTokenExp(String gst);

    @Modifying @Transactional
    @Query("Update FIN_EIN_UserGstDetails set AuthToken = :auth,TokenExpiry = :tokenExp " +
            " where Gstin = :gst")
    void setTokenExpForGst(String gst,String auth, String tokenExp);
}
