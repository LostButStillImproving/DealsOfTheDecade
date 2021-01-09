package com.gluonapplication.model.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyFactoryTest {

    @Test
    public void getHugeCompanyTest() {
        CompanyFactory companyFactory = new CompanyFactory();

        Company hugeCompany = companyFactory.getHugeCompany();

        assertTrue(hugeCompany instanceof HugeCompany);

    }

    @Test
    public void getMediumCompanyTest() {
        CompanyFactory companyFactory = new CompanyFactory();

        Company mediumCompany = companyFactory.getMediumCompany();

        assertTrue(mediumCompany instanceof MediumCompany);

    }

    @Test
    public void getSmallCompanyTest() {
        CompanyFactory companyFactory = new CompanyFactory();

        Company smallCompany = companyFactory.getSmallCompany();

        assertTrue(smallCompany instanceof SmallCompany);

    }
}