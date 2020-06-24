package tevalcourse.theory.graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {


    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        SET<String> discovered = new SET<>();

        String root = "http:..";
        queue.enqueue(root);
        discovered.add(root);

        while(!queue.isEmpty()) {
            String v = queue.dequeue();
            StdOut.println(v);

            In in = new In(v);
            String input = in.readAll();

            String regex = "http://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            while(matcher.find()) {
                String w = matcher.group();
                if(!discovered.contains(w)) {
                    discovered.add(w);
                    queue.enqueue(w);
                }
            }
        }
    }



}
