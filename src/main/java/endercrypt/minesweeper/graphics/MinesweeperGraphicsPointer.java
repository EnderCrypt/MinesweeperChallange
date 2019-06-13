package endercrypt.minesweeper.graphics;

import java.awt.Image;

public class MinesweeperGraphicsPointer
{
	private int tilesetIndex;
	private String asciiGraphics;

	protected MinesweeperGraphicsPointer(int tilesetIndex, String asciiGraphics)
	{
		this.tilesetIndex = tilesetIndex;
		this.asciiGraphics = asciiGraphics;
	}

	public Image getImage()
	{
		return MinesweeperGraphics.get(this.tilesetIndex);
	}

	public String getAscii()
	{
		return this.asciiGraphics;
	}
}
