package com.itz.gst.use_cases.gst_module.portal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itz.gst.helpers.common.results.ResultDao;
import com.itz.gst.helpers.common.token.ClaimsDao;
import com.itz.gst.helpers.common.token.ClaimsSet;
import com.itz.gst.helpers.configs.LoggerConfig;
import com.itz.gst.helpers.configs.ObjectMapperConfig;
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

    private HttpHeaders createHttpHeaders() throws Exception {
        //String encrypt = new BaseCrypto().base64Encrypt(authorize);
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("username", "mastergst");
        headers.add("password", "Malli#123");
        headers.add("ip_address", "117.192.158.80");
        headers.add("client_id", "06b390bc-6704-4fdf-b587-5b86caa7f931");
        headers.add("client_secret", "bc4312c6-4a4d-475c-a8d6-57eab18c15b2");
        headers.add("gstin", "29AABCT1332L000");
        return headers;
    }

    public String getAuthDetails() throws Exception {
        String res;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> response;
        ObjectMapper objectMapper = new ObjectMapperConfig().map();
        try {
            HttpHeaders headers = createHttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>("body", headers);
            //response = restTemplate.exchange(url, HttpMethod.GET, entity, JsonNode.class);
            res = restTemplate.exchange("https://api.mastergst.com/einvoice" +
                    "/authenticate?email=abdulludba1994@gmail.com", HttpMethod.GET, entity, String.class).getBody();
        } catch (Exception e) {
            LoggerConfig.logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
        // object mapper


        return res.toString();
    }

    @GetMapping(path = "/gst/{gstId}")
    public ResponseEntity<?> getGstAuth(HttpServletRequest request
            , @PathVariable(name = "gstId") String roleName) throws Exception {
        ClaimsDao claimsDao = claimsSet.getClaimsDetailsAfterSet(request.getHeader("Authorization"));
        String getRes = getAuthDetails();
        return new ResponseEntity<>(new ResultDao(getRes, "Success", true), HttpStatus.OK);
    }

}
