package graph;

/* See restrictions in Graph.java. */
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

/** The shortest paths through an edge-weighted graph.
 *  By overrriding methods getWeight, setWeight, getPredecessor, and
 *  setPredecessor, the client can determine how to represent the weighting
 *  and the search results.  By overriding estimatedDistance, clients
 *  can search for paths to specific destinations using A* search.
 *  @author Itzel Martinez
 */
public abstract class ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
        _fringe = new PriorityQueue<Integer>();



    }

    /** Initialize the shortest paths.  Must be called before using
     *  getWeight, getPredecessor, and pathTo. */

    /**run dijstraks algo
       a start is the same as above youre just taking heuristic which
       is estimated distance
       to change it from dijstraks to A* is just  aline
       take into account estimated distance
       dijstraks extends traversal?
       dijstraks is the same as traversal? idk
       nest two withing shortestpaths*/
    /**if (min > getWeight(vertice)) {.
       first = vertice;
       } setWeight(vertice, Integer.MAX_VALUE);
       setPredecessor(vertice, null);
       _fringe.insert(vertice);*/

    public void setPaths() {

        CompareHelper comp = new CompareHelper();
        _fringetoholdis = new PriorityQueue<Integer>(_G.vertexSize(), comp);

        int first = getSource();
        for (int vertice  : _G.vertices()) {

            if (vertice != getSource()) {

                setWeight(vertice, Double.POSITIVE_INFINITY);
                _fringetoholdis.add(vertice);
                setPredecessor(vertice, 0);

            } else {
                setWeight(vertice, 0);
                setPredecessor(vertice, 0);
                _fringetoholdis.add(vertice);
            }


        }


        while (!_fringetoholdis.isEmpty()) {
            int v = _fringetoholdis.remove();
            _correctlistoreturn.add(v);

            if (v == getDest()) {
                break;
            }

            for (int s : _G.successors(v)) {

                if ((getWeight(v) + getWeight(v, s)) < getWeight(s)) {

                    setWeight(s, (getWeight(v) + getWeight(v, s)));

                    setPredecessor(s, v);
                    _fringetoholdis.remove(s);
                    _fringetoholdis.add(s);
                }



            }
        }
    }



    /** Returns the starting vertex. */
    public int getSource() {
        return _source;
    }

    /** Returns the target vertex, or 0 if there is none. */
    public int getDest() {
        return _dest;
    }

    /** Returns the current weight of vertex V in the graph.  If V is
     *  not in the graph, returns positive infinity. */
    public abstract double getWeight(int v);

    /** Set getWeight(V) to W. Assumes V is in the graph. */
    protected abstract void setWeight(int v, double w);

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    public abstract int getPredecessor(int v);

    /** Set getPredecessor(V) to U. */
    protected abstract void setPredecessor(int v, int u);

    /** Returns an estimated heuristic weight of the shortest path from vertex
     *  V to the destination vertex (if any).  This is assumed to be less
     *  than the actual weight, and is 0 by default. */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    protected abstract double getWeight(int u, int v);

    /** Returns a list of vertices starting at _source and ending
     *  at V that represents a shortest path to V.  Invalid if there is a
     *  destination vertex other than V. */
    public List<Integer> pathTo(int v) {
        /** FIXME  shoudl give actual path*/
        List<Integer> returnlist = new ArrayList<Integer>();
        while (getPredecessor(v) != 0) {
            returnlist.add(v);
            v = getPredecessor(v);
        }

        returnlist.add(_source);
        Collections.reverse(returnlist);
        return returnlist;

    }




    /**for (int i = 0 ; i < _correctlistoreturn.size()) {
       int a = _correctlistoreturn.get(counter);

       if ( a != getDest()) {
       returnlist.add(_correctlistoreturn.get(counter));
       } else {
       break;
       }


       }*/


    /**Stack<Integer> temporaryStack = new Stack<Integer>();

       if (this.getDest() != v) {
       /**return  new error("cannot call with this vertex.");
       }

       int x = getDest();

       while (getPredecessor(x) != getSource()) {
       temporaryStack.push(x);
       x = getPredecessor(x);
       temporaryStack.push(x);
       }

       temporaryStack.push(getSource());

       while (!temporaryStack.empty()) {
       returnlist.add(temporaryStack.pop());
       }*/



    /** Returns a list of vertices starting at the source and ending at the
     *  destination vertex. Invalid if the destination is not specified. */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }

    /** Keeps track of unvisited vertices. */
    protected final PriorityQueue<Integer> _fringe;

    /** Keeps track of distances of the vertices. */

    protected  PriorityQueue<Integer> _fringetoholdis;

    /** It is the correct list of order to return for shortest paths. */

    protected ArrayList<Integer> _correctlistoreturn = new ArrayList<Integer>();
    /** The graph being searched. */
    protected final Graph _G;
    /** The starting vertex. */
    private final int _source;
    /** The target vertex. */
    private final int _dest;


    /** Class that reorders the fringe when an element is removed. */
    private class CompareHelper<T> implements Comparator<T> {

        /** Constructor for a compareHelper. */
        public CompareHelper() {
            super();
        }


        @Override
        public int compare(T first, T second) {
            double val1 = getWeight((Integer) first)
                + estimatedDistance((Integer) first);

            double val2 = getWeight((Integer) second)
                + estimatedDistance((Integer) second);

            if (val1 > val2) {
                return 1;
            } else if (val1 < val2) {
                return -1;
            } else {
                return 0;
            }
        }

    }





}
