package com.unicar.profile.domain.model;

import java.util.HashMap;
import java.util.Map;

public class Car {
    private String model;
    private String plate;
    private String color;

    public Car(String name, String plate, String color) {
        this.model = name;
        this.plate = plate;
        this.color = color;
    }

    public Car() {}

    public String getModel() {
        return model;
    }

    public String getPlate() {
        return plate;
    }

    public String getColor() {
        return color;
    }

    public Map<String, Object> toJson() {
        final Map<String, Object> json = new HashMap<>();
        if (model != null) json.put("model", model);
        if (plate != null) json.put("plate", plate);
        if (color != null) json.put("color", color);
        return json;
    }
}
