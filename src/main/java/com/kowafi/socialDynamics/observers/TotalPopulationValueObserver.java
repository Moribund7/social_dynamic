package com.kowafi.socialDynamics.observers;

import com.kowafi.socialDynamics.Simulation;
import com.kowafi.socialDynamics.population.agent.Agent;
import com.kowafi.socialDynamics.statistics.SimulationStatistics;

import java.util.Collection;

public class TotalPopulationValueObserver implements Observer {


    @Override
    public void observe(Simulation simulation) {
        Collection<Agent> agents = simulation.getAgentsReadOnly();

        double totalAgentsSize = agents.stream().mapToDouble(Agent::getSize).reduce(Double::sum).orElse(0);

        SimulationStatistics.getIteration(simulation.getNumberOfIterations()).setTotalPopulationValue(totalAgentsSize);
    }


}
