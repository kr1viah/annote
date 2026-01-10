package com.github.kusoroadeolu.annote.statements;

import com.github.kusoroadeolu.annote.exception.AnnoteException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public record Scope(Map<String, Variable> variables, Scope parent) {

    public Scope newScope() {
        return new Scope(new HashMap<>(), this);
    }

    public Variable get(String name) {
        Variable v = variables.get(name);
        if (v != null) return v;
        else if (parent != null) return parent.get(name);
        else return null;

    }

    public void put(String name, Variable var) {
        variables.put(name, var);
    }

    //There's probably a more efficient way to do this lol
    public Set<String> keySet(){
        Scope scope = this;
        HashMap<String, Variable> map = new HashMap<>();
        while (scope != null){
            map.putAll(scope.variables);
            scope = scope.parent;
        }
        return map.keySet();
    }
}