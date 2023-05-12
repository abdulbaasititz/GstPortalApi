package com.itz.gst.use_cases.aaa_module.user_master;

import com.itz.gst.helpers.configs.LoggerConfig;
import com.itz.gst.helpers.utils.OffsetBasedPageRequest;
import com.itz.gst.entity.UserMaster;
import com.itz.gst.use_cases.aaa_module.user_master.dao.UserMasterPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMasterService {
	@Autowired
	UserMasterRepository rep;
//	@Autowired
//	UserRoleRepository userRoleRepository;

	public Boolean checkUserMasterPk1(Integer pk0) throws Exception {
		try {
			UserMaster getVal = rep.findById(pk0);
			if (getVal == null)
				return false;
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return true;
	}

	public Boolean checkUserMasterPk2(String pk0) throws Exception {
		try {
			UserMaster getVal = rep.findByUserId(pk0);
			if (getVal == null)
				return false;
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return true;
	}

	public UserMaster getUserMasterPk1(Integer pk0) throws Exception {
		UserMaster getVal;
		try {
			getVal = rep.findById(pk0);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return getVal;
	}

	public UserMaster getUserMasterPk2(String pk0) throws Exception {
		UserMaster getVal;
		try {
			getVal = rep.findByUserId(pk0);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return getVal;
	}

	public void setUserMasterDetails(UserMaster val) throws Exception {
		try {
			rep.save(val);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public List<UserMasterPojo> getAllUserMaster() throws Exception {
		try {
			return rep.findAllByQry();
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public Page<UserMasterPojo> getAllUserMasterByPg(int start, int limit, String searchKey) throws Exception {

		try {
			return rep.findAllByQryWtPg(new OffsetBasedPageRequest(start, limit));
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public void delUserMasterDetails(UserMaster userMasterGet) throws Exception {
		try {
			rep.delete(userMasterGet);
		} catch (Exception e) {
			LoggerConfig.logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

//	public String setUserRoleDetails(UserRole val) throws Exception {
//		try {
//			userRoleRepository.save(val);
//		} catch (Exception e) {
//			LoggerConfig.logger.error(e.getMessage());
//			throw new Exception(e.getMessage());
//		}
//		return "1";
//	}
//
//	public UserRole getUserRolePk1(Integer pk0) throws Exception {
//		UserRole getVal;
//		try {
////			getVal = userRoleRepository.findByUserMasterId(pk0);
//			getVal = userRoleRepository.findById(pk0);
//		} catch (Exception e) {
//			LoggerConfig.logger.error(e.getMessage());
//			throw new Exception(e.getMessage());
//		}
//		return getVal;
//	}
//
//
//	public void delUserRole(Integer id) throws Exception {
//		try {
//			userRoleRepository.deleteByUserMasterId(id);
//		} catch (Exception e) {
//			LoggerConfig.logger.error(e.getMessage());
//			throw new Exception(e.getMessage());
//		}
//	}

}
