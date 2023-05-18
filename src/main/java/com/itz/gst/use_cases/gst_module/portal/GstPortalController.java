package com.itz.gst.use_cases.gst_module.portal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itz.gst.entity.UserGstDetails;
import com.itz.gst.entity.UserMaster;
import com.itz.gst.helpers.common.constant.UseCasesConstant;
import com.itz.gst.helpers.common.results.ResultDao;
import com.itz.gst.helpers.common.token.ClaimsDao;
import com.itz.gst.helpers.common.token.ClaimsSet;
import com.itz.gst.helpers.configs.LoggerConfig;
import com.itz.gst.helpers.configs.ObjectMapperConfig;
import com.itz.gst.use_cases.aaa_module.auth.dao.CustomerMasterIdDao;
import com.itz.gst.use_cases.aaa_module.user_master.UserMasterModel;
import com.itz.gst.use_cases.aaa_module.user_master.UserMasterRepository;
import com.itz.gst.use_cases.aaa_module.user_master.dao.UserMasterDao;
import com.itz.gst.use_cases.aaa_module.user_master.dao.UserMasterIdDao;
import com.itz.gst.use_cases.gst_module.portal.dao.AuthRes;
import com.itz.gst.use_cases.gst_module.portal.dao.GenerateIrnDao;
import com.itz.gst.use_cases.gst_module.portal.dao.GstAuthDetDao;
import com.itz.gst.use_cases.gst_module.portal.dao.UserGstDetailsDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.DataInput;

@RestController
@RequestMapping("${spring.base.path}")
public class GstPortalController {

    @Autowired
    ClaimsSet claimsSet;
    @Autowired
    GstPortalRepository gstPortalRepository;
    @Autowired
    UserMasterRepository userMasterRepository;

    private HttpHeaders createHttpHeaders(GstAuthDetDao gstAth) throws Exception {
        //String encrypt = new BaseCrypto().base64Encrypt(authorize);
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("username", gstAth.getUserName()); //"mastergst"
        headers.add("password", gstAth.getPassword()); //"Malli#123"
        headers.add("ip_address", gstAth.getIpAddress()); //"117.192.158.80"
        headers.add("client_id", gstAth.getClientId()); //"06b390bc-6704-4fdf-b587-5b86caa7f931"
        headers.add("client_secret", gstAth.getClientSecret()); //"bc4312c6-4a4d-475c-a8d6-57eab18c15b2"
        headers.add("gstin", gstAth.getGstin()); //"29AABCT1332L000"
        return headers;
    }

    public JsonNode getAuthDetails(GstAuthDetDao gstAth) throws Exception {
        String res;
        JsonNode resJson;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> response;
        ObjectMapper objectMapper = new ObjectMapperConfig().map();
        try {
            HttpHeaders headers = createHttpHeaders(gstAth);
            HttpEntity<String> entity = new HttpEntity<>("body", headers);
            //response = restTemplate.exchange(url, HttpMethod.GET, entity, JsonNode.class);
            resJson = restTemplate.exchange( gstAth.getUrl() +  //"https://api.mastergst.com/einvoice"
                    "/authenticate?email="+gstAth.getEmail(), HttpMethod.GET, entity, JsonNode.class).getBody(); //abdulludba1994@gmail.com
        } catch (Exception e) {
            LoggerConfig.logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
        // object mapper
        System.out.println(resJson);
        //AuthRes authRes = new ObjectMapper().readValue((DataInput) resJson.get("data"),AuthRes.class);
        return resJson.get("data");
    }

    @GetMapping(path = "/gst/{gstId}")
    public ResponseEntity<?> getGstAuth(HttpServletRequest request
            , @PathVariable(name = "gstId") String roleName) throws Exception {
        ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
        //check token exp
        Integer diff = gstPortalRepository.checkTokenExp(claimsDao.getGst());
        if(diff==null || diff < 10){
            //get token generate det
            GstAuthDetDao gstAuthDetDao =gstPortalRepository.getGstAuthDet(claimsDao.getSub(), claimsDao.getGst());
            //gen token
            JsonNode res = getAuthDetails(gstAuthDetDao);
            //set token det
            gstPortalRepository.setTokenExpForGst(claimsDao.getGst(),res.get("AuthToken").textValue(),res.get("TokenExpiry").textValue());
            System.out.println(res);
        }


        return new ResponseEntity<>(new ResultDao("", "Success", true), HttpStatus.OK);
    }

    @PostMapping(value = "register/gst-portal")
    public ResponseEntity<?> userMasterSet(HttpServletRequest request,@RequestBody UserGstDetailsDao val) throws Exception {
        UserMaster userMaster = userMasterRepository.findByUserIdAndPassword(val.getUserId(),val.getUserPassword());
        UserGstDetails userGstDetails = new UserGstDetails();
        userGstDetails.setUserMasterId(userMaster.getId());
        userGstDetails.setGstin(val.getGstin());
        userGstDetails.setUserName(val.getUserName());
        userGstDetails.setPassword(val.getPassword());
        userGstDetails.setEmail(val.getEmail());
        gstPortalRepository.save(userGstDetails);
        return new ResponseEntity<>(new ResultDao("Saved Successfully", "Success", true), HttpStatus.OK);
    }

    private HttpHeaders createHttpHeadersForIrn(GstAuthDetDao gstAth,String tkn) throws Exception {
        //String encrypt = new BaseCrypto().base64Encrypt(authorize);
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("username", gstAth.getUserName()); //"mastergst"
        headers.add("auth-token", tkn); //"tkn"
        headers.add("ip_address", gstAth.getIpAddress()); //"117.192.158.80"
        headers.add("client_id", gstAth.getClientId()); //"06b390bc-6704-4fdf-b587-5b86caa7f931"
        headers.add("client_secret", gstAth.getClientSecret()); //"bc4312c6-4a4d-475c-a8d6-57eab18c15b2"
        headers.add("gstin", gstAth.getGstin()); //"29AABCT1332L000"
        return headers;
    }

    public JsonNode genIrnNo(GenerateIrnDao val,GstAuthDetDao gstAth,String tkn) throws Exception {
        String res;
        JsonNode resJson;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> response;
        ObjectMapper objectMapper = new ObjectMapperConfig().map();
        try {
            HttpHeaders headers = createHttpHeadersForIrn(gstAth,tkn);
            HttpEntity<GenerateIrnDao> entity = new HttpEntity<>(val, headers);

            resJson = restTemplate.exchange( gstAth.getUrl() +  //"https://api.mastergst.com/einvoice"
                    "/type/GENERATE/version/V1_03?email="+gstAth.getEmail(), HttpMethod.POST, entity, JsonNode.class).getBody(); //abdulludba1994@gmail.com
        } catch (Exception e) {
            LoggerConfig.logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
        // object mapper
        System.out.println(resJson);
        //AuthRes authRes = new ObjectMapper().readValue((DataInput) resJson.get("data"),AuthRes.class);
        return resJson;
    }

    @PostMapping(value = "/einvoice/irn")
    public ResponseEntity<?> generateIrnDet(HttpServletRequest request,@RequestBody GenerateIrnDao val) throws Exception {
        ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
        JsonNode tokenDet = null,irnDet;
        //get token generate det
        GstAuthDetDao gstAuthDetDao =gstPortalRepository.getGstAuthDet(claimsDao.getSub(), claimsDao.getGst());
        String authTkn = gstAuthDetDao.getTkn();
        if(gstAuthDetDao.getDiff()==null || gstAuthDetDao.getDiff() < 5){
            //gen new token
            tokenDet = getAuthDetails(gstAuthDetDao);
            //set new token
            gstPortalRepository.setTokenExpForGst(claimsDao.getGst(),tokenDet.get("AuthToken").textValue(),tokenDet.get("TokenExpiry").textValue());
            authTkn = tokenDet.get("AuthToken").textValue();
        }
        irnDet = genIrnNo(val,gstAuthDetDao,authTkn);

        return new ResponseEntity<>(new ResultDao(irnDet, "Success", true), HttpStatus.OK);
    }

    public JsonNode getGstDet(GstAuthDetDao gstAth,String tkn) throws Exception {
        String res;
        JsonNode resJson;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> response;
        ObjectMapper objectMapper = new ObjectMapperConfig().map();
        try {
            HttpHeaders headers = createHttpHeadersForIrn(gstAth,tkn);
            HttpEntity<String> entity = new HttpEntity<>("body", headers);

            resJson = restTemplate.exchange( gstAth.getUrl() +  //"https://api.mastergst.com/einvoice"
                    "/type/GSTNDETAILS/version/V1_03?param1="+gstAth.getGstin()+"&email="+gstAth.getEmail(), HttpMethod.GET, entity, JsonNode.class).getBody(); //abdulludba1994@gmail.com
        } catch (Exception e) {
            LoggerConfig.logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
        // object mapper
        System.out.println(resJson);
        //AuthRes authRes = new ObjectMapper().readValue((DataInput) resJson.get("data"),AuthRes.class);
        return resJson;
    }

    @GetMapping(value = "/einvoice/gst")
    public ResponseEntity<?> getGstDetail(HttpServletRequest request) throws Exception {
        ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
        JsonNode tokenDet = null,irnDet;
        //get token generate det
        GstAuthDetDao gstAuthDetDao =gstPortalRepository.getGstAuthDet(claimsDao.getSub(), claimsDao.getGst());
        String authTkn = gstAuthDetDao.getTkn();
        if(gstAuthDetDao.getDiff()==null || gstAuthDetDao.getDiff() < 5){
            //gen new token
            tokenDet = getAuthDetails(gstAuthDetDao);
            //set new token
            gstPortalRepository.setTokenExpForGst(claimsDao.getGst(),tokenDet.get("AuthToken").textValue(),tokenDet.get("TokenExpiry").textValue());
            authTkn = tokenDet.get("AuthToken").textValue();
        }
        irnDet = getGstDet(gstAuthDetDao,authTkn);

        return new ResponseEntity<>(new ResultDao(irnDet, "Success", true), HttpStatus.OK);
    }


}
