package com.kowafi.socialDynamics;

import com.kowafi.socialDynamics.exceptions.MissingSimulationArguments;
import com.kowafi.socialDynamics.simulation.Agent;
import com.kowafi.socialDynamics.simulation.AgentBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.kowafi.socialDynamics.simulation.Strategy.COOPERATIVE;
import static com.kowafi.socialDynamics.simulation.Strategy.NONCOOPERATIVE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class SimulationTest extends BaseSimulationTest {

    public static final int BASIC_POPULATION_SIZE = 10;
    public static final AgentBuilder AGENT_BUILDER = new AgentBuilder().withSize(0);
    private static final int BASIC_AGENT_SIZE = 10;
    private static final int STRATEGY_RATIO = 1;
    @Mock
    AgentSelector agentSelectorMock;
    @Mock
    PopulationBuilder populationBuilder;


    private static Simulation getBasicSimulation() {
        SimulationBuilder simulationBuilder = new SimulationBuilder();
        PopulationBuilder populationBuilder = new PopulationBuilder().
                withSize(BASIC_POPULATION_SIZE).
                withStrategyRatio(COOPERATIVE, STRATEGY_RATIO).
                withAgentSize(BASIC_AGENT_SIZE).
                withSecondStrategy(NONCOOPERATIVE);
        AgentInteractionResolver agentInteractionResolver = new AgentInteractionResolver(PAYOFF_MATRIX);
        AgentSelector agentSelector = new AgentSelectorDummy();
        simulationBuilder.withAgentInteractionResolver(agentInteractionResolver).withAgentSelector(agentSelector).withPopulationBuilder(populationBuilder);

        return simulationBuilder.build();
    }

    @Test
    public void test() {
        assertTrue(true);
    }

    @Test
    public void createAgent() {

        Agent a = AGENT_BUILDER.withStrategy(COOPERATIVE).built();

        assertEquals(COOPERATIVE, a.getStrategy());
    }

    @Test
    public void createPopulationWithTwoStrategies() {
        PopulationBuilder populationBuilder = new PopulationBuilder();

        double COOPERATIVE_RATIO = 0.5;
        Population population = populationBuilder.withSize(BASIC_POPULATION_SIZE).withStrategyRatio(COOPERATIVE, COOPERATIVE_RATIO).withSecondStrategy(NONCOOPERATIVE).build();

        assertEquals(BASIC_POPULATION_SIZE, population.getSize());
        int numberOfCooperativeAgents = (int) (BASIC_POPULATION_SIZE * COOPERATIVE_RATIO);
        assertEquals(numberOfCooperativeAgents, population.getAgentsNumberWithStrategy(COOPERATIVE));
        assertEquals(BASIC_POPULATION_SIZE - numberOfCooperativeAgents, population.getAgentsNumberWithStrategy(NONCOOPERATIVE));
    }

    @Test
    public void resolveStrategies() {
        Agent cooperative1 = AGENT_BUILDER.withStrategy(COOPERATIVE).built();
        Agent cooperative2 = AGENT_BUILDER.withStrategy(COOPERATIVE).built();
        AgentInteractionResolver agentInteractionResolver = new AgentInteractionResolver(PAYOFF_MATRIX);

        agentInteractionResolver.resolveInteraction(cooperative1, cooperative2);
        assertEquals(100, cooperative1.getSize(), DELTA);
        assertEquals(100, cooperative1.getSize(), DELTA);

    }

    @Test
    public void resolveStrategiesCooperativeVsNonCooperative() {
        Agent cooperative = AGENT_BUILDER.withStrategy(COOPERATIVE).built();
        Agent nonCooperative = AGENT_BUILDER.withStrategy(NONCOOPERATIVE).built();
        AgentInteractionResolver agentInteractionResolver = new AgentInteractionResolver(PAYOFF_MATRIX);

        agentInteractionResolver.resolveInteraction(cooperative, nonCooperative);
        assertEquals(0, cooperative.getSize(), DELTA);
        assertEquals(300, nonCooperative.getSize(), DELTA);

        //check if resolveInteraction is commutative
        agentInteractionResolver.resolveInteraction(nonCooperative, cooperative);
        assertEquals(0, cooperative.getSize(), DELTA);
        assertEquals(600, nonCooperative.getSize(), DELTA);
    }

    @Test
    public void resolveStrategiesNonCooperativeVsNonCooperative() {
        Agent nonCooperative1 = AGENT_BUILDER.withStrategy(NONCOOPERATIVE).built();
        Agent nonCooperative2 = AGENT_BUILDER.withStrategy(NONCOOPERATIVE).built();
        AgentInteractionResolver agentInteractionResolver = new AgentInteractionResolver(PAYOFF_MATRIX);

        agentInteractionResolver.resolveInteraction(nonCooperative1, nonCooperative2);
        assertEquals(50, nonCooperative1.getSize(), DELTA);
        assertEquals(50, nonCooperative2.getSize(), DELTA);

    }

    @Test
    public void runBasicSimulationIterationsCount() {
        Simulation simulation = getBasicSimulation();

        simulation.run(10);

        assertEquals(10, simulation.getNumberOfIterations());
    }


    @Test
    public void testSizeDependingAgentSelector() {
        AgentBuilder agentBuilder = new AgentBuilder();
        agentBuilder.withStrategy(COOPERATIVE);
        Agent agent1 = agentBuilder.withSize(10).built();
        Agent agent2 = agentBuilder.withSize(0).built();
        Agent agent3 = agentBuilder.withSize(10).built();
        List<Agent> possibleAgents = List.of(agent1, agent3);
        Population population = new Population(List.of(agent1, agent2, agent3));
        AgentSelector agentSelector = new SizeDependingAgentSelector();

        AgentPair agentPair = agentSelector.selectAgentPair(population);

        assertTrue(possibleAgents.contains(agentPair.getFirst()));
        assertTrue(possibleAgents.contains(agentPair.getSecond()));
    }

    @Test(expected = MissingSimulationArguments.class)
    public void testSimulationBuildWithoutAllArguments() {
        SimulationBuilder simulationBuilder = new SimulationBuilder();
        simulationBuilder.withAgentSelector(agentSelectorMock);
        simulationBuilder.withPopulationBuilder(populationBuilder);

        simulationBuilder.build();
    }
}