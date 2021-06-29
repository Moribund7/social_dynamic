import simulation.Strategy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Simulation {
    public Population population;

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    private Simulation(Population population) {
        this.population = population;
    }

    public static Simulation initializeFromPropertiesFile(String fileName
    ) {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(fileName)) {
            props.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();//TODO add better exception
        }


        int populationSize = Integer.parseInt(props.getProperty("population.size"));
        double property = Double.parseDouble(props.getProperty("strategy.ratio"));
        PopulationBuilder populationBuilder = new PopulationBuilder().withSize(populationSize).withStrategyRatio(Strategy.fromString(props.getProperty("strategy")), property);

        return new Simulation(populationBuilder.build());

    }
}
