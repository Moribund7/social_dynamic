package com.kowafi.socialDynamics;

import com.kowafi.socialDynamics.exceptions.MissingSimulationArguments;

public class SimulationBuilder {
    private PopulationBuilder populationBuilder;
    private AgentInteractionResolver agentInteractionResolver;
    private AgentSelector agentSelector;

    public SimulationBuilder() {
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
        validate();
        return new Simulation(populationBuilder.build(), agentInteractionResolver, agentSelector);
    }

    private void validate() {
        validateAgentSelector();
        validatePopulationBuilder();
        validateAgentInteractionResolver();
    }


    private void validateAgentSelector() {
        if (agentSelector == null)
            throw new MissingSimulationArguments("Validation of simulation arguments failed. AgentSelector is missing");

    }

    private void validateAgentInteractionResolver() {
        if (agentInteractionResolver == null)
            throw new MissingSimulationArguments("Validation of simulation arguments failed. AgentInteractionResolver is missing");

    }

    private void validatePopulationBuilder() {
        if (populationBuilder == null)
            throw new MissingSimulationArguments("Validation of simulation arguments failed. PopulationBuilder is missing");

    }

    @Override
    public String toString() {
        return "SimulationBuilder{" +
                "populationBuilder=" + populationBuilder +
                ", agentInteractionResolver=" + agentInteractionResolver +
                ", agentSelector=" + agentSelector +
                '}';
    }
}
