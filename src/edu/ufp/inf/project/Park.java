package edu.ufp.inf.project;

import edu.princeton.cs.algs4.ST;

import java.io.Serializable;

public class Park extends POICategory implements Serializable {
    private int id;
    private String parkName;
    private String Local;
    private double Longitude;
    private double Latitude;
    private POICategory Category;

    public static ST<Integer, Park> parksST = new ST<>();

    public Park(String name, POICategory poiCategory, Location location, String categoria, int idCategoria, int id) {
        super(name, poiCategory, location, categoria, idCategoria);
        this.id = id;
        this.parkName = name;
    }

    public Park(String name, POICategory poiCategory, Location location) {
        super(name, poiCategory, location, poiCategory.getCategoria(), poiCategory.getId());
        this.id = poisST.size() + 1;
        Local = location.getLocal();
        Longitude = location.getLongitude();
        Latitude = location.getLatitude();
        Category = poiCategory;
    }

    /**
     * Retorna o ID livre mais baixo da ST de Parks
     *
     * @return
     */
    private int uniqueID() {
        int id = 1;
        while (parksST.contains(id)) {
            id++;
        }
        return id;
    }

    /**
     * Verifica que se já existe um Park com o mesmo ID, caso exista retorna, caso não exista chama a idUnico
     * para verificar o ultimo Id disponivel e coloca o ID na parksST
     */
    public void insertParkST() {
        if (parksST.contains(id)) {
            System.out.println("Park com este id ja se encontra em uso");
            return;
        }
        if (id <= 0) {
            id = uniqueID();
        }
        parksST.put(id, this);
    }

    /**
     * Imprime Todos os parks presentes na ST de Parks
     */
    public void printParkST() {
        System.out.println("\nPark:");
        for (int i : parksST.keys()) {
            System.out.println("ID: " + parksST.get(i).id);
            System.out.println("Name: " + parksST.get(i).parkName);
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
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
        return "Park{" +
                "id=" + id +
                ", parkName='" + parkName + '\'' +
                '}';
    }
}

