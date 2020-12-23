package com.contracts.tests;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AgreementControllerTest.class })
public class TestSuite {

	@BeforeClass
	public static void setUpClass() {
	}

}
