package com.kowafi.socialDynamics;

import com.kowafi.socialDynamics.payoffMatrix.PayoffMatrix;
import com.kowafi.socialDynamics.payoffMatrix.PayoffMatrixBuilder;
import com.kowafi.socialDynamics.population.PopulationBuilder;
import com.kowafi.socialDynamics.simulation.Strategy;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ConfigLoader {


    private SimulationBuilder simulationBuilder;

    /**
     * Reads given resource file as a string.
     *
     * @param fileName path to the resource file
     * @return the file's contents
     * @throws IOException if read fails for any reason
     */
    static String getResourceFileAsString(String fileName) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            if (is == null) return null;
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }

    public void loadConfigFromJson(String jsonFile) {

        String source;
        try {
            source = getResourceFileAsString(jsonFile);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Config file %s not found", jsonFile));
        }

        // TODO add config validation
        JSONObject configJson = new JSONObject(source);

        simulationBuilder = new SimulationBuilder();

        PopulationBuilder populationBuilder = getPopulationBuilder(configJson);
        AgentSelector agentSelector = getAgentSelector(configJson);
        AgentInteractionResolver agentInteractionResolver = getAgentInteractionResolver(configJson);

        simulationBuilder.withPopulationBuilder(populationBuilder);
        simulationBuilder.withAgentSelector(agentSelector);
        simulationBuilder.withAgentInteractionResolver(agentInteractionResolver);
    }

    private AgentInteractionResolver getAgentInteractionResolver(JSONObject configJson) {
        JSONObject payoffMatrixJsonObject = configJson.getJSONObject("agentInteractionResolver").getJSONObject("payoffMatrix");
        int CC = payoffMatrixJsonObject.getInt("CC");
        int CN = payoffMatrixJsonObject.getInt("CN");
        int NC = payoffMatrixJsonObject.getInt("NC");
        int NN = payoffMatrixJsonObject.getInt("NN");

        PayoffMatrixBuilder payoffMatrixBuilder = new PayoffMatrixBuilder();
        PayoffMatrix payoffMatrix = payoffMatrixBuilder.withCC(CC).withCN(CN).withNC(NC).withNN(NN).build();

        return new AgentInteractionResolver(payoffMatrix);

    }

    public SimulationBuilder getSimulationBuilder() {
        if (simulationBuilder == null) {
            throw new ExceptionInInitializerError("Configs not loaded yet.");
        } else return simulationBuilder;
    }

    private AgentSelector getAgentSelector(JSONObject configJson) {
        if (configJson.getJSONObject("agentSelector").getString("name").equals("SizeDepending")) {
            return new SizeDependingAgentSelector();
        } else throw new UnsupportedOperationException("Agent selector is not implemented"); //TODO change exception
    }

    private PopulationBuilder getPopulationBuilder(JSONObject configJson) {
        PopulationBuilder populationBuilder = new PopulationBuilder();
        JSONObject populationConfig = configJson.getJSONObject("population");
        JSONObject strategyConfig = configJson.getJSONObject("strategy");

        populationBuilder.withSize(populationConfig.getInt("size"));
        populationBuilder.withAgentSize(populationConfig.getInt("agentSize"));
        populationBuilder.withStrategyRatio(Strategy.fromString(strategyConfig.getString("firstStrategyName")), strategyConfig.getDouble("ratio"));
        populationBuilder.withSecondStrategy(Strategy.fromString(strategyConfig.getString("secondStrategyName")));
        return populationBuilder;
    }
}
