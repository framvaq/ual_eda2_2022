package org.eda2.practica2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Class {
    public static void main(String[] args) {
        int numAEscribirEnCadaIteracion = 0;
        String path = System.getProperty("user.dir") + System.getProperty("file.separator");
        File folder = new File(path + "datasets" + System.getProperty("file.separator"));
        File[] files = folder.listFiles();
        File result = new File(path + "docs" + System.getProperty("file.separator") + "result.csv");
        try {
            FileOutputStream fos = new FileOutputStream(result, true);
            long begin, end;
            long total = 0;
            Graph g;
            g = new Graph(files[numAEscribirEnCadaIteracion]);
            System.out.printf("%-20d%-20d", g.getNumVertices(), g.getNumEdges());
            fos.write((g.getNumVertices() + "," + g.getNumEdges() + ",").getBytes());
            fos.close();

            // Prim1
            fos = new FileOutputStream(result, true);
            begin = System.nanoTime();
            g.getPrim();
            end = System.nanoTime();
            total += end - begin;
            System.out.printf("%-20d", total);
            fos.write((total + ",").getBytes());
            fos.close();

            // Prim2conPQ
            fos = new FileOutputStream(result, true);
            total = 0;
            begin = System.nanoTime();
            g.getPrimPQ();
            end = System.nanoTime();
            total += end - begin;
            System.out.printf("%-20d", total);
            fos.write((total + ",").getBytes());
            fos.close();

            // Kruskal
            fos = new FileOutputStream(result, true);
            total = 0;
            begin = System.nanoTime();
            g.getKruskal();
            end = System.nanoTime();
            total += end - begin;
            System.out.printf("%-20d%n", total);
            fos.write((total + "\n").getBytes());
            fos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }
}
