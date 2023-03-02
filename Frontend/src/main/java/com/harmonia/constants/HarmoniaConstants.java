package com.harmonia.constants;

public class HarmoniaConstants {
    public static final String BASE_URL = "http://localhost:8080";
    public static final String USERS_DELETE_URL = BASE_URL + "/user/{userId}";
    public static final String USERS_LIST_URL = BASE_URL + "/user";
    public static final String USERS_ADD_URL = BASE_URL + "/user";
    public static final String USERS_EDIT_URL = BASE_URL + "/user/{userId}";
    public static final String DM_ADD_URL = BASE_URL + "/dmessage";
    public static final String DM_GETALL_URL = BASE_URL + "/dmessage";
    public static final String DM_EDIT_URL = BASE_URL + "/dmessage/{DmessageId}";
    public static final String DM_GET_A_ID = BASE_URL + "/dmessage/author/{authorId}";
    public static final String DM_GET_R_ID = BASE_URL + "/dmessage/recipient/{recipientId}";
    public static final String SERVER_LIST_ALL_URL = BASE_URL + "/server";
    public static final String SERVER_LIST_BY_NAME_URL = BASE_URL + "/server/name/{serverName}";
    public static final String SERVER_LIST_BY_MEMBER_ID_URL = BASE_URL + "/member/{memberId}";
}
