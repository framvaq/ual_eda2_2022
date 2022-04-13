package org.eda2.practica_1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private static final String MAIN_MENU = "###################################\n" +
            "# ANÁLISIS DE JUGADORES DE LA NBA #\n" +
            "###################################\n" +
            "Elige una opción:\n" +
            "\t1.- Obtener los 10 mejores con la ordenación por defecto\n" +
            "\t2.- Elegir el número de jugadores a obtener con la ordenación por defecto.\n" +
            "\t3.- Obtener los 10 mejores según un criterio de ordenación.\n" +
            "\t4.- Elegir tanto el número de jugadores como el método de ordenación.\n" +
            "\t5.- Análisis de eficiencia.\n" +
            "\t0.- Salir";
    private static final String NUM_PLAYERS_MENU = "## Elegir número de jugadores\n" +
            " -> 0 para obtener todos los jugadores\n" +
            " -> -1 para salir\n" +
            " -> Cualquier otro número";

    private static final String COMPARATOR_MENU = "## Elegir comparador\n" +
            " -> 0 para orden por defecto (puntos * porcentaje aciertos)\n" +
            " -> -1 para salir";
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Comparator<Player>> comparators = new ArrayList<>();
    private static Comparator<Player> c = null;
    private static int numPlayers = 10;

    private static int input = 0;
    private static int errors = 0;
    private static boolean exitMenu = false;

    public static void main(String[] args) {
        Players p = new Players();
        ArrayList<Player> bests = null;
        do {
            input = mainMenu();
            boolean print = true;
            switch (input) {
                case 1:
                    bests = p.getBestPlayers();
                    break;
                case 2:
                    numPlayersMenu();
                    bests = p.getBestPlayers(numPlayers);
                    break;
                case 3:
                    c = comparatorMenu();

                    System.out.println(c.getClass().getSimpleName());
                    bests = p.getBestPlayers(c);
                    break;
                case 4:
                    numPlayersMenu();
                    c = comparatorMenu();

                    System.out.println(c.getClass().getSimpleName());
                    bests = p.getBestPlayers(numPlayers, c);
                    break;
                case 5:
                    analisisDeEficiencia(p);
                    print = false;
                    break;
                default:
                    print = false;
                    System.out.println("Saliendo...");
                    break;
            }

            if (print) {
                System.out.println(bests);
            }
        } while (!exitMenu);
        System.out.println("Adiós.");

    }

    private static int selectOption() {
        try {
            input = sc.nextInt();
            exitMenu = true;
        } catch (InputMismatchException ime) {
            errors++;
            if (errors < 2) {
                exitMenu = false;
                errors++;
                System.out.println(
                        "No has introducido un número, prueba otra vez o pulsa cualquier tecla (excepto un número) para salir");
            } else {
                exitMenu = true;
                input = 0;
            }
            sc.next();
        }
        return input;
    }

    private static int mainMenu() {
        errors = 0;
        exitMenu = false;
        do {
            System.out.println(MAIN_MENU);
            input = selectOption();
        } while (!exitMenu);
        return input;
    }

    private static void numPlayersMenu() {
        errors = 0;
        System.out.println(NUM_PLAYERS_MENU);
        do {
            input = selectOption();
        } while (!exitMenu && errors < 2);
        numPlayers = input;
        // return numPlayers;
    }

    private static Comparator<Player> comparatorMenu() {
        if (comparators.size() < 1) {
            comparators.add(0, new PlayerDefComparator());
            comparators.add(1, new PlayerNameComparator());
            comparators.add(2, new PlayerNumberTeamsComparator());
        }
        exitMenu = false;
        int pos;
        errors = 0;

        do {
            System.out.println(COMPARATOR_MENU);
            int i = 1;
            for (Comparator<Player> c : comparators) {
                System.out.println(" -> " + i + ": " + c.getClass().getSimpleName());
                i++;
            }

            pos = selectOption();

        } while (!exitMenu);

        c = pos == 0 ? null : comparators.get(pos - 1);
        return c;
    }

    public static void analisisDeEficiencia(Players p) {
        System.out.println("////////////////////////////");
        System.out.println("// Análisis de eficiencia //");
        System.out.println("////////////////////////////");
        File f = new File(Players.route + "analisisPrueba_5sx50it.csv");
        // System.out.println("Ruta: " + f.getAbsolutePath());
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);

            int maxJugadores = p.getSize(); // 3921
            int maxIteraciones = 50;

            System.out.println("n \t\t t(ns)");
            for (int jugadores = 1; jugadores <= maxJugadores; jugadores += 5) {
                long currentTimeV1 = 0;
                for (int iteraciones = 0; iteraciones < maxIteraciones; iteraciones++) {
                    long start = System.nanoTime();
                    p.getBestPlayers(jugadores, null);
                    long end = System.nanoTime();
                    currentTimeV1 += end - start;
                }
                String write = jugadores + "," + (currentTimeV1 / maxIteraciones) + "\n";
                fw.write(write);
                System.out.println(jugadores + "\t\t" + (currentTimeV1 / maxIteraciones));
            }

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
