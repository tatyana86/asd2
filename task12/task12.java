import java.util.*;

class Vertex
{
    public int Value;
    public Vertex(int val)
    {
        Value = val;
    }
}

class SimpleGraph
{
    Vertex [] vertex;
    int [][] m_adjacency;
    int max_vertex;

    public SimpleGraph(int size)
    {
        max_vertex = size;
        m_adjacency = new int [size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex(int value)
    {
        for(int i = 0; i < vertex.length; i ++) {
            if(vertex[i] == null) {
                vertex[i] = new Vertex(value);
                break;
            }
        }
    }

    public void RemoveVertex(int v)
    {
        vertex[v] = null;

        for(int i = 0; i < vertex.length; i ++) {
            m_adjacency[v][i] = 0;
            m_adjacency[i][v] = 0;
        }
    }

    public boolean IsEdge(int v1, int v2)
    {
        if(vertex[v1] == null || vertex[v2] == null) {
            return false;
        }

        return m_adjacency[v1][v2] == 1 && m_adjacency[v2][v1] == 1;
    }

    public void AddEdge(int v1, int v2)
    {
        if(vertex[v1] == null || vertex[v2] == null) {
            return;
        }

        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
    }

    public void RemoveEdge(int v1, int v2)
    {
        if(vertex[v1] == null || vertex[v2] == null) {
            return;
        }

        m_adjacency[v1][v2] = 0;
        m_adjacency[v2][v1] = 0;
    }

    public ArrayList<Vertex> WeakVertices()
    {
        ArrayList<Vertex> weakVertices = new ArrayList<>();

        for(int i = 0; i < vertex.length; i ++) {
            ArrayList<Integer> indexesOfNeighbours = new ArrayList<>();

            for(int j = 0; j < vertex.length; j ++) {
                if(IsEdge(i, j)) {
                    indexesOfNeighbours.add(j);
                }
            }

            if(indexesOfNeighbours.size() == 1) {
                weakVertices.add(vertex[i]);
                continue;
            }

            boolean isBelongAtLeastOneTriangle = isEdgeBetweenNeighbours(indexesOfNeighbours);
            if(! isBelongAtLeastOneTriangle) {
                weakVertices.add(vertex[i]);
            }
        }

        return weakVertices;
    }

    protected boolean isEdgeBetweenNeighbours(ArrayList<Integer> indexesOfNeighbours) {
        boolean isEdge = false;

        for(Integer firstNeighIndex : indexesOfNeighbours) {
            for(Integer secondNeighIndex : indexesOfNeighbours) {
                if(firstNeighIndex != secondNeighIndex && IsEdge(firstNeighIndex, secondNeighIndex)) {
                    isEdge = true;
                    break;
                }
            }

            if(isEdge) {
                break;
            }
        }

        return isEdge;
    }
}