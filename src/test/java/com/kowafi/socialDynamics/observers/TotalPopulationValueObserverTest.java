package com.kowafi.socialDynamics.observers;

import com.kowafi.socialDynamics.Population;
import com.kowafi.socialDynamics.Simulation;
import com.kowafi.socialDynamics.simulation.Agent;
import com.kowafi.socialDynamics.simulation.AgentBuilder;
import com.kowafi.socialDynamics.statistics.IterationStatistics;
import com.kowafi.socialDynamics.statistics.SimulationStatistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.kowafi.socialDynamics.simulation.Strategy.COOPERATIVE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TotalPopulationValueObserverTest {
    private static final double DELTA = 0.001;
    @Mock
    Simulation simulationMock;
    @Mock
    Population populationMock;

    @Test
    public void testBasicObserver() {
        Observer observer = new TotalPopulationValueObserver();
        when(simulationMock.getPopulation()).thenReturn(populationMock);
        when(simulationMock.getNumberOfIterations()).thenReturn(1);
        AgentBuilder agentBuilder = new AgentBuilder();
        agentBuilder.withSize(10);
        agentBuilder.withStrategy(COOPERATIVE);

        List<Agent> agentList = List.of(agentBuilder.built(), agentBuilder.built(), agentBuilder.built());
        when(populationMock.getAgentsAsList()).thenReturn(agentList);

        observer.observe(simulationMock);

        IterationStatistics iterationStatistics = SimulationStatistics.getIteration(1);
        assertEquals(30, iterationStatistics.getTotalPopulationValue(), DELTA);
    }


}