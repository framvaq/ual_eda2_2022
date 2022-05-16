package org.eda2.practica2;

public class Edge implements Comparable<Edge> {
    private String from;
    private String to;
    private double weight;

    public Edge(String from, String to, Double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public String getSource() {
        return this.from;
    }

    public String getDestination() {
        return this.to;
    }

    public Double getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return "{" + this.from + " -> " + this.to + "}";
    }

}

