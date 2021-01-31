package com.gluonapplication.data;

import java.util.ArrayList;

public class ResultWraper {

    ArrayList<String> companies;
    ArrayList<Integer> days;

    public ArrayList<String> getCompanies() {
        return companies;
    }

    public ArrayList<Integer> getDays() {
        return days;
    }

    public ResultWraper(ArrayList<String> companies, ArrayList<Integer> days) {
        this.companies = companies;
        this.days = days;


    }
}
