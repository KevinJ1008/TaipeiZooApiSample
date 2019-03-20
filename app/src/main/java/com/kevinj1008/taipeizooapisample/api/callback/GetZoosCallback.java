package com.kevinj1008.taipeizooapisample.api.callback;

import com.kevinj1008.taipeizooapisample.api.bean.GetZoos;

public interface GetZoosCallback {

    void onCompleted(GetZoos zoos);

    void onError(String errorMessage);

}
