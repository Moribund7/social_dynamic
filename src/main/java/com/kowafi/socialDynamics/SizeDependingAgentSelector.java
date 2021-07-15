package com.kowafi.socialDynamics;

import com.kowafi.socialDynamics.population.Population;
import com.kowafi.socialDynamics.population.agent.Agent;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.sampling.DiscreteProbabilityCollectionSampler;
import org.apache.commons.rng.simple.RandomSource;

import java.util.Map;
import java.util.stream.Collectors;


public class SizeDependingAgentSelector implements AgentSelector {
    @Override
    public AgentPair selectAgentPair(Population population) {
        UniformRandomProvider rng = RandomSource.create(RandomSource.MT); // TODO add as injection

        Map<Agent, Double> agentSizeMap = population.getAgentsReadOnly().stream().collect(Collectors.toMap((a) -> (a), Agent::getSize));

        DiscreteProbabilityCollectionSampler<Agent> discreteProbabilityCollectionSampler = new DiscreteProbabilityCollectionSampler<>(rng, agentSizeMap);


        Agent agent1 = discreteProbabilityCollectionSampler.sample();
        Agent agent2 = discreteProbabilityCollectionSampler.sample();
        while (agent1.equals(agent2)) {
            agent2 = discreteProbabilityCollectionSampler.sample();
        }
        return new AgentPair(agent1, agent2);

    }
}
