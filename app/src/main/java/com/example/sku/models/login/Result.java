package com.example.sku.models.login;


import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("name")
    public String name;
    @SerializedName("email")
    public String email;
    @SerializedName("apiKey")
    public String apiKey;

    @SerializedName("message")
    public String message;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
