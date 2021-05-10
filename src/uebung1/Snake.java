package uebung1;

import java.awt.Point;
import java.util.Iterator;

public class Snake {

	public static void main(String[] args) {
		int maxX = 39;
		int minX = 0;
		int maxY = 9;
		int minY = 0;
		int rangeX = maxX - minX + 1;
		int rangeY = maxY - minY + 1;
		int randX = 0, randY = 0;
		int[] arrayX = new int[10];

		randX = (int) (Math.random() * rangeX) + minX;
		randY = (int) (Math.random() * rangeY) + minY;

		arrayX[0] = randX;

		Point playerPosition = new Point(randX, randY);

		randX = (int) (Math.random() * rangeX) + minX;
		randY = (int) (Math.random() * rangeY) + minY;
		rdmX(arrayX, randX, rangeX, minX);

		Point snakePosition = new Point(randX, randY);

		randX = (int) (Math.random() * rangeX) + minX;
		randY = (int) (Math.random() * rangeY) + minY;
		Point goldPosition = new Point(randX, randY);

		randX = (int) (Math.random() * rangeX) + minX;
		randY = (int) (Math.random() * rangeY) + minY;
		Point doorPosition = new Point(randX, randY);
		boolean rich = false;

		while (true) {
			// Raster mit Figuren zeichnen
			spielfeld(playerPosition, snakePosition, goldPosition, doorPosition);

			// Status feststellen
			if (rich && playerPosition.equals(doorPosition)) {
				System.out.println("Gewonnen!");
				break;
			}
			if (playerPosition.equals(snakePosition)) {
				System.out.println("ZZZZZZZ. Die Schlange hat dich!");
				break;
			}
			if (playerPosition.equals(goldPosition)) {
				rich = true;
				goldPosition.setLocation(-1, -1);
			}

			// Konsoleneingabe und Spielerposition verändern
			consoleInput(playerPosition);

			// Schlange bewegt sich in Richtung Spieler
			snakeMovement(playerPosition, snakePosition);
		}
	}

	private static void spielfeld(Point playerPosition, Point snakePosition, Point goldPosition, Point doorPosition) {
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 40; x++) {
				Point p = new Point(x, y);
				if (playerPosition.equals(p))
					System.out.print('&');
				else if (snakePosition.equals(p))
					System.out.print('S');
				else if (goldPosition.equals(p))
					System.out.print('$');
				else if (doorPosition.equals(p))
					System.out.print('#');
				else
					System.out.print('.');
			}
			System.out.println();
		}
	}

	private static void consoleInput(Point playerPosition) {
		switch (new java.util.Scanner(System.in).next().charAt(0)) {
		case 'h':
			playerPosition.y = Math.max(0, playerPosition.y - 1);
			break;
		case 't':
			playerPosition.y = Math.min(9, playerPosition.y + 1);
			break;
		case 'l':
			playerPosition.x = Math.max(0, playerPosition.x - 1);
			break;
		case 'r':
			playerPosition.x = Math.min(39, playerPosition.x + 1);
			break;
		}
	}

	private static void snakeMovement(Point playerPosition, Point snakePosition) {
		if (playerPosition.x < snakePosition.x)
			snakePosition.x--;
		else if (playerPosition.x > snakePosition.x)
			snakePosition.x++;
		if (playerPosition.y < snakePosition.y)
			snakePosition.y--;
		else if (playerPosition.y > snakePosition.y)
			snakePosition.y++;
	}

	public static void rdmX(int arrayX[], int randX, int rangeX, int minX) {
		for (int i = 0; i < arrayX.length; i++) {
			for (int j = 0; j < i; j++) {
				if (randX == arrayX[j]) {
					randX = (int) (Math.random() * rangeX) + minX;
				}
			}
		}

	}
}
