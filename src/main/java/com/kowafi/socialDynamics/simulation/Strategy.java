package com.kowafi.socialDynamics.simulation;

public enum Strategy {
    COOPERATIVE,
    NONCOOPERATIVE;

    public static Strategy fromString(String strategyString) {
        switch (strategyString) {
            case "cooperative":
                return COOPERATIVE;
            case "noncooperative":
                return NONCOOPERATIVE;

        }
        throw new RuntimeException("Wrong strategy name"); //TODO add better exception
    }
}
