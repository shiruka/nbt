package io.github.shiruka.nbt.misc;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents {@link Optional} for {@link Float}s.
 */
public final class OptionalFloat {

  /**
   * the empty.
   */
  private static final OptionalFloat EMPTY = new OptionalFloat();

  /**
   * the is present.
   */
  private final boolean isPresent;

  /**
   * the value.
   */
  private final float value;

  /**
   * ctor.
   */
  private OptionalFloat() {
    this.isPresent = false;
    this.value = 0.0f;
  }

  /**
   * ctor.
   *
   * @param value the value.
   */
  private OptionalFloat(final float value) {
    this.isPresent = true;
    this.value = value;
  }

  /**
   * gets the empty optional float.
   *
   * @return an empty instance.
   */
  @NotNull
  public static OptionalFloat empty() {
    return OptionalFloat.EMPTY;
  }

  /**
   * creates a new optional float instance.
   *
   * @param value the value to create.
   *
   * @return a newly created optional float.
   */
  @NotNull
  public static OptionalFloat of(final float value) {
    return new OptionalFloat(value);
  }

  /**
   * gets the value as float.
   *
   * @return value as float.
   */
  public float getAsFloat() {
    if (!this.isPresent) {
      throw new NoSuchElementException("No value present");
    }
    return this.value;
  }

  @Override
  public int hashCode() {
    return this.isPresent ? Float.hashCode(this.value) : 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final OptionalFloat that = (OptionalFloat) o;
    if (isPresent != that.isPresent) {
      return false;
    }
    return Float.compare(value, that.value) == 0;
  }

  @Override
  public String toString() {
    return this.isPresent ? String.format("OptionalFloat[%s]", this.value) : "OptionalFloat.empty";
  }

  /**
   * runs the action if the value is present.
   *
   * @param action the action to run.
   */
  public void ifPresent(final FloatConsumer action) {
    if (this.isPresent) {
      action.accept(this.value);
    }
  }

  /**
   * runs the action if the value is present otherwise runs the empty action.
   *
   * @param action the action to run.
   * @param emptyAction the empty action to run.
   */
  public void ifPresentOrElse(final FloatConsumer action, final Runnable emptyAction) {
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
   *
   * @return {@link #value} if the value is present otherwise the given float.
   */
  public float orElse(final float other) {
    return this.isPresent ? this.value : other;
  }

  /**
   * {@link #value} if {@link #isPresent} otherwise {@code other}.
   *
   * @param supplier the supplier to return.
   *
   * @return {@link #value} if the value is present otherwise the given supplier's value.
   */
  public float orElseGet(final FloatSupplier supplier) {
    return this.isPresent ? this.value : supplier.get();
  }

  /**
   * {@link #value} if presents or throw no value present exception.
   *
   * @return {@link #value}.
   */
  public float orElseThrow() {
    if (!this.isPresent) {
      throw new NoSuchElementException("No value present");
    }
    return this.value;
  }

  /**
   * {@link #value} if presents or throw no value present exception.
   *
   * @param exceptionSupplier the exception supplier to throw exception.
   * @param <X> type of the exception.
   *
   * @return {@link #value}.
   *
   * @throws X if the value is not present.
   */
  public <X extends Throwable> float orElseThrow(final Supplier<? extends X> exceptionSupplier)
    throws X {
    if (this.isPresent) {
      return this.value;
    } else {
      throw exceptionSupplier.get();
    }
  }
}
