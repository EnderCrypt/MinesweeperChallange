package endercrypt.minesweeper.utility.range.primitive;

import java.util.Random;

import endercrypt.minesweeper.utility.range.RangeController;

public class RangeControllerInteger extends RangeController<Integer>
{
	public RangeControllerInteger(String name, int minimum, int maximum)
	{
		super(name, minimum, maximum);
	}

	@Override
	protected RangeValueInteger createRangeValue(Integer minimum, Integer maximum)
	{
		return new RangeValueInteger(this, minimum, maximum);
	}

	@Override
	protected Integer generateRange(Random random)
	{
		int range = this.max - this.value;
		int number = random.nextInt(range);
		return this.value + number;
	}

	@Override
	protected Integer generateSpecific()
	{
		return this.value;
	}

}
