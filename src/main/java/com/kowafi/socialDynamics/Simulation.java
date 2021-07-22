package com.kowafi.socialDynamics;

import com.kowafi.socialDynamics.observers.Observer;
import com.kowafi.socialDynamics.population.Population;
import com.kowafi.socialDynamics.population.agent.Agent;
import com.kowafi.socialDynamics.simulation.Strategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Simulation {
    private final static Logger LOGGER = LoggerFactory.getLogger(Simulation.class);

    private final Collection<Observer> observers = new ArrayList<>();
    private final AgentInteractionResolver agentInteractionResolver;
    private final AgentSelector agentSelector;
    private final Population population;
    private int numberOfIterations = 0;

    protected Simulation(Population population, AgentInteractionResolver agentInteractionResolver, AgentSelector agentSelector) {
        this.population = population;
        this.agentInteractionResolver = agentInteractionResolver;
        this.agentSelector = agentSelector;
    }

    public static List<Strategy> getAvailableStrategies() {
        return List.of(Strategy.COOPERATIVE, Strategy.NONCOOPERATIVE); // TODO this should loaded from configs
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
            LOGGER.info("Iteration #{}, population {}", numberOfIterations, population);

            AgentPair agentPair = agentSelector.selectAgentPair(population);
            agentInteractionResolver.resolveInteraction(agentPair.getFirst(), agentPair.getSecond());

            for (Observer observer : observers) {
                observer.observe(this);
            }
        }
    }

    public Collection<Agent> getAgentsReadOnly() {
        return population.getAgentsReadOnly();
    }
}
