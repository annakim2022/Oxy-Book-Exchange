package com.example.oxybookexchange;

import android.widget.ImageView;

import java.math.BigInteger;

public class Listings {

    private String listingID;
    private String userID;
    private String ISBN;
    private String title;
    private String quality;
    private String price;
    private String course;
    private String semester;
    private String yearPublished;
    private String authors;
    private String profLast, profLast2, profLast3;


    public Listings(String listingID, String userID, String ISBN, String title, String quality, String price, String course,
                    String semester, String yearPublished, String authors, String profLast, String profLast2, String profLast3) {

        this.listingID = listingID;
        this.userID = userID;
        this.ISBN = ISBN;
        this.title = title;
        this.quality = quality;
        this.price = price;
        this.course = course;
        this.semester = semester;
        this.yearPublished = yearPublished;
        this.authors = authors;
        this.profLast = profLast;
        this.profLast2 = profLast2;
        this.profLast3 = profLast3;


    }


    public String getListingID() {
        return listingID;
    }

    public void setListingID(String listingID) {
        this.listingID = listingID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(String yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authorLast) {
        this.authors = authorLast;
    }


    public String getProfLast() {
        return profLast;
    }

    public void setProfLast(String profLast) {
        this.profLast = profLast;
    }

    public String getProfLast2() {
        return profLast2;
    }

    public void setProfLast2(String profLast2) {
        this.profLast2 = profLast2;
    }

    public String getProfLast3() {
        return profLast3;
    }

    public void setProfLast3(String profLast3) {
        this.profLast3 = profLast3;
    }
}
