package tevalcourse.merge_arrays;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinHeap {
    private final ArrayList<Integer> elements = new ArrayList<>();

    public void add(Integer e) {
        elements.add(e);
        int elementIndex = elements.size() - 1;
        while (!(elementIndex == 0) && !isCorrectChild(elementIndex)) {
            int parentIndex = parentIndex(elementIndex);
            swap(elementIndex, parentIndex);
            elementIndex = parentIndex;
        }
    }

    public ArrayList<Integer> popAll() {
        return IntStream.range(0, elements.size())
                .mapToObj(num -> popOne())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Integer popOne() {
        if (elements.isEmpty()) {
            throw new RuntimeException("Empty heap");
        }

        Integer result = elements.get(0);

        int lastIndex = elements.size() - 1;
        swap(0, lastIndex);
        elements.remove(lastIndex);

        int elementIndex = 0;
        while (!isLeaf(elementIndex) && !isCorrectParent(elementIndex)) {
            int smallerChildIndex = smallerChildIndex(elementIndex);
            swap(elementIndex, smallerChildIndex);
            elementIndex = smallerChildIndex;
        }

        return result;
    }

    private int smallerChildIndex(int index) {
        int leftChildIndex = leftChildIndex(index);
        int rightChildIndex = rightChildIndex(index);

        if (isInvalidIndex(rightChildIndex)
                || elements.get(leftChildIndex).compareTo(elements.get(rightChildIndex)) < 0) {
            return leftChildIndex;
        }

        return rightChildIndex;
    }

    private boolean isLeaf(int index) {
        return isInvalidIndex(leftChildIndex(index));
    }

    private boolean isCorrectParent(int index) {
        return isCorrect(index, leftChildIndex(index)) && isCorrect(index, rightChildIndex(index));
    }

    private boolean isCorrectChild(int index) {
        return isCorrect(parentIndex(index), index);
    }

    private boolean isCorrect(int parentIndex, int childIndex) {
        if (isInvalidIndex(parentIndex) || isInvalidIndex(childIndex)) {
            return true;
        }
        return elements.get(parentIndex).compareTo(elements.get(childIndex)) < 0;
    }

    private boolean isInvalidIndex(int index) {
        return index >= elements.size();
    }

    private void swap(int index1, int index2) {
        Integer tmp = elements.get(index1);
        elements.set(index1, elements.get(index2));
        elements.set(index2, tmp);
    }

    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int rightChildIndex(int index) {
        return 2 * index + 2;
    }
}
