package org.eda2.practica_1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Comparator<Player>> comparators = new ArrayList<>();
    private static int numPlayers = 10;
    private static final String MENU = "###################################\n" +
            "# ANÁLISIS DE JUGADORES DE LA NBA #\n" +
            "###################################\n" +
            "Elige una opción:\n" +
            "1.- Obtener los 10 mejores con la ordenación por defecto\n" +
            "2.- Elegir el número de jugadores a obtener con la ordenación por defecto.\n" +
            "3.- Obtener los 10 mejores según un criterio de ordenación.\n" +
            "4.- Elegir tanto el número de jugadores como el método de ordenación.";

    public static void main(String[] args) {
        int input = 0;
        Comparator<Player> c;

        Players p = new Players();
        do {
            input = menuPrincipal();
            switch (input) {
                case 1:
                    p.getBestPlayers();
                    break;
                case 2:
                    numPlayers = menuNumPlayers();
                    p.getBestPlayers(numPlayers);
                    break;
                case 3:
                    c = menuComparator();
                    p.getBestPlayers(c);
                    break;
                case 4:
                    numPlayers = menuNumPlayers();
                    c = menuComparator();
                    p.getBestPlayers(numPlayers, c);
                    break;
                default:
                    System.out.println("hola");
                    break;
            }
        } while (input != -1);
        System.out.println("Adiós.");

    }

    private static Comparator<Player> menuComparator() {
        if (comparators.isEmpty()) {
            fillComparators();
        }
        return null;
    }

    private static void fillComparators() {
        comparators.add(0, new PlayerDefComparator());
        comparators.add(1, new PlayerNameComparator());
        comparators.add(2, new PlayerNumberTeamsComparator());

    }

    private static int menuNumPlayers() {
        return 0;
    }

    private static int menuPrincipal() {
        int input = -1;
        int errors = 0;
        while (input == -1) {
            System.out.println(MENU);
            try {
                input = sc.nextInt();
            } catch (InputMismatchException ime) {
                errors++;
                if (errors < 2) {
                    errors++;
                    System.out.println(
                            "No has introducido un número, prueba otra vez o pulsa cualquier tecla (excepto un número) para salir");
                } else {
                    System.out.println("Saliendo...");
                    input = 0;
                }
                sc.next();
            }

        }
        return input;
    }
}
