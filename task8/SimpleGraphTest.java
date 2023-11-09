import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleGraphTest {
    SimpleGraph testGraph = new SimpleGraph(8);

    @Test
    public void addVertex() {
        testGraph.AddVertex(5);

        assertEquals(5, testGraph.vertex[0].Value);

        for(int i = 0; i < testGraph.max_vertex; i ++) {
            assertFalse(testGraph.IsEdge(0, i));
        }
    }

    @Test
    public void addEdge() {
        testGraph.AddVertex(5);
        testGraph.AddVertex(10);

        assertEquals(5, testGraph.vertex[0].Value);
        assertEquals(10, testGraph.vertex[1].Value);

        testGraph.AddEdge(0, 1);
        assertTrue(testGraph.IsEdge(0, 1));
    }

    @Test
    public void removeEdge() {
        testGraph.AddVertex(5);
        testGraph.AddVertex(10);

        assertEquals(5, testGraph.vertex[0].Value);
        assertEquals(10, testGraph.vertex[1].Value);

        testGraph.AddEdge(0, 1);
        assertTrue(testGraph.IsEdge(0, 1));

        testGraph.RemoveEdge(0, 1);
        assertFalse(testGraph.IsEdge(0, 1));
    }

    @Test
    public void removeVertex() {
        testGraph.AddVertex(1);
        testGraph.AddVertex(2);
        testGraph.AddVertex(3);
        testGraph.AddVertex(4);
        testGraph.AddVertex(5);

        testGraph.AddEdge(0, 1);
        testGraph.AddEdge(0, 2);
        testGraph.AddEdge(0, 3);
        testGraph.AddEdge(1, 3);
        testGraph.AddEdge(2, 3);
        testGraph.AddEdge(3, 3);
        testGraph.AddEdge(2, 4);
        testGraph.AddEdge(3, 4);

        assertTrue(testGraph.IsEdge(0, 2));
        assertTrue(testGraph.IsEdge(2, 3));
        assertTrue(testGraph.IsEdge(2, 4));

        testGraph.RemoveVertex(2);
        assertFalse(testGraph.IsEdge(0, 2));
        assertFalse(testGraph.IsEdge(2, 3));
        assertFalse(testGraph.IsEdge(2, 4));
    }
}