package tevalcourse.theory.symboltable;

/*
    - Line Segment intersection
        - think of line that goes through the whole plane vertically and
            - if left point of horizontal line is hit - add y coordinate to BST
            - if right point of horizontal line is hit - remove the y coordinate of the BST - no intersection found
            - if vertical line segment - range search for the interval of y points - any point in that range - horizontal line intersecting the vertical one


    - Rectangle intersection
        - sweep vertical line through the plane
        - use interval search instead of the range one
        - if a rectangle hit - put in tree
        - if right of a rectangle - remove from tree
        - if there is an intersection in tree - found
 */


/*
   one dimensional range search
 */
public class OneDRangeSearch<Key extends Comparable<Key>, Value> {
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

//    public int size(Key lo, Key hi) {
//        if(contains(hi)) return rank (hi) - rank(lo) + 1;
//        else return rank(hi) - rank(lo);
//    }

}


/*
     1D interval search
     - data structure to hold set of overlapping intervals

     Interval search tree
     - use only left as key in BST
     - store the max in the subtree in each node
     - to search - any interval intersecting the query one:
        - if the left is null -> go right
        - if the max endpoint in left is less than the lo -> go right
        - else go left
 */
class IntervalST<Key extends Comparable<Key>, Value> {
    class Node {
        private Key key;
        private Value interval;
        private Node left;
        private Node right;
        private Value max;

        public Node(Key key, Value interval) {
            this.key = key;
            this.interval = interval;
        }
    }

    private Node root;

    public IntervalST() {
    }

    void put(Key lo, Key hi, Value val){

    }

    Value get(Key lo, Key hi) {
//        Node x = root;
//        while(x!= null) {
//            if(x.inerval.intersects(lo, hi)){
//                return x.interval;
//            } else if(x.left == null) {
//                x = x.right;
//            } else if(x.left.max < lo) {
//                x = x.right;
//            } else{
//                x = x.left;
//            }
//        }
        return null;
    }

    void delete(Key lo, Key hi){

    }

    Iterable<Value> intersects(Key lo, Key hi){
        // TODO:
        return null;
    }
}