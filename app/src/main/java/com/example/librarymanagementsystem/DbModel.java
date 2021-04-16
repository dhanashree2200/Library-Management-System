package com.example.librarymanagementsystem;

import java.util.Date;

public class DbModel {
    int status,book;
    String book_id, book_title, book_auth, book_pub,stud_id, stud_name, stud_mail, stud_ph, stud_pass,i_book_id,i_stud_id,r_date,i_date;

    public DbModel( String book_id, String book_title, String book_auth, String book_pub,int status) {
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_auth = book_auth;
        this.book_pub = book_pub;
        this.status = status;
    }
    public DbModel() {
    }
    public DbModel(String stud_id, String stud_name,String stud_pass, String stud_mail, String stud_ph,int book ) {
        this.book = book;
        this.stud_id = stud_id;
        this.stud_name = stud_name;
        this.stud_pass = stud_pass;
        this.stud_mail = stud_mail;
        this.stud_ph = stud_ph;
    }


    public DbModel(String i_book_id, String i_stud_id, String i_date, String r_date) {
        this.i_book_id = i_book_id;
        this.i_stud_id = i_stud_id;
        this.i_date = i_date;
        this.r_date = r_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_auth() {
        return book_auth;
    }

    public void setBook_auth(String book_auth) {
        this.book_auth = book_auth;
    }

    public String getBook_pub() {
        return book_pub;
    }

    public void setBook_pub(String book_pub) {
        this.book_pub = book_pub;
    }

    public String getStud_id() {
        return stud_id;
    }

    public void setStud_id(String stud_id) {
        this.stud_id = stud_id;
    }

    public String getStud_name() {
        return stud_name;
    }

    public void setStud_name(String stud_name) {
        this.stud_name = stud_name;
    }

    public String getStud_mail() {
        return stud_mail;
    }

    public void setStud_mail(String stud_mail) {
        this.stud_mail = stud_mail;
    }

    public String getStud_ph() {
        return stud_ph;
    }

    public void setStud_ph(String stud_ph) {
        this.stud_ph = stud_ph;
    }

    public String getStud_pass() {
        return stud_pass;
    }

    public void setStud_pass(String stud_pass) {
        this.stud_pass = stud_pass;
    }

    public String getI_book_id() {
        return i_book_id;
    }

    public void setI_book_id(String i_book_id) {
        this.i_book_id = i_book_id;
    }

    public String getI_stud_id() {
        return i_stud_id;
    }

    public void setI_stud_id(String i_stud_id) {
        this.i_stud_id = i_stud_id;
    }

    public String getI_date() {
        return i_date;
    }

    public void setI_date(String i_date) {
        this.i_date = i_date;
    }

    public String getR_date() {
        return r_date;
    }

    public void setR_date(String r_date) {
        this.r_date = r_date;
    }

}
