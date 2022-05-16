package org.eda2.practica2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.TreeSet;

public class App {
    private static final String path = System.getProperty("user.dir") + System.getProperty("file.separator");

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

        // HashSet<Edge> prim = g.getPrim();
        // TreeSet<Edge> primTM = new TreeSet<>(prim);
        HashSet<Edge> primPQ = g.getPrimPQ();
        // TreeSet<Edge> primPQTM = new TreeSet<>(primPQ);
        // HashSet<Edge> kruskal = g.getKruskal();
        // TreeSet<Edge> kruskalTM = new TreeSet<>(kruskal);
        // System.out.println(g);
        // System.out.println(primTM);
        // System.out.println(primPQTM);
        // System.out.println(kruskalTM);
        // System.out.println(g.getPrimW());
        // System.out.println(g.getPrimPQW());
        // System.out.println(g.getKruskalW());

        /*
         * File folder = new File(path + "assets" +
         * System.getProperty("file.separator"));
         * File[] files = folder.listFiles();
         * File result = new File(path + "docs" + System.getProperty("file.separator") +
         * "result.csv");
         * // int attempts = 5;
         * System.out.printf("%-20s%-20s%-20s%-20s%-20s%n", "Vértices", "Aristas",
         * "Prim sin PQ", "Prim con PQ",
         * "Kruskal");
         * try {
         * FileWriter fw = new FileWriter(result);
         * fw.write("vertices,aristas,prim1,prim2pq,kruskal\n");
         * fw.close();
         * } catch (IOException e) {
         * e.getMessage();
         * }
         * for (int i = 0; i < files.length; i++) {
         * try {
         * long begin, end;
         * long total = 0;
         * Graph g = new Graph(files[i]);
         * System.out.printf("%-20d%-20d", g.getNumVertices(), g.getNumEdges());
         * if (g.getNumEdges() > 14000000) {
         * System.out.println();
         * continue;
         * }
         * FileOutputStream fos = new FileOutputStream(result, true);
         * fos.write((g.getNumVertices() + "," + g.getNumEdges() + ",").getBytes());
         * // Prim1
         * begin = System.nanoTime();
         * g.getPrim();
         * end = System.nanoTime();
         * total += end - begin;
         * System.out.printf("%-20d", total);
         * fos.write((total + ",").getBytes());
         * 
         * // Prim2conPQ
         * total = 0;
         * begin = System.nanoTime();
         * g.getPrimPQ();
         * end = System.nanoTime();
         * total += end - begin;
         * System.out.printf("%-20d", total);
         * fos.write((total + ",").getBytes());
         * 
         * // Kruskal
         * total = 0;
         * begin = System.nanoTime();
         * g.getKruskal();
         * end = System.nanoTime();
         * total += end - begin;
         * System.out.printf("%-20d%n", total);
         * fos.write((total + "\n").getBytes());
         * fos.close();
         * } catch (IOException ioe) {
         * ioe.getMessage();
         * } catch (OutOfMemoryError ome) {
         * System.err.println(ome.getMessage());
         * continue;
         * } catch (Exception e) {
         * System.err.println(e.getMessage());
         * continue;
         * }
         * }
         */
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