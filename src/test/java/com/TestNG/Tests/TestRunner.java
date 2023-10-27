package com.TestNG.Tests;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class TestRunner {

    public static void main(String[] args) {
        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[] { OrangeHRM.class });
        testng.addListener(tla);
        testng.run();
    }
}
