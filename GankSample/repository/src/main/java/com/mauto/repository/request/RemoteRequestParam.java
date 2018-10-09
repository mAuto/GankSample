package com.mauto.repository.request;

import java.util.HashMap;

/**
 * Created by haohuidong on 18-9-25.
 */

public class RemoteRequestParam implements RequestParam {
    public RequestMode mode;
    public String url;
    public HashMap<String, String> params;
}
