package endercrypt.minesweeper.utility.range.primitive;

import endercrypt.minesweeper.utility.range.RangeController;
import endercrypt.minesweeper.utility.range.RangeValue;

public class RangeValueDouble extends RangeValue<Double>
{
	public RangeValueDouble(RangeController<Double> rangeController, Double minimum, Double maximum)
	{
		super(rangeController, minimum, maximum);
	}

	@Override
	protected boolean checkMaxMin(Double minimum, Double maximum)
	{
		return minimum < maximum;
	}

	@Override
	protected boolean checkRange(Double number)
	{
		return number >= this.minimum && number <= this.maximum;
	}
}
