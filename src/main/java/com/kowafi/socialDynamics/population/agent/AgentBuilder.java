package com.kowafi.socialDynamics.population.agent;

import com.kowafi.socialDynamics.exceptions.MissingSimulationArguments;
import com.kowafi.socialDynamics.simulation.Strategy;

public class AgentBuilder {

    private Strategy strategy;
    private int size;
    private int nextAgentId = 0;

    public AgentBuilder() {
    }


    public AgentBuilder withStrategy(Strategy strategy) {
        this.strategy = strategy;
        return this;
    }


    public Agent built() {
        validate();
        Agent newAgent = new Agent(strategy, size, nextAgentId);
        nextAgentId += 1;
        return newAgent;
    }

    private void validate() {
        if (strategy == null) {
            throw new MissingSimulationArguments("Strategy not define in Agent Builder");
        }
    }

    public AgentBuilder withSize(int size) {
        this.size = size;
        return this;
    }
}
