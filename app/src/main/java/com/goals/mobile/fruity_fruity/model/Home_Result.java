package com.goals.mobile.fruity_fruity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mobile on 11/30/2016.
 */

public class Home_Result implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("pieceStatus")
    @Expose
    private String pieceStatus;
    @SerializedName("pieceQty")
    @Expose
    private String pieceQty;
    @SerializedName("bunchStatus")
    @Expose
    private String bunchStatus;
    @SerializedName("bunchQty")
    @Expose
    private String bunchQty;
    @SerializedName("boxStatus")
    @Expose
    private String boxStatus;
    @SerializedName("boxQty")
    @Expose
    private String boxQty;
    @SerializedName("isActive")
    @Expose
    private String isActive;
    @SerializedName("addedOn")
    @Expose
    private String addedOn;
    @SerializedName("lastModified")
    @Expose
    private String lastModified;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return
     * The price
     */
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The pieceStatus
     */
    public String getPieceStatus() {
        return pieceStatus;
    }

    /**
     *
     * @param pieceStatus
     * The pieceStatus
     */
    public void setPieceStatus(String pieceStatus) {
        this.pieceStatus = pieceStatus;
    }

    /**
     *
     * @return
     * The pieceQty
     */
    public String getPieceQty() {
        return pieceQty;
    }

    /**
     *
     * @param pieceQty
     * The pieceQty
     */
    public void setPieceQty(String pieceQty) {
        this.pieceQty = pieceQty;
    }

    /**
     *
     * @return
     * The bunchStatus
     */
    public String getBunchStatus() {
        return bunchStatus;
    }

    /**
     *
     * @param bunchStatus
     * The bunchStatus
     */
    public void setBunchStatus(String bunchStatus) {
        this.bunchStatus = bunchStatus;
    }

    /**
     *
     * @return
     * The bunchQty
     */
    public String getBunchQty() {
        return bunchQty;
    }

    /**
     *
     * @param bunchQty
     * The bunchQty
     */
    public void setBunchQty(String bunchQty) {
        this.bunchQty = bunchQty;
    }

    /**
     *
     * @return
     * The boxStatus
     */
    public String getBoxStatus() {
        return boxStatus;
    }

    /**
     *
     * @param boxStatus
     * The boxStatus
     */
    public void setBoxStatus(String boxStatus) {
        this.boxStatus = boxStatus;
    }

    /**
     *
     * @return
     * The boxQty
     */
    public String getBoxQty() {
        return boxQty;
    }

    /**
     *
     * @param boxQty
     * The boxQty
     */
    public void setBoxQty(String boxQty) {
        this.boxQty = boxQty;
    }

    /**
     *
     * @return
     * The isActive
     */
    public String getIsActive() {
        return isActive;
    }

    /**
     *
     * @param isActive
     * The isActive
     */
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    /**
     *
     * @return
     * The addedOn
     */
    public String getAddedOn() {
        return addedOn;
    }

    /**
     *
     * @param addedOn
     * The addedOn
     */
    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    /**
     *
     * @return
     * The lastModified
     */
    public String getLastModified() {
        return lastModified;
    }

    /**
     *
     * @param lastModified
     * The lastModified
     */
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

}