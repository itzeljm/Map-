package graph;

/* See restrictions in Graph.java. */
import java.util.Arrays;
import java.util.HashSet;
import java.util.Collection;
import java.util.Queue;
import java.util.Stack;


/** Implements a generalized traversal of a graph.  At any given time,
 *  there is a particular collection of untraversed vertices---the "fringe."
 *  Traversal consists of repeatedly removing an untraversed vertexex
 *  from the fringe, visting it, and then adding its untraversed
 *  successors to the fringe.
 *
 *  Generally, the client will extend Traversal.  By overriding the visit
 *  method, the client can determine what happens when a node is visited.
 *  By supplying an appropriate type of Queue object to the constructor,
 *  the client can control the behavior of the fringe. By overriding the
 *  shouldPostVisit and postVisit methods, the client can arrange for
 *  post-visits of a node (as in depth-first search).  By overriding
 *  the reverseSuccessors and processSuccessor methods, the client can control
 *  the addition of neighbor vertexices
 * to the fringe when a vertexex is visited.
 *
 *  Traversals may be interrupted or restarted, remembering the previously
 *  marked vertexices.
 *  @author Itzel Martinez
 */
public abstract class Traversal {

    /** A Traversal of G, using FRINGE as the fringe. */
    protected Traversal(Graph G, Queue<Integer> fringe) {
        _G = G;
        _fringe = fringe;
        _visited = new HashSet<Integer>();
        _postVisited = new Stack<Integer>();
    }

    /** Unmark all vertices in the graph. */
    public void clear() {

        for (int i : _visited) {
            _visited.remove(i);
        }
    }


    /** Initialize the fringe to V0 and perform a traversal. */
    public void traverse(Collection<Integer> V0) {

        _fringe.addAll(V0);
        while (!_fringe.isEmpty()) {
            int value = _fringe.remove();

            if (!marked(value)) {
                mark(value);
                _fringe.add(value);

                if (!visit(value)) {
                    break;
                }

                for (int current : _G.successors(value)) {
                    if (processSuccessor(value, current)) {
                        _fringe.add(current);


                    }
                }
            } else {
                if (shouldPostVisit(value) && !_postVisited.contains(value)) {
                    postVisit(value);
                    _postVisited.add(value);
                }
            }

        }
    }

















    /** Initialize the fringe to { V0 } and perform a traversal. */
    public void traverse(int v0) {
        traverse(Arrays.<Integer>asList(v0));
    }

    /** Returns true iff V has been marked. */
    protected boolean marked(int v) {
        if (_visited.contains(v)) {
            return true;
        }
        return false;
    }




    /** Mark vertex V. */
    protected void mark(int v) {
        if (_G.contains(v) && !_visited.contains(v)) {
            _visited.add(v);
        }

    }


    /** Perform a visit on vertex V.  Returns false iff the traversal is to
     *  terminate immediately. */
    protected boolean visit(int v) {
        return true;
    }

    /** Return true if we should postVisit V after traversing its
     *  successors.  (Post-visiting generally is useful only for depth-first
     *  traversals, although we define it for all traversals.) */
    protected boolean shouldPostVisit(int v) {
        return false;
    }

    /** Revisit vertexex V after traversing its successors.  Returns false iff
     *  the traversal is to terminate immediately. */
    protected boolean postVisit(int v) {
        return true;
    }

    /** Return true if we should schedule successors of V in reverse order. */
    protected boolean reverseSuccessors(int v) {
        /**gets siccesspr save it as a list
           reverse the lis*/

        return false;
    }

    /** Process successor V to U.  Returns true iff V is then to
     *  be added to the fringe.  By default, returns true iff V is unmarked. */
    protected boolean processSuccessor(int u, int v) {
        return !marked(v);
    }

    /** The graph being traversed. */
    private final Graph _G;
    /** The fringe. */
    protected final Queue<Integer> _fringe;
    /** The vertices already visited. */
    private final HashSet<Integer> _visited;

    /** Vertices that have been postvisited. */
    private final Stack<Integer> _postVisited;

}



