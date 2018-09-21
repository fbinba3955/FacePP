package com.tts.sjt.library.net;

public interface NetCallBack<T> {

    public void onSuccess(T response);

    public void onFail(String failMsg);

}
