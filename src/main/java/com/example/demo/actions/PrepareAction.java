package com.example.demo.actions;

import com.example.demo.annotations.MappedAt;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PrepareAction {

    private Map<String,Action> map;

    private Map<String,Action> ansverMap = new HashMap<>();


    @Autowired
    public PrepareAction(Map<String,Action> actionMap) {

        this.map = actionMap;

    }

    @PostConstruct
    public void init(){
        map.values().forEach(e-> ansverMap.put(e.getClass().getAnnotation(MappedAt.class).mapping(),e));
    }

    public Map<String, Action> prepareAnsverMap(){
        return ansverMap;
    }
}
