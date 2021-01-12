package com.tecacet.movie.service.jpa.specification;

public class SearchCriteria {

    private final String key;
    private final Operation operation;
    private final Object value;
    public SearchCriteria(String key, Operation operation, Object value) {
        super();
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Operation getOperation() {
        return operation;
    }

    public Object getValue() {
        return value;
    }

    public enum Operation {
        EQUAL,
        LESS,
        GREATER,
        LIKE
    }

}
