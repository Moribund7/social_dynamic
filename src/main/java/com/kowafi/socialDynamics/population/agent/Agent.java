package com.kowafi.socialDynamics.population.agent;

import com.kowafi.socialDynamics.simulation.Strategy;

import java.util.Objects;

public class Agent {

    private final Strategy strategy;
    private final int id;
    private int size;

    protected Agent(Strategy strategy, int size, int id) {
        this.strategy = strategy;
        this.size = size;
        this.id = id;
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
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        if (agent.id == this.id && (size != agent.size || strategy != agent.strategy))
            throw new IllegalStateException("Agents with same id cant have different parameters");
        return size == agent.size &&
                id == agent.id &&
                strategy == agent.strategy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strategy, size, id);
    }

    public int getId() {
        return id;
    }
}
