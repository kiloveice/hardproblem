package live.hardproblem.beans;

import java.io.Serializable;

public class HttpResponseEntity implements Serializable {
    private String code;
    private String message;
    private Object data;

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode(){
        return code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
