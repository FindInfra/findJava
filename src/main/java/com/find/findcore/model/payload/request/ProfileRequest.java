package com.find.findcore.model.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProfileRequest {
    String fullName;
    @NotBlank
    @Size(max = 120)
    String displayEmail;
    String phone;
    @Size(max = 20)
    String gender;
    String dateOfBirth;
    String provider;
    String avatarImage;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDisplayEmail() {
        return displayEmail;
    }

    public void setDisplayEmail(String displayEmail) {
        this.displayEmail = displayEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }
}