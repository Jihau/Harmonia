package com.harmonia.po;

import javafx.scene.image.Image;
/**
 * Class representing a single navigation option on the navbar
 * @author Harmonia team
 * @version 1.0
 **/

public class NavPO {
    int navId;
    String navName;
    Image image;
    public NavPO(int navId, String navName, Image image) {
        this.navId = navId;
        this.navName = navName;
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
    public String getNavName() {
        return navName;
    }
    public int getNavId() {
        return navId;
    }
}
