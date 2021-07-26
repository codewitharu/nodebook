package com.example.mynotestory;

public class RemindNote {
    private int rID;
    private String rtitle;
    private String rdate;
    private String rtime;
    private String rRepeat;
    private String rRepeatNo;
    private String rRepeatType;
    private String rActive;

    public RemindNote(int rID, String rtitle, String rdate, String rtime, String rRepeat, String rRepeatNo, String rRepeatType, String rActive) {
        this.rID = rID;
        this.rtitle = rtitle;
        this.rdate = rdate;
        this.rtime = rtime;
        this.rRepeat = rRepeat;
        this.rRepeatNo = rRepeatNo;
        this.rRepeatType = rRepeatType;
        this.rActive = rActive;
    }

    public RemindNote() {

    }

    public RemindNote(String rtitle, String rdate, String rtime, String rRepeat, String rRepeatNo, String rRepeatType, String rActive) {
        this.rtitle = rtitle;
        this.rdate = rdate;
        this.rtime = rtime;
        this.rRepeat = rRepeat;
        this.rRepeatNo = rRepeatNo;
        this.rRepeatType = rRepeatType;
        this.rActive = rActive;
    }

    public int getrID() {
        return rID;
    }

    public void setrID(int rID) {
        this.rID = rID;
    }

    public String getRtitle() {
        return rtitle;
    }

    public void setRtitle(String rtitle) {
        this.rtitle = rtitle;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public String getrRepeat() {
        return rRepeat;
    }

    public void setrRepeat(String rRepeat) {
        this.rRepeat = rRepeat;
    }

    public String getrRepeatNo() {
        return rRepeatNo;
    }

    public void setrRepeatNo(String rRepeatNo) {
        this.rRepeatNo = rRepeatNo;
    }

    public String getrRepeatType() {
        return rRepeatType;
    }

    public void setrRepeatType(String rRepeatType) {
        this.rRepeatType = rRepeatType;
    }

    public String getrActive() {
        return rActive;
    }

    public void setrActive(String rActive) {
        this.rActive = rActive;
    }
}
