package com.prosoft.dropwizard;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.cli.EnvironmentCommand;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.config.ServerFactory;
import com.yammer.dropwizard.lifecycle.ServerLifecycleListener;
import net.sourceforge.argparse4j.inf.Namespace;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.URI;
import java.net.URISyntaxException;


public class EmbeddedServerCommand<T extends Configuration> extends EnvironmentCommand<T> {

    private final Logger log = LoggerFactory.getLogger(EmbeddedServerCommand.class);

    private final Class<T> configurationClass;

    private Server server;

    public EmbeddedServerCommand(Service<T> service) {
        super(service, "embedded-server", "Starts an HTTP server for running within a larger application");
        this.configurationClass = service.getConfigurationClass();
    }

    @Override
    protected Class<T> getConfigurationClass() {
        return configurationClass;
    }

  @Override
    protected void run(Environment environment, Namespace namespace, T configuration) throws Exception {
        server = new ServerFactory(configuration.getHttpConfiguration(), environment.getName()).buildServer(environment);
        try {
            server.start();
            for (ServerLifecycleListener listener : environment.getServerListeners()) {
                listener.serverStarted(server);
            }
        }
        catch (Exception e) {
            server.stop();
            throw e;
        }
	}


    public void stop() throws Exception {
        try {
            stopJetty();
        }
        finally {
            unRegisterLoggingMBean();
        }
    }

    /**
     * We won't be able to run more than a single test in the same JVM instance unless
     * we do some tidying and un-register a logging m-bean added by Dropwizard.
     */
    private void unRegisterLoggingMBean() throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName loggerObjectName = new ObjectName("com.yammer:type=Logging");
        if (server.isRegistered(loggerObjectName)) {
            server.unregisterMBean(loggerObjectName);
        }
    }

    private void stopJetty() throws Exception {
        if (server != null) {
            server.stop();
            if (!server.isStopped())
                throw new RuntimeException("Failed to stop Jetty");
        }
    }

    public boolean isRunning() {
        return server != null && server.isRunning();
    }

    public URI getRootUriForConnector(String connectorName) {
        try {
        	
            Connector connector = getConnectorNamed(connectorName);
            
            String host = connector.getHost() != null ? connector.getHost() : "localhost";
            return new URI("http://" + host + ":" + connector.getPort());
        }
        catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    private Connector getConnectorNamed(String name) {
        Connector[] connectors = server.getConnectors();
        for (Connector connector : connectors) {
            if (connector.getName().equals(name)) {
                return connector;
            }
        }

        throw new IllegalStateException("No connector named " + name);
    }

}
