package endercrypt.minesweeper.utility.range.primitive;

import endercrypt.minesweeper.utility.range.RangeController;
import endercrypt.minesweeper.utility.range.RangeValue;

public class RangeValueLong extends RangeValue<Long>
{
	public RangeValueLong(RangeController<Long> rangeController, Long minimum, Long maximum)
	{
		super(rangeController, minimum, maximum);
	}

	@Override
	protected boolean checkMaxMin(Long minimum, Long maximum)
	{
		return minimum < maximum;
	}

	@Override
	protected boolean checkRange(Long number)
	{
		return number >= this.minimum && number <= this.maximum;
	}
}
