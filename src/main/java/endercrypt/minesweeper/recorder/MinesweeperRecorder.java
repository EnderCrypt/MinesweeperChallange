package endercrypt.minesweeper.recorder;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.squareup.gifencoder.GifEncoder;
import com.squareup.gifencoder.ImageOptions;

import endercrypt.minesweeper.Minesweeper;
import endercrypt.minesweeper.MinesweeperChild;
import endercrypt.minesweeper.graphics.MinesweeperGraphics;

public class MinesweeperRecorder extends MinesweeperChild
{
	private int width = -1;
	private int height = -1;

	private boolean recording = false;
	private List<MinesweeperRecordingFrame> frames = new ArrayList<>();

	public MinesweeperRecorder(Minesweeper minesweeper)
	{
		super(minesweeper);
	}

	public void setRecording(boolean record)
	{
		this.recording = record;
	}

	public boolean isRecording()
	{
		return this.recording;
	}

	public void saveFrame(MinesweeperRecordingFrame frame)
	{
		Objects.requireNonNull(frame);
		if (this.frames.size() == 0)
		{
			this.width = frame.getWidth();
			this.height = frame.getHeight();
		}
		else
		{
			if (frame.getWidth() != this.width || frame.getHeight() != this.height)
			{
				throw new IllegalArgumentException("frame width,height (" + frame.getWidth() + ", " + frame.getHeight() + ") does not match with recording width,height (" + this.width + ", " + this.height + ")");
			}
		}
		this.frames.add(frame);
	}

	public void saveGif(Path path) throws FileNotFoundException, IOException
	{
		int tileWidth = MinesweeperGraphics.getTileWidth();
		int tileHeigt = MinesweeperGraphics.getTileHeight();
		int screenWidth = this.width * tileWidth;
		int screenHeight = this.height * tileHeigt;

		try (OutputStream output = new BufferedOutputStream(new FileOutputStream(path.toFile())))
		{
			GifEncoder gifEncoder = new GifEncoder(output, screenWidth, screenHeight, 0);
			Iterator<MinesweeperRecordingFrame> iterator = this.frames.iterator();
			while (iterator.hasNext())
			{
				MinesweeperRecordingFrame frame = iterator.next();
				BufferedImage image = frame.generateImage();
				int[] rgbData = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
				ImageOptions options = new ImageOptions();
				options.setDelay(iterator.hasNext() ? 100 : 1000, TimeUnit.MILLISECONDS);

				gifEncoder.addImage(rgbData, screenWidth, options);
			}
			gifEncoder.finishEncoding();
		}
	}
}
