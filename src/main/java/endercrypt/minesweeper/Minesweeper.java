package endercrypt.minesweeper;

import java.util.Random;

public class Minesweeper
{
	private MinesweeperTile[][] board;

	private boolean record = false;
	private boolean autoprint = false;

	private MinesweeperInformation information;

	public Minesweeper(Random random, int width, int height, int bombs)
	{
		this.information = new MinesweeperInformation(this, width, height);

		// bomb board
		boolean[][] bombBoard = new boolean[width][height];
		int placedBombs = 0;
		while (placedBombs < bombs)
		{
			int x = (int) (width * random.nextDouble());
			int y = (int) (height * random.nextDouble());
			if (bombBoard[x][y] == false)
			{
				bombBoard[x][y] = true;
				placedBombs++;
			}
		}

		// real board
		this.board = new MinesweeperTile[width][height];
		for (int y = 0; y < this.information.getHeight(); y++)
		{
			for (int x = 0; x < this.information.getWidth(); x++)
			{
				this.board[x][y] = new MinesweeperTile(this, x, y, bombBoard[x][y]);
			}
		}

		// init neighbours
		for (int y = 0; y < this.information.getHeight(); y++)
		{
			for (int x = 0; x < this.information.getWidth(); x++)
			{
				this.board[x][y].initializeTile();
			}
		}
	}

	public int getWidth()
	{
		return this.information.getWidth();
	}

	public int getHeight()
	{
		return this.information.getHeight();
	}

	public MinesweeperInformation getInformation()
	{
		return this.information;
	}

	public boolean isInside(int x, int y)
	{
		return ((x >= 0) && (y >= 0) && (x < getWidth()) && (y < getHeight()));
	}

	public MinesweeperTile get(int x, int y)
	{
		return this.board[x][y];
	}

	public String getAscii()
	{
		StringBuilder sb = new StringBuilder();

		for (int y = 0; y < getHeight(); y++)
		{
			for (int x = 0; x < getWidth(); x++)
			{
				MinesweeperTile tile = this.board[x][y];
				sb.append(tile.getAscii());
			}
			sb.append('\n');
		}
		sb.setLength(sb.length() - 1);

		return sb.toString();
	}

	public void setRecord(boolean record)
	{
		this.record = record;
	}

	public boolean isRecord()
	{
		return this.record;
	}

	public void setAutoprint(boolean autoprint)
	{
		this.autoprint = autoprint;
	}

	protected void onModify()
	{
		// check win
		if (getInformation().checkWin())
		{
			getInformation().state = MinesweeperGameState.WON;
		}

		// auto print
		if (this.autoprint)
		{
			print();
		}
	}

	public void print()
	{
		System.out.println(getAscii());
		System.out.println();
	}
}
