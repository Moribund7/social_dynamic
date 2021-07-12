package com.kowafi.socialDynamics;

import com.kowafi.socialDynamics.payoffMatrix.PayoffMatrix;
import com.kowafi.socialDynamics.simulation.Strategy;

import java.util.Map;

import static com.kowafi.socialDynamics.simulation.Strategy.COOPERATIVE;
import static com.kowafi.socialDynamics.simulation.Strategy.NONCOOPERATIVE;

public class BaseSimulationTest {
    protected static final PayoffMatrix PAYOFF_MATRIX = new PayoffMatrix(Map.of(Strategy.COOPERATIVE, Map.of(Strategy.COOPERATIVE, 100, NONCOOPERATIVE, 0),
            NONCOOPERATIVE, Map.of(COOPERATIVE, 300, NONCOOPERATIVE, 50)));
    protected static final double DELTA = 0.001;
}
