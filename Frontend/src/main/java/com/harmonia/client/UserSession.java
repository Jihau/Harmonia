package com.harmonia.client;

public class UserSession {
    private static UserSession instance;
    private String userName;
    private int userId;
    private String pfpUrl;

    private UserSession(String userName, int userId, String pfpUrl){
        this.userName = userName;
        this.userId = userId;
        this.pfpUrl = pfpUrl;
    }

    public static UserSession getInstance(String userName, int userId, String pfpUrl){
        if(instance == null) {
            instance = new UserSession(userName, userId, pfpUrl);
        }
        return instance;
    }
    public String getUserName(){
        return userName;
    }
    public int getUserId(){
        return userId;
    }
    public String getPfpUrl(){
        return pfpUrl;
    }
    public void cleanUserSession(){
        userName="";
        userId=0;
        pfpUrl="";
    }
}
