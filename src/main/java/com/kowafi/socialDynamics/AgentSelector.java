package com.kowafi.socialDynamics;

import com.kowafi.socialDynamics.population.Population;

public interface AgentSelector {
        AgentPair selectAgentPair(Population population);
}
