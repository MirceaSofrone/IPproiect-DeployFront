package com.hpprediction.demo.security.oauth2.models;


import com.hpprediction.demo.exceptions.OAuth2AuthenticationProcessingException;
import com.hpprediction.demo.datamodels.AuthProviderEnum;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProviderEnum.GOOGLE.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException
                    ("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}