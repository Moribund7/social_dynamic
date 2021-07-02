package com.kowafi.socialDynamics;

public class AgentSelectorDummy implements AgentSelector {
    @Override
    public AgentPair selectAgentPair(Population population) {
        return new AgentPair(population.getAgentsAsList().get(0), population.getAgentsAsList().get(1));
    }
}
