package tevalcourse.theory.symboltable;

/*
    - 2-d orthogonal range search
    - keys are points in a plane
    - ability to find and count points in a rectangle
    - divide space into a grid of MxM
    - create list of points contained in each square
    - use 2d array to directly index relevant square
    - space M^2 + N
    - time 1+ N/M^2 per square examined
    - => M should be around square root N

    2d tree: - easy to expand to K dimensions
    - space partitioning
        - first point splits the space in 2 through a vertical line
        - second one a horizontal line on the respective side
   - finding points inside of rectangle
        - R + logN
        - worst case: R + square root N
   - find nearest neighbour
   - simulation of natural phenomenon
        - boids - three simple rules lead to complex emergent flocking behaviour
                - collision avoidance - point awy from the k nearest boids
                - flock centering - point towards the center of mass of k nearest boids
                - velocity matching - update velocity to average of k nearest boids
 */
public class TwoDRangeSearch<Key extends Comparable<Key>, Value> {
    class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;


}
