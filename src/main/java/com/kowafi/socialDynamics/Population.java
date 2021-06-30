package com.kowafi.socialDynamics;

import com.kowafi.socialDynamics.simulation.Agent;
import com.kowafi.socialDynamics.simulation.Strategy;

import java.util.Collection;
import java.util.List;

public class Population {

    private final Collection<Agent> population;


    public Population(Collection<Agent> population) {
        this.population = population;
    }


    public int getSize() {
        return population.size();
    }

    public long getAgentsNumberWithStrategy(Strategy strategy) {
        return population.stream().filter(agent -> agent.getStrategy().equals(strategy)).count();
    }

    public List<Agent> getAgentsAsList() {
        return List.copyOf(population);
    }
}
