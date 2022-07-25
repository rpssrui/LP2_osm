package edu.ufp.inf.project;

import com.sun.management.GcInfo;
import edu.princeton.cs.algs4.ST;

import java.io.Serializable;

public class Hotelaria extends POICategory implements Serializable {
    private int id;
    private boolean Hotel;
    private boolean Hostel;
    private boolean Motel;


    //atributos jfx
    private String Local;
    private double Longitude;
    private double Latitude;
    private POICategory Category;

    public static ST<Integer, Hotelaria> HotelariaST = new ST<>();

    public Hotelaria(User user, String name, POICategory poiCategory, Location location, String categoria, int idCategoria, int id, boolean isHotel, boolean isHostel, boolean isMotel) {
        super(name, poiCategory, location, categoria, idCategoria);
        this.id = id;
        this.Hotel = isHotel;
        this.Hostel = isHostel;
        this.Motel = isMotel;
    }

    public Hotelaria(String name, POICategory poiCategory, Location location, String categoria, int idCategoria, int id) {
        super(name, poiCategory, location, categoria, idCategoria);
        this.id = id;
    }

    public Hotelaria(String name, POICategory poiCategory, Location location) {
        super(name, poiCategory, location, poiCategory.getCategoria(), poiCategory.getId());
        this.Local = location.getLocal();
        this.id = poisST.size() + 1;
        this.Longitude = location.getLongitude();
        this.Latitude = location.getLatitude();
        this.Category = poiCategory;
    }

    /**
     * Retorna o ID livre mais baixo da ST de Hotelaria
     *
     * @return
     */
    private int uniqueID() {
        int id = 1;
        while (HotelariaST.contains(id)) {
            id++;
        }
        return id;
    }

    /**
     * Verifica que se já existe um Alojamento com o mesmo ID, caso exista retorna, caso não exista chama a idUnico
     * para verificar o ultimo Id disponivel e coloca o Alojamento na HotelariaST
     */
    public void insertHotelST() {
        if (HotelariaST.contains(id)) {
            System.out.println("Hotel possui um ID que ja se econtra em uso");
            return;
        }
        if (id <= 0) {
            id = uniqueID();
        }
        HotelariaST.put(id, this);
    }

    /**
     * Imprime Todos os alojamentos presentes na ST de Hotelaria
     */
    public void printHotelariaST() {
        System.out.println("\nHotelaria:");
        for (int i : HotelariaST.keys()) {
            System.out.println("ID: " + HotelariaST.get(i).id);
            System.out.println("Nome: " + HotelariaST.get(i).getName());
            if (HotelariaST.get(i).isHotel())
                System.out.println("Alojamento do tipo Hotel.");

            if (HotelariaST.get(i).isHostel())
                System.out.println("Alojamento do tipo Hostel.");

            if (HotelariaST.get(i).isMotel())
                System.out.println("Alojamento do tipo Motel.");
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

    public boolean isHotel() {
        return Hotel;
    }

    public void setHotel(boolean hotel) {
        Hotel = hotel;
    }

    public boolean isHostel() {
        return Hostel;
    }

    public void setHostel(boolean hostel) {
        Hostel = hostel;
    }

    public boolean isMotel() {
        return Motel;
    }

    public void setMotel(boolean motel) {
        Motel = motel;
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
        return "Hotelaria{" +
                "id=" + id +
                ", Hotel=" + Hotel +
                ", Hostel=" + Hostel +
                ", Motel=" + Motel +
                '}';
    }
}
