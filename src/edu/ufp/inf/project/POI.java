package edu.ufp.inf.project;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.ST;

public class POI extends Node implements Serializable {

    private int id;
    private String name;
    private Location location;

    private POICategory Category;
    private int visitsCount;

    public RedBlackBST<Date, Log> historicoLogs = new RedBlackBST<>();
    public static RedBlackBST<Integer, POI> poisST = new RedBlackBST<>();

    public POI(String name, Location location, POICategory poiCategory) {
        this.name = name;
        this.location = location;
        this.Category = poiCategory;
        this.id = uniqueID();
        this.visitsCount = usersWhoVisitedSemData().size();
        if (Location.localsST.contains(location.getLocal())) //verificar se localsST contem o local de location
            Location.localsST.put(location.getLocal(), Location.localsST.get(location.getLocal()) + 1); //se existir incrementar o nr de POIS nesse local
        else
            Location.localsST.put(location.getLocal(), 1); //adiciona uma nova entrada na localsST com o nr de POIS = 1

    }

    public POI() {
    }

    /**
     * Retorna o ID livre mais baixo da ST de pois
     *
     * @return
     */
    private int uniqueID() {
        int id = 1;
        while (poisST.contains(id)) {
            id++;
        }
        return id;
    }

    /**
     * Percorrer todos os historicos de pois visitados de todos os  users num intervalo de tempo
     *
     * @return users que o visitaram
     */
    public ST<Integer, User> usersWhoVisited(Date d1, Date d2) {
        ST<Integer, User> users = new ST<>();
        for (int userID : User.usersST.keys()) { //Percorrer ST de users
            for (Date data : User.usersST.get(userID).historicoPoisVisitadas.keys()) { //Verificar o hsitorico de POIs visitados de cada User
                if (User.usersST.get(userID).historicoPoisVisitadas.get(data).getId() == this.id && data.afterDate(d1) && data.beforeDate(d2)) //Se o existir no historico de POIs visiatdos um POI com o this.id
                    users.put(userID, User.usersST.get(userID)); //entao adicionar à ST de users
            }
        }
        return users;
    }

    /**
     * Percorrer todos os historicos de pois visitados de todos os  users
     *
     * @return users que o visitaram
     */
    public ST<Integer, User> usersWhoVisitedSemData() {
        ST<Integer, User> users = new ST<>();
        for (int userID : User.usersST.keys()) { //Percorrer ST de users
            for (Date data : User.usersST.get(userID).historicoPoisVisitadas.keys()) { //Verificar o hsitorico de POIs visitados de cada User
                if (User.usersST.get(userID).historicoPoisVisitadas.get(data).getId() == this.id) //Se o existir no historico de POIs visiatdos um POI com o this.id
                    users.put(userID, User.usersST.get(userID)); //entao adicionar à ST de users
            }
        }
        return users;
    }

    /**
     * Historico de Logs do POI
     *
     * @return historicoLogs
     */
    public RedBlackBST<Date, Log> getHistoricoLogs() {
        return historicoLogs;
    }

    /**
     * Adicona um Log ao Historico de Logs do POI
     * Adiciona um Log aos logsGlobais
     *
     * @param log
     */
    public void addLogsToHistorico(Log log) {
        this.historicoLogs.put(log.getDate(), log);
        Log.logsGlobal.add(log);
    }

    /**
     * Inserir utilizador na ST estática (global)
     * Utilizador recebe um ID único
     */
    public void insertPOISsT() {
        if (poisST.contains(id)) {
            System.out.println("POI possui um ID que ja se econtra em uso.");
            return;
        }
        if (id <= 0) {
            id = uniqueID();
        }
        poisST.put(id, this);
    }

    /**
     * Editar attributos dos POI's
     * Apenas Users do tipo ADMIN podem editar POI's
     *
     * @param name     (== null do not change)
     * @param location (== null do not change)
     * @param category (== null do not change)
     */
    public void editPOIsST(String name, Location location, POICategory category, User user) {
        if (user.getType().equals(Type.ADMIN)) {
            if (name != null)
                this.name = name;
            if (location != null)
                this.location = location;
            if (category != null)
                this.Category = category;

            poisST.put(id, this);
            System.out.println("Alteração efetuada com sucesso!");
        } else
            System.out.println("Utilizador do tipo Basic não pode editar POI's!");
    }

    public void editPOIsSTJFX(String name, Location location, POICategory category) {
        if (name != null)
            this.name = name;
        if (location != null)
            this.location = location;
        if (category != null)
            this.Category = category;

        poisST.put(id, this);
        System.out.println("Alteração efetuada com sucesso!");
    }

    /**
     * Remove o POIs de todas as estruturas de dados em que ele se econtra presente i.e poisST; categoryST; userST;
     */
    public void removePOIsST() {
        ST<Integer, User> users = this.usersWhoVisitedSemData();
        if (poisST.contains(this.id)) {
            poisST.delete(this.id);
            if (this instanceof Restauracao) {
                System.out.println("entrei");
                Restauracao.restauranteST.delete(this.id);
            } else if (this instanceof BocaIncendio) {
                BocaIncendio.bocaIncendioST.delete(this.id);
            } else if (this instanceof Semaforo) {
                Semaforo.semaforosST.delete(this.id);
            } else if (this instanceof EstacaoCarregamento) {
                EstacaoCarregamento.EstacaoCarregamentoST.delete(this.id);
            } else if (this instanceof Hotelaria) {
                Hotelaria.HotelariaST.delete(this.id);
            } else if (this instanceof Park) {
                Park.parksST.delete(this.id);
            }
            for (int id : users.keys()) {
                for (Date date : users.get(id).historicoPoisVisitadas.keys()) {
                    if (users.get(id).historicoPoisVisitadas.get(date).getId() == this.id) {
                        users.get(id).historicoPoisVisitadas.delete(date);
                    }
                }
            }
        } else
            System.out.println("POI não existe no sistema!");
    }

    /**
     * Pois que não receberam visitas num determinado periodo de tempo
     *
     * @return poisSemVisitas
     */
    public RedBlackBST<Integer, POI> notVisitedPoisByUsers(Date date1, Date date9) {
        RedBlackBST<Integer, POI> poisSemVisitas = poisST;
        for (int id : User.usersST.keys()) {
            for (int pid : poisST.keys()) {
                if (!User.usersST.get(id).nonVisitedPoisByLocal("global", date1, date9).contains(poisST.get(pid).id)) {
                    poisSemVisitas.delete(poisST.get(pid).getId());
                }
            }
        }
        return poisSemVisitas;
    }


    /**
     * Retorna o Top-5 de pois com mais visitas
     *
     * @return returnPois
     */
    public static RedBlackBST<Integer, POI> top5POIsWithMostVisits() {
        Integer[] array = new Integer[POI.poisST.size()];
        //getSizeOfVisisitados to array
        int i = 0;
        for (int id : POI.poisST.keys()) {
            array[i] = POI.poisST.get(id).getVisitsCount();
            i++;
        }
        Arrays.sort(array, Collections.reverseOrder());
        RedBlackBST<Integer, POI> returnPOIS = new RedBlackBST<>();

        int aux = 0;
        for (int id : POI.poisST.keys()) {
            if (POI.poisST.get(id).getVisitsCount() >= array[4] && aux < 5) {
                returnPOIS.put(POI.poisST.get(id).getId(), POI.poisST.get(id));
                aux++;
            }
        }
        return returnPOIS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public POICategory getPoiCategory() {
        return Category;
    }

    public void setPoiCategory(POICategory poiCategory) {
        this.Category = poiCategory;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getVisitsCount() {
        return visitsCount;
    }

    public void setVisitsCount() {
        this.visitsCount = this.visitsCount + 1;
    }
}