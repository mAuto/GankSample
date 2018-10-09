package com.mauto.repository.remote.OkHttp;

public class NetPointer {

    public static String POINTER_HEADER = "https://gank.io/api/data/";

    public static String POINTER_GET_PARAM_COUNT = "count";// 请求个数
    public static String POINTER_GET_PARAM_FLAG = "flag";// 第几页

    public static String POINTER_GANKIO_ALL = POINTER_HEADER + "all";
    public static String POINTER_GANKIO_ANDROID = POINTER_HEADER + "Android";
    public static String POINTER_GANKIO_IOS = POINTER_HEADER + "iOS";

    public static String POINTER_GANKIO_ANDROID_GET = POINTER_GANKIO_ALL
            + "/" + POINTER_GET_PARAM_COUNT
            + "/" + POINTER_GET_PARAM_FLAG;
}
