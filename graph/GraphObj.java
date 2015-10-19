package graph;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Set;
import java.util.Collections;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.HashSet;

/* See restrictions in Graph.java. */

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author Itzel Martinez
 */


abstract class GraphObj extends Graph {

    /** A new, empty Graph. */
    GraphObj() {

        edges = new ArrayList<int[]>();
        _graph = new HashMap<Integer, ArrayList<Integer>>();



    }


    @Override
    public int vertexSize() {
        return _graph.size();
    }

    @Override
    public int maxVertex() {
        /** could also make a hashset of mkeyset and iterate through that*/
        int max = Integer.MIN_VALUE;

        if (!_graph.isEmpty()) {

            for (int i : vertices()) {
                max = Math.max(max, i);

            }
            return max;
        }

        return 0;
    }

    @Override
    public int edgeSize() {
        int total = 0;
        if (!_graph.isEmpty()) {


            for (ArrayList<Integer> b : _graph.values()) {
                for (int c : b) {
                    total++;
                }
            }

            if (isDirected()) {
                return total;
            }
            int self = 0;
            for (int i : _graph.keySet()) {
                for (int f : _graph.get(i)) {
                    if (i == f) {
                        self += 1;
                    }
                }
            }
            return (total + self) / 2;
        }

        return 0;
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {


        if (contains(v)) {
            return _graph.get(v).size();

        }
        return 0;


    }


    @Override
    public abstract int inDegree(int v);



    @Override
    public boolean contains(int u) {
        if (_graph.containsKey(u)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(int u, int v) {
        if (_graph.containsKey(u)) {
            for (int i : _graph.get(u)) {
                if (i == v) {
                    return true;
                }
            }
            return false;

        }

        return false;
    }

    @Override
    public int add() {
        int lowest = this.maxVertex();

        ArrayList<Integer> empty = new ArrayList<Integer>();
        if (_graph.isEmpty()) {
            _graph.put(1, empty);
            return 1;
        }

        for (int i : _graph.keySet()) {
            lowest  = Math.min(i, lowest);
        }

        while (_graph.containsKey(lowest)) {
            lowest += 1;
            if (!_graph.containsKey(lowest)) {
                _graph.put(lowest, empty);
                break;
            }

        }

        return lowest;
    }

    @Override
    public int add(int u, int v) {
        if (!isDirected()) {

            if (!_graph.get(u).contains(v)) {
                _graph.get(u).add(v);
                int[] edgy = new int[2];
                edgy[0] = u;
                edgy[1] = v;
                edges.add(edgy);

            }

            if (!_graph.get(v).contains(u)) {
                _graph.get(v).add(u);
                int[] edgy = new int[2];
                edgy[0] = v;
                edgy[1] = u;
                edges.add(edgy);

            }

            return u;
        }

        if (!_graph.get(u).contains(v)) {
            _graph.get(u).add(v);
            int[] edgy = new int[2];
            edgy[0] = u;
            edgy[1] = v;
            edges.add(edgy);

        }

        return u;
    }




    @Override
    public void remove(int v) {

        if (_graph.containsKey(v)) {
            _graph.remove(v);


            HashSet<Integer> a = new HashSet<Integer>(_graph.keySet());

            for (int edg : a) {
                ArrayList<Integer> b = new ArrayList<Integer>(_graph.get(edg));
                for (int c : b) {
                    if (c == v) {
                        int index = b.indexOf(c);
                        _graph.get(edg).remove(index);
                    }
                }
            }
        }
    }







    @Override
    public void remove(int u, int v) {
        if (isDirected()) {
            int index1 = 0;
            boolean exists = false;
            for (int[] i : edges) {
                if (i[0] == u && i[1] == v) {
                    exists = true;
                    index1 = edges.indexOf(i);
                }
            }

            if (exists) {
                edges.remove(index1);
            }

            if (contains(u, v)) {
                ArrayList<Integer> youlinks = _graph.get(u);
                if (youlinks.contains(v)) {
                    int index3 = youlinks.indexOf(v);
                    youlinks.remove(index3);
                }
            }
        }
        if (contains(u, v)) {
            int index = 0; boolean exists = false;
            for (int[] i : edges) {
                if (i[0] == u && i[1] == v) {
                    exists = true;
                    index = edges.indexOf(i);
                }
            }
            if (exists) {
                edges.remove(index);
            }
            int index2 = 0; boolean exists2 = false;
            for (int[] i : edges) {
                if (i[0] == v && i[1] == u) {
                    exists2 = true;
                    index2 = edges.indexOf(i);
                }
            }
            if (exists2) {
                edges.remove(index2);
            }
            ArrayList<Integer> arraylist = _graph.get(u);
            if (arraylist.contains(v)) {
                int index1 = arraylist.indexOf(v);
                int index3 = _graph.get(v).indexOf(u);
                arraylist.remove(index1);
                _graph.get(v).remove(index3);
            }
        }
    }


    @Override
    public Iteration<Integer> vertices() {
        Set<Integer> vertices = _graph.keySet();
        Iterator<Integer> a = vertices.iterator();
        Iteration<Integer> b = Iteration.iteration(a);
        return b;
    }

    @Override
    public int successor(int v, int k) {


        if (_graph.containsKey(v)) {
            ArrayList<Integer> a = _graph.get(v);
            if (0 <= k && k < a.size()) {
                return a.get(k);
            } else {
                return 0;
            }


        }

        return 0;
    }

    @Override
    public abstract int predecessor(int v, int k);




    @Override
    public Iteration<Integer> successors(int v) {


        Iterator<Integer> empt = Collections.emptyIterator();
        Iteration<Integer> emptiter = Iteration.iteration(empt);

        if (mine(v)) {
            if (_graph.containsKey(v)) {
                ArrayList<Integer> a = _graph.get(v);
                Iterator<Integer> b = a.iterator();
                Iteration<Integer> c = Iteration.iteration(b);
                return c;
            }

            return emptiter;
        }

        return emptiter;

    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {

        if (!_graph.isEmpty()) {

            Iterator<int[]> biggy = edges.iterator();
            Iteration<int[]> edgeiteration = Iteration.iteration(biggy);
            return edgeiteration;

        }

        Iterator<int[]> empt = Collections.emptyIterator();
        Iteration<int[]> emptiter = Iteration.iteration(empt);
        return emptiter;

    }



    @Override
    protected boolean mine(int v) {
        if (_graph.containsKey(v)) {
            return true;
        }
        return false;
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!mine(v)) {
            throw new NoSuchElementException("Vertex is not in the graph");
        }
    }



    @Override
    protected int edgeId(int u, int v) {

        if (contains(u, v)) {

            if (isDirected()) {
                return  (((u + v) * (u + v + 1)) / 2 + v);
            }

            int first = Math.min(u, v);
            int second = Math.max(u, v);

            return (((first + second) * (first + second + 1)) / 2 + second);
        }

        return 0;
    }

    /** This is my graph using adjancency lists. */
    protected HashMap<Integer, ArrayList<Integer>> _graph;

    /**Araylist that keeps track of my edges. */
    protected ArrayList<int[]> edges;

}



