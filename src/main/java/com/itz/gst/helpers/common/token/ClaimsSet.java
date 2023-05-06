package com.itz.gst.helpers.common.token;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itz.gst.helpers.utils.JwtUtil;
import com.itz.gst.persistence.models.aaa_module.UserMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClaimsSet {
    @Autowired
    JwtUtil jwtUtil;

    public ClaimsSet() {
        super();
    }

//    public Boolean setClaimsDetailsFromToken(String token) throws Exception {
//        ClaimsDao claimsDao = getClaimsDetails(token);
//        jwtUtil.setOrgDetails(claimsDao.getPlt(), claimsDao.getEid());
//        return true;
//    }

    public static Map<String, Object> setClaimsDetails(UserMaster userDao) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", userDao.getRoleMasterId());
        claims.put("usr", userDao.getId());
        claims.put("plt", "ITZ");
        return claims;
    }

    public ClaimsDao getClaimsDetailsAfterSet(String token) {
        ClaimsDao claimsDao = getClaimsDetails(token);
        jwtUtil.setOrgDetails(claimsDao.getPlt(), claimsDao.getUsr());
        return claimsDao;
    }

    public ClaimsDao getClaimsDetailsAfterSetNewPlt(String token, String plant) {
        ClaimsDao claimsDao = getClaimsDetails(token);
        jwtUtil.setOrgDetails(plant, claimsDao.getUsr());
        return claimsDao;
    }

    public ClaimsDao getClaimsDetails(String token) {
        try {
            java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder();
            token = token.substring(7);
            String[] parts = token.split("\\.");
            String payloadJson = new String(decoder.decode(parts[1]));
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(payloadJson, ClaimsDao.class);
        } catch (JsonProcessingException e) {
            throw new BadCredentialsException("UnAuthorised Token");
        }
    }
}
