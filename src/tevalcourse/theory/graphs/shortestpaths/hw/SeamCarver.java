package tevalcourse.theory.graphs.shortestpaths.hw;

import edu.princeton.cs.algs4.Picture;

import java.awt.Color;
import java.util.Arrays;

public class SeamCarver {

    private static final double BORDER_ENERGY = 1000;

    private Picture picture;
    private double[][] energy;

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
        return new Picture(picture);
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

        double[][] distTo = new double[width][height];
        int[][] edgeTo = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                distTo[x][y] = x == 0 ? energy[x][y] : Double.POSITIVE_INFINITY;
            }
        }

        for (int x = 1; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double minEnergy = distTo[x - 1][y];
                int minCol = y;

                if (y != 0) {
                    if (distTo[x - 1][y - 1] < minEnergy) {
                        minEnergy = distTo[x - 1][y - 1];
                        minCol = y - 1;
                    }
                }

                if (y != height - 1) {
                    if (distTo[x - 1][y + 1] < minEnergy) {
                        minEnergy = distTo[x - 1][y + 1];
                        minCol = y + 1;
                    }
                }

                if (energy[x][y] + minEnergy < distTo[x][y]) {
                    distTo[x][y] = energy[x][y] + minEnergy;
                    edgeTo[x][y] = minCol;
                }
            }
        }

        double minEnergyLastRow = Double.POSITIVE_INFINITY;
        int minRolLastCol = 0;
        for (int i = 0; i < height; i++) {
            if (distTo[width - 1][i] < minEnergyLastRow) {
                minEnergyLastRow = distTo[width - 1][i];
                minRolLastCol = i;
            }
        }

        int[] horSeam = new int[width];
        for (int i = width - 1; i >= 0; i--) {
            horSeam[i] = minRolLastCol;
            minRolLastCol = edgeTo[i][minRolLastCol];
        }

        return horSeam;
    }

    public int[] findVerticalSeam() {
        int width = width();
        int height = height();

        double[][] distTo = new double[width][height];
        int[][] edgeTo = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                distTo[x][y] = y == 0 ? energy[x][y] : Double.POSITIVE_INFINITY;
            }
        }

        for (int y = 1; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double minEnergy = distTo[x][y - 1];
                int minCol = x;

                if (x != 0) {
                    if (distTo[x - 1][y - 1] < minEnergy) {
                        minEnergy = distTo[x - 1][y - 1];
                        minCol = x - 1;
                    }
                }

                if (x != width - 1) {
                    if (distTo[x + 1][y - 1] < minEnergy) {
                        minEnergy = distTo[x + 1][y - 1];
                        minCol = x + 1;
                    }
                }

                if (energy[x][y] + minEnergy < distTo[x][y]) {
                    distTo[x][y] = energy[x][y] + minEnergy;
                    edgeTo[x][y] = minCol;
                }
            }
        }

        double minEnergyLastCol = Double.POSITIVE_INFINITY;
        int minColLastRow = 0;
        for (int i = 0; i < width; i++) {
            if (distTo[i][height - 1] < minEnergyLastCol) {
                minEnergyLastCol = distTo[i][height - 1];
                minColLastRow = i;
            }
        }

        int[] vertSeam = new int[height];
        for (int i = height - 1; i >= 0; i--) {
            vertSeam[i] = minColLastRow;
            minColLastRow = edgeTo[minColLastRow][i];
        }

        return vertSeam;
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
        this.picture = resizedPicture;
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
        this.picture = resizedPicture;
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
            int diff = nextPixel - pixel;
            if (diff > 1 || diff < -1) {
                throw new IllegalArgumentException("Seam pixels differ by more than 1");
            }
        }
    }


    public static void main(String[] args) {
        SeamCarver seamCarver = new SeamCarver(new Picture(args[0]));
        System.out.println(seamCarver.width() + " " + seamCarver.height());

        int[] verticalSeam = seamCarver.findVerticalSeam();
        System.out.println(Arrays.toString(verticalSeam));

        seamCarver.removeVerticalSeam(verticalSeam);
        System.out.println(seamCarver.width() + " " + seamCarver.height());

        int[] horizontalSeam = seamCarver.findHorizontalSeam();
        System.out.println(Arrays.toString(horizontalSeam));

        seamCarver.removeHorizontalSeam(horizontalSeam);
        System.out.println(seamCarver.width() + " " + seamCarver.height());
    }
}
