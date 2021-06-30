package com.kowafi.socialDynamics.simulation;

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
        return new Agent(strategy, size);
    }

    public AgentBuilder withSize(int size) {
        this.size = size;
        return this;
    }
}
