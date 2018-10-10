package com.zencode.guru.network;

import android.app.Application;

import com.network.utils.common.NetWorkUtilBaseUrl;
import com.network.utils.parser.NetworkParserHelper;

public class Global extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkUtilBaseUrl.BASEURL="http://www.steats.com";
        NetWorkUtilBaseUrl.HEADER_PARAMS.put("access_token","o57z4voQdNxf0U9yuMJT");

    }
}
