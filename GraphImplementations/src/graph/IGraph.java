package graph;

import java.util.HashSet;

public interface IGraph<TVertexData, TEdgeData>
{
    // https://codeahoy.com/learn/graphalgorithms/ch4/

    public boolean   hasVertex   (TVertexData vertex);
    public void      addVertex   (TVertexData vertex);
    public void      removeVertex(TVertexData vertex);

    public boolean   hasEdge     (TVertexData vertex1, TVertexData vertex2);
    public void      addEdge     (TVertexData vertex1, TVertexData vertex2, TEdgeData data, Direction direction);
    public void      removeEdge  (TVertexData vertex1, TVertexData vertex2);
    public TEdgeData getEdgeData (TVertexData vertex1, TVertexData vertex2);
    
    public HashSet<TVertexData> getVertices();
    public HashSet<Edge<TVertexData, TEdgeData>> getEdges();
    public HashSet<Edge<TVertexData, TEdgeData>> getEdges(TVertexData vertex);
}