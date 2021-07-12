package com.kowafi.socialDynamics.payoffMatrix;

import org.junit.Test;

import static com.kowafi.socialDynamics.simulation.Strategy.COOPERATIVE;
import static com.kowafi.socialDynamics.simulation.Strategy.NONCOOPERATIVE;
import static org.junit.Assert.assertEquals;

public class PayoffMatrixTest {

    @Test
    public void simplePayoffMatrixTest() {
        int cc = 1;
        int cn = 2;
        int nn = 3;
        int nc = 4;
        PayoffMatrixBuilder payoffMatrixBuilder = new PayoffMatrixBuilder();

        PayoffMatrix payoffMatrix = payoffMatrixBuilder.withCC(cc).withCN(cn).withNC(nc).withNN(nn).build();

        assertEquals((Integer) cc, payoffMatrix.get(COOPERATIVE).get(COOPERATIVE));
        assertEquals((Integer) cn, payoffMatrix.get(COOPERATIVE).get(NONCOOPERATIVE));
        assertEquals((Integer) nc, payoffMatrix.get(NONCOOPERATIVE).get(COOPERATIVE));
        assertEquals((Integer) nn, payoffMatrix.get(NONCOOPERATIVE).get(NONCOOPERATIVE));


    }

}