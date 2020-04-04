package chapter_9.Question9_7;

public class HtWt implements Comparable<HtWt> {
    private int Ht;
    private int Wt;

    public HtWt(int h, int w) {
        Ht = h;
        Wt = w;
    }

    public int compareTo(HtWt second) {
       if (this.Ht != second.Ht) {
            return Integer.compare(this.Ht, second.Ht);
        } else {
            return Integer.compare(this.Wt, second.Wt);
        }
    }

    public String toString() {
        return "(" + Ht + ", " + Wt + ")";
    }

    public boolean isBefore(HtWt other) {
		return this.Ht < other.Ht && this.Wt < other.Wt;
    }
}
