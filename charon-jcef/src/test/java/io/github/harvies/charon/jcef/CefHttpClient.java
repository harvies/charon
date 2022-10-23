package io.github.harvies.charon.jcef;

import io.github.harvies.charon.http.CharsetUtil;
import io.github.harvies.charon.model.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.cef.callback.CefAuthCallback;
import org.cef.callback.CefURLRequestClient;
import org.cef.network.CefRequest;
import org.cef.network.CefURLRequest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class CefHttpClient {

    private final static ConcurrentHashMap<Integer, CefHttpResponse> resultMap = new ConcurrentHashMap<>();
    private final static long readTimeout = 60000;

    public static CefHttpClient INSTANCE = new CefHttpClient();

    public ApiResult<String> execute(CefRequest request, String charset) {
        ApiResult<String> apiResult = new ApiResult<>();
        try {
            HttpRequestTask httpRequestTask = new HttpRequestTask(request, charset);
            httpRequestTask.execute();
            long beginTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - beginTime < readTimeout) {
                CefHttpResponse result = resultMap.get(request.hashCode());
                if (result != null) {
                    CefURLRequest.Status requestStatus = result.getCefURLRequest().getRequestStatus();
                    if (CefURLRequest.Status.UR_SUCCESS.equals(requestStatus)) {
                        apiResult.setSuccess(true);
                        apiResult.setData(result.getResposeStr());
                    } else if (CefURLRequest.Status.UR_FAILED.equals(requestStatus)) {
                        apiResult.setSuccess(false);
                    }
                    break;
                }
                Thread.sleep(100);
            }
            resultMap.remove(request.hashCode());
        } catch (Exception e) {
            log.warn("", e);
        }
        return apiResult;
    }


    private class HttpRequestTask implements CefURLRequestClient {
        private CefRequest request;
        private String charset;
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        public HttpRequestTask(CefRequest request, String charset) {
            this.charset = charset;
            this.request = request;
        }

        @Override
        public void onRequestComplete(CefURLRequest cefURLRequest) {
            CefHttpResponse cefHttpResponse = new CefHttpResponse();
            cefHttpResponse.setCefURLRequest(cefURLRequest);
            byte[] bytes = os.toByteArray();
            if (charset != null) {
                try {
                    cefHttpResponse.setResposeStr(new String(bytes, charset));
                } catch (UnsupportedEncodingException e) {
                    log.warn("", e);
                }
            } else {
                String ioEncode = CharsetUtil.getIOEncode(new ByteArrayInputStream(bytes), bytes.length);
                try {
                    cefHttpResponse.setResposeStr(new String(bytes, ioEncode));
                } catch (UnsupportedEncodingException e) {
                    log.warn("", e);
                }
            }
            resultMap.put(request.hashCode(), cefHttpResponse);
        }

        @Override
        public void onUploadProgress(CefURLRequest cefURLRequest, int i, int i1) {

        }

        @Override
        public void onDownloadProgress(CefURLRequest cefURLRequest, int i, int i1) {

        }

        @Override
        public void onDownloadData(CefURLRequest cefURLRequest, byte[] bytes, int i) {
            try {
                IOUtils.write(bytes, os);
            } catch (IOException e) {
                log.warn("", e);
            }
        }

        @Override
        public boolean getAuthCredentials(boolean b, String s, int i, String s1, String s2, CefAuthCallback cefAuthCallback) {
            return false;
        }

        @Override
        public void setNativeRef(String s, long l) {

        }

        @Override
        public long getNativeRef(String s) {
            return 0;
        }

        public void execute() {
            CefURLRequest.create(request, this);
        }
    }

}
