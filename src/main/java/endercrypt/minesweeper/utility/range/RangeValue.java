package endercrypt.minesweeper.utility.range;

import java.util.Objects;

public abstract class RangeValue<T extends Number>
{
	private final RangeController<T> rangeController;

	protected final T minimum;
	protected final T maximum;

	public RangeValue(RangeController<T> rangeController, T minimum, T maximum)
	{
		Objects.requireNonNull(rangeController);
		Objects.requireNonNull(minimum);
		Objects.requireNonNull(maximum);

		this.rangeController = rangeController;

		this.minimum = minimum;
		this.maximum = maximum;
	}

	public void setSpecific(T value)
	{
		Objects.requireNonNull(value);

		validateRange(value);

		this.rangeController.value = value;
		this.rangeController.max = null;
	}

	public void setRange(T minimum, T maximum)
	{
		Objects.requireNonNull(minimum);
		Objects.requireNonNull(maximum);

		if (checkMaxMin(minimum, maximum) == false)
		{
			throw new IllegalArgumentException("maximum (" + maximum + ") cannot be less than minimum (" + minimum + ")");
		}

		validateRange(minimum);
		validateRange(maximum);

		this.rangeController.value = minimum;
		this.rangeController.max = maximum;
	}

	private void validateRange(T number)
	{
		if (checkRange(number) == false)
		{
			throw new IllegalArgumentException(number + " must be between " + this.minimum + " and " + this.maximum);
		}
	}

	protected abstract boolean checkMaxMin(T minimum, T maximum);

	protected abstract boolean checkRange(T number);

	public void any()
	{
		setRange(this.minimum, this.maximum);
	}
}
