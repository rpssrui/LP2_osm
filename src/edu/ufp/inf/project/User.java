package edu.ufp.inf.project;

import edu.princeton.cs.algs4.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;


public class User implements Serializable {

    private int id;
    private String name;
    private Type type;
    public RedBlackBST<Date, POI> historicoPoisNaoVisitadas = new RedBlackBST<>();
    public RedBlackBST<Date, POI> historicoPoisVisitadas = new RedBlackBST<>();
    public static SeparateChainingHashST<Integer, User> usersST = new SeparateChainingHashST<>();
    private int sizeOfVisitados;

    public User(String name, Type type) {
        this.name = name;
        this.type = type;
        this.sizeOfVisitados = historicoPoisVisitadas.size();
    }

    /**
     * Retorna o ID livre mais baixo da ST de utilizadores
     *
     * @return
     */
    private int uniqueID() {
        int id = 1;
        while (usersST.contains(id)) {
            id++;
        }
        return id;
    }

    /**
     * Pois que o User tenha visitado num determinado dado Local e durante um determindado periodo de tempo(date1-date2)
     *
     * @param local, date1, date2
     * @return pois
     */
    public ST<Integer, POI> visitedPoisByLocal(String local, Date date1, Date date2) {
        ST<Integer, POI> pois = visetedPoisByDate(date1, date2);
        ST<Integer, POI> response = new ST<>();

        for (int id : pois.keys()) {
            if (pois.get(id).getLocation().getLocal().equals(local) || local.equals("global")) {
                response.put(pois.get(id).getId(), pois.get(id));
            }
        }
        return response;
    }

    /**
     * Pois que o User tenha visitado num determinado periodo de tempo(date1-date2)
     *
     * @param date1, date2
     * @return pois
     */
    public ST<Integer, POI> visetedPoisByDate(Date date1, Date date2) {
        ST<Integer, POI> pois = new ST<>();
        for (Date data : historicoPoisVisitadas.keys()) {
            if (data.afterDate(date1) && data.beforeDate(date2)) {
                pois.put(historicoPoisVisitadas.get(data).getId(), historicoPoisVisitadas.get(data));
            }
        }
        return pois;
    }

    /**
     * Pois que o User não tenha visitado num determinado dado local
     *
     * @param local
     * @return pois
     */
    public ST<Integer, POI> nonVisitedPoisByLocal(String local, Date d1, Date d2) {
        ST<Integer, POI> pois = new ST<>();
        for (int id : POI.poisST.keys()) {
            if (!visitedPoisByLocal(local, d1, d2).contains(id) && (POI.poisST.get(id).getLocation().getLocal().equals(local) || local.equals("global")))
                pois.put(id, POI.poisST.get(id));
        }
        return pois;
    }


    /**
     * Adicionar POI ao historico de POIS Visitados
     *
     * @param id - POI
     */
    public void addPOI(int id, Date d1) {
        if (!POI.poisST.contains(id))
            System.out.println("Nao existe nenhum POI com esse ID guardada na ST");
        else {
            for (Date data : historicoPoisVisitadas.keys()) {
                if (historicoPoisVisitadas.get(data).getId() == id) {
                    System.out.println("Este user ja visitou esse POI.");
                    return;
                }
            }
            sizeOfVisitados++;
            POI.poisST.get(id).setVisitsCount();
            historicoPoisVisitadas.put(d1, POI.poisST.get(id));
        }
    }

    /**
     * Inserir utilizador na ST estática (global)
     * Utilizador recebe um ID único
     */
    public void insertUsersST() {
        if (usersST.contains(id)) {
            System.out.println("Utilizador possui um ID que ja se econtra em uso.");
            return;
        }
        if (id <= 0) {
            id = uniqueID();
        }
        usersST.put(id, this);
    }

    /**
     * Editar os atributos de User
     *
     * @param name (== null do not change)
     * @param type (== null do not change)
     */
    public void editUser(String name, Type type) {
        if (name != null)
            this.name = name;
        if (type != null)
            this.type = type;
        System.out.println("Alteração efetuada com sucesso!");
        usersST.put(id, this);
    }

    /**
     * Remove o User de todas as estruturas de dados em que ele se ecoontra presente
     */
    public void removerUserST() {
        if (usersST.contains(this.id))
            usersST.delete(this.id);
        else
            System.out.println("Utilizador não se econtra presente no sistema!");
    }

    /**
     * Imprime todos os utilizadores guardados na ST, na consola
     */
    public void printUsersST() {
        System.out.println("\nUsers Table:");
        for (int id : usersST.keys()) {
            System.out.println("Id: " + usersST.get(id).id + "\tName: " + usersST.get(id).name + "\tType: " + usersST.get(id).type);
        }
    }

    /**
     * Retorna os 5 users que mais pois visitaram até ao momento
     *
     * @return rerturnUsers
     */
    public static SeparateChainingHashST<Integer, User> top5UsersWithMostPOIS() {
        Integer[] array = new Integer[usersST.size()];

        int i = 0;
        for (int id : usersST.keys()) {
            array[i] = usersST.get(id).sizeOfVisitados;
            i++;
        }
        Arrays.sort(array, Collections.reverseOrder());

        SeparateChainingHashST<Integer, User> returnUsers = new SeparateChainingHashST<>();
        int aux = 0;
        for (int id : usersST.keys()) {
            if (usersST.get(id).sizeOfVisitados >= array[4] && aux < 5) {
                returnUsers.put(usersST.get(id).getId(), usersST.get(id));
                aux++;
            }
        }
        return returnUsers;
    }

    /**
     * Retorna os 5 pois mais visitados neste momento
     *
     * @return rerturnPOIS
     */


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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getSizeOfVisitados() {
        return sizeOfVisitados;
    }

    public void setSizeOfVisitados(int sizeOfVisitados) {
        this.sizeOfVisitados = sizeOfVisitados;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    public static void readUsersFile() {
        String line;
        In in = new In(".//data//outputUser.txt");
        if (!in.exists()) {
            System.out.println("Ficheiro Invalido");
        } else {
            while (in.hasNextLine()) {
                line = in.readLine();
                int numeroUsers = Integer.parseInt(line);
                for (int i = 0; i < numeroUsers; i++) {
                    line = in.readLine();
                    String[] userInput = line.split(",", 3);
                    User user = new User(userInput[1], Type.fromString(userInput[2]));
                    user.insertUsersST();
                }
            }
        }
    }

    public static void writeUsersFile() {
        Out out = new Out(".//data//outputUser.txt");
        out.println(User.usersST.size());
        for (int userID : User.usersST.keys()) {
            out.println(userID + "," + User.usersST.get(userID).getName() + "," + User.usersST.get(userID).getType().toString());
        }
    }

}