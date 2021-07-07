package com.kowafi.socialDynamics.observers;

import com.kowafi.socialDynamics.Population;
import com.kowafi.socialDynamics.Simulation;
import com.kowafi.socialDynamics.SimulationStatistics;
import com.kowafi.socialDynamics.simulation.Agent;

import java.util.List;

public class TotalPopulationValueObserver implements Observer {
    private double totalAgentsSize;

    public double getValue() {
        return totalAgentsSize;
    }

    @Override
    public void observe(Simulation simulation) {
        Population population = simulation.getPopulation();
        List<Agent> agents = population.getAgentsAsList();

        double totalAgentsSize = 0;
        for (Agent agent : agents) {
            totalAgentsSize += agent.getSize();
        }
        this.totalAgentsSize = totalAgentsSize; //TODO use streams

        SimulationStatistics.getIteration(simulation.getNumberOfIterations()).setTotalPopulationValue(totalAgentsSize);
    }
}
