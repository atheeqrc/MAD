package com.example.asus.commercialbank;

public class Transaction {
    private  int transId;
    private  int accountNumber;
    private  int tAccountNumber;
    private  String Date;
    private int amount;


    public Transaction(int transId, int accountNumber, int tAccountNumber, String date, int amount) {
        this.transId = transId;
        this.accountNumber = accountNumber;
        this.tAccountNumber = tAccountNumber;
        Date = date;
        this.amount = amount;
    }

    public Transaction(int accountNumber, int tAccountNumber, String date, int amount) {
        this.accountNumber = accountNumber;
        this.tAccountNumber = tAccountNumber;
        Date = date;
        this.amount = amount;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int gettAccountNumber() {
        return tAccountNumber;
    }

    public void settAccountNumber(int tAccountNumber) {
        this.tAccountNumber = tAccountNumber;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
