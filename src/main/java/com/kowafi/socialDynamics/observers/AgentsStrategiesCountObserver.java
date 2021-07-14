package com.kowafi.socialDynamics.observers;

import com.kowafi.socialDynamics.Population;
import com.kowafi.socialDynamics.Simulation;
import com.kowafi.socialDynamics.simulation.Agent;
import com.kowafi.socialDynamics.simulation.Strategy;
import com.kowafi.socialDynamics.statistics.SimulationStatistics;

import java.util.List;

public class AgentsStrategiesCountObserver implements Observer {
    @Override
    public void observe(Simulation simulation) {
        List<Agent> agents = getAgents(simulation);
        List<Strategy> strategiesInSimulation = getAvaliableStrategies();

        for (Strategy strategy : strategiesInSimulation) {
            long agentsCount = agents.stream().filter((a) -> a.getStrategy().equals(strategy)).count();
            SimulationStatistics.getIteration(simulation.getNumberOfIterations()).setAgentsWithStrategyCount(strategy, agentsCount);

        }
    }

    private List<Strategy> getAvaliableStrategies() {
        return List.of(Strategy.COOPERATIVE, Strategy.NONCOOPERATIVE); // TODO this should be in simulation
    }

    private List<Agent> getAgents(Simulation simulation) {
        Population population = simulation.getPopulation();
        return population.getAgentsAsList();
    }
}
