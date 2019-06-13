package endercrypt.minesweeper.recorder;

import endercrypt.minesweeper.Minesweeper;
import endercrypt.minesweeper.MinesweeperChild;

public class MinesweeperRecorder extends MinesweeperChild
{
	private boolean recording = false;

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

	public void saveFrame(RecordingFrame frame)
	{
		// TODO Auto-generated method stub
	}
}
