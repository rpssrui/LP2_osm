package edu.ufp.inf.project;

import java.io.Serializable;

public class POICategory extends POI implements Serializable {
    private int idCategoria;
    private String categoria;

    public POICategory(String name, POICategory poiCategory, Location location, String categoria, int idCategoria) {
        super(name, location, poiCategory);
        this.idCategoria = idCategoria;
        this.categoria = categoria;
    }

    public POICategory(int id, String categoria) {
        super();
        this.idCategoria = id;
        this.categoria = categoria;
    }

    @Override
    public int getId() {
        return idCategoria;
    }

    @Override
    public void setId(int id) {
        this.idCategoria = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "POICategory{" +
                "id=" + idCategoria +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
