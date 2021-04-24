import simulation.Agent;
import simulation.Strategy;

import java.util.Collection;

public class Population {

    private final Collection<Agent> population;


    public Population(Collection<Agent> population) {
        this.population = population;
    }


    public int getSize() {
        return population.size();
    }

    public long getAgentsNumberWithStrategy(Strategy strategy) {
        return population.stream().filter(agent -> agent.getStrategy().equals(strategy)).count();
    }
}
