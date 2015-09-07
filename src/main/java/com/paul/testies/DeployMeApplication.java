package com.paul.testies;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.paul.testies.helloworld.HelloWorldResource;
import com.paul.testies.health.TemplateHealthCheck;

public class DeployMeApplication extends Application<DeployMeConfiguration> {
    public static void main(String[] args) throws Exception {
        new DeployMeApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<DeployMeConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DeployMeConfiguration configuration,
                    Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
        configuration.getTemplate(),
        configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
        new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}