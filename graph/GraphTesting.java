package graph;


import org.junit.Test;
import static org.junit.Assert.*;

/** Unit tests for the Graph class.
 *  @author Itzel Martinez
 */
public class GraphTesting {

    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
    }

    @Test
    public void addTest() {
        Graph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(2, 3);
        g.add(2, 4);
        g.add(4, 6);
        g.add(5, 6);
        g.remove(2, 3);
        assertEquals(7, g.vertexSize());
        assertEquals(3, g.edgeSize());
        assertEquals(false, g.contains(2, 3));
        g.remove(2);
        assertEquals(false, g.contains(2, 4));
        assertEquals(2, g.edgeSize());

        assertEquals("Initial graph has wrong maxvertex", 7, g.maxVertex());
        assertEquals("Initial graph has wrong out degree", 1, g.outDegree(4));
        assertEquals("Initial graph has wrong out degree", 0, g.outDegree(2));

        assertEquals(true, g.contains(1));
        assertEquals(true, g.contains(5));
        assertEquals(true, g.contains(7));
        assertEquals(false, g.contains(8));

    }

    @Test
    public void removeTest() {
        UndirectedGraph graphy = new UndirectedGraph();
        graphy.add();
        graphy.add();
        graphy.remove(2);
        graphy.remove(1);
        assertEquals(false, graphy.contains(2));
        assertEquals(false, graphy.contains(1));
        assertEquals(false, graphy.contains(2, 3));
        assertEquals(false, graphy.contains(4, 6));

        int one = graphy.add();
        int two = graphy.add();
        int three = graphy.add();
        int four = graphy.add();
        int five = graphy.add();
        int six = graphy.add();
        int seven = graphy.add();

        graphy.add(1, 2);
        graphy.add(2, 3);
        graphy.add(3, 4);
        graphy.add(4, 5);
        graphy.add(5, 6);
        graphy.add(6, 7);

        assertEquals(true, graphy.contains(1, 2));
        assertEquals(true, graphy.contains(2, 1));

        assertEquals(true, graphy.contains(2, 3));
        assertEquals(true, graphy.contains(3, 2));

        assertEquals(true, graphy.contains(3, 4));
        assertEquals(true, graphy.contains(4, 3));

        assertEquals(true, graphy.contains(4, 5));
        assertEquals(true, graphy.contains(5, 4));

        assertEquals(true, graphy.contains(5, 6));
        assertEquals(true, graphy.contains(6, 5));

        assertEquals(true, graphy.contains(6, 7));
        assertEquals(true, graphy.contains(7, 6));


        graphy.remove(1, 2);
        graphy.remove(2, 3);
        graphy.remove(6, 7);
        assertEquals(false, graphy.contains(1, 2));
        assertEquals(false, graphy.contains(2, 1));
        assertEquals(false, graphy.contains(3, 2));
        assertEquals(false, graphy.contains(7, 6));



    }


    @Test
    public void sucessoridTest() {
        DirectedGraph second = new DirectedGraph();
        second.add();
        second.add();
        second.add();
        second.add();
        second.add(1, 2);
        second.add(1, 3);
        second.add(1, 4);
        int sucessor = second.successor(1, 2);
        assertEquals(4, second.successor(1, 2));
        assertEquals(2, second.successor(1, 0));
        assertEquals(3, second.successor(1, 1));

        int one = second.edgeId(1, 2);
        int two = second.edgeId(1, 3);
        int three = second.edgeId(1, 4);



        UndirectedGraph undy = new UndirectedGraph();
        undy.add();
        undy.add();
        undy.add();
        int hey = undy.edgeId(1, 2);
        int what = undy.edgeId(1, 3);
        int yo = undy.edgeId(1, 4);

        int you = undy.edgeId(2, 1);
        int up = undy.edgeId(3, 1);
        int homie = undy.edgeId(4, 1);

        assertEquals(hey, you);
        assertEquals(what, up);
        assertEquals(yo, homie);

    }

    @Test
    public void mineTest() {
        UndirectedGraph third = new UndirectedGraph();
        third.add();
        third.add();
        third.add();
        assertEquals(true, third.mine(3));
        assertEquals(false, third.mine(4));
        third.checkMyVertex(2);

    }

    @Test
    public void undirectedGraphTest() {
        UndirectedGraph fourth = new UndirectedGraph();
        fourth.add();
        fourth.add();
        fourth.add();
        fourth.add();
        fourth.add();
        fourth.add();
        fourth.add();
        fourth.add();
        fourth.add();
        int ten = fourth.add();
        fourth.add(1, 2);
        fourth.add(1, 3);
        fourth.add(1, 4);
        assertEquals(3, fourth.inDegree(1));
        assertEquals(0, fourth.inDegree(6));
        assertEquals(1, fourth.inDegree(2));
        assertEquals(4, fourth.predecessor(1, 2));
        assertEquals(0, fourth.predecessor(6, 2));
        assertEquals(1, fourth.predecessor(3, 0));
        assertEquals(1, fourth.outDegree(2));
        assertEquals(3, fourth.outDegree(1));



    }


    @Test
    public void directedGraphTest() {
        DirectedGraph fifth = new DirectedGraph();
        fifth.add();
        fifth.add();
        fifth.add();
        fifth.add();
        fifth.add();
        fifth.add();
        fifth.add();
        fifth.add();
        fifth.add();
        int ten = fifth.add();

        fifth.add(2, 1);
        fifth.add(2, 3);
        fifth.add(2, 4);


        assertEquals(1, fifth.inDegree(1));
        assertEquals(0, fifth.inDegree(6));
        assertEquals(0, fifth.inDegree(2));

        assertEquals(3, fifth.outDegree(2));
        assertEquals(0, fifth.outDegree(1));

        assertEquals(0, fifth.predecessor(2, 1));
        assertEquals(0, fifth.predecessor(6, 2));
        assertEquals(2, fifth.predecessor(3, 0));

        fifth.add(5, 2);
        fifth.add(6, 5);

        assertEquals(5, fifth.predecessor(2, 0));

        fifth.add(10, 2);

        assertEquals(10, fifth.predecessor(2, 1));

        assertEquals(0, fifth.predecessor(2, 4));

    }
    /**fifth.add(7,2);
     // this errors here when taking the
     */
    /**assertEquals(7, fifth.predecessor(2, 2));*/



}
