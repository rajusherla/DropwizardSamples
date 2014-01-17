package com.prosoft.dropwizard;


import com.prosoft.dropwizard.health.TemplateHealthCheck;
import com.prosoft.dropwizard.resources.CalculationResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class CalculationService extends EmbeddableService<CalculationConfiguration> {
    public static void main(String[] args) throws Exception {
        new CalculationService().run(args);
    }

    private final EmbeddedServerCommand<CalculationConfiguration> embeddedServerCommand =
            new EmbeddedServerCommand<CalculationConfiguration>(this);

    @Override
    public void run(CalculationConfiguration configuration,
                    Environment environment) {
        final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();
        environment.addResource(new CalculationResource(template, defaultName));
        environment.addHealthCheck(new TemplateHealthCheck(template));
    }

	@Override
	public void initialize(Bootstrap<CalculationConfiguration> bootstrap) {
		super.initialize(bootstrap);
		bootstrap.setName("hello-world");
	}


}
