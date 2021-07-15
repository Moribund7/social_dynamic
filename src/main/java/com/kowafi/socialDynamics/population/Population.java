package com.kowafi.socialDynamics.population;

import com.kowafi.socialDynamics.population.agent.Agent;
import com.kowafi.socialDynamics.simulation.Strategy;

import java.util.List;

public class Population {

    private final List<Agent> population;


    public Population(List<Agent> population) {
        this.population = population;
    }


    public int getSize() {
        return population.size();
    }

    public long getAgentsNumberWithStrategy(Strategy strategy) {
        return population.stream().filter(agent -> agent.getStrategy().equals(strategy)).count();
    }

    public List<Agent> getAgentsReadOnly() {
        return List.copyOf(population);
    }

    @Override
    public String toString() {
        return "Population{" +
                "population=" + population +
                '}';
    }

    public Agent getAgentWithId(int id) {
        return population.get(id);
    }

    public void addToAgentSize(int id, int size) {
        getAgentWithId(id).addToSize(size);
    }
}
