package alioth.mrsheng.space.core;

public enum ErrorCodeMessage {

    SUCCESS("0", "SUCCESS"),

    FAIL("-1", "FAIL"),

    ;

    private String code;

    private String message;

    ErrorCodeMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
