package com.gluonapplication.model.company;

public class CompanyFactory {

    public Company getHugeCompany() {
        return new HugeCompany();
    }

    public Company getMediumCompany() {
        return new MediumCompany();
    }

    public Company getSmallCompany() {
        return new SmallCompany();
    }
}

