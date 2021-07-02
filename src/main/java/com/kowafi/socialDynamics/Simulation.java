package com.kowafi.socialDynamics;

import com.kowafi.socialDynamics.observers.Observer;

import java.util.Collection;
import java.util.Collections;

public class Simulation {
    private final Collection<Observer> observers = Collections.emptyList();
    public Population population;
    private int numberOfIterations = 0;
    private final AgentInteractionResolver agentInteractionResolver;
    private final AgentSelector agentSelector;

    protected Simulation(Population population, AgentInteractionResolver agentInteractionResolver, AgentSelector agentSelector) {
        this.population = population;
        this.agentInteractionResolver = agentInteractionResolver;
        this.agentSelector = agentSelector;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
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

//            AgentPair agentPair = agentSelector.selectAgentPair(population);
//            agentInteractionResolver.resolveInteraction(agentPair.getFirst(), agentPair.getSecond());

            for (Observer observer : observers) {
                observer.observe(this);
            }
        }
    }
}
