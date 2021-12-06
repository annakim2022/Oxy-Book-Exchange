package com.example.oxybookexchange;

import android.widget.ImageView;

import java.math.BigInteger;

public class Listings {

    private String listingID;
    private String userEmail;
    private String ISBN;
    private String title;
    private String quality;
    private String price;
    private String course;
    private String semester;
    private String yearPublished;
    private String authors;
    private String professors;


    public Listings(String listingID, String userEmail, String ISBN, String title, String quality, String price, String course,
                    String semester, String yearPublished, String authors, String professors) {

        this.listingID = listingID;
        this.userEmail = userEmail;
        this.ISBN = ISBN;
        this.title = title;
        this.quality = quality;
        this.price = price;
        this.course = course;
        this.semester = semester;
        this.yearPublished = yearPublished;
        this.authors = authors;
        this.professors = professors;

    }


    public String getListingID() {
        return listingID;
    }

    public void setListingID(String listingID) {
        this.listingID = listingID;
    }

    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public String getProfessors() {
        return professors;
    }

    public void setProfessors(String profLast) {
        this.professors = professors;
    }

}
