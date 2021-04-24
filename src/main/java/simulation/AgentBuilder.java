package simulation;

public class AgentBuilder {

    private Strategy strategy;

    public AgentBuilder() {
    }


    public AgentBuilder withStrategy(Strategy strategy) {
        this.strategy = strategy;
        return this;
    }


    public Agent built() {
        return new Agent(strategy);
    }
}
