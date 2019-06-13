package endercrypt.minesweeper.recorder;

import endercrypt.minesweeper.MinesweeperTile;

public class MinesweeperRecordingFrame
{
	private int width;
	private int height;
	MinesweeperTile[][] board;

	public MinesweeperRecordingFrame(int width, int height, MinesweeperTile[][] board)
	{
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
}
