package com.kowafi.socialDynamics.simulation;

import com.kowafi.socialDynamics.exceptions.MissingSimulationArguments;

public class AgentBuilder {

    private Strategy strategy;
    private int size;

    public AgentBuilder() {
    }


    public AgentBuilder withStrategy(Strategy strategy) {
        this.strategy = strategy;
        return this;
    }


    public Agent built() {
        validate();
        return new Agent(strategy, size);
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
