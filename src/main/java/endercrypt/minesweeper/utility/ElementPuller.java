package endercrypt.minesweeper.utility;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jooq.lambda.Seq;

public class ElementPuller<T>
{
	private Supplier<T> supplier;

	public ElementPuller(Stream<T> stream)
	{
		this(stream.collect(Collectors.toList()));
	}

	public ElementPuller(List<T> list)
	{
		this(new Supplier<T>()
		{
			private Iterator<T> iterator = list.iterator();

			@Override
			public T get()
			{
				return (this.iterator.hasNext()) ? this.iterator.next() : null;
			}
		});
	}

	public ElementPuller(Supplier<T> supplier)
	{
		this.supplier = supplier;
	}

	/* SINGLE */

	/**
	 * returns 1 element or null if unavailable
	 * @return an instance or null
	 */
	public T nullable()
	{
		return this.supplier.get();
	}

	/**
	 * returns an optional that will contain an element if available
	 * @return an optional
	 */
	public Optional<T> optional()
	{
		return Optional.ofNullable(nullable());
	}

	/**
	 * returns an element if available, if not, returs the element supplied as argument
	 * @param other
	 * @return an element
	 */
	public T orElse(T element)
	{
		return optional().orElse(element);
	}

	/**
	 * returns an element if available, if not, returs the element supplied from the supplier from the arguments
	 * @param other
	 * @return
	 */
	public T orElseGet(Supplier<T> elementSupplier)
	{
		Objects.requireNonNull(elementSupplier);
		return optional().orElseGet(elementSupplier);
	}

	/**
	 * returns an element or throws a NoSuchElementException exception if no element is available
	 * @return an instance
	 */
	public T demand()
	{
		return demandOrElseThrow(NoSuchElementException::new);
	}

	/**
	 * returns an element if available or throw the supplied exception
	 * @param exceptionSupplier
	 * @return <T> or
	 * @throws the supplied exception
	 */
	public <X extends Throwable> T demandOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X
	{
		return optional().orElseThrow(exceptionSupplier);
	}

	/* LIST */

	/**
	 * returns a list of up to {@code count} elements, if that many elements are available if available, if not the list will just be smaller
	 * @param count
	 * @return a limited list
	 */
	public List<T> list(long count)
	{
		return stream(count).collect(Collectors.toList());
	}

	/**
	 * returns a list of all available elements
	 * @param count
	 * @return a list
	 */
	public List<T> list()
	{
		return stream().collect(Collectors.toList());
	}

	/* ITERATE */

	public Iterator<T> iterator(int count)
	{
		return stream(count).iterator();
	}

	/**
	 * returns an iterator which allows iterating through all elements available
	 * @return an iterator
	 */
	public Iterator<T> iterator()
	{
		return stream().iterator();
	}

	/* STREAM */

	/**
	 * returns a seq of all available elements
	 * @param count
	 * @return a stream
	 */
	public Seq<T> seq()
	{
		return Seq.generate(this.supplier).limitWhile(i -> i != null);
	}

	/**
	 * returns a stream of all available elements
	 * @param count
	 * @return a stream
	 */
	public Stream<T> stream()
	{
		return seq().stream();
	}

	/**
	 * returns a stream of up to {@code count} elements, if that many elements are available if available, if not the stream will just be smaller
	 * @param count
	 * @return a limited stream
	 */
	public Stream<T> stream(long count)
	{
		return stream().limit(count);
	}
}