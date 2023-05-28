package com.itz.gst.use_cases.gst_module.portal;

import com.itz.gst.entity.GstType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GstTypeRepository extends JpaRepository<GstType, Long> {

}
