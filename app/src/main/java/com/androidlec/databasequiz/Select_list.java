package com.androidlec.databasequiz;

public class Select_list {

    //필드
    private int sdNo;
    private String sdName;
    private String sdDept;
    private String sdTel;


    //생성자


    public Select_list(int sdNo, String sdName, String sdDept, String sdTel) {
        this.sdNo = sdNo;
        this.sdName = sdName;
        this.sdDept = sdDept;
        this.sdTel = sdTel;
    }

    public int getSdNo() {
        return sdNo;
    }

    public void setSdNo(int sdNo) {
        this.sdNo = sdNo;
    }

    public String getSdName() {
        return sdName;
    }

    public void setSdName(String sdName) {
        this.sdName = sdName;
    }

    public String getSdDept() {
        return sdDept;
    }

    public void setSdDept(String sdDept) {
        this.sdDept = sdDept;
    }

    public String getSdTel() {
        return sdTel;
    }

    public void setSdTel(String sdTel) {
        this.sdTel = sdTel;
    }
}
