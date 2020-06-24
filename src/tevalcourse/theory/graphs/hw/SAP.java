package tevalcourse.theory.graphs.hw;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class SAP {

    private final Digraph digraph;
    private final Map<Integer, Map<Integer, Map.Entry<Integer, Integer>>> cache;

    public SAP(Digraph digraph) {
        if (digraph == null) {
            throw new IllegalArgumentException("Digraph argument cannot be null");
        }
        this.digraph = digraph;
        this.cache = new HashMap<>();
    }

    public int length(int v, int w) {
        validateVertices(v, w);
        Integer found = getDistance(v, w);
        if (found == null) {
            return sap(v, w).getValue();
        }
        return found;
    }

    private Integer getDistance(int v, int w) {
        return Optional.ofNullable(cache.get(v))
                .flatMap(to -> Optional.ofNullable(to.get(w))
                        .map(Map.Entry::getValue))
                .orElse(null);
    }

    public int ancestor(int v, int w) {
        validateVertices(v, w);
        Integer found = getAncestor(v, w);
        if (found == null) {
            return sap(v, w).getKey();
        }
        return found;
    }

    private Integer getAncestor(int v, int w) {
        return Optional.ofNullable(cache.get(v))
                .flatMap(to -> Optional.ofNullable(to.get(w))
                        .map(Map.Entry::getKey))
                .orElse(null);
    }


    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        int minNumVertices = validateVertices(v, w);
        if (minNumVertices == 0) {
            return -1;
        }
        return sap(v, w).getValue();
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        int minNumVertices = validateVertices(v, w);
        if (minNumVertices == 0) {
            return -1;
        }
        return sap(v, w).getKey();
    }

    private Entry<Integer, Integer> sap(int v, int w) {
        BreadthFirstDirectedPaths vPaths = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths wPaths = new BreadthFirstDirectedPaths(digraph, w);

        int distance = Integer.MAX_VALUE;
        Integer ancestor = null;

        for (int vertex = 0; vertex < digraph.V(); vertex++) {
            if (vPaths.hasPathTo(vertex)
                    && vPaths.distTo(vertex) < distance
                    && wPaths.hasPathTo(vertex)
                    && wPaths.distTo(vertex) < distance) {
                int sum = vPaths.distTo(vertex) + wPaths.distTo(vertex);
                if (distance > sum) {
                    distance = sum;
                    ancestor = vertex;
                }
            }
        }

        if (distance == Integer.MAX_VALUE) {
            distance = -1;
            ancestor = -1;
        }

        Entry<Integer, Integer> entry = Map.entry(ancestor, distance);

        cache.putIfAbsent(v, new HashMap<>());
        cache.get(v).putIfAbsent(w, entry);

        cache.putIfAbsent(w, new HashMap<>());
        cache.get(w).putIfAbsent(v, entry);

        return entry;
    }

    private Entry<Integer, Integer> sap(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths vPaths = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths wPaths = new BreadthFirstDirectedPaths(digraph, w);

        int distance = Integer.MAX_VALUE;
        int ancestor = -2;

        for (int vertex = 0; vertex < digraph.V(); vertex++) {
            if (vPaths.hasPathTo(vertex)
                    && vPaths.distTo(vertex) < distance
                    && wPaths.hasPathTo(vertex)
                    && wPaths.distTo(vertex) < distance) {
                int sum = vPaths.distTo(vertex) + wPaths.distTo(vertex);
                if (distance > sum) {
                    distance = sum;
                    ancestor = vertex;
                }
            }
        }

        if (distance == Integer.MAX_VALUE) {
            distance = -1;
            ancestor = -1;
        }

        return Map.entry(ancestor, distance);
    }

    private void validateVertices(int v, int w) {
        int numVertices = digraph.V();
        if ((v < 0 || v >= numVertices) || (w < 0 || w >= numVertices)) {
            throw new IllegalArgumentException("Vertex not in range " + v);
        }
    }

    private int validateVertices(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new IllegalArgumentException("Null not valid argument");
        }
        int numVerticesV = validateVertices(v);
        int numVerticesW = validateVertices(w);
        return Math.min(numVerticesV, numVerticesW);
    }

    private int validateVertices(Iterable<Integer> vertices) {
        int numVertices = 0;
        for (Integer v : vertices) {
            if (v == null) {
                throw new IllegalArgumentException("Null not valid argument");
            }
            if (v < 0 || v >= digraph.V()) {
                throw new IllegalArgumentException("Vertex not in range " + v);
            }
            numVertices++;
        }
        return numVertices;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}