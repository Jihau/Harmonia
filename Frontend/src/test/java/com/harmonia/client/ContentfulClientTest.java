package com.harmonia.client;

import com.harmonia.utils.HarmoniaUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContentfulClientTest {

    @Test
    void getLabels() {
        ContentfulClient.loadLabels("fi");
        assertEquals(HarmoniaUtils.getLabelForIdentifier("test", "test"), "test");
        assertEquals(HarmoniaUtils.getLabelForIdentifier("username", "username"), "Käyttäjätunnus", "You need to create the contentful field username for fi!");
        assertEquals(HarmoniaUtils.getLabelForIdentifier("password", "password"), "Salasana", "You need to create the contentful field password for fi!");
    }

    @Test
    void getLabelsArabic() {
        ContentfulClient.loadLabels("ar");
        assertEquals(HarmoniaUtils.getLabelForIdentifier("test", "test"), "test");
        assertEquals(HarmoniaUtils.getLabelForIdentifier("username", "username"), "اسم المستخدم:", "You need to create the contentful field username for ar!");
        assertEquals(HarmoniaUtils.getLabelForIdentifier("password", "password"), "كلمة السر:", "You need to create the contentful field password for ar!");
    }

}
