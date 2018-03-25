package se.johannalynn.nosework.noseworktournament.model;

import java.util.Arrays;

public enum ResultType {
    TOURNAMENT, CONTEST, EVENT;

    public static ResultType getByType(final String type) {
        for(ResultType resultType : ResultType.values()) {
            if(resultType.name().equalsIgnoreCase(type)) {
                return resultType;
            }
        }
        return null;
    }
}
