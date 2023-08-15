package io.github.shiruka.nbt.misc;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents {@link Optional} for {@link Short}s.
 */
public final class OptionalShort {

  /**
   * the empty.
   */
  private static final OptionalShort EMPTY = new OptionalShort();

  /**
   * the is present.
   */
  private final boolean isPresent;

  /**
   * the value.
   */
  private final short value;

  /**
   * ctor.
   */
  private OptionalShort() {
    this.isPresent = false;
    this.value = 0;
  }

  /**
   * ctor.
   *
   * @param value the value.
   */
  private OptionalShort(final short value) {
    this.isPresent = true;
    this.value = value;
  }

  /**
   * gets the empty optional short.
   *
   * @return an empty instance.
   */
  @NotNull
  public static OptionalShort empty() {
    return OptionalShort.EMPTY;
  }

  /**
   * creates a new optional short instance.
   *
   * @param value the value to create.
   * @return a newly created optional short.
   */
  @NotNull
  public static OptionalShort of(final short value) {
    return new OptionalShort(value);
  }

  /**
   * gets the value as short.
   *
   * @return value as short.
   */
  public short getAsShort() {
    if (!this.isPresent) {
      throw new NoSuchElementException("No value present");
    }
    return this.value;
  }

  @Override
  public int hashCode() {
    return this.isPresent ? Short.hashCode(this.value) : 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final OptionalShort that = (OptionalShort) o;
    if (isPresent != that.isPresent) {
      return false;
    }
    return value == that.value;
  }

  @Override
  public String toString() {
    return this.isPresent ? String.format("OptionalShort[%s]", this.value) : "OptionalShort.empty";
  }

  /**
   * runs the action if the value is present.
   *
   * @param action the action to run.
   */
  public void ifPresent(final ShortConsumer action) {
    if (this.isPresent) {
      action.accept(this.value);
    }
  }

  /**
   * runs the action if the value is present otherwise runs the empty action..
   *
   * @param action      the action to run.
   * @param emptyAction the empty action to run.
   */
  public void ifPresentOrElse(final ShortConsumer action, final Runnable emptyAction) {
    if (this.isPresent) {
      action.accept(this.value);
    } else {
      emptyAction.run();
    }
  }

  /**
   * returns {@code true} if the value is not present.
   *
   * @return {@code true} if the value is not present.
   */
  public boolean isEmpty() {
    return !this.isPresent;
  }

  /**
   * returns {@code true} if the value is present.
   *
   * @return {@code true} if the value is present.
   */
  public boolean isPresent() {
    return this.isPresent;
  }

  /**
   * {@link #value} if {@link #isPresent} otherwise {@code other}.
   *
   * @param other the other to return.
   * @return {@link #value} if the value is present otherwise the given short.
   */
  public short orElse(final short other) {
    return this.isPresent ? this.value : other;
  }

  /**
   * {@link #value} if {@link #isPresent} otherwise {@code other}.
   *
   * @param supplier the supplier to return.
   * @return {@link #value} if the value is present otherwise the given supplier's value.
   */
  public short orElseGet(final ShortSupplier supplier) {
    return this.isPresent ? this.value : supplier.get();
  }

  /**
   * {@link #value} if presents or throw no value present exception.
   *
   * @return {@link #value}.
   */
  public short orElseThrow() {
    if (!this.isPresent) {
      throw new NoSuchElementException("No value present");
    }
    return this.value;
  }

  /**
   * {@link #value} if presents or throw no value present exception.
   *
   * @param exceptionSupplier the exception supplier to throw exception.
   * @param <X>               type of the exception.
   * @return {@link #value}.
   * @throws X if the value is not present.
   */
  public <X extends Throwable> short orElseThrow(final Supplier<? extends X> exceptionSupplier)
    throws X {
    if (this.isPresent) {
      return this.value;
    } else {
      throw exceptionSupplier.get();
    }
  }
}
