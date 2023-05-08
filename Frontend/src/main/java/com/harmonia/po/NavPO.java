package com.harmonia.po;

import javafx.scene.image.Image;

/** A class representing a navigation item.
 * Used to populate the navigation bar.
 * Stores the ID, name, and image of the navigation item.
 * Provides getters for all fields.
 * Author: Harmonia Team
 * @version 2.0
 */
public class NavPO {
    int navId;
    String navName;
    Image image;

    /** Constructor for NavPO.
     * @param navId the ID of the navigation item
     * @param navName the name of the navigation item
     * @param image the image of the navigation item
     */
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
