package endercrypt.minesweeper;

public class MinesweeperChild
{
	private final Minesweeper minesweeper;

	public MinesweeperChild(MinesweeperChild minesweeperChild)
	{
		this(minesweeperChild.getMinesweeper());
	}

	public MinesweeperChild(Minesweeper minesweeper)
	{
		this.minesweeper = minesweeper;
	}

	public final Minesweeper getMinesweeper()
	{
		return this.minesweeper;
	}

}
