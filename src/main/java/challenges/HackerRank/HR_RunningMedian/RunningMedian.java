package challenges.HackerRank.HR_RunningMedian;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class RunningMedian {


    public static void main(String[] args) {
        // Lista fixa de valores
        List<Integer> a = List.of(12, 4, 5, 3, 8, 7);

        // Calcula a mediana em execução
        List<Double> result = runningMedian(a);

        // Imprime os resultados
        result.forEach(System.out::println);
    }

    public static List<Double> runningMedian(List<Integer> numbers) {
        PriorityQueue<Integer> lowers = new PriorityQueue<>((first, second) -> second - first);
        PriorityQueue<Integer> highers = new PriorityQueue<>();
        List<Double> answ = new ArrayList<>();
        for (Integer num : numbers) {
            addNum(num, lowers, highers);
            balanceHeaps(lowers, highers);
            answ.add(getMedian(lowers, highers));
        }
        return answ;
    }

    private static void balanceHeaps(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
        PriorityQueue<Integer> biggerHeap = lowers.size() > highers.size() ? lowers : highers;
        PriorityQueue<Integer> lowerHeap = highers.size() < lowers.size() ? highers : lowers;
        if (biggerHeap.size() - lowerHeap.size() >= 2) {
            lowerHeap.add(biggerHeap.poll());
        }
    }

    private static void addNum(Integer num, PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
        if (lowers.isEmpty() || num < lowers.peek()) {
            lowers.add(num);
        } else {
            highers.add(num);
        }
    }

    private static Double getMedian(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {

        PriorityQueue<Integer> biggerHeap = lowers.size() > highers.size() ? lowers : highers;
        PriorityQueue<Integer> lowerHeap = highers.size() < lowers.size() ? highers : lowers;

        if (biggerHeap.size() == lowerHeap.size()) {
            return ((double) biggerHeap.peek() + (double) lowerHeap.peek()) / 2;
        } else {
            return (double) biggerHeap.peek();
        }
    }
}
