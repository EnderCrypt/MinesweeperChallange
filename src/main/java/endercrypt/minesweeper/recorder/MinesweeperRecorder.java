package endercrypt.minesweeper.recorder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import endercrypt.minesweeper.Minesweeper;
import endercrypt.minesweeper.MinesweeperChild;

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

	public void saveGif(Path path)
	{

	}
}
