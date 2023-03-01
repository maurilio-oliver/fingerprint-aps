package org.example.model;

import java.time.LocalDateTime;
import java.util.Map;

public class FingerPrint {
    private Integer id;
    private String fingerPrint;

    private byte[] test;

    public byte[] getTest() {
        return test;
    }

    public void setTest(byte[] test) {
        this.test = test;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

}
