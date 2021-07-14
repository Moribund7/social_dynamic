package com.kowafi.socialDynamics.statistics;

import com.kowafi.socialDynamics.simulation.Strategy;

import java.util.HashMap;
import java.util.Map;

public class IterationStatistics {
    private final Map<Strategy, Long> agentsWithStrategyCount;
    private double totalPopulationValue;

    public IterationStatistics() {
        agentsWithStrategyCount = new HashMap<>();
    }

    public Map<Strategy, Long> getAgentsWithStrategyCount() {
        return agentsWithStrategyCount;
    }

    public void setAgentsWithStrategyCount(Strategy strategy, long agentsCount) {
        this.agentsWithStrategyCount.put(strategy, agentsCount);
    }

    public double getTotalPopulationValue() {
        return totalPopulationValue;
    }

    public void setTotalPopulationValue(double totalPopulationValue) {
        this.totalPopulationValue = totalPopulationValue;
    }
}
