package endercrypt.minesweeper;

import java.util.function.Predicate;

public class MinesweeperInformation extends MinesweeperChild
{
	private final int width, height;
	protected MinesweeperGameState state = MinesweeperGameState.ALIVE;

	public MinesweeperInformation(Minesweeper minesweeper, int width, int height)
	{
		super(minesweeper);
		this.width = width;
		this.height = height;
	}

	public int getWidth()
	{
		return this.width;
	}

	public int getHeight()
	{
		return this.height;
	}

	private int internalCounter(Predicate<MinesweeperTile> predicate)
	{
		int mines = 0;
		for (int y = 0; y < this.height; y++)
		{
			for (int x = 0; x < this.width; x++)
			{
				MinesweeperTile tile = getMinesweeper().get(x, y);
				if (predicate.test(tile))
				{
					mines++;
				}
			}
		}
		return mines;
	}

	protected boolean checkWin()
	{
		for (int y = 0; y < getHeight(); y++)
		{
			for (int x = 0; x < getWidth(); x++)
			{
				MinesweeperTile tile = getMinesweeper().get(x, y);
				if (tile.isOpen() == false && tile.isMine() == false)
				{
					return false;
				}
			}
		}
		return true;
	}

	public int countMines()
	{
		return internalCounter((tile) -> tile.isMine());
	}

	public int countMarks(MinesweeperMark mark)
	{
		return internalCounter((tile) -> tile.getMark() == mark);
	}

	public MinesweeperGameState getGameState()
	{
		return this.state;
	}

	public boolean isAlive()
	{
		return (getGameState() == MinesweeperGameState.ALIVE);
	}

	public boolean isGameOver()
	{
		return (isAlive() == false);
	}

	public void validateAlive()
	{
		if (isGameOver())
		{
			throw new IllegalStateException("Game is " + getGameState());
		}
	}
}
