package brainteasers.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PeopleHeightInQueue {

    static class Person implements Comparator<Person> {
        int height;
        int infront;

        public Person() {
        }

        public Person(int height, int infront) {
            this.height = height;
            this.infront = infront;
        }

        public int compare(Person p1, Person p2) {
            return p1.height - p2.height;
        }
    }


    public static ArrayList<Integer> order(List<Integer> heights, List<Integer> infronts) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (heights == null || infronts == null || heights.size() != infronts.size())
            return result;

        int n = heights.size();
        Person[] persons = new Person[n];
        for (int i = 0; i < n; i++)
            persons[i] = new Person(heights.get(i), infronts.get(i));

        Arrays.sort(persons, new Person());
        Person[] temp = new Person[n];
        for (Person p : persons) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (count == p.infront) {
                    while (temp[i] != null && i < n - 1)
                        i++;
                    temp[i] = p;
                    break;
                }
                if (temp[i] == null) {
                    count++;
                }

            }
        }
        for (int i = 0; i < n; i++)
            result.add(temp[i].height);
        return result;
    }


    public static void main(String[] args) {
        System.out.println(order(Arrays.asList(5, 3, 2, 6, 1, 4), Arrays.asList(0, 1, 2, 0, 3, 2)));
    }

}
