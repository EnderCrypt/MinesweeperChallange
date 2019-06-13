package endercrypt.minesweeper;

import java.util.Random;

import endercrypt.minesweeper.utility.range.RangeValue;
import endercrypt.minesweeper.utility.range.primitive.RangeControllerDouble;
import endercrypt.minesweeper.utility.range.primitive.RangeControllerInteger;
import endercrypt.minesweeper.utility.range.primitive.RangeControllerLong;
import endercrypt.minesweeper.utility.range.primitive.RangeValueInteger;

public class MinesweeperBuilder
{
	private final RangeControllerLong seed = new RangeControllerLong("seed", 0, Long.MAX_VALUE);
	private final RangeControllerInteger width = new RangeControllerInteger("width", 0, Integer.MAX_VALUE);
	private final RangeControllerInteger height = new RangeControllerInteger("height", 0, Integer.MAX_VALUE);
	private final RangeControllerInteger bombs = new RangeControllerInteger("bombs", 0, Integer.MAX_VALUE);
	private final RangeControllerDouble bombsPercent = new RangeControllerDouble("bombsPercent", 0.0, 1.0);
	private boolean record = false;

	public MinesweeperBuilder()
	{
		seed().any();
	}

	public RangeValue<Long> seed()
	{
		return this.seed.getRangeValue();
	}

	public RangeValueInteger width()
	{
		return this.width.getRangeValue();
	}

	public RangeValueInteger height()
	{
		return this.height.getRangeValue();
	}

	public BombsOption bombs()
	{
		return new BombsOption()
		{
			@Override
			public RangeValue<Integer> count()
			{
				return MinesweeperBuilder.this.bombs.getRangeValue();
			}

			@Override
			public RangeValue<Double> percent()
			{
				return MinesweeperBuilder.this.bombsPercent.getRangeValue();
			}
		};
	}

	public interface BombsOption
	{
		public RangeValue<Integer> count();

		public RangeValue<Double> percent();
	}

	public void setRecord(boolean record)
	{
		this.record = record;
	}

	public Minesweeper build()
	{
		long seed = this.seed.generate(new Random());
		Random random = new Random(seed);

		int width = this.width.generate(random);
		int height = this.height.generate(random);
		if (this.bombs.isUnset() && this.bombsPercent.isUnset())
		{
			throw new IllegalArgumentException("bomb count not set");
		}
		int bombs = 0;
		if (this.bombs.isSet())
		{
			bombs = this.bombs.generate(random);
		}
		else
		{
			double percent = this.bombsPercent.generate(random);
			bombs = (int) Math.floor((width * height) * percent);
		}

		return new Minesweeper(random, width, height, bombs, this.record);
	}
}
