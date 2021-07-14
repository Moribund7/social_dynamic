package com.kowafi.socialDynamics.observers;

import com.kowafi.socialDynamics.Population;
import com.kowafi.socialDynamics.Simulation;
import com.kowafi.socialDynamics.simulation.Agent;
import com.kowafi.socialDynamics.statistics.SimulationStatistics;

import java.util.List;

public class TotalPopulationValueObserver implements Observer {
    private double totalAgentsSize;

    public double getValue() {
        return totalAgentsSize;
    }

    @Override
    public void observe(Simulation simulation) {
        List<Agent> agents = getAgents(simulation);

        double totalAgentsSize = 0;
        for (Agent agent : agents) {
            totalAgentsSize += agent.getSize();
        }
        this.totalAgentsSize = totalAgentsSize; //TODO use streams

        SimulationStatistics.getIteration(simulation.getNumberOfIterations()).setTotalPopulationValue(totalAgentsSize);
    }

    private List<Agent> getAgents(Simulation simulation) {
        Population population = simulation.getPopulation();
        return population.getAgentsAsList(); // TODO refactor. Simulatio should return list of agents from population.
    }
}
