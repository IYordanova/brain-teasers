import java.util.ArrayList;
import java.util.Arrays;

public class MergeIntervals {
    static class Interval {
        int start;
        int end;

        public Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "(" + start + ", " + end + ")";
        }
    }

    private static ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        int i = 0;
        while(i < intervals.size()){
            Interval in = intervals.get(i);
            if(in.end < newInterval.start){
                i++;
            }else if(in.start > newInterval.end){
                intervals.add(i, newInterval);
                break;
            }else{
                newInterval.start = Math.min(in.start, newInterval.start);
                newInterval.end = Math.max(in.end, newInterval.end);
                intervals.remove(i);
            }
        }
        if(i == intervals.size()){
            intervals.add(newInterval);
        }
        return intervals;
    }

    public static void main(String[] args) {
        // start and end in different existing intervals
//        System.out.println(insert(new ArrayList<>(Arrays.asList(
//                new Interval(1, 2),
//                new Interval(3, 5),
//                new Interval(6, 7),
//                new Interval(8, 10),
//                new Interval(12, 16)
//        )), new Interval(4, 9))); //[(1, 2), (3, 10), (12, 16)]
//
//        // end not in any existing interval
//        System.out.println(insert(new ArrayList<>(Arrays.asList(
//                new Interval(1, 2),
//                new Interval(3, 5),
//                new Interval(8, 10),
//                new Interval(12, 16)
//        )), new Interval(4, 7))); // [(1, 2), (3, 7), (8, 10), (12, 16)]
//
//        // start not in any existing interval
//        System.out.println(insert(new ArrayList<>(Arrays.asList(
//                new Interval(1, 2),
//                new Interval(3, 5),
//                new Interval(8, 10),
//                new Interval(12, 16)
//        )), new Interval(6, 9))); // [(1, 2), (3, 5), (6, 10), (12, 16)]
//
//        // neither start nor end in any existing interval
//        System.out.println(insert(new ArrayList<>(Arrays.asList(
//                new Interval(1, 2),
//                new Interval(3, 5),
//                new Interval(8, 10),
//                new Interval(12, 16)
//        )), new Interval(6, 7))); // [(1, 2), (3, 5), (6, 7), (8, 10), (12, 16)]
//
//
//        System.out.println(insert(new ArrayList<>(Arrays.asList(
//                new Interval(1, 2),
//                new Interval(3, 6)
//        )), new Interval(8, 10))); // [(1, 2), (3, 6), (8, 10)]
//
//        System.out.println(insert(new ArrayList<>(Arrays.asList(
//                new Interval(3, 6),
//                new Interval(8, 10)
//        )), new Interval(1, 2))); // [(1, 2), (3, 6), (8, 10)]
//
//        System.out.println(insert(new ArrayList<>(Arrays.asList(
//                new Interval(3, 5),
//                new Interval(8, 10)
//        )), new Interval(1, 12))); // [(1, 12)]

        System.out.println(insert(new ArrayList<>(Arrays.asList(
                new Interval(1, 2),
                new Interval(8, 10)
        )), new Interval(3, 6))); // [(1, 2) (3, 6) (8, 10) ]

        System.out.println(insert(new ArrayList<>(Arrays.asList(
                new Interval(1843800, 3108132),
                new Interval(4043245, 6937345),
                new Interval(11563937, 11635326),
                new Interval(11692273, 13669650),
                new Interval(13942356, 14227460),
                new Interval(22120808, 22865563),
                new Interval(23235798, 24453438),
                new Interval(30863049, 32633738),
                new Interval(32732908, 34529743),
                new Interval(39435417, 41953244),
                new Interval(44906964, 45093815),
                new Interval(49260315, 49386262),
                new Interval(49935113, 50861375),
                new Interval(51101300, 55230325),
                new Interval(55392301, 56108148),
                new Interval(59783337, 63362038),
                new Interval(67941045, 68479751),
                new Interval(71854209, 74111726),
                new Interval(79865051, 80842645),
                new Interval(81712401, 82084838),
                new Interval(83846540, 84468062),
                new Interval(84597297, 88972926),
                new Interval(89403172, 93584639),
                new Interval(96663912, 97013240)
        )), new Interval(77455213, 9744919)));
    }
}
