package practica_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Map.Entry;

/**
 * Clase que encapsula un grafo y permite obtener su árbol de recubrimiento
 * mínimo.
 */
public class Graph {
    private static File file;
    private static final String path = System.getProperty("user.dir") + System.getProperty("file.separator") +
            "practica_2" + System.getProperty("file.separator") + "assets" + System.getProperty("file.separator");
    private static final int MAX_WEIGHT = 10000;

    private HashMap<String, HashMap<String, Double>> graph;

    private boolean directed;
    private String source;
    private int numVertices;
    private int numEdges;
    private double primW = 0;
    private double primPQW = 0;
    private double kruskalW = 0;

    public Graph(File f) {
        // Check if file is good
        if (f == null) {
            return;
        }
        Scanner sc = null;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Read file
        // First line indicates if graph is directed or not directed
        String line = sc.nextLine();
        if (line.equals("0")) {
            this.directed = false;
        } else if (line.equals("1")) {
            throw new UnsupportedOperationException("No se ha implementado el tratamiento de grafos dirigidos.");
        } else {
            System.out.println("Error en el fichero");
            return;
        }

        // Second line indicates number of vertices.
        // First vertex is set as source, then add the rest
        this.graph = new HashMap<>();
        this.numVertices = Integer.parseInt(sc.nextLine());
        this.source = sc.nextLine();
        graph.put(this.source, new HashMap<String, Double>());
        for (int i = 1; i < numVertices; i++) {
            String vertex = sc.nextLine();
            graph.put(vertex, new HashMap<String, Double>());
        }

        // Now get number of edges and add them
        this.numEdges = Integer.parseInt(sc.nextLine());
        while (sc.hasNextLine()) {
            String[] edge = sc.nextLine().split(" ");
            this.graph.get(edge[0]).put(edge[1], Double.parseDouble(edge[2]));
            if (!this.directed) {
                if (this.graph.get(edge[1]) == null) {
                    this.graph.put(edge[1], new HashMap<String, Double>());
                }
                this.graph.get(edge[1]).put(edge[0], Double.parseDouble(edge[2]));
            }
        }
    }

    public Graph() {
        this(getFile());
    }

    public static File getFile() {
        Scanner sc = new Scanner(System.in);
        File folder = new File(path);
        System.out.println("Introduce el número del fichero o número de vertices y aristas (Xvertices_Y): ");
        int i = 0;
        File[] list = folder.listFiles();
        for (File f : list) {
            i++;
            System.out.println(i + " -> " + f.getName());
        }
        String fileName = sc.nextLine();
        sc.close();
        try {
            file = list[Integer.parseInt(fileName) - 1];
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            for (File f : list) {
                if (f.getName().contains(fileName)) {
                    fileName = f.getName();
                    break;
                }
            }
            file = new File(path + fileName);
        }
        if (!file.exists() && !file.isFile()) {
            System.out.println("El fichero " + file.getName() + " no existe o es un directorio");
            return null;
        }
        System.out.println("Fichero elegido: " + file.getAbsolutePath());
        return file;
    }

    public static File createFile(int vertices, int directed) {
        return createFile(vertices, 1, directed);
    }

    public static File createFile(int vertices, int edges, int directed) {
        if (vertices <= 1) {
            throw new IllegalArgumentException(
                    "El número de vertices mínimo es 2.");
        }
        if (directed == 1) {
            throw new UnsupportedOperationException("No se ha implementado el tratamiento de grafos dirigidos.");
        }
        if (directed != 0 && directed != 1) {
            throw new IllegalArgumentException("El campo \"directed\" sólo puede ser 0 o 1");
        }
        int maxEdgesInGraph = vertices * (vertices - 1);
        if (directed == 0) {
            maxEdgesInGraph /= 2;
        }
        int maxEdgesToWrite = vertices * 1000; // performance reasons
        int max_edges = maxEdgesInGraph > maxEdgesToWrite ? maxEdgesToWrite : maxEdgesInGraph;
        if (edges > max_edges) {
            edges = max_edges;
        }

        int min_edges = vertices - 1;
        if (edges < min_edges) {
            edges = min_edges;
        }

        String name = "myGraph_" + vertices + "vertices_" + edges + "edges.txt";
        File f = new File(path + name);
        try {
            FileOutputStream fos = new FileOutputStream(f, false);

            // First line writes if is directed or not
            fos.write((directed + "\n").getBytes());
            // Then write the vertices: first the number, then the vertices themselves
            fos.write((vertices + "\n").getBytes());
            HashMap<String, HashSet<String>> aux = new HashMap<>();
            for (int i = 0; i < vertices; i++) {
                fos.write((i + "\n").getBytes());
                aux.put(i + "", new HashSet<String>());
            }
            // Finally the edges: first the number, then the edges themselves
            fos.write((edges + "\n").getBytes());
            // Write the minimun to be connected
            for (int i = 0; i < min_edges; i++) {
                fos.write((i + " " + (i + 1) + " " + Math.random() * MAX_WEIGHT + "\n").getBytes());
                aux.get(i + "").add(i + 1 + "");
            }

            // begin from the actual number of edges
            for (int i = min_edges; i < edges; i++) {
                // System.gc();
                // Check if v1 and v2 aren`t equal or adjacent
                int v1 = (int) (Math.random() * vertices);
                String v1str = v1 + "";
                HashSet<String> auxValues = aux.get(v1str); // Adjacency map, to make sure there are no duplicates
                // Avoids infinite loop if v1 contains all vertices
                while (auxValues.size() == vertices - 1) {
                    v1 = (int) (Math.random() * vertices);
                    auxValues = aux.get(v1 + "");
                }
                v1str = new String(v1 + "");
                int v2 = (int) (Math.random() * vertices);
                String v2str = v2 + "";
                while (v1 == v2 || auxValues.contains(v2str)) {
                    v2 = (int) (Math.random() * vertices);
                    v2str = new String(v2 + "");
                }

                if (directed == 0) {
                    auxValues = aux.get(v2str);
                    if (auxValues.contains(v1str)) {
                        i--;
                        continue;
                    }
                }
                fos.write((v1 + " " + v2 + " " + (Math.random() * MAX_WEIGHT) + "\n").getBytes());

                aux.get(v1str).add(v2str);
                if (directed == 0) {
                    aux.get(v2str).add(v1str);
                }
            }

            fos.close();
        } catch (IOException e) {
            System.err.println("No se ha podido crear el fichero");
            e.printStackTrace();
        }
        return f;
    }

    public HashMap<String, HashMap<String, Double>> getGraph() {
        return graph;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean getDirected() {
        return this.directed;
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public boolean addVertex(String vertex) {
        if (this.graph.containsKey(vertex)) {
            return false;
        }
        this.graph.put(vertex, new HashMap<String, Double>());
        return true;
    }

    public boolean addEdge(String v1, String v2, double w) {
        addVertex(v1);
        addVertex(v2);
        this.graph.get(v1).put(v2, w);
        if (!this.directed) {
            this.graph.get(v2).put(v1, w);
        }
        return true;
    }

    public Double getWeight(String v1, String v2) {
        return containsEdge(v1, v2) ? this.graph.get(v1).get(v2) : null;
    }

    private boolean containsEdge(String v1, String v2) {
        if (this.graph.containsKey(v1)) {
            return this.graph.get(v1).containsKey(v2);
        }
        return false;
    }

    public double getPrimW() {
        return this.primW;
    }

    public double getPrimPQW() {
        return this.primPQW;
    }

    public double getKruskalW() {
        return this.kruskalW;
    }

    /**
     * Implementación del algoritmo de Prim SIN priority queue
     * 
     * @return HashMap<String, HashMap<String, Double>> El árbol resultante
     */
    public HashSet<Edge> getPrim() {
        if (!checkST()) {
            return null;
        }
        // If source is not set, set a new one
        if (this.source == null || !this.graph.containsKey(this.source)) {
            this.source = this.graph.keySet().iterator().next();
        }

        // Initialize structures
        HashSet<String> notVisited = new HashSet<>();
        for (String v : this.graph.keySet()) {
            notVisited.add(v);
        }
        notVisited.remove(this.source);

        HashMap<String, String> vertices = new HashMap<>();
        HashMap<String, Double> weights = new HashMap<>();
        for (String vertex : notVisited) {
            Double weight = getWeight(this.source, vertex);
            if (weight != null) { // adj to source
                vertices.put(vertex, this.source);
                weights.put(vertex, weight);
            } else {
                vertices.put(vertex, null);
                weights.put(vertex, Double.MAX_VALUE);
            }

        }
        vertices.put(this.source, this.source);
        weights.put(this.source, 0.0);

        HashSet<Edge> result = new HashSet<>();
        String leastWeight = null;
        while (!notVisited.isEmpty()) {
            // Get minimum weight
            double min = Double.MAX_VALUE;
            leastWeight = null;
            for (String v : notVisited) {
                double weight = weights.get(v);
                if (weight < min) {
                    min = weight;
                    leastWeight = v;
                }
            }

            if (leastWeight == null) {// if not connected
                break;
            }
            notVisited.remove(leastWeight); // Now it's visited

            // Add to result
            String vertAssoc = vertices.get(leastWeight);
            double w = getWeight(vertAssoc, leastWeight);
            result.add(new Edge(vertAssoc, leastWeight, w));
            this.primW += w;

            // Update values in notVisited
            for (String to : notVisited) {
                Double weight = getWeight(leastWeight, to); // If null or bigger, nothing to do
                if (weight != null && weight < weights.get(to)) {
                    weights.put(to, weight);
                    vertices.put(to, leastWeight);
                }
            }
        }

        return result;
    }

    /**
     * @return HashMap<String, HashMap<String, Double>>
     */
    public HashSet<Edge> getPrimPQ() {
        if (!checkST()) {
            return null;
        }

        if (this.source == null || !this.graph.containsKey(this.source)) {
            this.source = this.graph.keySet().iterator().next();
        }

        HashSet<String> notVisited = new HashSet<>();
        for (String v : this.graph.keySet()) {
            notVisited.add(v);
        }
        notVisited.remove(this.source);

        PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
        HashSet<Edge> result = new HashSet<>();
        String from = this.source;
        String to;
        Edge aux;

        while (!notVisited.isEmpty()) {
            Double weight;
            for (Entry<String, Double> it : this.graph.get(from).entrySet()) {
                to = it.getKey();
                // Only add to the queue if the edge hasn't been visited
                if (notVisited.contains(to)) {
                    edges.add(new Edge(from, to, it.getValue()));
                }
            }

            do {
                aux = edges.poll();
                to = aux.getDestination();
            } while (!notVisited.contains(to)); // to prevent cycles
            from = aux.getSource();
            weight = aux.getWeight();

            this.primPQW += weight;
            notVisited.remove(to);
            result.add(new Edge(from, to, weight));

            from = to;
        }
        return result;
    }

    /**
     * @return HashMap<String, HashMap<String, Double>>
     */
    public HashSet<Edge> getKruskal() {
        if (!checkST()) {
            return null;
        }

        HashMap<String, Double> notVisited = new HashMap<>();
        for (String v : graph.keySet()) {
            notVisited.put(v, Double.MAX_VALUE);
        }

        HashMap<String, String> edges = new HashMap<>();
        while (!notVisited.isEmpty()) {
            String minKey = notVisited.keySet().iterator().next(); // Any key
            Double minValue = Double.MAX_VALUE;
            for (Entry<String, Double> e : notVisited.entrySet()) {
                if (Double.compare(e.getValue(), minValue) == -1) {
                    minValue = e.getValue();
                    minKey = e.getKey();
                }
            }
            notVisited.remove(minKey);

            for (Entry<String, Double> it : graph.get(minKey).entrySet()) {
                String to = it.getKey();
                Double weight = getWeight(edges.get(to), to);
                weight = weight == null ? Double.MAX_VALUE : weight;
                if (notVisited.containsKey(to) && it.getValue() < Double.MAX_VALUE && it.getValue() < weight) {
                    notVisited.put(to, it.getValue());
                    edges.put(to, minKey);
                }
            }
        }

        HashSet<Edge> result = new HashSet<>();
        for (Entry<String, String> it : edges.entrySet()) {
            String to = it.getKey();
            String from = it.getValue();
            double w = getWeight(from, to);
            result.add(new Edge(from, to, w));
            this.kruskalW += w;
        }
        return result;
    }

    public boolean checkST() {
        return this.numVertices <= this.numEdges - 1;
    }

    @Override
    public String toString() {
        String s = "Grafo:\n\t";
        int e = 0;

        for (Entry<String, HashMap<String, Double>> entry : this.graph.entrySet()) {
            s += entry.getKey() + entry.getValue() + "\n\t";
            e += entry.getValue().size();
        }
        return s + "\n numVertices=" + this.numVertices + "\n graph.size=" + this.graph.size() + "\n numEdges="
                + this.numEdges + "\n aristas=" + e;
    }

}
