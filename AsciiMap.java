/**
 * @ Nikunj Gupta 
 * This program creates several methods that are used to flood fill a US states with the user's
 * choice of row, column and character as many times as the user wants. The map is printed after each change.
 */

import java.util.Scanner;

/* JHU CTY AP CS A: Starter class for Unit 16 flood fill problem */
public class AsciiMap {
	/**
	 * ASCII drawing of the map of US with state boundaries
	 */
	private static String[] asciiMap = { "   ", "   ",
			"         ,__                                                  _,   ",
			"      \\~\\|  ~~---___              ,                          | \\   ",
			"       |      / |   ~~~~~~~|~~~~~| ~~---,                  _/,  >   ",
			"      /~-_--__| |          |     \\     / ~\\~~/          /~| ||,'   ",
			"      |       /  \\         |------|   {    / /~)     __-  ',|_\\,   ",
			"     /       |    |~~~~~~~~|      \\    \\   | | '~\\  |_____,|~,-'   ",
			"     |~~--__ |    |        |____  |~~~~~|--| |__ /_-'     {,~   ",
			"     |   |  ~~~|~~|        |    ~~\\     /  `-' |`~ |~_____{/   ",
			"     |   |     |  '---------,      \\----|   |  |  ,' ~/~\\,|`   ",
			"     ',  \\     |    |       |~~~~~~~|    \\  | ,'~~\\  /    |   ",
			"      |   \\    |    |       |       |     \\_-~    /`~___--\\   ",
			"      ',   \\  ,-----|-------+-------'_____/__----~~/      /   ",
			"       '_   '\\|     |      |~~~|     |    |      _/-,~~-,/   ",
			"         \\    |     |      |   |_    |    /~~|~~\\    \\,/   ",
			"          ~~~-'     |      |     `~~~\\___|   |   |    /   ",
			"              '-,_  | _____|          |  /   | ,-'---~\\   ",
			"                  `~'~  \\             |  `--,~~~~-~~,  \\   ",
			"                         \\/~\\      /~~~`---`         |  \\   ",
			"                             \\    /                   \\  |   ",
			"                              \\  |                     '\\'   ", "                               `~'   ",
			"   ", "   ", };
	/**
	 * character array representing the map
	 */
	private char[][] map;

	/**
	 * AsciiMap constructor. Creates map array of characters representing image
	 * being drawn using static asciiMap.
	 */
	public AsciiMap() {
		map = new char[asciiMap.length][80];
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if (c < asciiMap[r].length())
					map[r][c] = asciiMap[r].charAt(c);
				else
					map[r][c] = ' ';
			}

		}
	}

	/**
	 * Returns the width of the image.
	 */
	public int getWidth() {
		return map[0].length;
	}

	/**
	 * Returns the height of the image.
	 */
	public int getHeight() {
		return map.length;
	}

	/**
	 * Returns the character at the given coordinate.
	 * 
	 * @param row    row coordinate of the cell
	 * @param column column coordinate of the cell
	 * @return the character at the given coordinate
	 */
	public char charAt(int row, int column) {
		return map[row][column];
	}

	/**
	 * Implements the flood fill algorithm.
	 * 
	 * @param row    row coordinate of the starting cell
	 * @param column column coordinate of the starting cell
	 * @param ch     character to be used when flooding the array
	 */

	public void floodFill(int row, int column, char ch) {
		// Check if the starting position is within the bounds of the array
		if (row < 0 || row >= map.length || column < 0 || column >= map[0].length) {
			return;
		}

		// Check if the starting position already has the target character
		if (map[row][column] == ch) {
			return;
		}

		// Store the original character at the starting position
		char originalChar = map[row][column];

		// Perform the flood fill
		floodFillHelper(row, column, ch, originalChar);
	}

	// Private helper method for the floodFill method to execute the flood filling
	private void floodFillHelper(int row, int column, char ch, char originalChar) {
		// Check if the current position is within the bounds of the array
		if (row < 0 || row >= map.length || column < 0 || column >= map[0].length) {
			return;
		}

		// Check if the current position has the original character
		if (map[row][column] != originalChar) {
			return;
		}

		// Replace the character at the current position with the target character
		map[row][column] = ch;

		// Recursively call the floodFillHelper method for each adjacent cell
		floodFillHelper(row - 1, column, ch, originalChar); // up
		floodFillHelper(row + 1, column, ch, originalChar); // down
		floodFillHelper(row, column - 1, ch, originalChar); // left
		floodFillHelper(row, column + 1, ch, originalChar); // right
	}

	@Override
	public String toString() {
		System.out.print(" ");
		for (int i = 0; i < map[0].length; i++) {
			System.out.print(i % 10);
		}
		System.out.println("");
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < map.length; r++) {
			sb.append(r % 10);
			for (int c = 0; c < map[r].length; c++) {
				sb.append(map[r][c]);
			}
			sb.append("\n"); // add a newline character after each row
		}
		return sb.toString();
	}

	// main method that asks for user input and accurately prints the map
	public static void main(String[] args) {

		String quit = "no";
		AsciiMap map = new AsciiMap();
		System.out.println("Welcome to the Paint The Map program!\n");
		System.out.println(map);

		while (quit.equals("no")) {
			Scanner input = new Scanner(System.in);
			System.out.println("What row would you like to fill: ");
			int row = input.nextInt();
			System.out.println("What column would you like to fill: ");
			int col = input.nextInt();
			System.out.println("What character would you like to use?: ");
			char fillChar = input.next().charAt(0);

			map.floodFill(row, col, fillChar);

			System.out.println("Map after flood fill at (" + row + "," + col + ") with " + fillChar + ":");
			System.out.println(map);

			System.out.println("Would you like to quit: (Enter no if you want to continue or yes to end)");
			quit = input.next();

		}
	}

}
