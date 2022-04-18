package practica_2;

import java.util.HashSet;
import java.util.TreeSet;

public class App {
    public static void main(String[] args) {
        // Graph.createFile(2, 1, 0);
        // Graph.createFile(3, 1, 0);
        // Graph.createFile(4, 1, 0);
        // Graph.createFile(6, 20, 0);

        // // Graph.createFile(20000, (20000 * 19999) / 2, 0);

        // int max_edges;
        // for (int i = 1000; i < 21000; i += 1000) {
        // max_edges = (i * (i - 1)) / 2; // They are undirected
        // for (int j = 1; j < max_edges; j *= 4) {
        // Graph.createFile(i, j, 0);
        // }
        // Graph.createFile(i, max_edges, 0);
        // }

        Graph g = new Graph(Graph.createFile(7, 200, 0));

        HashSet<Edge> prim = g.getPrim();
        TreeSet<Edge> primTM = new TreeSet<>(prim);
        HashSet<Edge> primPQ = g.getPrimPQ();
        TreeSet<Edge> primPQTM = new TreeSet<>(primPQ);
        HashSet<Edge> kruskal = g.getKruskal();
        TreeSet<Edge> kruskalTM = new TreeSet<>(kruskal);
        System.out.println(g);
        System.out.println(primTM);
        System.out.println(primPQTM);
        System.out.println(kruskalTM);
        System.out.println(g.getPrimW());
        System.out.println(g.getPrimPQW());
        System.out.println(g.getKruskalW());

        Graph g2 = new Graph();
        long begin, end, total = 0;
        int attempts = 10;
        System.out.println("TEST DE RENDIMIENTO:");
        System.out.println("Algoritmo de Prim sin cola de prioridad:");
        System.out.println("t(ns)");
        for (int i = 0; i < attempts; i++) {
            begin = System.nanoTime();
            g.getPrim();
            end = System.nanoTime();
            total += end - begin;
        }
        total /= 10;
        System.out.println(total);
        total = 0;
        for (int i = 0; i < attempts; i++) {
            begin = System.nanoTime();
            g.getPrimPQ();
            end = System.nanoTime();
            total += end - begin;
        }
        total /= 10;
        System.out.println(total);

        total = 0;
        for (int i = 0; i < attempts; i++) {
            begin = System.nanoTime();
            g.getKruskal();
            end = System.nanoTime();
            total += end - begin;
        }
        total /= 10;
        System.out.println(total);
        /*
         * "myGraph_7000vertices_1048576edges.txt
         * 132 -> myGraph_7000vertices_16384edges.txt
         * 133 -> myGraph_7000vertices_262144edges.txt
         * 134 -> myGraph_7000vertices_4194304edges.txt
         * 135 -> myGraph_7000vertices_65536edges.txt
         * 136 -> myGraph_7000vertices_6999edges.txt
         * 137 -> myGraph_7000vertices_7000000edges.txt"
         */
    }
}