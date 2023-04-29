package com.harmonia.po;

import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

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
