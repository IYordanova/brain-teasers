package brainteasers.math;

import java.util.*;
import java.util.stream.IntStream;

public class PrimeSum {
    private static ArrayList<Integer> primeSum(int A) {
            ArrayList<Integer> a = new ArrayList<>();
            for (int i = 0; i < A + 1; i++) {
                if (A < i) {
                    break;
                }

                if (!isPrime(i)) {
                    continue;
                }

                int second = A - i;
                if (!isPrime(second)) {
                    continue;
                }

                a.add(i);
                a.add(second);
                break;
            }

            return a;
        }


        private static boolean isPrime ( int number){
            return number > 1
                    && IntStream
                    .range(2, number)
                    .noneMatch(i -> number % i == 0);
        }

        public static void main (String[]args){
            System.out.println(primeSum(16777214));
        }


    }
