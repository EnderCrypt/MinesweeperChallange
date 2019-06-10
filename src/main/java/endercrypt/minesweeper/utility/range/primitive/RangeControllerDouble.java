package endercrypt.minesweeper.utility.range.primitive;

import java.util.Random;

import endercrypt.minesweeper.utility.range.RangeController;
import endercrypt.minesweeper.utility.range.RangeValue;

public class RangeControllerDouble extends RangeController<Double>
{
	public RangeControllerDouble(String name, double minimum, double maximum)
	{
		super(name, minimum, maximum);
	}

	@Override
	protected RangeValue<Double> createRangeValue(Double minimum, Double maximum)
	{
		return new RangeValueDouble(this, minimum, maximum);
	}

	@Override
	protected Double generateRange(Random random)
	{
		double range = this.max - this.value;
		double number = random.nextDouble() * range;
		return this.value + number;
	}

	@Override
	protected Double generateSpecific()
	{
		return this.value;
	}
}
