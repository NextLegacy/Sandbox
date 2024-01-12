package graph;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class GraphAdjacencyList<TVertexData, TEdgeData> implements IGraph<TVertexData, TEdgeData>
{
    class Vertex
    {
        final TVertexData data;
        final ArrayList<Edge<TVertexData, TEdgeData>> edges;

        public Vertex(TVertexData data)
        {
            this.data  = data;
            this.edges = new ArrayList<>();
        }
    }

    private final List<Vertex> adjacencyList;

    public GraphAdjacencyList()
    {
        adjacencyList = new ArrayList<>();
    }

    public boolean hasVertex(TVertexData vertex)
    {
        for (Vertex v : adjacencyList)
        {
            if (v.data.equals(vertex)) return true;
        }

        return false;
    }

    public void addVertex(TVertexData vertex)
    {
        if (hasVertex(vertex)) return;

        adjacencyList.add(new Vertex(vertex));
    }
    
    public void removeVertex(TVertexData vertex)
    {
        if (!hasVertex(vertex)) return;

        for (Vertex v : adjacencyList)
        {
            for (Edge<TVertexData, TEdgeData> e : v.edges)
            {
                if (e.vertexA.equals(vertex) || e.vertexB.equals(vertex))
                {
                    v.edges.remove(e);
                }
            }
        }
    }

    public boolean hasEdge(TVertexData vertexA, TVertexData vertexB)
    {
        if (!hasVertex(vertexA) || !hasVertex(vertexB)) return false;

        for (Vertex vertex : adjacencyList)
        {
            for (Edge<TVertexData, TEdgeData> edge : vertex.edges)
            {
                if (edge.vertexA.equals(vertexA) && edge.vertexB.equals(vertexB)) return true;
                if (edge.vertexA.equals(vertexB) && edge.vertexB.equals(vertexA)) return true;
            }
        }

        return false;
    }

    public void addEdge(TVertexData vertexA, TVertexData vertexB, TEdgeData data, Direction direction)
    {
        if (!hasVertex(vertexA)) addVertex(vertexA);
        if (!hasVertex(vertexB)) addVertex(vertexB);

        Edge<TVertexData, TEdgeData> edge = new Edge<>(vertexA, vertexB, data, direction);

        Vertex vertexOfEdgeA = null;
        Vertex vertexOfEdgeB = null;

        for (Vertex vertex : adjacencyList)
        {
            if (vertex.data.equals(vertexA)) vertexOfEdgeA = vertex;
            if (vertex.data.equals(vertexB)) vertexOfEdgeB = vertex;
        
            if (vertexOfEdgeA != null && vertexOfEdgeB != null) break;
        }

        vertexOfEdgeA.edges.add(edge);
        vertexOfEdgeB.edges.add(edge);
    }

    public void removeEdge(TVertexData vertexA, TVertexData vertexB)
    {
        if (!hasVertex(vertexA) || !hasVertex(vertexB)) return;

        Vertex vertexOfEdgeA = null;
        Vertex vertexOfEdgeB = null;

        for (Vertex vertex : adjacencyList)
        {
            if (vertex.data.equals(vertexA)) vertexOfEdgeA = vertex;
            if (vertex.data.equals(vertexB)) vertexOfEdgeB = vertex;
        
            if (vertexOfEdgeA != null && vertexOfEdgeB != null) break;
        }

        Edge<TVertexData, TEdgeData> edgeToRemove = null;

        for (Edge<TVertexData, TEdgeData> edge : vertexOfEdgeA.edges)
        {
            if (edge.vertexA.equals(vertexA) && edge.vertexB.equals(vertexB)) edgeToRemove = edge;
            if (edge.vertexA.equals(vertexB) && edge.vertexB.equals(vertexA)) edgeToRemove = edge;

            if (edgeToRemove != null) break;
        }
        
        vertexOfEdgeA.edges.remove(edgeToRemove);
        vertexOfEdgeB.edges.remove(edgeToRemove);
    }

    public TEdgeData getEdgeData(TVertexData vertexA, TVertexData vertexB)
    {
        if (!hasVertex(vertexA) || !hasVertex(vertexB)) return null;

        Vertex vertexOfEdge = null;

        for (Vertex vertex : adjacencyList)
        {
            if (vertex.data.equals(vertexA)) 
            {
                vertexOfEdge = vertex;
                break;
            }
        }

        if (vertexOfEdge == null) return null;

        for (Edge<TVertexData, TEdgeData> edge : vertexOfEdge.edges)
        {
            if (edge.vertexA.equals(vertexA) && edge.vertexB.equals(vertexB)) return edge.data;
            if (edge.vertexA.equals(vertexB) && edge.vertexB.equals(vertexA)) return edge.data;
        }

        return null;
    }

    public HashSet<Edge<TVertexData, TEdgeData>> getEdges()
    {
        HashSet<Edge<TVertexData, TEdgeData>> edges = new HashSet<>();

        for (Vertex vertex : adjacencyList)
        {
            for (Edge<TVertexData, TEdgeData> edge : vertex.edges)
            {
                edges.add(edge);
            }
        }

        return edges;
    }

    public HashSet<Edge<TVertexData, TEdgeData>> getEdges(TVertexData vertexOfEdge)
    {
        HashSet<Edge<TVertexData, TEdgeData>> edges = new HashSet<>();

        for (Vertex vertex : adjacencyList)
        {
            if (!vertex.data.equals(vertexOfEdge)) continue;

            for (Edge<TVertexData, TEdgeData> edge : vertex.edges)
            {
                edges.add(edge);
            }
        }

        return edges;
    }

    public HashSet<TVertexData> getVertices()
    {
        HashSet<TVertexData> vertices = new HashSet<>();

        for (Vertex vertex : adjacencyList)
        {
            vertices.add(vertex.data);
        }

        return vertices;
    }
}
