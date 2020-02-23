package com.example.android.rxjavaretrofitsample.http;

/**
 * created by zyh
 * on 2019-08-10
 */
public class HttpResult<T> {
    private boolean error;
    private T results;
    private int msgCode;

    public int getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(int msgCode) {
        this.msgCode = msgCode;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
