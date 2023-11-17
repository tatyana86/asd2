import java.util.*;

class Vertex
{
    public int lastIndex = -1;
    public int Value;
    public boolean Hit;
    public Vertex(int val)
    {
        Value = val;
        Hit = false;
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
            //m_adjacency[i][v] = 0;
        }
    }

    public boolean IsEdge(int v1, int v2)
    {
        if(vertex[v1] == null || vertex[v2] == null) {
            return false;
        }

        // return m_adjacency[v1][v2] == 1 && m_adjacency[v2][v1] == 1;
        return m_adjacency[v1][v2] == 1;
    }

    public void AddEdge(int v1, int v2)
    {
        if(vertex[v1] == null || vertex[v2] == null) {
            return;
        }

        m_adjacency[v1][v2] = 1;
        //m_adjacency[v2][v1] = 1;
    }

    public void RemoveEdge(int v1, int v2)
    {
        if(vertex[v1] == null || vertex[v2] == null) {
            return;
        }

        m_adjacency[v1][v2] = 0;
        //m_adjacency[v2][v1] = 0;
    }

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo)
    {
        Stack<Vertex> wayByVertex = new Stack<>();

        for(int i = 0; i < vertex.length; i ++) {
            if(vertex[i] != null) {
                vertex[i].Hit = false;
            }
        }

        recursionDepthFirstSearch(VFrom, VTo, wayByVertex);

        return new ArrayList<>(wayByVertex);
    }

    private boolean recursionDepthFirstSearch(int currentIndex, int bIndex, Stack<Vertex> way) {
        vertex[currentIndex].Hit = true;
        way.push(vertex[currentIndex]);

        ArrayList<Integer> indexesOfNotHit = new ArrayList<>();

        for(int i = 0; i < vertex.length; i ++) {
            if(m_adjacency[currentIndex][i] == 0) {
                continue;
            }

            if(i == bIndex) {
                way.push(vertex[i]);
                return true;
            }

            if(! vertex[i].Hit) {
                indexesOfNotHit.add(i);
            }
        }

        for(Integer index : indexesOfNotHit) {
            if(recursionDepthFirstSearch(index, bIndex, way)) {
                return true;
            }
        }

        way.pop();

        return false;
    }

    public class Queue<T>
    {
        ArrayList<T> array;

        public Queue()
        {
            this.array = new ArrayList<>();
        }

        public void enqueue(T item)
        {
            this.array.add(item);
        }

        public T dequeue()
        {
            if(size() == 0) {
                return null;
            }

            return this.array.remove(0);
        }

        public int size()
        {
            return this.array.size();
        }

    }

    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo)
    {
        Queue<Integer> indexesOfNotHit = new Queue<Integer>();
        ArrayList<Vertex> wayByVertex = new ArrayList<>();

        for(int i = 0; i < vertex.length; i ++) {
            if(vertex[i] != null) {
                vertex[i].Hit = false;
            }
        }

        vertex[VFrom].Hit = true;

        boolean isWayExist = recursionBreadFirstSearch(VFrom, VTo, indexesOfNotHit);

        if(isWayExist) {
            createWayVertex(VFrom, VTo, wayByVertex);
        }

        return new ArrayList<>(wayByVertex);
    }

    private boolean recursionBreadFirstSearch(int currentIndex, int bIndex, Queue<Integer> indexesOfNotHit)
    {
        for(int i = 0; i < vertex.length; i ++) {
            if(m_adjacency[currentIndex][i] == 0) {
                continue;
            }

            if(! vertex[i].Hit) {
                vertex[i].lastIndex = currentIndex;
                indexesOfNotHit.enqueue(i);
                vertex[i].Hit = true;
            }

            if(i == bIndex) {
                return true;
            }
        }

        if(indexesOfNotHit.size() == 0) {
            return false;
        }

        int nextIndex = indexesOfNotHit.dequeue();
        if(! recursionBreadFirstSearch(nextIndex, bIndex, indexesOfNotHit)) {
            return false;
        }

        return true;
    }

    private void createWayVertex(int indexFrom, int index, ArrayList<Vertex> way) {
        way.add(0, vertex[index]);
        if(index == indexFrom) {
            return;
        }
        createWayVertex(indexFrom, vertex[index].lastIndex, way);
    }
}