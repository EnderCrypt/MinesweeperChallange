package endercrypt.minesweeper.graphics;

public class MinesweeperGraphicsIndex
{
	public static final MinesweeperGraphicsRange NUMBERS = new MinesweeperGraphicsRange(0, 1, 8, (i) -> " " + i + " ");

	private static int index = 9;
	public static final MinesweeperGraphicsPointer EMPTY = new MinesweeperGraphicsPointer(index++, "   ");
	public static final MinesweeperGraphicsPointer MINE = new MinesweeperGraphicsPointer(index++, " X ");
	public static final MinesweeperGraphicsPointer UNOPENED = new MinesweeperGraphicsPointer(index++, "[ ]");
	public static final MinesweeperGraphicsPointer EXCLAMATION = new MinesweeperGraphicsPointer(index++, "[!]");
	public static final MinesweeperGraphicsPointer QUESTION = new MinesweeperGraphicsPointer(index++, "[?]");
}
