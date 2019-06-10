package endercrypt.minesweeper.utility.range;

import java.util.Objects;
import java.util.Random;
import endercrypt.minesweeper.utility.MinesweeperUtility;

public abstract class RangeController<T extends Number>
{
	private final String name;
	private final RangeValue<T> rangeValue;

	protected T value = null;
	protected T max = null;

	public RangeController(String name, T minimum, T maximum)
	{
		Objects.requireNonNull(name);
		this.name = MinesweeperUtility.capitalize(name);
		this.rangeValue = createRangeValue(minimum, maximum);
	}

	protected abstract RangeValue<T> createRangeValue(T minimum, T maximum);

	public boolean isUnset()
	{
		return (this.value == null);
	}

	public boolean isSet()
	{
		return (isUnset() == false);
	}

	public boolean isRange()
	{
		return (this.max != null);
	}

	public <R extends RangeValue<T>> R getRangeValue()
	{
		return (R) this.rangeValue;
	}

	public T generate(Random random)
	{
		if (isUnset())
		{
			throw new IllegalArgumentException("value not set for " + this.name);
		}
		if (isRange())
		{
			return generateRange(random);
		}
		else
		{
			return generateSpecific();
		}
	}

	protected abstract T generateRange(Random random);

	protected abstract T generateSpecific();
}
