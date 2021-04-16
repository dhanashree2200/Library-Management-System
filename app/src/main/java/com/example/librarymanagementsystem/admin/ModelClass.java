package com.example.librarymanagementsystem.admin;

import java.util.Date;

public class ModelClass {
    String bookId,bookTitle,bookAuth,bookPub,sid,rDate,iDate;

//    public ModelClass(String bookId, String sid, String iDate, String rDate) {
//        this.bookId = bookId;
//        this.sid = sid;
//        this.iDate = iDate;
//        this.rDate = rDate;
//    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public String getiDate() {
        return iDate;
    }

    public void setiDate(String iDate) {
        this.iDate = iDate;
    }

    public ModelClass(String bookId, String bookTitle, String bookAuth, String bookPub) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuth = bookAuth;
        this.bookPub = bookPub;
    }


    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuth() {
        return bookAuth;
    }

    public void setBookAuth(String bookAuth) {
        this.bookAuth = bookAuth;
    }

    public String getBookPub() {
        return bookPub;
    }

    public void setBookPub(String bookPub) {
        this.bookPub = bookPub;
    }
}
