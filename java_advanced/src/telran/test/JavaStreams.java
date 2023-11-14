package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class JavaStreams {
	
	int[] arr = {10, 13, 8, 7, 3, 5 , 6};

	@Test
	@Disabled
	void arrayStreamTest() {
		
		//computing sum of the even numbers
		
		int [] empty = {};
		assertEquals(24, Arrays.stream(arr)
				.filter(n -> n % 2 == 0)
				.sum());
		assertEquals(0, Arrays.stream(empty)
				.filter(n -> n % 2 != 0)
				.max().orElse(0));
		assertEquals(13, Arrays.stream(arr)
				.filter(n -> n % 2 != 0)
				.max().orElse(0));
	
	}
	
	@Test
	@Disabled
	void displaySportloto() {
		
		Random gen = new Random();
		gen.ints(7, 1, 50).distinct().forEach(n -> System.out.print(n + " "));
	
	}
	
	@Test
	@Disabled
	void evenOddGrouping() {
		
		Map<String, List<Integer>> mapOddEven = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(n -> n % 2 == 0 ? "even" : "odd"));
		System.out.println(mapOddEven);
		
	}
	
	@Test
	@Disabled
	void displayOccurenceSorted() {
		
		String[] strings = {"lpm", "y", "a", "lpm", "aa", "yy", "yy", "aa", "lpm"};
		Map<String, Long> occurancesMap = Arrays.stream(strings).collect(Collectors.groupingBy(s -> s, Collectors.counting()));
		occurancesMap.entrySet().stream().sorted((e1, e2) -> {
			int res = Long.compare(e2.getValue(), e1.getValue());
			if (res == 0) {res = e1.getKey().compareTo(e2.getKey());}
			return res;
		}).forEach(e -> System.out.printf("$s => %d\n", e.getKey(), e.getValue()));
		
	}
	
	@Test
	void stringStream() {
		
		String string = "Hello";
		
		//string.chars().forEach(c -> System.out.printf("%c,", c));
		
		string.chars().mapToObj(c -> "" + (char)c).forEach(s -> System.out.printf(s + ","));
		
	}

}

