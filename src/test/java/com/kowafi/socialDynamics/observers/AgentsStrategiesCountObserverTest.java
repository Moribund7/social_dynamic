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
import static com.kowafi.socialDynamics.simulation.Strategy.NONCOOPERATIVE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AgentsStrategiesCountObserverTest {
    @Mock
    Simulation simulationMock;
    @Mock
    Population populationMock;

    @Test
    public void testObserver() {
        Observer observer = new AgentsStrategiesCountObserver();
        when(simulationMock.getPopulation()).thenReturn(populationMock);
        when(simulationMock.getNumberOfIterations()).thenReturn(1);
        AgentBuilder cooperativeAgentBuilder = new AgentBuilder();
        cooperativeAgentBuilder.withSize(10);
        cooperativeAgentBuilder.withStrategy(COOPERATIVE);

        AgentBuilder noncooperativeAgentBuilder = new AgentBuilder();
        noncooperativeAgentBuilder.withSize(10);
        noncooperativeAgentBuilder.withStrategy(NONCOOPERATIVE);


        List<Agent> agentList = List.of(
                cooperativeAgentBuilder.built(),
                cooperativeAgentBuilder.built(),
                cooperativeAgentBuilder.built(),
                noncooperativeAgentBuilder.built(),
                noncooperativeAgentBuilder.built());
        when(populationMock.getAgentsAsList()).thenReturn(agentList);

        observer.observe(simulationMock);

        IterationStatistics iterationStatistics = SimulationStatistics.getIteration(1);
        assertEquals(Long.valueOf(3), iterationStatistics.getAgentsWithStrategyCount().get(COOPERATIVE));
        assertEquals(Long.valueOf(2), iterationStatistics.getAgentsWithStrategyCount().get(NONCOOPERATIVE));
    }


}