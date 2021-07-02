package com.kowafi.socialDynamics;

import com.kowafi.socialDynamics.simulation.Strategy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SimulationBuilder {
    private PopulationBuilder populationBuilder;
    private AgentInteractionResolver agentInteractionResolver;
    private AgentSelector agentSelector;

    public SimulationBuilder() {
    }

    public static Simulation initializeFromPropertiesFile(String fileName) {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(fileName)) {
            props.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();//TODO add better exception
        }


        int populationSize = Integer.parseInt(props.getProperty("population.size"));
        double property = Double.parseDouble(props.getProperty("strategy.ratio"));
        PopulationBuilder populationBuilder = new PopulationBuilder().withSize(populationSize).withStrategyRatio(Strategy.fromString(props.getProperty("strategy")), property);

        return new Simulation(populationBuilder.build(), null, null);

    }

    public SimulationBuilder withAgentInteractionResolver(AgentInteractionResolver agentInteractionResolver) {
        this.agentInteractionResolver = agentInteractionResolver;
        return this;

    }

    public SimulationBuilder withAgentSelector(AgentSelector agentSelector) {
        this.agentSelector = agentSelector;
        return this;

    }

    public SimulationBuilder withPopulationBuilder(PopulationBuilder populationBuilder) {
        this.populationBuilder = populationBuilder;
        return this;
    }

    public Simulation build() {
        return new Simulation(populationBuilder.build(), agentInteractionResolver, agentSelector);
    }
}
