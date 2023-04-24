package com.harmonia.client;

import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;

import java.util.Map;

public class ContentfulClient {
    public static Map<String, String> CONTENTFUL_LABELS;

    public static void loadLabels() {
        CDAClient client = CDAClient.builder()
                .setSpace("e1rl81rzkl0m")
                .setToken("GzMfJ82u5ukH8cFinpMmECFGEWE-u9b6WxnqTMoMVYk")
                .build();
        CDAArray array = client.fetch(CDAEntry.class).withLocale("fi").all();
    }
}
