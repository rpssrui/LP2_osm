package edu.ufp.inf.project;

import edu.princeton.cs.algs4.ST;

import java.io.Serializable;

public class EstacaoCarregamento extends POICategory implements Serializable {
    private int idEstacao;
    private String veiculo;
    private String Local;
    private double Longitude;
    private double Latitude;
    private POICategory Category;

    public static ST<Integer, EstacaoCarregamento> EstacaoCarregamentoST = new ST<>();


    public EstacaoCarregamento(String name, POICategory poiCategory, Location location, String categoria, int idCategoria, String veiculo) {
        super(name, poiCategory, location, categoria, idCategoria);
        this.veiculo = veiculo;
    }

    public EstacaoCarregamento(int id, String categoria, int id1, String veiculo) {
        super(id, categoria);
        this.idEstacao = id1;
        this.veiculo = veiculo;
    }

    public EstacaoCarregamento(String name, POICategory poiCategory, Location location) {
        super(name, poiCategory, location, poiCategory.getCategoria(), poiCategory.getId());
        Local = location.getLocal();
        this.idEstacao = poisST.size() + 1;
        Longitude = location.getLongitude();
        Latitude = location.getLatitude();
        Category = poiCategory;
    }

    @Override
    public int getId() {
        return idEstacao;
    }

    @Override
    public void setId(int id) {
        this.idEstacao = id;
    }


    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public int getIdEstacao() {
        return idEstacao;
    }

    public void setIdEstacao(int idEstacao) {
        this.idEstacao = idEstacao;
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

    /**
     * Retorna o ID livre mais baixo da ST de estacoes de carregamento
     *
     * @return
     */
    private int uniqueID() {
        int id = 1;
        while (EstacaoCarregamentoST.contains(id)) {
            id++;
        }
        return id;
    }

    /**
     * Verifica que se já existe uma Estacao de Carregamento com o mesmo ID, caso exista retorna, caso não exista chama a idUnico
     * para verificar o ultimo Id disponivel e coloca o ID na EstacaoCarregamento
     */
    public void insertEstacoesCarregamentoST() {
        if (EstacaoCarregamentoST.contains(idEstacao)) {
            System.out.println("Estacao de Carregamento possui um ID que ja se econtra em uso");
            return;
        }
        if (idEstacao <= 0) {
            idEstacao = uniqueID();
        }
        EstacaoCarregamentoST.put(idEstacao, this);
    }

    /**
     * Imprime Todos as Estacoes de Carregamento presentes na ST de Estacoes de Carregamento
     */
    public void printEstacoesCarregamentoST() {
        System.out.println("\nEstacoes de Carregamento:");
        for (int i : EstacaoCarregamentoST.keys()) {
            System.out.println("ID: " + EstacaoCarregamentoST.get(i).idEstacao);
            System.out.println("Nome: " + EstacaoCarregamentoST.get(i).getName());
            System.out.println("Veiculo:" + EstacaoCarregamentoST.get(i).veiculo);
        }
    }

    @Override
    public String toString() {
        return "EstacaoCarregamento{" +
                "id=" + idEstacao +
                ", veiculo='" + veiculo + '\'' +
                '}';
    }
}
