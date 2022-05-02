package com.example.bankacc.core.dto;


public class OpenAccountResponse extends BaseResponse{

    private String id;

    public OpenAccountResponse(String id, String message) {
        super(message);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
