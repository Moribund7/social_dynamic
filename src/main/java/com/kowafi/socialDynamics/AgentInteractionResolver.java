package com.kowafi.socialDynamics;

import com.kowafi.socialDynamics.payoffMatrix.PayoffMatrix;
import com.kowafi.socialDynamics.population.agent.Agent;

public class AgentInteractionResolver {

    public final PayoffMatrix PAYOFF_MATRIX;

    public AgentInteractionResolver(PayoffMatrix payoff_matrix) {
        PAYOFF_MATRIX = payoff_matrix;
    }


    public void resolveInteraction(Agent agent1, Agent agent2) {

        int firstAgentPayoff = getFirstAgentPayoff(agent1, agent2);

        int secondAgentPayoff = getFirstAgentPayoff(agent2, agent1);

        agent1.addToSize(firstAgentPayoff);
        agent2.addToSize(secondAgentPayoff);
    }

    private int getFirstAgentPayoff(Agent agent1, Agent agent2) {
        return PAYOFF_MATRIX.get(agent1.getStrategy()).get(agent2.getStrategy());
    }
}
