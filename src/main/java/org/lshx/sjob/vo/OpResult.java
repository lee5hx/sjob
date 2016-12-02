package org.lshx.sjob.vo;

/**
 * Created by lee5hx on 16/10/25.
 */
public class OpResult<T> {
    private int code ;
    private T Result;
    private String errorMessage;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getResult() {
        return Result;
    }

    public void setResult(T result) {
        Result = result;
    }
}
