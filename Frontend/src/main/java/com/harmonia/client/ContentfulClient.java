package com.harmonia.client;

import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.LocalizedResource;
import com.harmonia.constants.HarmoniaMessagesConstants;

import java.util.LinkedHashMap;
import java.util.Map;

public class ContentfulClient {


    public static void loadLabels(String lang) {
        Map<String, String[]> languageProperties = new LinkedHashMap<>();
        languageProperties.put("en-US", new String[]{"a152txh9sqmm", "vegBUFffokA5M4QdrryQ9HRUIJm4EtEE-vymsDAVz7Y"});
        languageProperties.put("fi", new String[]{"a152txh9sqmm", "vegBUFffokA5M4QdrryQ9HRUIJm4EtEE-vymsDAVz7Y"});
        languageProperties.put("ar", new String[]{"b3d2vb8ir299", "gRmKfIaOIdLl2eb574v0jdClheK86Cx_oJC0ABP_dtg"});
        // Add more locales
        if (lang.equalsIgnoreCase("fi") || lang.equalsIgnoreCase("ar")) {
            lang = lang.toLowerCase();
        } else {
            lang = "en-US";
        }
        CDAClient client = CDAClient.builder()
                .setSpace(languageProperties.get(lang)[0])
                .setToken(languageProperties.get(lang)[1])
                .build();
        CDAArray modelArray = client.fetch(CDAEntry.class).withLocale(lang).all();
        HarmoniaMessagesConstants.CONTENTFUL_RESOURCE = (LocalizedResource) modelArray.items().get(0);
    }
}
