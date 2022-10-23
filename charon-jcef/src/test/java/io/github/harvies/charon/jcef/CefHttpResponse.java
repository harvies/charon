package io.github.harvies.charon.jcef;

import org.cef.network.CefURLRequest;

public class CefHttpResponse {
    private CefURLRequest cefURLRequest;
    private String resposeStr;

    public CefURLRequest getCefURLRequest() {
        return cefURLRequest;
    }

    public void setCefURLRequest(CefURLRequest cefURLRequest) {
        this.cefURLRequest = cefURLRequest;
    }

    public String getResposeStr() {
        return resposeStr;
    }

    public void setResposeStr(String resposeStr) {
        this.resposeStr = resposeStr;
    }
}
