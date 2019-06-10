package endercrypt.minesweeper.utility.range.primitive;

import endercrypt.minesweeper.utility.range.RangeController;
import endercrypt.minesweeper.utility.range.RangeValue;

public class RangeValueInteger extends RangeValue<Integer>
{
	public RangeValueInteger(RangeController<Integer> rangeController, Integer minimum, Integer maximum)
	{
		super(rangeController, minimum, maximum);
	}

	@Override
	protected boolean checkMaxMin(Integer minimum, Integer maximum)
	{
		return minimum < maximum;
	}

	@Override
	protected boolean checkRange(Integer number)
	{
		return number >= this.minimum && number <= this.maximum;
	}
}
