package com.gluonapplication.model;

import com.gluonapplication.model.company.Company;
import com.gluonapplication.model.company.CompanyFactory;
import java.util.Scanner;

import static java.lang.Thread.*;

public class TestMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CompanyFactory companyFactory = new CompanyFactory();
        Company company = companyFactory.getMediumCompany();

        Thread t1 = new Thread(() -> {
            while (true) {
                company.updateBudget();
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                System.out.println("Make a decision");
                String input = scanner.nextLine();
                if (input.equals("b")) {
                        company.makeBusinessDecision();
                    System.out.println(company);
                } else break;
            }
            System.out.println(company);
        });

        t1.start();
        t2.start();

    }
}
