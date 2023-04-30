package com.harmonia.client;

import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.LocalizedResource;
import com.harmonia.constants.HarmoniaMessagesConstants;

public class ContentfulClient {


    public static void loadLabels(String lang) {
        // Add more locales
        if (!lang.equals("fi")) {
            lang = "en-US";
        }
        CDAClient client = CDAClient.builder()
                .setSpace("e1rl81rzkl0m")
                .setToken("GzMfJ82u5ukH8cFinpMmECFGEWE-u9b6WxnqTMoMVYk")
                .build();
        CDAArray modelArray = client.fetch(CDAEntry.class).withLocale(lang).all();
        HarmoniaMessagesConstants.CONTENTFUL_RESOURCE = (LocalizedResource) modelArray.items().get(0);
    }
}
