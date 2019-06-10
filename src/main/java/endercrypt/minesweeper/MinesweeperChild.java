package endercrypt.minesweeper;

public class MinesweeperChild
{
	private Minesweeper minesweeper;

	public MinesweeperChild(Minesweeper minesweeper)
	{
		this.minesweeper = minesweeper;
	}

	public final Minesweeper getMinesweeper()
	{
		return this.minesweeper;
	}

}
