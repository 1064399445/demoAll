package cn.jun.utils;

import java.util.List;

public class ResultVo<V>{

    private String reCode;
    private String reMsg;
    private V reData;

    public ResultVo() {}

    public ResultVo(String reCode, String reMsg, V reData) {
        this.reCode = reCode;
        this.reMsg = reMsg;
        this.reData = reData;
    }

    public ResultVo(String reCode, String reMsg) {
        this.reCode = reCode;
        this.reMsg = reMsg;
    }

    public String getReCode() {
        return reCode;
    }

    public void setReCode(String reCode) {
        this.reCode = reCode;
    }

    public String getReMsg() {
        return reMsg;
    }

    public void setReMsg(String reMsg) {
        this.reMsg = reMsg;
    }

    public V getReData() {
        return reData;
    }

    public void setReData(V reData) {
        this.reData = reData;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "reCode='" + reCode + '\'' +
                ", reMsg='" + reMsg + '\'' +
                ", reData=" + reData +
                '}';
    }
}
