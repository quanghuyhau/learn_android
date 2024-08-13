package com.example.atomi.retrofit;

import java.util.ArrayList;

public class MainNews {

    private String status;
    private String totalresults;
    private ArrayList<Model> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalresults() {
        return totalresults;
    }

    public void setTotalresults(String totalresults) {
        this.totalresults = totalresults;
    }

    public ArrayList<Model> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Model> articles) {
        this.articles = articles;
    }

    public MainNews(String status, String totalresults, ArrayList<Model> articles) {
        this.status = status;
        this.totalresults = totalresults;
        this.articles = articles;
    }
}

//package com.example.atomi.retrofit;
//
//        import java.util.ArrayList;
//
//public class MainNews {
//
//    private String status;
//    private String totalresults;
//    private ArrayList<Model> results;
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getTotalresults() {
//        return totalresults;
//    }
//
//    public void setTotalresults(String totalresults) {
//        this.totalresults = totalresults;
//    }
//
//    public ArrayList<Model> getResults() {
//        return results;
//    }
//
//    public void setResults(ArrayList<Model> results) {
//        this.results = results;
//    }
//
//    public MainNews(String status, String totalresults, ArrayList<Model> results) {
//        this.status = status;
//        this.totalresults = totalresults;
//        this.results = results;
//    }
//}
//
