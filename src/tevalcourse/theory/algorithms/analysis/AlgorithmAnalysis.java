package tevalcourse.theory.algorithms.analysis;

public class AlgorithmAnalysis {
    /*  Complexity:

     A) Tilde approximation -> g(N)/f(N) ~ 1 (approaches 1) as N grows

     B) Order of growth -> g(N) ~ af(N)

     C) Cost model -> defining the base operations

      ------ Name ------ Order of Growth ------ Description ------ Example ------

         1. Constant           1           statement              adding 2 numbers
         2. Logarithmic        logN        divide in half         binary search
         3. Linear             N           loop                   find Max/Min
         4. Linearithmic       NlogN       divide and conquer     mergesort
         5. Quadratic          N^2         double loop            check all pairs
         6. Cubic              N^3         triple loop            check all triples
         7. Exponential        2^N         exhasutive search      check all subsets
     */


    /* Memory usage:

     A) for  primitive types
     ------ Type ------ Number of Bytes ------

         1. boolean          1
         2. byte             1
         3. char             2
         4. int              4
         5. float            4
         6. long             8
         7. double           8

     B) Objects - sum of all fields + a typical overhead of 16B + padded to multiple of 8

     C) References - usually ref to an address so 8B (64 bit machine)

     D) Linked Lists - Node(item + next + obj overhead + ref to the enclosing instance)

     E) Arrays - implemented as objects, so N*type size + obj overhead + size overhead + padding

     F) Strings - obj overhead, reference to char array, hash, padding
     */


    /* Union Find:

     A) represented by array of ints, id[], where the item is it's index and the values are initially same as  the index
     B) 2 items are considered connected if their values in the id[] are equal
     C) connecting 2 items p and q is representing by changing the values that equal to id[p] to the one of id[q]



     */
}


// init and union - loop through array so N^2 - union is too expensive
class UnionFindQuickTime {
    private int[] id;

    public UnionFindQuickTime(int N) {
        this.id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }
}

// lazy approach - id[i] is parent of i, trees can get too tall => find becomes slow over time
class UnionFindQuickUnion {
    private int[] id;

    public UnionFindQuickUnion(int N) {
        this.id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    private int root(int i) {
        while(i != id[i]) {
            i = id[i];
        }
        return i;
    }

    // check it they have same root
    boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
}

// weights to avoid tall trees + path compression
class UnionFindQuickUnionImproved {
    private int[] id;
    private int[] treeSize;

    public UnionFindQuickUnionImproved(int N) {
        this.id = new int[N];
        this.treeSize = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            treeSize[i] = 1;
        }
    }

    private int root(int i) {
        while(i != id[i]) {
            // improvement - compressing the tree on the way up
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    // check it they have same root
    boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if(i == j) return;
        if(treeSize[i] < treeSize[j]) {
            id[i] = j;
            treeSize[j] += treeSize[i];
        } else {
            id[j] = i;
            treeSize[i] += treeSize[j];
        }
    }
}