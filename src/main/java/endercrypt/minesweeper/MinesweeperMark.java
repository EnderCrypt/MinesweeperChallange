package endercrypt.minesweeper;

import endercrypt.minesweeper.graphics.MinesweeperGraphicsIndex;
import endercrypt.minesweeper.graphics.MinesweeperGraphicsPointer;

public enum MinesweeperMark
{
	UNMARKED(MinesweeperGraphicsIndex.UNOPENED),
	QUESTION(MinesweeperGraphicsIndex.QUESTION),
	MINE(MinesweeperGraphicsIndex.EXCLAMATION);

	private MinesweeperGraphicsPointer graphicsPointer;

	private MinesweeperMark(MinesweeperGraphicsPointer graphicsPointer)
	{
		this.graphicsPointer = graphicsPointer;
	}

	public MinesweeperGraphicsPointer getGraphicsPointer()
	{
		return this.graphicsPointer;
	}
}
