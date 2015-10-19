package graph;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;


/* See restrictions in Graph.java. */

/** Represents an undirected graph.  Out edges and in edges are not
 *  distinguished.  Likewise for successors and predecessors.
 *
 *  @author Itzel Martinez
 */
public class UndirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return false;
    }

    @Override
    public int inDegree(int v) {
        int totsindegree = 0;

        if (contains(v)) {
            for (int vertice : this.vertices()) {
                if (this.contains(vertice, v)) {
                    totsindegree += 1;

                }
            }

        }
        return totsindegree;
    }


    @Override
    public int predecessor(int v, int k) {
        return this.successor(v , k);
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        ArrayList<Integer> predsubject = new ArrayList<Integer>();
        if (mine(v)) {
            for (int i : this.vertices()) {
                if (contains(v, i)) {
                    predsubject.add(i);
                }
            }

            Iterator<Integer> prediter = predsubject.iterator();
            Iteration<Integer> predy = Iteration.iteration(prediter);
            return predy;

        }

        Iterator<Integer> empt = Collections.emptyIterator();
        Iteration<Integer> emptiter = Iteration.iteration(empt);
        return emptiter;

    }

}
