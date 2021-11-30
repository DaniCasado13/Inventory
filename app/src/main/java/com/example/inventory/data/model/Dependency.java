package com.example.inventory.data.model;

public class Dependency {
    private String name;
    private String shortName;
    private String description;
    private String imagen;

    public Dependency(String name, String shortName, String description, String imagen) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.imagen = imagen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
