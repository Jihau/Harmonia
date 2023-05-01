package com.harmonia.client;

import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.LocalizedResource;
import com.harmonia.constants.HarmoniaMessagesConstants;

import java.util.LinkedHashMap;
import java.util.Map;

/** This class is used to load the labels from Contentful.
 *  * @author Harmonia Team
 *  * @version 1.2
 *  */

public class ContentfulClient {

/** Method to load the labels from Contentful.*/
    public static void loadLabels(String lang) {
        Map<String, String[]> languageProperties = new LinkedHashMap<>();
        languageProperties.put("en-US", new String[]{"a152txh9sqmm", "vegBUFffokA5M4QdrryQ9HRUIJm4EtEE-vymsDAVz7Y"});
        languageProperties.put("fi", new String[]{"a152txh9sqmm", "vegBUFffokA5M4QdrryQ9HRUIJm4EtEE-vymsDAVz7Y"});
        languageProperties.put("ar", new String[]{"b3d2vb8ir299", "gRmKfIaOIdLl2eb574v0jdClheK86Cx_oJC0ABP_dtg"});
        languageProperties.put("es", new String[]{"elrjuwcd4gaw", "ElvBfQvu7VkEsd8hBM-HjmBK12YC0VS0woJHyFxcSSg"});
        languageProperties.put("fr", new String[]{"34d6vcvt9fx2", "QqBPFxz_kV68jyoQbhYu0HicqB6wZlFV9ffh4gQ77i8"});
        // Add more locales
        if (lang.equalsIgnoreCase("fi") || lang.equalsIgnoreCase("ar")|| lang.equalsIgnoreCase("es") || lang.equalsIgnoreCase("fr")) {
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
