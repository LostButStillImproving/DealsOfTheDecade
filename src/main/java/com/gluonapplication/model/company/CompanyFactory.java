package com.gluonapplication.model.company;

public class CompanyFactory {

        public Company getCompany(String companyType){

            if(companyType.equalsIgnoreCase("SMALL")){

                return new SmallCompany();

            } else if(companyType.equalsIgnoreCase("MEDIUM")) {

                return new MediumCompany();

            } else if (companyType.equalsIgnoreCase("HUGE")) {

                return new HugeCompany();

            } else {

                return new YourMom();
            }
        }

    public static void main(String[] args) {
        CompanyFactory companyFactory = new CompanyFactory();
        Company smallCompany = companyFactory.getCompany("SMALL");
        Company mediumCompany = companyFactory.getCompany("MEDIUM");
    }
}

