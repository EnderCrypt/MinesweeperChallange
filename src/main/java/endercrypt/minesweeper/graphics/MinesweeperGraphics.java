package endercrypt.minesweeper.graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;

public class MinesweeperGraphics
{
	private static boolean loaded = false;

	private static int tileWidth;
	private static int tileHeight;
	private static List<BufferedImage> tiles = new ArrayList<>();

	public static synchronized void loadTileset(InputStream inputStream, int tileWidth, int tileHeight) throws IOException
	{
		Objects.requireNonNull(inputStream);

		// load image
		BufferedImage image = ImageIO.read(inputStream);
		if (image == null)
		{
			throw new IllegalArgumentException("inputStream does not contain image");
		}

		// verify size
		if (image.getWidth() % tileWidth != 0 || image.getHeight() % tileHeight != 0)
		{
			throw new IllegalArgumentException("image does not match tile dimensions " + image.getWidth() + ", " + image.getHeight() + " for tiles of size " + tileWidth + ", " + tileHeight);
		}

		// variables
		MinesweeperGraphics.tileWidth = tileWidth;
		MinesweeperGraphics.tileHeight = tileHeight;
		int width = image.getWidth() / tileWidth;
		int height = image.getHeight() / tileHeight;

		// cut & save
		tiles.clear();
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				int px = x * tileWidth;
				int py = y * tileHeight;
				BufferedImage subImage = image.getSubimage(px, py, tileWidth, tileHeight);
				tiles.add(subImage);
			}
		}

		// finalize
		loaded = true;
	}

	private static void verifyTileset()
	{
		if (loaded == false)
		{
			try
			{
				try (InputStream input = new BufferedInputStream(MinesweeperGraphics.class.getResourceAsStream("/tileset.png")))
				{
					loadTileset(input, 32, 32);
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static int getTileWidth()
	{
		verifyTileset();
		return tileWidth;
	}

	public static int getTileHeight()
	{
		verifyTileset();
		return tileHeight;
	}

	public static Image get(int index)
	{
		verifyTileset();
		return tiles.get(index);
	}
}
