package graph;
import java.util.ArrayList;
import org.junit.Test;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import java.util.List;

/** Unit tests for the Traversal class.
 *  @author Itzel Martinez
 */
public class TraversalTesting {


    private class FindDpList extends DepthFirstTraversal {
        private ArrayList<Integer> visited;
        private ArrayList<Integer> postVisited;



        public FindDpList(Graph G) {
            super(G);
            visited = new ArrayList<Integer>();
            postVisited = new ArrayList<Integer>();

        }

        protected boolean visit(int v) {
            visited.add(v);
            super.visit(v);
            return true;

        }

        protected ArrayList<Integer> returnVisitedList() {
            return visited;
        }

        protected ArrayList<Integer> returnPostVisitedList() {
            return postVisited;
        }
        protected boolean postVisit(int a) {
            postVisited.add(a);
            return true;
        }






    }





    private class FindBfList extends BreadthFirstTraversal {

        ArrayList<Integer> visited;


        public FindBfList(Graph G) {
            super(G);
            visited = new ArrayList<Integer>();

        }


        protected boolean visit(int v) {
            visited.add(v);
            return super.visit(v);


        }

        protected ArrayList<Integer> returnVisitedList() {
            return visited;
        }


    }



    @Test
    public void breadthfirstraversaltestwithclients() {

        Graph graph = new DirectedGraph();
        graph.add();
        graph.add();
        graph.add();
        graph.add();
        int five = graph.add();

        graph.add(5, 4);
        graph.add(5, 3);
        graph.add(4, 1);
        graph.add(3, 2);
        graph.add(1, 5);

        FindBfList first = new FindBfList(graph);
        first.traverse(5);

        List<Integer> correctlist = asList(5, 4, 3, 1, 2);
        assertEquals(correctlist, first.returnVisitedList());




    }



    @Test
    public void depthfirstraversaltest() {


        Graph graph = new DirectedGraph();
        graph.add();
        graph.add();
        graph.add();
        graph.add();
        int five = graph.add();

        graph.add(5, 4);
        graph.add(5, 3);
        graph.add(4, 1);
        graph.add(3, 2);
        graph.add(1, 5);

        FindDpList depthfirstobj = new FindDpList(graph);
        depthfirstobj.traverse(5);


        List<Integer> correctlist = asList(5, 3, 2, 4, 1);
        List<Integer> postvistcorrectlist = asList(2, 3, 1, 4, 5);

        assertEquals(correctlist, depthfirstobj.returnVisitedList());
        assertEquals(postvistcorrectlist,
                     depthfirstobj.returnPostVisitedList());





    }










}
