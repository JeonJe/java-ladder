package nextstep.fp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamStudy {

	public static long countWords() throws IOException {
		String contents = new String(Files.readAllBytes(Paths
				.get("src/main/resources/fp/war-and-peace.txt")), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

		long count = 0;
		for (String w : words) {
		  if (w.length() > 12) count++;  
		}
		return count;
	}

	public static void printLongestWordTop100() throws IOException {
		String contents = new String(Files.readAllBytes(Paths
				.get("src/main/resources/fp/war-and-peace.txt")), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		
		List<String> wordTop100 = words.stream()
				.filter(word -> word.length() > 12)
				.sorted(Comparator.comparing(String::length).reversed())
				.distinct()
				.map(word -> word.toLowerCase())
				.limit(100)
				.collect(Collectors.toList());

		System.out.println(wordTop100);
	}

	public static List<Integer> doubleNumbers(List<Integer> numbers) {
		return numbers.stream().map(x -> 2 * x).collect(Collectors.toList());
	}

	public static long sumAll(List<Integer> numbers) {
		return numbers.stream().reduce(0, (x, y) -> x + y);
	}

	public static long sumOverThreeAndDouble(List<Integer> numbers) {
		return numbers.stream()
				.filter(x -> x > 3)
				.map(x -> x * 2)
				.reduce(0, (x, y) -> x + y);
	}
}