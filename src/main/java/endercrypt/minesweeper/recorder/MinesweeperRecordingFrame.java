package endercrypt.minesweeper.recorder;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import endercrypt.minesweeper.MinesweeperTile;
import endercrypt.minesweeper.graphics.MinesweeperGraphics;

public class MinesweeperRecordingFrame
{
	private int width;
	private int height;
	private MinesweeperTile[][] board;

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

	public BufferedImage generateImage()
	{
		int tileWidth = MinesweeperGraphics.getTileWidth();
		int tileHeigt = MinesweeperGraphics.getTileHeight();
		int screenWidth = this.width * tileWidth;
		int screenHeight = this.height * tileHeigt;

		BufferedImage image = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics();
		for (int y = 0; y < this.height; y++)
		{
			for (int x = 0; x < this.width; x++)
			{
				int px = x * tileWidth;
				int py = y * tileHeigt;
				MinesweeperTile tile = this.board[x][y];
				Image tileImage = tile.generateImage();
				g2d.drawImage(tileImage, px, py, null);
			}
		}
		g2d.dispose();
		return image;
	}
}
