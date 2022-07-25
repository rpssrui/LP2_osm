package edu.ufp.inf.project;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.RedBlackBST;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Node implements Serializable {
    private Point point;
    private ArrayList<Tag> tags = new ArrayList<>();

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }


    /**
     * Verifica que se já existe uma tag com a mesma key, caso exista retorna, caso não exista
     * adiciona a tag ao node
     */
    public void addtagToNode(String key, String value) {
        Tag tag = new Tag(key, value);

        if (tags.contains(tag)) {
            System.out.println("Node already contains this tag");
            return;
        }
        tags.add(tag);

    }

    /**
     * Primeiro verifica se o node tem tags, caso não tenha imprime uma mensagem
     * Caso tenha percorre o arraylist de tags e imprime
     */
    public void printTags() {
        if (this.tags.isEmpty()) {
            System.out.println("Node sem tags.");

        } else {
            for (Tag tag : this.tags) {
                System.out.println(tag);
            }
        }
    }

    /**
     * Escreve para um ficheiro os dados da ST de pois, assim como as suas tags respetivas, caso existam
     */
    public static void writeNodesToFile() {
        Out out = new Out(".//data//outputNode.txt");

        out.println(POI.poisST.size());
        for (int idPoi : POI.poisST.keys()) {
            out.print(idPoi + "," +
                    POI.poisST.get(idPoi).getLocation().getLatitude() + "," +
                    POI.poisST.get(idPoi).getLocation().getLongitude() + "," +
                    POI.poisST.get(idPoi).getPoiCategory().getCategoria() + "," +
                    POI.poisST.get(idPoi).getTags().size() + ",");
            for (Tag tag : POI.poisST.get(idPoi).getTags()) {
                out.print(tag.getKey() + "," + tag.getValue());
            }
            out.println();
        }
    }

    /**
     * Escreve para um ficheiro os dados da ST de pois, assim como as suas tags respetivas, caso existam
     * Para demonstração os valores de latitude e longitude são randomizados
     */
    public static void writeNodesToFileRand() {
        Out out = new Out(".//data//outputNodeRand.txt");
        Random r = new Random(System.currentTimeMillis());
        out.println(POI.poisST.size());
        for (int idPoi : POI.poisST.keys()) {
            out.print(idPoi + "," +
                    POI.poisST.get(idPoi).getLocation().getLatitude() * r.nextDouble() + "," +
                    POI.poisST.get(idPoi).getLocation().getLongitude() * r.nextDouble() + "," +
                    POI.poisST.get(idPoi).getPoiCategory().getCategoria() + "," +
                    POI.poisST.get(idPoi).getTags().size() + ",");
            for (Tag tag : POI.poisST.get(idPoi).getTags()) {
                out.print(tag.getKey() + "," + tag.getValue());
            }
            out.println();
        }
    }


    /**
     * Lê nodes apartir do ficheiro, com a respetiva categoria
     * Adiciona-os à st de nodes
     */
    public static void readNodesFromFile() {
        String line;
        In in = new In(".//data//outputNodeRand.txt");
        if (!in.exists()) {
            System.out.println("Failed to read file");
        } else {
            while (in.hasNextLine()) {
                line = in.readLine();
                int nodesCount = Integer.parseInt(line);
                int idPOI = 0;
                String categoria;

                for (int i = 0; i < nodesCount; i++) {
                    line = in.readLine();
                    String[] newPOI = line.split(",", 4);
                    String[] tags = line.split(",");
                    idPOI = Integer.parseInt(newPOI[0]);
                    Location location = new Location("global", Double.parseDouble(newPOI[1]), Double.parseDouble(newPOI[2]));
                    int virgulaPos = newPOI[3].indexOf(",");
                    categoria = newPOI[3].substring(0, virgulaPos);
                    if (categoria.equals("ChargerStation")) {
                        POICategory category = new POICategory(1, "ChargerStation");
                        EstacaoCarregamento poi = new EstacaoCarregamento("EstacaoCarregamento" + i, category, location);
                        poi.insertPOISsT();
                        poi.insertEstacoesCarregamentoST();
                        if (Integer.parseInt(tags[4]) != 0) {
                            for (int j = 5; j < tags.length - 1; j = j + 2) {
                                poi.addtagToNode(tags[j], tags[j + 1]);
                            }
                        }
                    } else if (categoria.equals("Restaurante")) {
                        POICategory category = new POICategory(2, "Restaurante");
                        Restauracao poi = new Restauracao("Restaurante" + i, category, location);
                        poi.insertPOISsT();
                        poi.insertRestaurantesST();
                        if (Integer.parseInt(tags[4]) != 0) {
                            for (int j = 5; j < tags.length - 1; j = j + 2) {
                                poi.addtagToNode(tags[j], tags[j + 1]);
                            }
                        }

                    } else if (categoria.equals("Hotelaria")) {
                        POICategory category = new POICategory(3, "Hotelaria");
                        Hotelaria poi = new Hotelaria("Hotelaria" + i, category, location);
                        poi.insertPOISsT();
                        poi.insertHotelST();
                        if (Integer.parseInt(tags[4]) != 0) {
                            for (int j = 5; j < tags.length - 1; j = j + 2) {
                                poi.addtagToNode(tags[j], tags[j + 1]);
                            }
                        }

                    } else if (categoria.equals("Parque")) {
                        POICategory category = new POICategory(4, "Parque");
                        Park poi = new Park("Parque" + i, category, location);
                        poi.insertPOISsT();
                        poi.insertParkST();
                        if (Integer.parseInt(tags[4]) != 0) {
                            for (int j = 5; j < tags.length - 1; j = j + 2) {
                                poi.addtagToNode(tags[j], tags[j + 1]);
                            }
                        }

                    } else if (categoria.equals("BocaIncendio")) {
                        POICategory category = new POICategory(5, "BocaIncendio");
                        BocaIncendio poi = new BocaIncendio("BocaIncendio" + i, category, location);
                        poi.insertPOISsT();
                        poi.insertBocaIncendioST();
                        if (Integer.parseInt(tags[4]) != 0) {
                            for (int j = 5; j < tags.length - 1; j = j + 2) {
                                poi.addtagToNode(tags[j], tags[j + 1]);
                            }
                        }

                    } else if (categoria.equals("Semaforo")) {
                        POICategory category = new POICategory(6, "Semaforo");
                        Semaforo poi = new Semaforo("Semaforo" + i, category, location);
                        poi.insertPOISsT();
                        poi.insertSemaforoST();
                        if (Integer.parseInt(tags[4]) != 0) {
                            for (int j = 5; j < tags.length - 1; j = j + 2) {
                                poi.addtagToNode(tags[j], tags[j + 1]);
                            }
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }


}


