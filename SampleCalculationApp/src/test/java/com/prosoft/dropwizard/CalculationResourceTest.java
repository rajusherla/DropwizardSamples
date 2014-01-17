package com.prosoft.dropwizard;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import junit.framework.Assert;

import org.junit.Test;

import com.prosoft.dropwizard.core.Calculation;
import com.prosoft.dropwizard.resources.CalculationResource;
import com.yammer.dropwizard.testing.ResourceTest;

public class CalculationResourceTest extends ResourceTest {
	private final Calculation request = new Calculation();
	
	private final String template = new String();
	private final String defaultName = new String();


	public CalculationResourceTest() {
		request.setNumber1(45);
		request.setNumber2(12);
	}

	@Test
	public void getSampleDataTest() {
		Calculation response = client().resource("/calc/sampledata")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.get(Calculation.class);

		Assert.assertEquals(response.getNumber1(), 12);
		Assert.assertEquals(response.getNumber2(), 45);

	}

	@Test
	public void substractionCalculationTest() {

		// Act
		Calculation response = client().resource("/calc/sub")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.post(Calculation.class, request);

		Assert.assertEquals(response.getNumber1(), 45);
		Assert.assertEquals(response.getNumber2(), 12);
		Assert.assertEquals(response.getResult(), 33);
		Assert.assertEquals(response.getTittle(), "substraction");

	}

	@Test
	public void multiplicationCalculationTest() {

		// Act
		Calculation response = client().resource("/calc/mul")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.post(Calculation.class, request);

		Assert.assertEquals(response.getNumber1(), 45);
		Assert.assertEquals(response.getNumber2(), 12);
		Assert.assertEquals(response.getResult(), 540);
		Assert.assertEquals(response.getTittle(), "multiplication");

	}

	@Test
	public void additionCalculationTest() {

		// Act
		Calculation response = client().resource("/calc/add")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.post(Calculation.class, request);

		Assert.assertEquals(response.getNumber1(), 45);
		Assert.assertEquals(response.getNumber2(), 12);
		Assert.assertEquals(response.getResult(), 57);
		Assert.assertEquals(response.getTittle(), "addition");

	}

	@Test
	public void divisionCalculationTest() {

		// Act
		Calculation response = client().resource("/calc/div")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.post(Calculation.class, request);

		Assert.assertEquals(response.getNumber1(), 45);
		Assert.assertEquals(response.getNumber2(), 12);
		Assert.assertEquals(response.getResult(), 3);
		Assert.assertEquals(response.getTittle(), "division");

	}
	@Override
	protected void setUpResources() throws Exception {
		// TODO Auto-generated method stub
		addResource(new CalculationResource(template, defaultName));

	}

}
