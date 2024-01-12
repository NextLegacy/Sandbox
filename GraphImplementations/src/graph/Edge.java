package graph;

final class Edge<TVertexData, TEdgeData>
{
    public final TVertexData vertexA;
    public final TVertexData vertexB;

    public TEdgeData data;

    public Direction direction;

    public Edge(TVertexData vertexA, TVertexData vertexB, TEdgeData data, Direction direction)
    {
        this.vertexA   = vertexA  ;
        this.vertexB   = vertexB  ;
        this.data      = data     ;
        this.direction = direction;
    }
}
