package com.prosoft.dropwizard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Configuration;


public abstract class EmbeddableService <T extends Configuration> extends Service <T>
{
	 private static final Logger LOG = LoggerFactory.getLogger(EmbeddableService.class);

    private final EmbeddedServerCommand<T> embeddedServerCommand =
            new EmbeddedServerCommand<T>(this);

    public void startEmbeddedServer(String configFileName) throws Exception {
        run(new String[] {"embedded-server", configFileName});
    }

    public void stopEmbeddedServer() throws Exception {
        embeddedServerCommand.stop();
    }

    public boolean isEmbeddedServerRunning() {
        return embeddedServerCommand.isRunning();
    }

    @Override
    public void initialize(Bootstrap <T> bootstrap) {
        bootstrap.addCommand(embeddedServerCommand);
    }

}
