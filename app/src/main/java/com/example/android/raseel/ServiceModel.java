package com.example.android.raseel;

/**
 * The ServiceModel Class is used to hold and store the data/information of the service
 * including the service image, the service title, and sthe ervice description
 */

public class ServiceModel {

    private int serivceImage;

    private String serviceTitle;

    private String serviceDescription;

    public ServiceModel(int serivceImage, String serviceTitle, String serviceDescription) {
        this.serivceImage = serivceImage;
        this.serviceTitle = serviceTitle;
        this.serviceDescription = serviceDescription;
    }

    public int getSerivceImage() {
        return serivceImage;
    }

    public void setSerivceImage(int serivceImage) {
        this.serivceImage = serivceImage;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
}