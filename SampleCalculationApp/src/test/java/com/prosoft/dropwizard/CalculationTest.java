package com.prosoft.dropwizard;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.prosoft.dropwizard.core.Calculation;
import com.yammer.dropwizard.testing.JsonHelpers;

public class CalculationTest {

	@Test
	public void serializesToJSON() throws Exception {
		final Calculation calculation = new Calculation();
		calculation.setNumber1(1);
		calculation.setNumber2(3);
		calculation.setResult(3);
		calculation.setTittle("addition");
		assertStringMatchesJsonFixture("Calculation can be serialized to JSON",
				JsonHelpers.asJson(calculation),
				"/calucationDomain.json");
	}

	@Test
	public void deserializesFromJSON() throws Exception {

		Calculation calculation = JsonHelpers.fromJson(
				jsonFixture("/calucationDomain.json"),
				Calculation.class);
		Assert.assertEquals(1, calculation.getNumber1());
		Assert.assertEquals(3, calculation.getNumber2());
		Assert.assertEquals(3, calculation.getResult());
		Assert.assertEquals("addition", calculation.getTittle());

	}

	public void assertStringMatchesJsonFixture(String reason,
			String representation, String fixtureClasspath) throws IOException {

		assertThat(representation).isEqualTo(jsonFixture(fixtureClasspath))
				.describedAs(reason);
	}

	public String jsonFixture(String fixtureClasspath) throws IOException {
		ObjectMapper om = new ObjectMapper();
		return om.writeValueAsString(om.readValue(fixture(fixtureClasspath),
				JsonNode.class));
	}

	public static String fixture(String fixtureClasspath) throws IOException {
		return Resources.toString(
				CalculationTest.class.getResource(fixtureClasspath),
				Charsets.UTF_8).trim();
	}
}