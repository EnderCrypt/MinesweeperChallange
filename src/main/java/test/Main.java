package test;

import endercrypt.minesweeper.Minesweeper;
import endercrypt.minesweeper.MinesweeperBuilder;

public class Main
{
	public static void main(String[] args)
	{
		MinesweeperBuilder builder = new MinesweeperBuilder();
		builder.seed().any();
		builder.width().setSpecific(10);
		builder.height().setSpecific(10);
		builder.bombs().percent().setSpecific(0.2);
		Minesweeper minesweeper = builder.build();
		minesweeper.setAutoprint(true);

		for (int y = 0; y < minesweeper.getInformation().getHeight(); y++)
		{
			for (int x = 0; x < minesweeper.getInformation().getWidth(); x++)
			{
				minesweeper.get(x, y).open();
			}
		}
	}
}
