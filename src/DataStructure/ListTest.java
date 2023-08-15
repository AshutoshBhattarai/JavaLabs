package DataStructure;

import java.util.*;
import java.util.stream.Collectors;

public class ListTest {
    public static void main(String[] args) {

        //List test1
        int list3[] = {10, 12, 5, 9, 12};
        // Arrays.stream(list3).sorted().forEach(System.out::println);

        //List test 2
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            numList.add(new Random().nextInt(100));
        }
//        System.out.println(numList.stream().sorted().toList());
//        System.out.println("Reverse list: " + numList.stream().sorted(Comparator.reverseOrder()).toList());
        int index = Arrays.binarySearch(numList.stream().sorted().toArray(), 10);
        System.out.println(index);
        if (index >= 0)
            System.out.println("Found at index" + index);
        else
            System.out.println("Not found");

        List<Integer> filterNumList = numList.stream()
                .sorted()
                .filter(x -> x < 20)
                .map(x -> x * 20)
                .toList();

        //System.out.println(filterNumList);

        int sum = numList.stream().reduce(0, Integer::sum);
        int sum2 = numList.parallelStream()
                .filter(x -> x < 10)
                .mapToInt(n -> n)
                .sum();
        System.out.println("The sum is " + sum2);

        //List Test 3
        List<String> list = Arrays.asList("Alex", "John", "Jane", "Alice", "Bob", "Alex");
        List<String> filterList = list.stream()
                .sorted(Comparator.reverseOrder())
                .filter(x -> x.toLowerCase().startsWith("j"))
                .collect(Collectors.toList());
        System.out.println(list.stream().sorted().collect(Collectors.joining("-")));

        System.out.println(filterList.stream().anyMatch(x -> x.startsWith("z")));

        System.out.println(filterList);

        Map<Boolean, List<String>> groupTest = list.stream().collect(
                Collectors.groupingBy(x -> x.startsWith("A")));
        System.out.println(groupTest);

        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
