package com.harmonia.po;

import javafx.scene.image.Image;
/**
 * Class representing a single navigation option on the navbar
 * @author Harmonia team
 * @version 1.0
 **/

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

    /**
     * Returns the image of the navigation item.
     * @return image of the navigation item
     */
    public Image getImage() {
        return image;
    }
    public String getNavName() {
        return navName;
    }
    /**
     * Returns the ID of the navigation item.
     * @return ID of the navigation item
     */
    public int getNavId() {
        return navId;
    }
}
