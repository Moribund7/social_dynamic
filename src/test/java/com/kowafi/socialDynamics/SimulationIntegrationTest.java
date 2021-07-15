package com.kowafi.socialDynamics;

import com.kowafi.socialDynamics.population.Population;
import com.kowafi.socialDynamics.population.agent.Agent;
import org.junit.Test;

import static com.kowafi.socialDynamics.simulation.Strategy.COOPERATIVE;
import static org.junit.Assert.assertEquals;

public class SimulationIntegrationTest extends BaseSimulationTest {

    private static final int POPULATION_SIZE = 10;
    private static final int POPULATION_COOPERATIVE_AGENTS_SIZE = 5;
    private static final int AGENT_SIZE = 10;
    private static final int FIXED_PAYOFF = 100;

    @Test
    public void simulationBasicIntegrationTest() {
        ConfigLoader configLoader = new ConfigLoader();
        configLoader.loadConfigFromJson("basicTestSimulationParameters.json");

        SimulationBuilder simulationBuilder = configLoader.getSimulationBuilder();
        Simulation simulation = simulationBuilder.build();

        int numberOfIterations = 10;

        Population population = simulation.getPopulation();
        assertEquals(POPULATION_SIZE, population.getSize());
        assertEquals(POPULATION_COOPERATIVE_AGENTS_SIZE, population.getAgentsNumberWithStrategy(COOPERATIVE));
        assertEquals(AGENT_SIZE * POPULATION_SIZE, getActualCumulativeSizeOfPopulation(population), DELTA);

        simulation.run(numberOfIterations);

        population = simulation.getPopulation();
        assertEquals(POPULATION_SIZE, population.getSize());
        assertEquals(POPULATION_COOPERATIVE_AGENTS_SIZE, population.getAgentsNumberWithStrategy(COOPERATIVE));
        assertEquals(getExpectedCumulativeSizeOfPopulation(numberOfIterations), getActualCumulativeSizeOfPopulation(population), DELTA);
    }

    private Double getActualCumulativeSizeOfPopulation(Population population) {
        return population.getAgentsReadOnly().stream().map(Agent::getSize).reduce(Double::sum).orElse(-42.0); // wrong value
    }


    private int getExpectedCumulativeSizeOfPopulation(int numberOfIterations) {
        return AGENT_SIZE * POPULATION_SIZE + 2 * numberOfIterations * FIXED_PAYOFF;
    }
}