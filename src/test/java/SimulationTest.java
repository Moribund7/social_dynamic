import org.junit.Test;
import simulation.Agent;
import simulation.AgentBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static simulation.Strategy.COOPERATIVE;
import static simulation.Strategy.NONCOOPERATIVE;

public class SimulationTest {

    public static final int POPULATION_SIZE = 10;

    @Test
    public void test() {
        assertTrue(true);
    }

    @Test
    public void createAgent() {
        AgentBuilder agentBuilder = new AgentBuilder();

        Agent a = agentBuilder.withStrategy(COOPERATIVE).built();

        assertEquals(COOPERATIVE, a.getStrategy());
    }

    @Test
    public void createPopulationWithOneStrategy() {
        PopulationBuilder populationBuilder = new PopulationBuilder();

        int STRATEGY_RATIO = 1;
        Population population = populationBuilder.withSize(POPULATION_SIZE).withStrategyRatio(COOPERATIVE, STRATEGY_RATIO).build();

        assertEquals(POPULATION_SIZE, population.getSize());
        int numberOfCooperativeAgents = POPULATION_SIZE * STRATEGY_RATIO;
        assertEquals(numberOfCooperativeAgents, population.getAgentsNumberWithStrategy(COOPERATIVE));
    }

    @Test
    public void createPopulationWithTwoStrategies() {
        PopulationBuilder populationBuilder = new PopulationBuilder();

        double COOPERATIVE_RATIO = 0.5;
        Population population = populationBuilder.withSize(POPULATION_SIZE).withStrategyRatio(COOPERATIVE, COOPERATIVE_RATIO).withSecondStrategy(NONCOOPERATIVE).build();

        assertEquals(POPULATION_SIZE, population.getSize());
        int numberOfCooperativeAgents = (int) (POPULATION_SIZE * COOPERATIVE_RATIO);
        assertEquals(numberOfCooperativeAgents, population.getAgentsNumberWithStrategy(COOPERATIVE));


    }
}