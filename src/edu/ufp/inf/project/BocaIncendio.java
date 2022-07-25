package edu.ufp.inf.project;

import edu.princeton.cs.algs4.ST;

import java.io.Serializable;

public class BocaIncendio extends POICategory implements Serializable {
    private int id;
    private boolean manual;
    private boolean automatica;
    private String Local;
    private double Longitude;
    private double Latitude;
    private POICategory Category;

    public static ST<Integer, BocaIncendio> bocaIncendioST = new ST<>();

    public BocaIncendio(String name, POICategory poiCategory, Location location, String categoria, int idCategoria, int id, boolean manual, boolean automatica) {
        super(name, poiCategory, location, categoria, idCategoria);
        this.id = id;
        this.manual = manual;
        this.automatica = automatica;
    }


    public BocaIncendio(String name, POICategory poiCategory, Location location) {
        super(name, poiCategory, location, poiCategory.getCategoria(), poiCategory.getId());
        this.id = poisST.size() + 1;
        Local = location.getLocal();
        Longitude = location.getLongitude();
        Latitude = location.getLatitude();
        Category = poiCategory;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }

    public boolean isAutomatica() {
        return automatica;
    }

    public void setAutomatica(boolean automatica) {
        this.automatica = automatica;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public POICategory getCategory() {
        return Category;
    }

    public void setCategory(POICategory category) {
        Category = category;
    }

    @Override
    public String toString() {
        return "BocaIncendio{" +
                "id=" + id +
                ", manual=" + manual +
                ", automatica=" + automatica +
                '}';
    }

    /**
     * Retorna o ID livre mais baixo da ST de Bocas de incendio
     *
     * @return
     */
    private int uniqueID() {
        int id = 1;
        while (bocaIncendioST.contains(id)) {
            id++;
        }
        return id;
    }

    /**
     * Verifica que se já existe uma Boca de Incendio com o mesmo ID, caso exista retorna, caso não exista chama a idUnico
     * para verificar o ultimo Id disponivel e coloca o ID na bocaIncendioST
     */
    public void insertBocaIncendioST() {
        if (bocaIncendioST.contains(id)) {
            System.out.println("Boca de Incendio possui um ID que ja se econtra em uso");
            return;
        }
        if (id <= 0) {
            id = uniqueID();
        }
        bocaIncendioST.put(id, this);
    }

    /**
     * Imprime Todos as Bocas de Incendio presentes na ST de Bocas de Incendio
     */
    public void printBocaIcendioST() {
        System.out.println("\nBocaIncendio:");
        for (int i : bocaIncendioST.keys()) {
            System.out.println("ID: " + bocaIncendioST.get(i).id);
            if (bocaIncendioST.get(i).manual && !bocaIncendioST.get(i).automatica) {
                System.out.println("Manual");
            } else if (!bocaIncendioST.get(i).manual && bocaIncendioST.get(i).automatica) {
                System.out.println("Automatica");
            }
        }
    }
}
