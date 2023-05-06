package com.itz.gst.helpers.utils;

import java.util.Base64;

public class BaseCrypto {
    public String base64Encrypt(String key) throws Exception {
        if (key.length() < 1)
            return "";
        return Base64.getEncoder().encodeToString(key.getBytes());
    }

    public String base64Decrypt(String encodedString) throws Exception {
        if (encodedString.length() < 1)
            return "";
        return new String(Base64.getDecoder().decode(encodedString));
    }
}
