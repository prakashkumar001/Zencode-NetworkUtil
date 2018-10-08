package com.network.utils.listener;

import java.io.Serializable;

public interface NetWorkResultListener extends Serializable{
    void onSuccess(Object object);
    void onFailure(String error);
}
