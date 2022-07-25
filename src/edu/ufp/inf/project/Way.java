package edu.ufp.inf.project;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import javafx.beans.binding.DoubleExpression;

import java.io.Serializable;
import java.util.ArrayList;

public class Way extends DirectedEdgeNew implements Serializable {
    private int id;
    private ArrayList<Tag> tags = new ArrayList<>();
    public static RedBlackBST<Integer, Way> waysST = new RedBlackBST<>();

    public Way(int v, int w, double distance, int tempoMedio) {
        super(v, w, distance, tempoMedio);
        this.id = uniqueID();
    }

    private int uniqueID() {
        int id = 1;
        while (waysST.contains(id)) {
            id++;
        }
        return id;
    }

    /**
     * Verifica que se já existe uma way com o mesmo ID, caso exista retorna, caso não exista chama a idUnico
     * para verificar o ultimo Id disponivel e coloca o ID na WaysST
     */
    public void addWaytoWaysST(Way way) {
        if (waysST.contains(id)) {
            System.out.println("Way possui um ID que ja se econtra em uso.");
            return;
        }
        if (id <= 0) {
            id = uniqueID();
        }
        waysST.put(id, way);
    }

    /**
     * Verifica que se já existe uma tag com a mesma key, caso exista retorna, caso não exista
     * adiciona a tag à way
     */
    public void addtagToWay(String key, String value) {
        Tag tag = new Tag(key, value);
        if (tags.contains(tag)) {
            System.out.println("Way already contains this tag");
            return;
        }
        tags.add(tag);
    }

    /**
     * Lê ways apartir do ficheiro
     * Adiciona-as à st de ways
     */
    public static void readWaysFromFile() {
        String line;
        In in = new In(".//data//dataset1_ways_nodepairs.txt");
        if (!in.exists()) {
            System.out.println("Failed to read file");
        } else {
            while (in.hasNextLine()) {
                line = in.readLine();
                int waysCount = Integer.parseInt(line);
                for (int i = 0; i < waysCount; i++) {
                    line = in.readLine();
                    String[] newWay = line.split(",", 5);
                    String[] tags = line.split(",");
                    Way way = new Way(Integer.parseInt(newWay[1]), Integer.parseInt(newWay[2]), Double.parseDouble(newWay[3]), 100);
                    if (Integer.parseInt(tags[4]) != 0)
                        for (int j = 5; j < tags.length - 1; j = j + 2) {
                            way.addtagToWay(tags[j], tags[j + 1]);
                        }
                    way.addWaytoWaysST(way);
                }
            }
        }
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }
}
