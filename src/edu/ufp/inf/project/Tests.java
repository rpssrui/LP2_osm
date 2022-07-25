package edu.ufp.inf.project;

import edu.princeton.cs.algs4.*;

import java.util.ArrayList;

public class Tests {
    //Criar Datas
    Date date1 = new Date(1960, 4, 14);
    Date date9 = new Date(2030, 11, 18);

    //Teste requisito 5a
    //Funcao User.visitedPoisByLocal
    public void testVisitedPoisByLocal(User user, Date date1, Date date2, String local) {
        System.out.println("\ttestVisitedPoisByLocal");
        ST<Integer, POI> poiUser = new ST<>();
        poiUser = user.visitedPoisByLocal(local, date1, date2);
        System.out.println("Tamanho: " + poiUser.size());
        for (int date : poiUser.keys()) {
            System.out.println("POIs visitados por " + User.usersST.get(user.getId()).getName() + " -> " + poiUser.get(date).getName());
        }
    }

    //Teste requisito 5b
    //Funcao User.nonVisitedPoisByLocal
    public void testNonVisitedPoisbyLocal(User user, Date date1, Date date2, String local) {
        System.out.println("\ttestNonVisitedPoisbyLocal");
        ST<Integer, POI> poiNaoVisitadosUser = new ST<>();
        poiNaoVisitadosUser = user.nonVisitedPoisByLocal(local, date1, date2);
        for (int date : poiNaoVisitadosUser.keys()) {
            System.out.println("POIs nao visitados por: " + User.usersST.get(user.getId()).getName() + "-> " + poiNaoVisitadosUser.get(date).getName());
        }
    }

    //Teste requisito 5c
    //poi.usersWhoVisited
    public void testUsersWhoVisited(POI poi, Date date1, Date date2) {
        System.out.println("\ttestUsersWhoVisited");
        ST<Integer, User> userPOI = poi.usersWhoVisited(date1, date2);
        for (int userId : userPOI.keys()) {
            System.out.println("Users que visitaram o POI " + poi.getName() + "-> " + userPOI.get(userId).getName());
        }
    }

    //Teste requisito 5g
    public void testReq5g() {
        RedBlackBST<Integer, POI> aux = aux = POI.poisST;
        for (int id : aux.keys()) {
            if (POI.poisST.get(id) instanceof EstacaoCarregamento) {
                System.out.println("ID:" + POI.poisST.get(id).getId() + "->" + POI.poisST.get(id).getName());
            }
        }
    }

    //Testa a funcao que adiciona logs ao historico
    public void testGetHistoricoLogs(POI poi) {
        System.out.println("\ttestGetHistoricoLogs");
        RedBlackBST<Date, Log> histLogs = poi.getHistoricoLogs();
        for (Date data : histLogs.keys()) {
            System.out.println("Data: " + histLogs.get(data).getDate().getDay() + "/" + histLogs.get(data).getDate().getMonth() + "/" + histLogs.get(data).getDate().getYear() + " Log: " + histLogs.get(data).getMessage());
        }
    }

    //Teste requesito 5e
    public void testTop5UsersWithMostPOIS() {
        System.out.println("\tTop-5 Users com mais POIs visitados:");
        SeparateChainingHashST<Integer, User> aux = User.top5UsersWithMostPOIS();
        for (int id : aux.keys()) {
            System.out.println("Nome: " + aux.get(id).getName() + "\tID: " + aux.get(id).getId());
        }
    }

    //Teste requesito 5f
    public void testTop5PoisWithMostVisits() {
        System.out.println("\tTop-5 POIs visitados mais visitados:");
        RedBlackBST<Integer, POI> aux = POI.top5POIsWithMostVisits();
        for (int id : aux.keys()) {
            System.out.println("Nome: " + aux.get(id).getName() + "\tID: " + aux.get(id).getId());
        }
    }


    //Teste requesito5d
    public void testNotVisitedPoisByUsers(POI poi) {
        System.out.println("\tTodos os POIS não visitados por nenhum User:");
        RedBlackBST<Integer, POI> aux = poi.notVisitedPoisByUsers(date1, date9);
        for (int id : aux.keys()) {
            System.out.println(aux.get(id).getName());
        }
    }

    //imprimir toda a informação atual sobre os pois
    public void now() {
        for (int id : POI.poisST.keys()) {
            System.out.println("Id:" + POI.poisST.get(id).getId());
            System.out.println("Name:" + POI.poisST.get(id).getName());
            System.out.println("Location:" + POI.poisST.get(id).getLocation());
            System.out.println("Categoria:" + POI.poisST.get(id).getPoiCategory().getCategoria());
            POI.poisST.get(id).printTags();
            ST<Integer, User> users = POI.poisST.get(id).usersWhoVisited(date1, date9);
            if (users.size() > 0) {
                System.out.println("Utilizadores que já visitiram o POI:");
                for (int uID : users.keys()) {
                    System.out.println("Nome: " + users.get(uID).getName() + "\t\tID: " + users.get(uID).getId() + "\t\tTipo: " + users.get(uID).getType());
                }
                System.out.println("____________________________________");
            } else
                System.out.println("POI sem visitas.");
        }
    }

    //print shortestPath
    public void imprimirShortestPathDistance(EdgeWeightedDigraphNew e, int vertice1, int vertice2) {

        DijkstraSPNew dijkstra = new DijkstraSPNew(e, vertice1);
        double soma = 0;
        for (DirectedEdgeNew ed : dijkstra.pathTo(vertice2)) {
            soma = soma + ed.getDistance();
            System.out.println(ed);
        }
        System.out.println("Total Distance (from " + vertice1 + "->" + vertice2 + ") : " + soma + "m");
    }

    public void imprimirShortestPathTempoMedio(EdgeWeightedDigraphNew e, int vertice1, int vertice2, int type) {

        DijkstraSPNew dijkstra = new DijkstraSPNew(e, vertice1, type);

        for (DirectedEdgeNew ed : dijkstra.pathTo(vertice2)) {
            System.out.println(ed);
        }
        System.out.println("Total Average Time (from " + vertice1 + "->" + vertice2 + ") : " + dijkstra.distTo(vertice2) + "s (type=" + type + ")");
    }

    public void imprimirShortestPathAvoid(EdgeWeightedDigraphNew edge, int v1, int v2, ArrayList<Integer> verticesAEvitar) {
        DijkstraSPNew dijkstra = new DijkstraSPNew(edge, v1);

        if (dijkstra.hasPathTo(v2)) {
            StdOut.printf("From: %d to %d (%.2f) \n", v1, v2, dijkstra.distTo(v2));
            for (DirectedEdgeNew ed : dijkstra.pathTo(v2)) {
                if (verticesAEvitar.contains(ed.from()) || verticesAEvitar.contains(ed.to())) {
                    System.out.println(" ");
                }
                System.out.println(ed + "  ");
            }
        } else
            StdOut.printf("%d to %d  sem caminho\n", v1, v2);
    }

    public void printSubGraph(EdgeWeightedDigraphNew e, POI poi) {
        ArrayList<Integer> aux = new ArrayList<>();

        if (poi instanceof Restauracao) {
            for (int id : Restauracao.restauranteST) {
                aux.add(id);
            }
        } else if (poi instanceof Hotelaria) {
            for (int id : Hotelaria.HotelariaST) {
                aux.add(id);
            }
        } else if (poi instanceof Park) {
            for (int id : Park.parksST) {
                aux.add(id);
            }
        } else if (poi instanceof Semaforo) {
            for (int id : Semaforo.semaforosST) {
                aux.add(id);
            }
        } else if (poi instanceof BocaIncendio) {
            for (int id : BocaIncendio.bocaIncendioST) {
                aux.add(id);
            }
        } else if (poi instanceof EstacaoCarregamento) {
            for (int id : EstacaoCarregamento.EstacaoCarregamentoST) {
                aux.add(id);
            }
        }

        EdgeWeightedDigraphNew subGraph = e.buildSubGraph(aux);
        System.out.println(subGraph);
    }

}
