import simulation.Agent;
import simulation.Strategy;

import java.util.Map;

public class AgentInteractionResolver {

    public final Map<Strategy, Map<Strategy, Integer>> PAYOFF_MATRIX;

    public AgentInteractionResolver(Map<Strategy, Map<Strategy, Integer>> payoff_matrix) {
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
