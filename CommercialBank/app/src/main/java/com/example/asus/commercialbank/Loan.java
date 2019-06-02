package com.example.asus.commercialbank;

public class Loan {

    private int loanId;
    private String username;
    private String loanType;
    private String comments;

    public Loan(String username, String loanType, String comments) {
        this.username = username;
        this.loanType = loanType;
        this.comments = comments;
    }

    public Loan(int loanId, String username, String loanType, String comments) {
        this.loanId = loanId;
        this.username = username;
        this.loanType = loanType;
        this.comments = comments;
    }

    public Loan(int loanId, String loanType, String comments) {
        this.loanId = loanId;
        this.loanType = loanType;
        this.comments = comments;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
