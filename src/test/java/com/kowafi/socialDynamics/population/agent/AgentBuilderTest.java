package com.kowafi.socialDynamics.population.agent;

import com.kowafi.socialDynamics.simulation.Strategy;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AgentBuilderTest {

    @Test
    public void test() {
        AgentBuilder agentBuilder = new AgentBuilder();
        agentBuilder.withStrategy(Strategy.COOPERATIVE).withSize(10);

        Agent agent1 = agentBuilder.built();
        Agent agent2 = agentBuilder.built();

        assertEquals(10, agent1.getSize(), 0.01);
        assertEquals(10, agent2.getSize(), 0.01);
        assertEquals(Strategy.COOPERATIVE, agent1.getStrategy());
        assertEquals(Strategy.COOPERATIVE, agent2.getStrategy());
        assertEquals(0, agent1.getId());
        assertEquals(1, agent2.getId());


    }

}