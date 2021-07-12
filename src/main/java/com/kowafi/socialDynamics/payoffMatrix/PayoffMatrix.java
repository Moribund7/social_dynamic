package com.kowafi.socialDynamics.payoffMatrix;

import com.kowafi.socialDynamics.simulation.Strategy;

import java.util.Map;

public class PayoffMatrix {
    Map<Strategy, Map<Strategy, Integer>> map;

    public PayoffMatrix(Map<Strategy, Map<Strategy, Integer>> map) {
        this.map = map;
    }

    public Map<Strategy, Integer> get(Strategy strategy) {
        return map.get(strategy);
    }
}
