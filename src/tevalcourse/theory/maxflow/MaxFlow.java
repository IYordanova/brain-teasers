package tevalcourse.theory.maxflow;

/*
    MinCut problem:
    - edge-weighted digraph
    - weights - positive number, refer to it as capacity
    - cut - partition of the vertices into 2 disjoint sets with s in one set A and t in the other set B
    - capacity - sum of the capacities of the edges from A to B
    - find a way to divide the flow into sets with min capacity

    MaxFlow
    - edge-weighted digraph, start s and target t
    - weights - positive, capacity
    - flow - another value to the edges - positive and not greater than the capacity
    - local equilibrium at the vertices - inflow = outflow at every vertex except the s and t
    - assign a value to the flow - how many stuff can you get from the source to the target

    Ford-Fulkerson Algorithm
    - flows and capacities are always integers
    - find an undirected path from/to such that
        - increases the flow on forward edge
        - decreases the flow on forward edge
    - can revert path to "remove" flow
    -> while augmenting path exist:
    - start with 0 flow
    - find bottleneck capacity
    - increase flow by that amount
    -> for minCut:
        - compute A set of vertices connected to s by an undirected path with no full forward
        or empty backward edges

    Relationship between flow and a cut
    - net flow across - for cut A,B is the sum of the flows of its edges from A to B minus the
        sum of the flows on its edges from B to A
    - flow-value lemma - let f be any flow, A,B be any cut, then the net flow across is equal the value of f
    - value of the flow is always <= to the capacity of the cut

    Augmented path theorem
    - a flow f is maxflow if no augmented paths

    Maxflow-mincut theorem
    - value of the maxflow = capacity of mincut

    THe following conditions are equivalent for any flow:
    - there is a cut whose capacity equals the value of the flow f
    - f is a max flow
    - there is no augmenting path with respect to f

    Choosing augmented paths:
    - shortest paths - BFS queue - <= 1/2EV
    - fattest paths  - PQ        - <= Eln(EU)
    - random paths   - randomized Q - <= EU
    - DFS paths      - DFS stack - <= EU

    Residual capacity
    - forward edge = capacity - flow
    - backward edge = flow

    Augment flow
    - forward edge - add the delta
    - backward edge - remove it


    Applications:
    - data mining
    - open-pit mining
    - bipartite matching - 2 sets of vertices, connection from one to the other 
    - network reliability
    - baseball elimination
    - image segmentation
    - distributed computing
    - multi-camera scene reconstruction
    - sensor placement
    - security of statistical data

 */
public class MaxFlow {

}
