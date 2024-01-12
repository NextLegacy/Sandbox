package graph;

import java.util.*;

class GraphMatrix<TVertexData, TEdgeData> implements IGraph<TVertexData, TEdgeData>
{
    // generic Graph class using adjacency matrix
    private TVertexData[] vertices;
    private TEdgeData[][] edges;

    public GraphMatrix(TVertexData[] vertices, TEdgeData[][] edges)
    {
        this.vertices = vertices;
        this.edges = edges;
    }

    private int getVertexIndex(TVertexData vertex)
    {
        for (int i = 0; i < vertices.length; i++)
        {
            if (vertices[i].equals(vertex))
            {
                return i;
            }
        }
        return -1;
    }

    public boolean hasVertex(TVertexData vertex)
    {
        return getVertexIndex(vertex) != -1;
    }


    public void addVertex(TVertexData vertex)
    {
        if (hasVertex(vertex)) return;

        TVertexData[] newVertices = Utils.makeArray(vertices.length + 1, vertices);
        TEdgeData[][] newEdges    = Utils.makeArray(vertices.length + 1, edges);

        for (int i = 0; i < vertices.length; i++)
        {
            newVertices[i] = vertices[i];
            for (int j = 0; j < vertices.length; j++)
            {
                newEdges[i][j] = edges[i][j];
            }
        }

        newVertices[vertices.length] = vertex;

        vertices = newVertices;
        edges = newEdges;
    }
    
    public void removeVertex(TVertexData vertex)
    {
        if (!hasVertex(vertex)) return;

        int vertexIndex = getVertexIndex(vertex);

        TVertexData[] newVertices = Utils.makeArray(vertices.length - 1, vertices);
        TEdgeData[][] newEdges    = Utils.makeArray(vertices.length - 1, edges);

        for (int i = 0; i < vertices.length; i++)
        {
            if (i == vertexIndex) continue;

            newVertices[i] = vertices[i];
            for (int j = 0; j < vertices.length; j++)
            {
                if (j == vertexIndex) continue;

                newEdges[i][j] = edges[i][j];
            }
        }

        vertices = newVertices;
        edges = newEdges;
    }

    public boolean hasEdge(TVertexData vertexA, TVertexData vertexB)
    {
        if (!hasVertex(vertexA) || !hasVertex(vertexB)) return false;

        int vertexAIndex = getVertexIndex(vertexA);
        int vertexBIndex = getVertexIndex(vertexB);

        return edges[vertexAIndex][vertexBIndex] != null;
    }

    public void addEdge(TVertexData vertexA, TVertexData vertexB, TEdgeData data, Direction direction)
    {
        if (!hasVertex(vertexA)) addVertex(vertexA);
        if (!hasVertex(vertexB)) addVertex(vertexB);

        int vertexAIndex = getVertexIndex(vertexA);
        int vertexBIndex = getVertexIndex(vertexB);

        if      (direction == Direction.A_TO_B) edges[vertexAIndex][vertexBIndex] = data;
        else if (direction == Direction.B_TO_A) edges[vertexBIndex][vertexAIndex] = data;
        else if (direction == Direction.BOTH)
        {
            edges[vertexBIndex][vertexAIndex] = data;
            edges[vertexAIndex][vertexBIndex] = data;
        }
    }

    public void removeEdge(TVertexData vertexA, TVertexData vertexB)
    {
        if (!hasVertex(vertexA) || !hasVertex(vertexB)) return;

        int vertexAIndex = getVertexIndex(vertexA);
        int vertexBIndex = getVertexIndex(vertexB);

        edges[vertexAIndex][vertexBIndex] = null;
        edges[vertexBIndex][vertexAIndex] = null;
    }

    public TEdgeData getEdgeData(TVertexData vertexA, TVertexData vertexB)
    {
        if (!hasVertex(vertexA) || !hasVertex(vertexB)) return null;

        int vertexAIndex = getVertexIndex(vertexA);
        int vertexBIndex = getVertexIndex(vertexB);

        return edges[vertexAIndex][vertexBIndex];
    }

    public HashSet<Edge<TVertexData, TEdgeData>> getEdges()
    {
        HashSet<Edge<TVertexData, TEdgeData>> edges = new HashSet<>();

        Edge<TVertexData, TEdgeData> edge;

        for (int i = 0; i < vertices.length; i++)
        {
            for (int j = i; j < vertices.length; j++)
            {
                if (this.edges[i][j] == null) continue;

                edge = new Edge<>
                (
                    vertices[i], 
                    vertices[j], 
                    this.edges[i][j],
                    hasEdge(vertices[i], vertices[j]) ? hasEdge(vertices[j], vertices[i]) ? Direction.BOTH : Direction.A_TO_B : Direction.B_TO_A
                );

                edges.add(edge);
            }
        }

        return edges;
    }

    public HashSet<Edge<TVertexData, TEdgeData>> getEdges(TVertexData vertex)
    {
        HashSet<Edge<TVertexData, TEdgeData>> edges = new HashSet<>();

        Edge<TVertexData, TEdgeData> edge;

        int vertexIndex = getVertexIndex(vertex);

        for (int i = 0; i < vertices.length; i++)
        {
            if (this.edges[vertexIndex][i] == null) continue;

            edge = new Edge<>
            (
                vertices[vertexIndex], 
                vertices[i], 
                this.edges[vertexIndex][i],
                hasEdge(vertices[vertexIndex], vertices[i]) ? hasEdge(vertices[i], vertices[vertexIndex]) ? Direction.BOTH : Direction.A_TO_B : Direction.B_TO_A
            );

            edges.add(edge);
        }

        return edges;
    }

    public HashSet<TVertexData> getVertices()
    {
        return new HashSet<>(Arrays.asList(vertices));
    }
}