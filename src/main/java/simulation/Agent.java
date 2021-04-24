package simulation;

public class Agent {

    private final Strategy strategy;

    public Agent(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {

        return this.strategy;
    }
}
