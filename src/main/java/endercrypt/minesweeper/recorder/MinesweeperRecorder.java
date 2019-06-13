package endercrypt.minesweeper.recorder;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.squareup.gifencoder.GifEncoder;
import com.squareup.gifencoder.ImageOptions;

import endercrypt.minesweeper.Minesweeper;
import endercrypt.minesweeper.MinesweeperChild;
import endercrypt.minesweeper.graphics.MinesweeperGraphics;

public class MinesweeperRecorder extends MinesweeperChild
{
	private static final ExecutorService executor = Executors.newFixedThreadPool(4);

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

	public void saveGif(Path path) throws MinesweeperRecorderException
	{
		saveGif(path, executor);
	}

	public void saveGif(Path path, ExecutorService executor) throws MinesweeperRecorderException
	{
		// dimensions
		int tileWidth = MinesweeperGraphics.getTileWidth();
		int tileHeigt = MinesweeperGraphics.getTileHeight();
		int screenWidth = this.width * tileWidth;
		int screenHeight = this.height * tileHeigt;

		try
		{
			// generate images
			List<Future<BufferedImage>> imageFutures = new ArrayList<>();
			this.frames.stream().map(frame -> executor.submit(() -> frame.generateImage())).forEach(imageFutures::add);

			// save images
			try (OutputStream output = new BufferedOutputStream(new FileOutputStream(path.toFile()), 1024 * 16))
			{
				GifEncoder gifEncoder = new GifEncoder(output, screenWidth, screenHeight, 0);

				int frame = 0;
				Iterator<Future<BufferedImage>> iterator = imageFutures.iterator();
				while (iterator.hasNext())
				{
					frame++;

					// image
					BufferedImage image = iterator.next().get();
					int[] rgbData = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

					// options
					ImageOptions options = new ImageOptions();
					int speed = (int) Math.max(1000.0 / frame, 100);
					options.setDelay(iterator.hasNext() ? speed : 1000, TimeUnit.MILLISECONDS);

					// save image
					gifEncoder.addImage(rgbData, screenWidth, options);
				}
				gifEncoder.finishEncoding();
			}
		}
		catch (ExecutionException | IOException | InterruptedException e)
		{
			throw new MinesweeperRecorderException(e);
		}
	}
}
