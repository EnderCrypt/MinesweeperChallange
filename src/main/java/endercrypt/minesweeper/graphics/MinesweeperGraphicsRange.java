package endercrypt.minesweeper.graphics;

import java.util.function.Function;

public class MinesweeperGraphicsRange
{
	private int tile_start;
	private int index_start;
	private int index_stop;

	private Function<Integer, String> asciiGenerator;

	protected MinesweeperGraphicsRange(int tile_start, int index_start, int index_stop, Function<Integer, String> asciiGenerator)
	{
		this.tile_start = tile_start;
		this.index_start = index_start;
		this.index_stop = index_stop;
		this.asciiGenerator = asciiGenerator;
	}

	public MinesweeperGraphicsPointer getIndex(int index)
	{
		if (index < this.index_start || index > this.index_stop)
		{
			throw new IllegalArgumentException("index must be whitin range (" + this.index_start + " to " + this.index_stop + ")");
		}
		int tile_index = this.tile_start + index;
		return new MinesweeperGraphicsPointer(tile_index, this.asciiGenerator.apply(tile_index));
	}
}
