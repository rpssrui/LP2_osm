package edu.ufp.inf.project;

import edu.princeton.cs.algs4.ST;

import java.io.Serializable;

public class Semaforo extends POICategory implements Serializable {
    private int id;
    private boolean automoveis;
    private boolean peoes;
    private String Local;
    private double Longitude;
    private double Latitude;
    private POICategory Category;

    public static ST<Integer, Semaforo> semaforosST = new ST<>();

    public Semaforo(String name, POICategory poiCategory, Location location, String categoria, int idCategoria, int id, boolean automoveis, boolean peoes) {
        super(name, poiCategory, location, categoria, idCategoria);
        this.id = id;
        this.automoveis = automoveis;
        this.peoes = peoes;
    }

    public Semaforo(String name, POICategory poiCategory, Location location) {
        super(name, poiCategory, location, poiCategory.getCategoria(), poiCategory.getId());
        this.id = poisST.size() + 1;
        Local = location.getLocal();
        ;
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

    public boolean isAutomoveis() {
        return automoveis;
    }

    public void setAutomoveis(boolean automoveis) {
        this.automoveis = automoveis;
    }

    public boolean isPeoes() {
        return peoes;
    }

    public void setPeoes(boolean peoes) {
        this.peoes = peoes;
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
        return "Semaforo{" +
                "id=" + id +
                ", automoveis=" + automoveis +
                ", peoes=" + peoes +
                '}';
    }

    /**
     * Retorna o último ID disponível
     *
     * @return id
     */
    private int uniqueID() {
        int id = 1;
        while (semaforosST.contains(id)) {
            id++;
        }
        return id;
    }

    /**
     * Verifica que se já existe um Semaforo com o mesmo ID, caso exista retorna, caso não exista chama a idUnico
     * para verificar o ultimo Id disponivel e coloca o ID na semaforosST
     */
    public void insertSemaforoST() {
        if (semaforosST.contains(id)) {
            System.out.println("Semaforo possui um ID que ja se econtra em uso");
            return;
        }
        if (id <= 0) {
            id = uniqueID();
        }
        semaforosST.put(id, this);
    }

    /**
     * Imprime Todos os Semaforos presentes na ST de Semaforos
     */
    public void imprimirSemaforoST() {
        System.out.println("\nSemaforos:");
        for (int i : semaforosST.keys()) {
            System.out.println("ID: " + semaforosST.get(i).id);
            if (semaforosST.get(i).automoveis && !semaforosST.get(i).peoes) {
                System.out.println("Automovel");
            } else if (!semaforosST.get(i).automoveis && semaforosST.get(i).peoes) {
                System.out.println("Peoes");
            }
        }
    }
}