import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;

public class AndroidPatternGenerator {
	private static final int MATRIX_ORDER = 3;
	private static final int NO_OF_POINTS = MATRIX_ORDER * MATRIX_ORDER;
	private static final int MIN_PATTERN_LENGTH = 4;

	private static HashSet<String> possiblePatterns;

	public static void main(String[] args) throws IOException{
		possiblePatterns = new HashSet<String>();
		for (int i=0; i<NO_OF_POINTS; i++)
			getPattern(i + "", i);

		System.out.println("Generated [" + possiblePatterns.size() + "] patterns");

		ArrayList<String> patternList = new ArrayList<String>();
		patternList.addAll(possiblePatterns);

		final String sequentialFilePath = "sequential.txt";
		System.out.print("\nWriting sequential output to [" + sequentialFilePath + "]...");
		Collections.sort(patternList);
		BufferedWriter sequentialWriter = new BufferedWriter(new FileWriter(sequentialFilePath));
		for (String pattern: patternList)
			sequentialWriter.write(pattern + "\n");
		sequentialWriter.close();
		System.out.println("Done");

		final String shuffledFilePath = "shuffled.txt";
		System.out.print("\nWriting shuffled output to [" + shuffledFilePath + "]...");
		Collections.shuffle(patternList);
		BufferedWriter shuffledWriter = new BufferedWriter(new FileWriter(shuffledFilePath));
		for (String pattern: patternList)
			randomWriter.write(pattern + "\n");
		shuffledWriter.close();
		System.out.println("Done");
	}

	private static void getPattern(String currentPattern, int currentPoint) {
		Stack<Integer> pointStack = new Stack<Integer>();

		for (int i=NO_OF_POINTS-1; i>=0; i--)
			if (!currentPattern.contains(i + "") && isReachable(currentPattern, currentPoint, i))
				pointStack.push(i);
		
		while (!pointStack.isEmpty()) {
			int poppedPoint = pointStack.pop();

			String newPattern = currentPattern + poppedPoint;
			if (newPattern.length() >= MIN_PATTERN_LENGTH)
				possiblePatterns.add(newPattern);
			getPattern(newPattern, poppedPoint);
		}
	}

	private static boolean isReachable(String pattern, int point1, int point2) {
		int row1 = point1 / MATRIX_ORDER;
		int row2 = point2 / MATRIX_ORDER;
		int rowDiff = Math.abs(row1 - row2);
		if (rowDiff == 1)
			return true;

		int col1 = point1 % MATRIX_ORDER;
		int col2 = point2 % MATRIX_ORDER;
		int colDiff = Math.abs(col1 - col2);
		if (colDiff == 1)
			return true;

		if (rowDiff == 0 || colDiff == 0 || rowDiff == colDiff) {
			int midPoint = (point1 + point2) / 2;
			if (!pattern.contains(midPoint + ""))
				return false;
		}

		return true;
	}
}
