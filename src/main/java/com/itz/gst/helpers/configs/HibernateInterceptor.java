package com.itz.gst.helpers.configs;


import org.hibernate.EmptyInterceptor;
import org.springframework.stereotype.Component;

@Component
public class HibernateInterceptor extends EmptyInterceptor {
//    @Value("${spring.multiTenant.prefix}")
//    private final String multiTenantPrefix = "##plant##";

//    @Override
//    public String onPrepareStatement(String sql) {
//        String plantName = JwtUtil.plt;
//        sql = sql.replaceAll(multiTenantPrefix, plantName + "_");
//        return super.onPrepareStatement(sql);
//    }

}