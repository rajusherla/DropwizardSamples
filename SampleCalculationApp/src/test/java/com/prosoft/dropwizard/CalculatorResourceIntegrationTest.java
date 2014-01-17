package com.prosoft.dropwizard;

import static org.fest.assertions.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import junit.framework.Assert;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.ClassRule;
import org.junit.Test;

import com.prosoft.dropwizard.core.Calculation;
import com.yammer.dropwizard.testing.JsonHelpers;

public class CalculatorResourceIntegrationTest {

	@ClassRule
	public static TestContext provider = new TestContext(false);

	private static DefaultHttpClient httpClient = null;

	@Test
	public void getSampleDataTest() {

		try {
			final Calculation calculation = new Calculation();
			calculation.setNumber1(12);
			calculation.setNumber2(45);

			httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
					"http://localhost:18081/calc/sampledata");
			getRequest.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {

				Assert.fail("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			StringBuffer responseBody = new StringBuffer();
			while ((output = br.readLine()) != null) {
				responseBody.append(output);
			}
			assertThat(responseBody.toString()).isEqualToIgnoringCase(
					JsonHelpers.asJson(calculation));
			assertThat(200).isEqualTo(response.getStatusLine().getStatusCode());

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
			}
		}

	}

	@Test
	public void additionCalculationTest() {
		try {

			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
					"http://localhost:18081/calc/add");

			String string = "{\"number1\":12,\"number2\":45}";
			StringEntity input = new StringEntity(string);
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 201) {
				Assert.fail("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			StringBuffer responseBody = new StringBuffer();
			while ((output = br.readLine()) != null) {
				responseBody.append(output);
			}
			final Calculation calculation = new Calculation();
			calculation.setNumber1(12);
			calculation.setNumber2(45);
			calculation.setResult(57);
			calculation.setTittle("addition");
			assertThat(responseBody.toString()).isEqualToIgnoringCase(
					JsonHelpers.asJson(calculation));

			assertThat(201).isEqualTo(response.getStatusLine().getStatusCode());

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
			}
		}

	}

	@Test
	public void multiplicationCalculationTest() {
		try {

			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
					"http://localhost:18081/calc/mul");

			String string = "{\"number1\":12,\"number2\":45}";
			StringEntity input = new StringEntity(string);
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 201) {
				Assert.fail("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			StringBuffer responseBody = new StringBuffer();
			while ((output = br.readLine()) != null) {
				responseBody.append(output);
			}
			final Calculation calculation = new Calculation();
			calculation.setNumber1(12);
			calculation.setNumber2(45);
			calculation.setResult(540);
			calculation.setTittle("multiplication");
			assertThat(responseBody.toString()).isEqualToIgnoringCase(
					JsonHelpers.asJson(calculation));

			assertThat(201).isEqualTo(response.getStatusLine().getStatusCode());

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
			}
		}

	}

	@Test
	public void substractionCalculationTest() {
		try {

			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
					"http://localhost:18081/calc/sub");

			String string = "{\"number1\":45,\"number2\":12}";
			StringEntity input = new StringEntity(string);
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 201) {
				Assert.fail("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			StringBuffer responseBody = new StringBuffer();
			while ((output = br.readLine()) != null) {
				responseBody.append(output);
			}
			final Calculation calculation = new Calculation();
			calculation.setNumber1(45);
			calculation.setNumber2(12);
			calculation.setResult(33);
			calculation.setTittle("substraction");
			assertThat(responseBody.toString()).isEqualToIgnoringCase(
					JsonHelpers.asJson(calculation));

			assertThat(201).isEqualTo(response.getStatusLine().getStatusCode());

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
			}
		}

	}
	
	@Test
	public void divisionCalculationTest() {
		try {

			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
					"http://localhost:18081/calc/div");

			String string = "{\"number1\":45,\"number2\":12}";
			StringEntity input = new StringEntity(string);
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 201) {
				Assert.fail("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			StringBuffer responseBody = new StringBuffer();
			while ((output = br.readLine()) != null) {
				responseBody.append(output);
			}
			final Calculation calculation = new Calculation();
			calculation.setNumber1(45);
			calculation.setNumber2(12);
			calculation.setResult(3);
			calculation.setTittle("division");
			assertThat(responseBody.toString()).isEqualToIgnoringCase(
					JsonHelpers.asJson(calculation));

			assertThat(201).isEqualTo(response.getStatusLine().getStatusCode());

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
			}
		}

	}
}
