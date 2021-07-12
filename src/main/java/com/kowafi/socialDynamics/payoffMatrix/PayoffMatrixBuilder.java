package com.kowafi.socialDynamics.payoffMatrix;

import com.kowafi.socialDynamics.simulation.Strategy;

import java.util.HashMap;
import java.util.Map;

import static com.kowafi.socialDynamics.simulation.Strategy.COOPERATIVE;
import static com.kowafi.socialDynamics.simulation.Strategy.NONCOOPERATIVE;

public class PayoffMatrixBuilder {

    Map<Strategy, Map<Strategy, Integer>> map;

    public PayoffMatrixBuilder() {
        this.map = Map.of(COOPERATIVE, new HashMap<>(), NONCOOPERATIVE, new HashMap<>());
    }

    public PayoffMatrixBuilder withCC(int cc) {
        map.get(COOPERATIVE).put(COOPERATIVE, cc);
        return this;
    }

    public PayoffMatrixBuilder withCN(int cn) {
        map.get(COOPERATIVE).put(NONCOOPERATIVE, cn);
        return this;
    }

    public PayoffMatrixBuilder withNC(int nc) {
        map.get(NONCOOPERATIVE).put(COOPERATIVE, nc);
        return this;
    }

    public PayoffMatrixBuilder withNN(int nn) {
        map.get(NONCOOPERATIVE).put(NONCOOPERATIVE, nn);
        return this;
    }

    public PayoffMatrix build() {
        return new PayoffMatrix(map);
    }
}
