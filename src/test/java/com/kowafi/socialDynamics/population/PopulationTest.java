package com.kowafi.socialDynamics.population;

import com.kowafi.socialDynamics.population.agent.Agent;
import com.kowafi.socialDynamics.population.agent.AgentBuilder;
import com.kowafi.socialDynamics.simulation.Strategy;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PopulationTest {


    @Test
    public void testPopulationGetAgent() {
        AgentBuilder agentBuilder = new AgentBuilder().withSize(10).withStrategy(Strategy.COOPERATIVE);
        Agent agent0 = agentBuilder.built();
        Agent agent1 = agentBuilder.built();

        List<Agent> agents = List.of(agent0, agent1);
        Population population = new Population(agents);

        Agent agent = population.getAgentWithId(0);
        assertEquals(agent0, agent);
        assertNotEquals(agent1, agent);

    }

    @Test
    public void testPopulationUpdateAgentSize() {
        Agent agent0 = new AgentBuilder().withSize(10).withStrategy(Strategy.COOPERATIVE).built();
        List<Agent> agents = List.of(agent0);
        assertEquals(10, agent0.getSize(), 0.001);
        Population population = new Population(agents);

        population.addToAgentSize(0, 50);
        assertEquals(10 + 50, agent0.getSize(), 0.001);


    }
}