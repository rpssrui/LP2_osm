package edu.ufp.inf.project;

import edu.princeton.cs.algs4.ST;

import java.io.Serializable;

public class Restauracao extends POICategory implements Serializable {
    private int idRestaurante;
    private boolean veganOption;
    private boolean vegetarianOptions;

    private String Local;
    private double Longitude;
    private double Latitude;
    private POICategory Category;

    public static ST<Integer, Restauracao> restauranteST = new ST<>();

    public Restauracao(String name, POICategory poiCategory, Location location, String categoria, int idCategoria, int id, boolean veganOption, boolean vegetarianOptions) {
        super(name, poiCategory, location, categoria, idCategoria);
        this.idRestaurante = id;
        this.veganOption = veganOption;
        this.vegetarianOptions = vegetarianOptions;
    }

    public Restauracao(int id, String categoria, int idRestaurante, boolean veganOption, boolean vegetarianOptions) {
        super(id, categoria);
        this.idRestaurante = idRestaurante;
        this.veganOption = veganOption;
        this.vegetarianOptions = vegetarianOptions;
    }

    public Restauracao(String name, POICategory poiCategory, Location location) {
        super(name, poiCategory, location, poiCategory.getCategoria(), poiCategory.getId());
        this.idRestaurante = poisST.size() + 1;
        this.Local = location.getLocal();
        this.Longitude = location.getLongitude();
        this.Latitude = location.getLatitude();
        this.Category = poiCategory;
    }

    /**
     * Retorna o ID livre mais baixo da ST de restaurantes
     *
     * @return
     */
    private int uniqueID() {
        int id = 1;
        while (restauranteST.contains(id)) {
            id++;
        }
        return id;
    }

    /**
     * Verifica que se já existe um Restaurante com o mesmo ID, caso exista retorna, caso não exista chama a idUnico
     * para verificar o ultimo Id disponivel e coloca o Restaurante na restauranteST
     */
    public void insertRestaurantesST() {
        if (restauranteST.contains(idRestaurante)) {
            System.out.println("Restaurante possui um ID que ja se econtra em uso");
            return;
        }
        if (idRestaurante <= 0) {
            idRestaurante = uniqueID();
        }
        restauranteST.put(idRestaurante, this);
    }

    /**
     * Imprime Todos os Restaurantes presentes na ST de Restauracao
     */
    public void printrestauranteST() {
        System.out.println("\nRestaurante:");
        for (int i : restauranteST.keys()) {
            System.out.println("ID: " + restauranteST.get(i).idRestaurante);
            System.out.println("Nome: " + restauranteST.get(i).getName());
            if (restauranteST.get(i).hasVeganOption())
                System.out.println("Contém opcao vegan.");

            if (restauranteST.get(i).hasVegetarianOptions())
                System.out.println("Contém opcao vegetariana.");

            if (!restauranteST.get(i).hasVegetarianOptions() && !restauranteST.get(i).hasVeganOption())
                System.out.println("Restaurante sem opcao vegan ou vegetariana");
        }
    }


    @Override
    public int getId() {
        return idRestaurante;
    }

    @Override
    public void setId(int id) {
        this.idRestaurante = id;
    }

    public boolean hasVeganOption() {
        return veganOption;
    }

    public void setVeganOption(boolean veganOption) {
        this.veganOption = veganOption;
    }

    public boolean hasVegetarianOptions() {
        return vegetarianOptions;
    }

    public void setVegetarianOptions(boolean vegetarianOptions) {
        this.vegetarianOptions = vegetarianOptions;
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
        return "Restauracao{" +
                "id=" + idRestaurante +
                ", veganOption=" + veganOption +
                ", vegetarianOptions=" + vegetarianOptions +
                '}';
    }
}
