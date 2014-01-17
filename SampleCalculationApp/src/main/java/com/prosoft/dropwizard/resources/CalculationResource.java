package com.prosoft.dropwizard.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.prosoft.dropwizard.core.Calculation;

@Path("/calc")
@Produces(MediaType.APPLICATION_JSON)
public class CalculationResource {

	private final String template;
	private final String defaultName;

	public CalculationResource(String template, String defaultName) {
		this.template = template;
		this.defaultName = defaultName;
	}

	@POST
	@Path("/add")
	public Response addition(Calculation calculation) {
		calculation.setResult(calculation.getNumber1()
				+ calculation.getNumber2());
		calculation.setTittle("addition");
		return Response.status(201).entity(calculation).build();
	}

	@POST
	@Path("/sub")
	public Response substraction(Calculation calculation) {
		calculation.setResult(calculation.getNumber1()
				- calculation.getNumber2());
		calculation.setTittle("substraction");
		return Response.status(201).entity(calculation).build();
	}

	@POST
	@Path("/mul")
	public Response multiplication(Calculation calculation) {
		calculation.setResult(calculation.getNumber1()
				* calculation.getNumber2());
		calculation.setTittle("multiplication");
		return Response.status(201).entity(calculation).build();
	}
	
	@POST
	@Path("/div")
	public Response divison(Calculation calculation) {
		calculation.setResult(calculation.getNumber1()
				/calculation.getNumber2());
		calculation.setTittle("division");
		return Response.status(201).entity(calculation).build();
	}
	
	@GET
	@Path("/sampledata")
	public Response getSampleData() {
		Calculation calculation = new Calculation();
		calculation.setNumber1(12);
		calculation.setNumber2(45);
		return Response.status(200).entity(calculation).build();
	}
	
	
}
