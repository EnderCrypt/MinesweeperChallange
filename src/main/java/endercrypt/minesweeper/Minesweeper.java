package endercrypt.minesweeper;

import java.util.NoSuchElementException;
import java.util.Random;

import endercrypt.minesweeper.recorder.MinesweeperRecorder;
import endercrypt.minesweeper.recorder.MinesweeperRecordingFrame;

public class Minesweeper
{
	private MinesweeperTile[][] board;

	private boolean autoprint = false;
	private MinesweeperInformation information;
	private MinesweeperRecorder recorder;

	public Minesweeper(Random random, int width, int height, int bombs, boolean record)
	{
		this.information = new MinesweeperInformation(this, width, height);

		// recorder
		this.recorder = record ? new MinesweeperRecorder(this) : null;
		this.recordFrame();

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

	public void setAutoprint(boolean autoprint)
	{
		this.autoprint = autoprint;
	}

	public MinesweeperRecorder getRecorder()
	{
		if (this.recorder == null)
		{
			throw new NoSuchElementException("The recorder is not present as was set in " + MinesweeperBuilder.class.getSimpleName());
		}
		return this.recorder;
	}

	private void recordFrame()
	{
		if (this.recorder != null)
		{
			this.recorder.saveFrame(new MinesweeperRecordingFrame(getWidth(), getHeight(), this.board));
		}
	}

	protected void onModify()
	{
		// check win
		if (getInformation().checkWin())
		{
			getInformation().state = MinesweeperGameState.WON;
		}

		// record
		recordFrame();

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
