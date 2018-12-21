package com.example.vikash.darkskyclient.events;

public class ErrorEvent {
    private String errMsg;

    public ErrorEvent(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
