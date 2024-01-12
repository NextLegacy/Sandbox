package graph;

import java.util.HashMap;
import java.util.HashSet;

public class GraphFastAdjacencyList<TVertexData, TEdgeData> implements IGraph<TVertexData, TEdgeData>
{
    private final HashMap<TVertexData, HashMap<TVertexData, Edge<TVertexData, TEdgeData>>> adjacencyList;

    public GraphFastAdjacencyList()
    {
        adjacencyList = new HashMap<>();
    }

    public boolean hasVertex(TVertexData vertex)
    {
        return adjacencyList.containsKey(vertex);
    }

    public void addVertex(TVertexData vertex)
    {
        if (hasVertex(vertex)) return;

        adjacencyList.put(vertex, new HashMap<>());
    }
    
    public void removeVertex(TVertexData vertex)
    {
        if (!hasVertex(vertex)) return;

        for (var edges : adjacencyList.values())
        {
            edges.remove(vertex);
        }
    }

    public boolean hasEdge(TVertexData vertexA, TVertexData vertexB)
    {
        if (!hasVertex(vertexA) || !hasVertex(vertexB)) return false;

        return adjacencyList.get(vertexA).containsKey(vertexB);
    }

    public void addEdge(TVertexData vertexA, TVertexData vertexB, TEdgeData data, Direction direction)
    {
        if (!hasVertex(vertexA)) addVertex(vertexA);
        if (!hasVertex(vertexB)) addVertex(vertexB);

        Edge<TVertexData, TEdgeData> edge = new Edge<>(vertexA, vertexB, data, direction);

        adjacencyList.get(vertexA).put(vertexB, edge);
        adjacencyList.get(vertexB).put(vertexA, edge);
    }

    public void removeEdge(TVertexData vertexA, TVertexData vertexB)
    {
        if (!hasVertex(vertexA) || !hasVertex(vertexB)) return;

        adjacencyList.get(vertexA).remove(vertexB);
        adjacencyList.get(vertexB).remove(vertexA);
    }

    public TEdgeData getEdgeData(TVertexData vertexA, TVertexData vertexB)
    {
        if (!hasVertex(vertexA) || !hasVertex(vertexB)) return null;

        return adjacencyList.get(vertexA).get(vertexB).data;
    }

    public HashSet<Edge<TVertexData, TEdgeData>> getEdges()
    {
        HashSet<Edge<TVertexData, TEdgeData>> edges = new HashSet<>();

        for (var vertexEdges : adjacencyList.values())
        {
            edges.addAll(vertexEdges.values());
        }

        return edges;
    }

    public HashSet<Edge<TVertexData, TEdgeData>> getEdges(TVertexData vertex)
    {
        if (!hasVertex(vertex)) return null;

        return new HashSet<>(adjacencyList.get(vertex).values());
    }

    public HashSet<TVertexData> getVertices()
    {
        return new HashSet<>(adjacencyList.keySet());
    }
}