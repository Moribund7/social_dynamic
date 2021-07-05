package com.kowafi.socialDynamics;

import com.kowafi.socialDynamics.simulation.Agent;

public class AgentPair {
    private final Agent agent1;
    private final Agent agent2;

    public AgentPair(Agent agent1, Agent agent2) {
        this.agent1 = agent1;
        this.agent2 = agent2;
    }

    public Agent getSecond() {
        return agent2;
    }

    public Agent getFirst() {
        return agent1;
    }
}
