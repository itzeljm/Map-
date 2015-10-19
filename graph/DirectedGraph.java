package graph;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;


/* See restrictions in Graph.java. */

/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author Itzel Martinez
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
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


        ArrayList<Integer> pred = new ArrayList<Integer>();

        if (mine(v)) {
            for (int i : this.vertices()) {
                if (contains(i, v)) {
                    pred.add(i);
                }
            }

            if (pred.size() > k) {
                return pred.get(k);
            }
            return 0;
        }
        return 0;
    }

    @Override
    public Iteration<Integer> predecessors(int v) {

        ArrayList<Integer> predsubject = new ArrayList<Integer>();

        if (mine(v)) {
            for (int i : this.vertices()) {
                if (contains(i, v)) {
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
