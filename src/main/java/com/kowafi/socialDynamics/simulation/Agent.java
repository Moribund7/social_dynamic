package com.kowafi.socialDynamics.simulation;

public class Agent {

    private final Strategy strategy;
    private int size;

    protected Agent(Strategy strategy, int size) {
        this.strategy = strategy;
        this.size = size;
    }

    public Strategy getStrategy() {

        return this.strategy;
    }

    public void addToSize(int agentSizeChange) {
        this.size += agentSizeChange;
    }


    public double getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "strategy=" + strategy +
                ", size=" + size +
                '}';
    }
}
