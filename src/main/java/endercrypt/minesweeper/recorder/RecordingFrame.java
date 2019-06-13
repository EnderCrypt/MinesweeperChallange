package endercrypt.minesweeper.recorder;

import endercrypt.minesweeper.MinesweeperTile;

public class RecordingFrame
{
	private int width;
	private int height;
	MinesweeperTile[][] board;

	public RecordingFrame(int width, int height, MinesweeperTile[][] board)
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
