package tevalcourse.theory.graphs.shortestpaths.hw;

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;
import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class SeamCarver {

    private static final double BORDER_ENERGY = 1000;

    private Picture picture;
    private double[][] energy;

    private static class Pixel {
        int x;
        int y;

        public Pixel(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pixel pixel = (Pixel) o;
            return x == pixel.x && y == pixel.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }


    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException("Picture cannot be 'null'");
        }
        this.picture = picture;
        this.energy = new double[picture.width()][picture.height()];
        calcEnergy();
    }

    private void calcEnergy() {
        int w = width();
        int h = height();
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                energy[x][y] = calcEnergy(x, y);
            }
        }
    }

    private double calcEnergy(int x, int y) {
        if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1) {
            return BORDER_ENERGY;
        }
        double xDiff = calcDiff(picture.get(x - 1, y), picture.get(x + 1, y));
        double yDiff = calcDiff(picture.get(x, y - 1), picture.get(x, y + 1));
        return Math.sqrt(xDiff + yDiff);
    }

    private double calcDiff(Color prevColor, Color nextColor) {
        int redDiff = prevColor.getRed() - nextColor.getRed();
        int greenDiff = prevColor.getGreen() - nextColor.getGreen();
        int blueDiff = prevColor.getBlue() - nextColor.getBlue();
        return Math.pow(redDiff, 2) + Math.pow(greenDiff, 2) + Math.pow(blueDiff, 2);
    }

    public Picture picture() {
        return picture;
    }

    public int width() {
        return picture.width();
    }

    public int height() {
        return picture.height();
    }

    public double energy(int x, int y) {
        if (x < 0 || x > width() - 1 || y < 0 || y > height() - 1) {
            throw new IllegalArgumentException("Pixel coordinates out of range.");
        }
        return energy[x][y];
    }

    public int[] findHorizontalSeam() {
        int width = width();
        int height = height();

        int[] minSeam = null;
        double seamMinWeight = Double.POSITIVE_INFINITY;

        for (int row = 0; row < height; row++) {
            Map<Pixel, Pixel> edgeFrom = new HashMap<>();
            Map<Pixel, Double> distTo = new HashMap<>();

            Queue<Pixel> pq = new Queue<>();
            Pixel start = new Pixel(0, row);
            pq.enqueue(start);
            distTo.put(start, energy[start.x][start.y]);

            while (!pq.isEmpty()) {
                Pixel currentPixel = pq.dequeue();
                int x = currentPixel.x + 1;
                int y = currentPixel.y;
                if (x < width - 1) {
                    if (y > 0) {
                        relax(pq, distTo, edgeFrom, currentPixel, new Pixel(x, y - 1));
                    }
                    if (y < height - 1) {
                        relax(pq, distTo, edgeFrom, currentPixel, new Pixel(x, y + 1));
                    }
                    if (x < width - 1) {
                        relax(pq, distTo, edgeFrom, currentPixel, new Pixel(x + 1, y));
                    }
                }
            }

            Stack<Pixel> mst = new Stack<>();
            double sumWeight = 0;
            for (Pixel p = start; p != null; p = edgeFrom.get(p)) {
                mst.push(p);
                sumWeight += distTo.get(p);
            }

            if (sumWeight < seamMinWeight) {
                seamMinWeight = sumWeight;
                minSeam = new int[mst.size()];
                Iterator<Pixel> iterator = mst.iterator();
                int i = 0;
                while (iterator.hasNext()) {
                    minSeam[i] = iterator.next().x;
                }
            }
        }

        return minSeam;
    }

    public int[] findVerticalSeam() {
        int width = width();
        int height = height();

        int[] minSeam = null;
        double seamMinWeight = Double.POSITIVE_INFINITY;

        for (int col = 0; col < width; col++) {
            Map<Pixel, Pixel> edgeFrom = new HashMap<>();
            Map<Pixel, Double> distTo = new HashMap<>();

            Queue<Pixel> pq = new Queue<>();
            Pixel start = new Pixel(col, 0);
            pq.enqueue(start);
            distTo.put(start, energy[start.x][start.y]);

            while (!pq.isEmpty()) {
                Pixel currentPixel = pq.dequeue();
                int x = currentPixel.x;
                int y = currentPixel.y + 1;
                if (y < height - 1) {
                    if (x > 0) {
                        relax(pq, distTo, edgeFrom, currentPixel, new Pixel(x - 1, y));
                    }
                    if (x < width - 1) {
                        relax(pq, distTo, edgeFrom, currentPixel, new Pixel(x + 1, y));
                    }
                    relax(pq, distTo, edgeFrom, currentPixel, new Pixel(x, y));
                }
            }

            Stack<Pixel> mst = new Stack<>();
            double sumWeight = 0;
            for (Pixel p = start; p != null; p = edgeFrom.get(p)) {
                mst.push(p);
                sumWeight += distTo.get(p);
            }

            if (sumWeight < seamMinWeight) {
                seamMinWeight = sumWeight;
                minSeam = new int[mst.size()];
                Iterator<Pixel> iterator = mst.iterator();
                int i = 0;
                while (iterator.hasNext()) {
                    minSeam[i] = iterator.next().y;
                }
            }
        }

        return minSeam;
    }

    private void relax(Queue<Pixel> pq, Map<Pixel, Double> distTo, Map<Pixel, Pixel> edgeFrom, Pixel v, Pixel w) {
        double weight = energy[w.x][w.y];
        if (distTo.get(w) == null || distTo.get(w) > distTo.get(v) + weight) {
            distTo.put(w, distTo.get(v) + weight);
            edgeFrom.put(w, v);
            pq.enqueue(w);
        }
    }

    public void removeHorizontalSeam(int[] seam) {
        if (seam == null) {
            throw new IllegalArgumentException("Seam cannot be 'null'");
        }

        int height = height();
        int width = width();
        if (height <= 1) {
            throw new IllegalArgumentException("Cannot remove seam when height <= 1");
        }

        validateSeam(seam, width, height);

        Picture resizedPicture = new Picture(width, height - 1);
        for (int y = 0; y < width; y++) {
            int k = 0;
            for (int x = 0; x < height; x++) {
                if (x != seam[y]) {
                    resizedPicture.set(y, k, picture.get(y, x));
                    k++;
                }
            }
        }
        this.picture = resizedPicture;

        double[][] newEnergy = new double[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (seam[x] != y) {
                    newEnergy[x][y] = calcEnergy(x, y);
                } else {
                    newEnergy[x][y] = energy[x][y];
                }
            }
        }
        this.energy = newEnergy;
    }

    public void removeVerticalSeam(int[] seam) {
        if (seam == null) {
            throw new IllegalArgumentException("Seam cannot be 'null'");
        }

        int width = width();
        int height = height();
        if (width <= 1) {
            throw new IllegalArgumentException("Cannot remove seam when width <= 1");
        }
        validateSeam(seam, height, width);

        Picture resizedPicture = new Picture(width - 1, height);
        for (int y = 0; y < height; y++) {
            int k = 0;
            for (int x = 0; x < width; x++) {
                if (x != seam[y]) {
                    resizedPicture.set(k, y, picture.get(x, y));
                    k++;
                }
            }
        }
        this.picture = resizedPicture;

        double[][] newEnergy = new double[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (seam[y] != x) {
                    newEnergy[x][y] = calcEnergy(x, y);
                } else {
                    newEnergy[x][y] = energy[x][y];
                }
            }
        }
        this.energy = newEnergy;
    }

    private void validateSeam(int[] seam, int expectedLen, int maxValue) {
        int seamLen = seam.length;
        if (seamLen != expectedLen) {
            throw new IllegalArgumentException("Trying to remove seam with wrong length");
        }
        for (int i = 0; i < seamLen - 1; i++) {
            int pixel = seam[i];
            if (pixel < 0 || pixel > maxValue) {
                throw new IllegalArgumentException("Seam pixel out of range");
            }

            int nextPixel = seam[i + 1];
            if (nextPixel - pixel != 1) {
                throw new IllegalArgumentException("Seam pixels differ by more than 1");
            }
        }
    }


    public static void main(String[] args) {
        SeamCarver seamCarver = new SeamCarver(new Picture(args[0]));
        System.out.println(Arrays.toString(seamCarver.findVerticalSeam()));
        //System.out.println(Arrays.toString(seamCarver.findHorizontalSeam()));
    }
}
