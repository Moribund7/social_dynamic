package com.kowafi.socialDynamics;

import com.kowafi.socialDynamics.population.Population;

public class AgentSelectorDummy implements AgentSelector {
    @Override
    public AgentPair selectAgentPair(Population population) {
        return new AgentPair(population.getAgentWithId(0), population.getAgentWithId(1));
    }
}
