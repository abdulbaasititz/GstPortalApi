package com.itz.gst.use_cases.aaa_module.user_master;

import com.itz.gst.helpers.common.constant.UseCasesConstant;
import com.itz.gst.helpers.common.results.ReportDao;
import com.itz.gst.helpers.common.results.ResultDao;
import com.itz.gst.helpers.common.results.ResultsDao;
import com.itz.gst.helpers.common.token.ClaimsDao;
import com.itz.gst.helpers.common.token.ClaimsSet;
import com.itz.gst.persistence.models.aaa_module.UserMaster;
import com.itz.gst.use_cases.aaa_module.auth.dao.CustomerMasterIdDao;
import com.itz.gst.use_cases.aaa_module.user_master.dao.UserMasterDao;
import com.itz.gst.use_cases.aaa_module.user_master.dao.UserMasterIdDao;
import com.itz.gst.use_cases.aaa_module.user_master.dao.UserMasterPojo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("${spring.base.path}")
public class UserMasterController {
	@Autowired
	UserMasterService userMasterService;
	@Autowired
	ClaimsSet claimsSet;

	@PostMapping(value = "/user-master")
	public ResponseEntity<?> userMasterSet(HttpServletRequest request,@RequestBody UserMasterDao val) throws Exception {

		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));

		UserMaster userMaster1 = userMasterService.getUserMasterPk2(val.getUserId());
		if (userMaster1 == null) {
			UserMasterModel userMasterModel = new UserMasterModel();
			int admin = 1;
			String roleName = UseCasesConstant.roleMasterName[admin];
			UserMaster userMaster = userMasterModel.setUserMaster(val,2);
			userMasterService.setUserMasterDetails(userMaster);
			return new ResponseEntity<>(new ResultDao(new ModelMapper().map(userMaster, CustomerMasterIdDao.class), "User Created Successfully", true), HttpStatus.OK);
		} else if (!userMaster1.getIsActive()) {
			throw new Exception("Value Set But Not Activated ,Please Call Admin");
		} else {
			throw new Exception("Value Already Set");
		}
	}

	@PutMapping(value = "/user-master")
	public ResponseEntity<?> userMasterUpdate(HttpServletRequest request, @RequestBody UserMasterIdDao val) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		UserMaster userMasterGet = userMasterService.getUserMasterPk1(val.getId());
		//UserMaster userMasterGet = userMasterService.getUserMasterPk2(val.getUserId());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		if (userMasterGet == null)
			throw new Exception("No User Set");
		if (!userMasterGet.getUserId().equals(val.getUserId()))
			throw new Exception("Cant change the userId or create new account");

		userMasterGet.setPassword(val.getPassword());
		userMasterGet.setDesignation(UseCasesConstant.roleMasterName[1]);
		userMasterGet.setRoleMasterId(1);
		userMasterGet.setPhoneNumber(val.getPhoneNumber());
		userMasterGet.setUserName(val.getUserName());
		userMasterGet.setIsActive(val.getIsActive());
		userMasterGet.setEmail(val.getEmail());
		userMasterGet.setUpBy(claimsDao.getUsr());
//		userMasterGet.getUserRole().setRoleMasterId(2);
//		userMasterGet.getUserRole().setUpBy(val.getUserId());
		userMasterService.setUserMasterDetails(userMasterGet);
		return new ResponseEntity<>(new ResultDao(modelMapper.map(userMasterGet, CustomerMasterIdDao.class), "User Updated Successfully", true), HttpStatus.OK);
	}

	@DeleteMapping(path = "/user-master/{id}")
	public ResponseEntity<?> userMasterDelete(HttpServletRequest request
			, @PathVariable(name = "id") Integer id) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		UserMaster userMasterGet = userMasterService.getUserMasterPk1(id);
		if (userMasterGet == null) {
			throw new Exception("No User Set");
		} else {
//			userMasterGet.setIsActive(false);
//			userMasterGet.setIsDelete(true);
//			userMasterService.setUserMasterDetails(userMasterGet);
			userMasterService.delUserMasterDetails(userMasterGet);
		}
		return new ResponseEntity<>(new ReportDao("User deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping(path = "/user-master/{id}")
	public ResponseEntity<?> userMasterGetAllByUserId(HttpServletRequest request
			, @PathVariable(name = "id") Integer id) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		//UserMaster userMasterGet = userMasterService.getUserMasterPk2(userId);
		UserMaster userMasterGet = userMasterService.getUserMasterPk1(id);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
//		modelMapper.typeMap(UserMaster.class, UserMasterDao.class).addMappings(mapper -> mapper.map(UserMaster -> UserMaster.getUserRole().getRoleMasterId(),
//				UserMasterDao::setRoleMasterId));
		UserMasterDao getValALl = modelMapper.map(userMasterGet, UserMasterDao.class);
		return new ResponseEntity<>(new ResultDao(getValALl, "Success", true), HttpStatus.OK);
	}

	@GetMapping(path = "/user-master")
	public ResponseEntity<?> userMasterGetAll(HttpServletRequest request
			, @RequestParam(required = false, name = "start", defaultValue = "1") int pageNumber
			, @RequestParam(required = false, name = "limit", defaultValue = "25") int pageSize
			, @RequestParam(required = false, name = "searchKey", defaultValue = "-1") String searchKey
			, @RequestParam(required = false, name = "orderBy", defaultValue = "-1") String orderBy
			, @RequestParam(required = false, name = "sortOrder", defaultValue = "-1") int sortOrder
			, @RequestParam(required = false, name = "isPagination", defaultValue = "true") Boolean isPagination) throws Exception {
		ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
		List<UserMasterPojo> getVal;
		long totalCount;
		if (isPagination) {
			Page<UserMasterPojo> getAllWtPg = userMasterService.getAllUserMasterByPg(pageNumber - 1, pageSize - (pageNumber - 1), searchKey);
			getVal = getAllWtPg.getContent();
			totalCount = getAllWtPg.getTotalElements();
		} else {
			getVal = userMasterService.getAllUserMaster();
			totalCount = getVal.size();
			pageNumber = 1;
			pageSize = Math.toIntExact(totalCount);
		}

		ResultsDao resultsDao = new ResultsDao(getVal, pageNumber, pageSize, totalCount);

		return new ResponseEntity<>(resultsDao, HttpStatus.OK);
	}

}
