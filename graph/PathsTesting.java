package graph;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.HashMap;

/**12.3.7,.6
   trickiest part is that you are gonig to have to create a queue data
   strucutre that implements the queue interface
   that has a priority that takes the smaller distance has higher priorities

   distance queue
   need to give traversal a distance queue implement queue interface
   such that the thing that is closes is going to be removed by removed
   and also have to make sure that whenever you use queue method like add
   and addall it doesnt break
   has to make remove work
   we need to tell the distance queue that when
   i process an edge if it has updated then
   need to update the new distance edge
   need to implent queue interface
   look back at lecture 17
   three wayds to implement using extend which alreday implement
   queue
   delegates or adapt

   take araylist priority que queue treeset
   if we choose to create a fringe data strucutre by extending
   an existing class that already
   implements queue
   youl need to somewho ensure that the remove method
   always remove the smalles vertes
   in t he fringe

   **vertex size is given by its weight plyus its estimatedDistance

   create a class using comparator - extension
   create

   weight for a vertex is its distance from source

   Edge weights are provided by the class that overrides ShortestPaths
   (in the form of the getWeight(u, v)) method.

   Vertex weights are the total estimated distance, and you'll store them using
   the setWeight(v, w) method.
   Knowing what to change them is exactly what A* does, slowly
   making things better as it goes.
   See lecture slides or DSIJ.


*/



public class PathsTesting {

    private class VisualGraph extends SimpleShortestPaths {

        public VisualGraph(LabeledGraph<Double, Double> G,
                           int start, int destination) {

            super(G, start, destination);
            _graph = G;
            _estimatedDistanceBook = new HashMap<Integer, Double>();

        }



        /** Returns the current weight of edge (U, V) in the graph.
         * If (U, V) is not in the graph, returns positive infinity. */
        @Override
        public double getWeight(int u, int v) {
            if (_graph.contains(u, v)) {
                return _graph.getLabel(u, v);
            }
            return Double.POSITIVE_INFINITY;
        }

        /** Returns an estimated heuristic weight
         * of the shortest path from vertex
         *  V to the destination vertex (if any).  This is assumed to be less
         *  than the actual weight, and is 0 by default. */
        @Override
        public double estimatedDistance(int v) {
            if (_graph.contains(v)) {
                return _estimatedDistanceBook.get(v);
            }
            return 0;
        }

        public void setEstimate(int vertex, double estimate) {
            _estimatedDistanceBook.put(vertex, estimate);

        }

        /**double estimation = Integer.MIN_VALUE;
           double approxweight = 0;
           int whereWereAt = getDest();


           if (getDest() != 0) {


           while (getPredecessor(whereWereAt) != v) {
           estimation += getWeight(getPredecessor(whereWereAt), whereWereAt);
           whereWereAt = getPredecessor(whereWereAt);
           }
           return estimation;

           } else {
           return 0;

           }*/




        LabeledGraph<Double, Double> _graph;
        HashMap<Integer, Double> _estimatedDistanceBook;

    }



    @Test
    public void simpleshortestpathstesting() {
        Graph tempgraph = new DirectedGraph();
        LabeledGraph<Double, Double> graphy =
            new LabeledGraph<Double, Double>(tempgraph);
        graphy.add(); graphy.add();
        graphy.add(); graphy.add();
        graphy.add(); graphy.add();
        graphy.add(); graphy.add();
        graphy.add(4, 2, 12.2);
        graphy.add(4, 3, 102.0);
        graphy.add(4, 5, 11.2);
        graphy.add(5, 6, 30.0);
        graphy.add(2, 3, 6.5);
        graphy.add(5, 3, 9.1);
        graphy.add(4, 1, 1.99);
        graphy.add(6, 7, 564564287946.2);
        graphy.add(1, 8, 19.0);
        VisualGraph tester = new VisualGraph(graphy, 4, 3);
        tester.setEstimate(4, 102.0);
        tester.setEstimate(1, 1000.0);
        tester.setEstimate(7, 12000.0);
        tester.setEstimate(8, 3000.0);
        tester.setEstimate(2, 4.0);
        tester.setEstimate(3, 0.0);
        tester.setEstimate(5, 100.1);
        tester.setEstimate(6, 40.0); tester.setPaths();

        assertEquals(4 , tester.getPredecessor(5));
        assertEquals(4 , tester.getPredecessor(2));
        assertEquals(12.2, tester.getWeight(4, 2), 0.01);
        assertEquals(0.0, tester.estimatedDistance(3), 0.01);
        assertEquals(100.1, tester.estimatedDistance(5), 0.01);
        List<Integer> correctlist = tester.pathTo(3);
        int i = 0;


        while (i < correctlist.size()) {
            System.out.println(correctlist.get(i));
            i += 1;
        }
        assertEquals(2, correctlist.get(1), .0);
        assertEquals(4, correctlist.get(0), .0);
        assertEquals(3, correctlist.get(2), .0);



    }
}

/** Graph tempgraph = new DirectedGraph();
    LabeledGraph<Double, Double> graphy =
    new LabeledGraph<Double, Double>(tempgraph);

    graphy.add();
    graphy.add();
    graphy.add();
    graphy.add();
    graphy.add();
    int six = graphy.add();
    graphy.add(4, 2, 12.2);
    graphy.add(4, 3, 102.0);
    graphy.add(4, 5, 11.2);
    graphy.add(5, 6, 30.0);
    graphy.add(2, 3, 6.5);
    graphy.add(5, 3, 9.1);
    VisualGraph tester = new VisualGraph(graphy, 4, 3);
    tester.setEstimate(4, 102.0);
    tester.setEstimate(2, 4.0);
    tester.setEstimate(3, 0.0);
    tester.setEstimate(5, 5.1);
    tester.setEstimate(6, 40.0);
    tester.setPaths();
    tester.setPredecessor(5, 4);
    tester.setPredecessor(2, 4);
    tester.setPredecessor(3, 2);
    tester.setPredecessor(3, 4);
    tester.setPredecessor(3, 5);
    tester.setPredecessor(6, 5);

    assertEquals( 4, tester.getPredecessor(5));

    assertEquals( 4 , tester.getPredecessor(2));

    assertEquals(12.2, tester.getWeight(4, 2), 0.01);

    assertEquals(0.0, tester.estimatedDistance(3), 0.01);

    assertEquals(102.0, tester.estimatedDistance(4), 0.01);

    List<Integer> correctlist = tester.pathTo(3);

    /**int i = 0;
    while (i < correctlist.size()) {
    System.out.println(correctlist.get(i));
    i += 1;
    }
    /
    assertEquals(2, correctlist.get(1), .0);*/

/**public VisualGraph(Graph G, int start, int destination) {
   super(G, start, destination);
   _graph = G;
   _secondWeightBook = new HashMap<Integer, Integer>();
   _estimatedDistanceBook = new HashMap<Integer, Double>();
   }



   Returns the current weight of edge (U, V) in the graph.  If (U, V) is
   *  not in the graph, returns positive infinity.
   @Override
   public double getWeight(int u, int v) {
   if (_graph.contains(u, v)) {
   return _predecessors.get(u).get(v);
   }
   return Integer.MAX_VALUE;
   } /
   return (getWeight(v) - getWeight(u));
   }

   return Integer.MAX_VALUE;
   }

   Returns an estimated heuristic weight of the shortest path from vertex
   *  V to the destination vertex (if any).  This is assumed to be less
   *  than the actual weight, and is 0 by default. /
   @Override
   public double estimatedDistance(int v) {
   return _estimatedDistanceBook.get(v);


   /**double estimation = Integer.MIN_VALUE;
   double approxweight = 0;
   int whereWereAt = getDest();

   if (getDest() != 0) {


   while (getPredecessor(whereWereAt) != v) {
   estimation += getWeight(getPredecessor(whereWereAt), whereWereAt);
   whereWereAt = getPredecessor(whereWereAt);
   }
   return estimation;

   } else {
   return 0;

   }/

   }

   HashMap<Integer, Integer> _secondWeightBook;
   HashMap<Integer, Integer> _predecessors;
   Graph _graph; */

