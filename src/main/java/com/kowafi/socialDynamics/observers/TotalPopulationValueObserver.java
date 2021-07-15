package com.kowafi.socialDynamics.observers;

import com.kowafi.socialDynamics.Simulation;
import com.kowafi.socialDynamics.population.agent.Agent;
import com.kowafi.socialDynamics.statistics.SimulationStatistics;

import java.util.Collection;

public class TotalPopulationValueObserver implements Observer {
    private double totalAgentsSize;

    public double getValue() {
        return totalAgentsSize;
    }

    @Override
    public void observe(Simulation simulation) {
        Collection<Agent> agents = simulation.getAgentsReadOnly();

        double totalAgentsSize = 0;
        for (Agent agent : agents) {
            totalAgentsSize += agent.getSize();
        }
        this.totalAgentsSize = totalAgentsSize; //TODO use streams

        SimulationStatistics.getIteration(simulation.getNumberOfIterations()).setTotalPopulationValue(totalAgentsSize);
    }


}
