package dohaeng.com.bubang.utils;

import java.util.Map;

/**
 * 이 문서는 통신에 필요한 url을 정리 합니다.
 */
public class HttpURL extends Thread {
    public static final String UserRegist  = "/api/Auth/Register";
    public static final String UserLogin   = "/api/Auth/Login";
//    public static final String UserEnrollment = "/api/Auth/Enrollment";
//    public static final String NmapString = "http://bullswiz.com:10444/api/Map/FindLocationToText";
    public static final String Nmapaddress = "/api/Map/FindLocationToText";
    public static final String Nmaplatitude = "/api/Map/FindLocationToGeo";

}