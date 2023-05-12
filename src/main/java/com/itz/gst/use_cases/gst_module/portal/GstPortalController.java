package com.itz.gst.use_cases.gst_module.portal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itz.gst.helpers.common.results.ResultDao;
import com.itz.gst.helpers.common.token.ClaimsDao;
import com.itz.gst.helpers.common.token.ClaimsSet;
import com.itz.gst.helpers.configs.LoggerConfig;
import com.itz.gst.helpers.configs.ObjectMapperConfig;
import com.itz.gst.use_cases.gst_module.portal.dao.GstAuthDetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("${spring.base.path}")
public class GstPortalController {

    @Autowired
    ClaimsSet claimsSet;
    @Autowired
    GstPortalRepository gstPortalRepository;

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

    public String getAuthDetails(GstAuthDetDao gstAth) throws Exception {
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

        return resJson.get("data").get("TokenExpiry").textValue();
    }

    @GetMapping(path = "/gst/{gstId}")
    public ResponseEntity<?> getGstAuth(HttpServletRequest request
            , @PathVariable(name = "gstId") String roleName) throws Exception {
        ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
        //check token exp
        Integer diff = gstPortalRepository.checkTokenExp(claimsDao.getGst());
        if(diff==null || diff < 30){
            //get token generate det
            GstAuthDetDao gstAuthDetDao =gstPortalRepository.getGstAuthDet(claimsDao.getSub(), claimsDao.getGst());
            //gen token
            String tokenExp = getAuthDetails(gstAuthDetDao);
            //set token det
            gstPortalRepository.setTokenExpForGst(claimsDao.getGst(),tokenExp);
            System.out.println(tokenExp);
        }


        return new ResponseEntity<>(new ResultDao("", "Success", true), HttpStatus.OK);
    }

}
