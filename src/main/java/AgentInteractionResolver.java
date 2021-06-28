import simulation.Agent;
import simulation.Strategy;

import java.util.Map;

public class AgentInteractionResolver {

    public static final Map<Strategy, Map<Strategy, Integer>> PAYOFF_MATRIX = Map.of(Strategy.COOPERATIVE, Map.of(Strategy.COOPERATIVE, 100));

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
