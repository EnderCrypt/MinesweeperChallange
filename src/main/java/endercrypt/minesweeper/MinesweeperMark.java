package endercrypt.minesweeper;

public enum MinesweeperMark
{
	UNMARKED(' '),
	QUESTION('?'),
	BOMB('!');

	private char ascii;

	private MinesweeperMark(char ascii)
	{
		this.ascii = ascii;
	}

	public char getAscii()
	{
		return this.ascii;
	}
}
