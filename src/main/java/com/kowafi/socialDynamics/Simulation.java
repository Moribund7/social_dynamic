package com.kowafi.socialDynamics;

import com.kowafi.socialDynamics.observers.Observer;
import com.kowafi.socialDynamics.simulation.Strategy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

public class Simulation {
    private final Collection<Observer> observers = Collections.emptyList();
    public Population population;
    private int numberOfIterations = 0;

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    private Simulation(Population population) {
        this.population = population;
    }

    public static Simulation initializeFromPropertiesFile(String fileName) {

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

    public int getNumberOfIterations() {
        return numberOfIterations;
    }

    public Population getPopulation() {
        return population;
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void run(int numberOfIterations) {
        for (int iteration = 0; iteration < numberOfIterations; iteration++) {
            this.numberOfIterations += 1;

            //TODO add body to resolve interactions


            for (Observer observer : observers) {
                observer.observe(this);
            }
        }
    }
}
