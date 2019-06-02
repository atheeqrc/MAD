package com.example.asus.commercialbank;

public class Complaint {

   private int complaintId;
    private String username;
    private String type;
    private String complaint;

    public Complaint(int complaintId, String username, String type, String complaint) {
        this.complaintId = complaintId;
        this.username = username;
        this.type = type;
        this.complaint = complaint;
    }

    public Complaint(String username, String type, String complaint) {
        this.username = username;
        this.type = type;
        this.complaint = complaint;
    }

    public Complaint(int complaintId, String type, String complaint) {
        this.complaintId = complaintId;
        this.type = type;
        this.complaint = complaint;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
}
