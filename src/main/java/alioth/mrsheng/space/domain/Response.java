package alioth.mrsheng.space.domain;

import alioth.mrsheng.space.core.ErrorCodeMessage;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response<T> {

    @JsonProperty("errcode")
    private String errorCode;

    @JsonProperty("errmsg")
    private String errorMessage;

    private T data;

    public Response() {
    }

    public Response(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 返回成功的对象
     */
    public static <T> Response<T> buildSuccess() {
        Response<T> response = new Response<>();
        response.setErrorCode(ErrorCodeMessage.SUCCESS.getCode());
        response.setErrorMessage(ErrorCodeMessage.SUCCESS.getMessage());
        return response;
    }
}
