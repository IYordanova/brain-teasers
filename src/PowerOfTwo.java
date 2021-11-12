import java.math.BigInteger;


public class PowerOfTwo {


    private static boolean isPowerOfTwo(BigInteger n) {
        return n.and(n.subtract(BigInteger.ONE)).equals(BigInteger.ZERO);
    }


    public static int solution(String x) {
        if (x.equals("1")) {
            return 0;
        }

        if (x.equals("2")) {
            return 1;
        }

        BigInteger moveUpFuel = new BigInteger(x);
        int moveUpOpCounter = 0;
        boolean upDone = false;

        BigInteger moveDownFuel = new BigInteger(x);
        int moveDownOpCounter = 0;
        boolean downDone = false;

        while (!upDone || !downDone) {
            if (!upDone && !isPowerOfTwo(moveUpFuel)) {
                moveUpFuel = moveUpFuel.add(BigInteger.ONE);
                moveUpOpCounter++;
            } else {
                upDone = true;
            }

            if (!downDone && !isPowerOfTwo(moveDownFuel)) {
                moveDownFuel = moveDownFuel.subtract(BigInteger.ONE);
                moveDownOpCounter++;
            } else {
                downDone = true;
            }
        }


        BigInteger current;
        int opCounter;

        if (moveUpOpCounter > moveDownOpCounter) {
            current = moveDownFuel;
            opCounter = moveDownOpCounter;
        } else if (moveUpOpCounter < moveDownOpCounter) {
            current = moveUpFuel;
            opCounter = moveUpOpCounter;
        } else {
            if (moveUpFuel.compareTo(moveDownFuel) > 0) {
                current = moveDownFuel;
                opCounter = moveDownOpCounter;
            } else {
                current = moveUpFuel;
                opCounter = moveUpOpCounter;
            }
        }

        BigInteger two = new BigInteger("2");
        while (!current.equals(BigInteger.ONE)) {
            current = current.divide(two);
            opCounter++;
        }

        return opCounter;
    }

    public static int solution3(String x) {
        if (x.equals("0") || x.equals("1")) {
            return 0;
        }

        if (x.equals("2")) {
            return 1;
        }
        BigInteger current = new BigInteger(x);
        int counter = 0;

        BigInteger ceilingPowerOfTwo = ceilingPowerOfTwo(current);
        int ceilingCounter = ceilingPowerOfTwo.subtract(current).intValue();

        BigInteger floorPowerOfTwo = floorPowerOfTwo(current);
        int floorCounter = current.subtract(floorPowerOfTwo).intValue();

        if (floorCounter < ceilingCounter) {
            counter = floorCounter;
            current = floorPowerOfTwo;
        } else if (ceilingCounter < floorCounter) {
            counter = ceilingCounter;
            current = ceilingPowerOfTwo;
        } else {
            if (ceilingPowerOfTwo.compareTo(floorPowerOfTwo) < 0) {
                counter = ceilingCounter;
                current = ceilingPowerOfTwo;
            } else {
                counter = floorCounter;
                current = floorPowerOfTwo;
            }
        }

        BigInteger two = new BigInteger("2");
        while (!current.equals(BigInteger.ONE)) {
            current = current.divide(two);
            counter++;
        }

        return counter;
    }

    private enum RoundingMode {
        UP, DOWN
    }

    private static BigInteger ceilingPowerOfTwo(BigInteger x) {
        return BigInteger.ZERO.setBit(log2(x, RoundingMode.UP));
    }

    private static BigInteger floorPowerOfTwo(BigInteger x) {
        return BigInteger.ZERO.setBit(log2(x, RoundingMode.DOWN));
    }

    private static int log2(BigInteger x, RoundingMode mode) {
        int logFloor = x.bitLength() - 1;
        switch (mode) {
            case DOWN:
                return logFloor;
            case UP:
                return isPowerOfTwo(x) ? logFloor : logFloor + 1;
            default:
                throw new AssertionError();
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(solution3("0") + ", should be 0");
        System.out.println(solution3("1") + ", should be 0");
        System.out.println(solution3("4") + ", should be 2");
        System.out.println(solution3("15") + ", should be 5");
        System.out.println(System.currentTimeMillis() - start);
    }
}