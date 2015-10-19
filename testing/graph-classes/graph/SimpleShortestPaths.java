package graph;
import java.util.HashMap;
/* See restrictions in Graph.java. */

/** A partial implementation of ShortestPaths that contains the weights of
 *  the vertices and the predecessor edges.   The client needs to
 *  supply only the two-argument getWeight method.
 *  @author Itzel Martinez =)
 */
public abstract class SimpleShortestPaths extends ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public SimpleShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public SimpleShortestPaths(Graph G, int source, int dest) {
        super(G, source, dest);
        _predecessor = new HashMap<Integer, Integer>();
        _weightBook = new HashMap<Integer, Double>();
    }


    /** Returns the current weight of vertex V in the graph.  If V is
     *  not in the graph, returns positive infinity. */
    @Override
    public double getWeight(int v) {

        if (_weightBook.containsKey(v)) {
            return _weightBook.get(v);
        }
        return Double.POSITIVE_INFINITY;
    }

    /** Set getWeight(V) to W. Assumes V is in the graph. */

    @Override
    protected void setWeight(int v, double w) {

        _weightBook.put(v, w);


    }

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    @Override
    public int getPredecessor(int v) {

        if (_predecessor.containsKey(v)) {
            if (_predecessor.get(v) == null) {
                return 0;
            } else {
                return _predecessor.get(v);
            }
        } else {
            return 0;
        }
    }


    /** Set getPredecessor(V) to U. */

    @Override
    protected void setPredecessor(int v, int u) {

        _predecessor.put(v, u);
    }

   /**Keeps track of the predecessors. */
    private HashMap<Integer, Integer> _predecessor;


    /**Keeps track of the weights. */

    private HashMap<Integer, Double> _weightBook;

}
