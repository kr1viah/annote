package com.github.kusoroadeolu.annote.statements;

import java.util.HashMap;
import java.util.Map;

public record Scope(Map<String, Variable> variables, Scope parent) {

    public Scope newScope() {
        return new Scope(new HashMap<>(), this);
    }

    public Variable get(String name) {
        Variable v = variables.get(name);
        if (v != null) return v;
        if (parent != null) return parent.get(name);
        return null;
    }

    public void put(String name, Variable var) {
        variables.put(name, var);
    }
}