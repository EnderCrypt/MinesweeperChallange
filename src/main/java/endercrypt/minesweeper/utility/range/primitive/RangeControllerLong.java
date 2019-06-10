package endercrypt.minesweeper.utility.range.primitive;

import java.util.Random;

import endercrypt.minesweeper.utility.range.RangeController;

public class RangeControllerLong extends RangeController<Long>
{
	public RangeControllerLong(String name, long minimum, long maximum)
	{
		super(name, minimum, maximum);
	}

	@Override
	protected RangeValueLong createRangeValue(Long minimum, Long maximum)
	{
		return new RangeValueLong(this, minimum, maximum);
	}

	@Override
	protected Long generateRange(Random random)
	{
		long range = this.max - this.value;
		long number = random.nextLong() % range;
		return this.value + number;
	}

	@Override
	protected Long generateSpecific()
	{
		return this.value;
	}

}
