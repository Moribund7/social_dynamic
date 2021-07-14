package com.kowafi.socialDynamics.statistics;

import java.util.ArrayList;
import java.util.List;

public class SimulationStatistics {
    private static final List<IterationStatistics> iterationsStatistics = new ArrayList<>();

    public static IterationStatistics getIteration(int numberOfIterations) {
        if (iterationsStatistics.size() < numberOfIterations) {
            iterationsStatistics.add(new IterationStatistics());
        }
        return iterationsStatistics.get(numberOfIterations - 1);
    }

    public static int getNumberOfIterations() {
        return iterationsStatistics.size();
    }
}
