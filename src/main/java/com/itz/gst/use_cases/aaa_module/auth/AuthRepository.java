package com.itz.gst.use_cases.aaa_module.auth;

import com.itz.gst.persistence.models.aaa_module.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<UserMaster, Long> {
    //    @Query(value = "select TOP 1 * " +
//            "from userInfo where userId = :username",nativeQuery = true)
    UserMaster findByUserId(String username);

    UserMaster findByUserIdAndIsActive(String usrId, boolean b);
}