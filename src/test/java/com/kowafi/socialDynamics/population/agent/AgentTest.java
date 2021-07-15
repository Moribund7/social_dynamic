package com.kowafi.socialDynamics.population.agent;

import com.kowafi.socialDynamics.simulation.Strategy;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AgentTest {

    @Test
    public void agentWithSameStrategyDifferentSize() {
        Agent agent1 = new Agent(Strategy.COOPERATIVE, 1, 1);
        Agent agent2 = new Agent(Strategy.COOPERATIVE, 2, 2);

        assertNotEquals(agent1, agent2);
    }

    @Test
    public void agentWithDifferentStrategyDifferentSize() {
        Agent agent1 = new Agent(Strategy.NONCOOPERATIVE, 1, 1);
        Agent agent2 = new Agent(Strategy.COOPERATIVE, 2, 2);

        assertNotEquals(agent1, agent2);
    }

    @Test
    public void agentWithSameStrategySameSize() {
        Agent agent1 = new Agent(Strategy.COOPERATIVE, 1, 1);
        Agent agent2 = new Agent(Strategy.COOPERATIVE, 1, 2);

        assertNotEquals(agent1, agent2);
    }

    @Test(expected = IllegalStateException.class)
    public void agentWithDifferentStrategySameSizeSameIdShouldThrowException() {
        Agent agent1 = new Agent(Strategy.COOPERATIVE, 1, 1);
        Agent agent2 = new Agent(Strategy.NONCOOPERATIVE, 1, 1);

        assertNotEquals(agent1, agent2);
    }

    @Test(expected = IllegalStateException.class)
    public void agentWithSameStrategyDifferentSizeSameIdShouldThrowException() {
        Agent agent1 = new Agent(Strategy.COOPERATIVE, 1, 1);
        Agent agent2 = new Agent(Strategy.COOPERATIVE, 2, 1);

        assertNotEquals(agent1, agent2);
    }

    @Test
    public void testEqualsAgents() {
        Agent agent1 = new Agent(Strategy.COOPERATIVE, 1, 1);
        Agent agent2 = new Agent(Strategy.COOPERATIVE, 1, 1);

        assertEquals(agent1, agent2);
    }


}