package com.kowafi.socialDynamics.observers;

import com.kowafi.socialDynamics.Simulation;
import com.kowafi.socialDynamics.population.agent.Agent;
import com.kowafi.socialDynamics.simulation.Strategy;
import com.kowafi.socialDynamics.statistics.SimulationStatistics;

import java.util.Collection;
import java.util.List;

public class AgentsStrategiesCountObserver implements Observer {
    @Override
    public void observe(Simulation simulation) {
        Collection<Agent> agents = simulation.getAgentsReadOnly();
        List<Strategy> strategiesInSimulation = Simulation.getAvailableStrategies();

        for (Strategy strategy : strategiesInSimulation) {
            long agentsCount = agents.stream().filter((a) -> a.getStrategy().equals(strategy)).count();
            SimulationStatistics.getIteration(simulation.getNumberOfIterations()).setAgentsWithStrategyCount(strategy, agentsCount);

        }
    }

}
