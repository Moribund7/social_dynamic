import simulation.Agent;
import simulation.AgentBuilder;
import simulation.Strategy;

import java.util.Collection;
import java.util.HashSet;

public class PopulationBuilder {

    private final Collection<Agent> population = new HashSet<>();
    private int size;
    private Strategy strategy;
    private double ratio;
    private Strategy secondStrategy;
    private final AgentBuilder agentBuilder = new AgentBuilder().withSize(10);

    public PopulationBuilder() {
    }

    public PopulationBuilder withSize(int size) {
        this.size = size;
        return this;
    }

    public PopulationBuilder withStrategyRatio(Strategy strategy, double ratio) {
        this.strategy = strategy;
        this.ratio = ratio;
        return this;
    }


    public Population build() {
        double numberOfAgentWithStrategy = Math.floor(size * ratio);
        for (int i = 0; i < size; i++) {
            if (i < numberOfAgentWithStrategy) {
                population.add(agentBuilder.withStrategy(strategy).built());
            } else {
                population.add(agentBuilder.withStrategy(secondStrategy).built());
            }

        }

        return new Population(population);
    }

    public PopulationBuilder withSecondStrategy(Strategy secondStrategy) {
        this.secondStrategy = secondStrategy;
        return this;
    }
}
