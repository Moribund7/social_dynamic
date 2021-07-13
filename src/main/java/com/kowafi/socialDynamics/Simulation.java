package com.kowafi.socialDynamics;

import com.kowafi.socialDynamics.observers.Observer;

import java.util.ArrayList;
import java.util.Collection;

public class Simulation {
    private final Collection<Observer> observers = new ArrayList<>();
    private final AgentInteractionResolver agentInteractionResolver;
    private final AgentSelector agentSelector;
    public Population population;
    private int numberOfIterations = 0;

    protected Simulation(Population population, AgentInteractionResolver agentInteractionResolver, AgentSelector agentSelector) {
        this.population = population;
        this.agentInteractionResolver = agentInteractionResolver;
        this.agentSelector = agentSelector;
    }

    public int getNumberOfIterations() {
        return numberOfIterations;
    }

    public Population getPopulation() {
        return population;
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void run(int numberOfIterations) {
        for (int iteration = 0; iteration < numberOfIterations; iteration++) {
            this.numberOfIterations += 1;

            AgentPair agentPair = agentSelector.selectAgentPair(population);
            agentInteractionResolver.resolveInteraction(agentPair.getFirst(), agentPair.getSecond());

            for (Observer observer : observers) {
                observer.observe(this);
            }
        }
    }
}
