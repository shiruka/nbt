package io.github.shiruka.nbt.misc;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents {@link Optional} for {@link Byte}s.
 */
public final class OptionalByte {

  /**
   * the empty.
   */
  private static final OptionalByte EMPTY = new OptionalByte();

  /**
   * the is present.
   */
  private final boolean isPresent;

  /**
   * the value.
   */
  private final byte value;

  /**
   * ctor.
   */
  private OptionalByte() {
    this.isPresent = false;
    this.value = 0;
  }

  /**
   * ctor.
   *
   * @param value the value.
   */
  private OptionalByte(final byte value) {
    this.isPresent = true;
    this.value = value;
  }

  /**
   * gets the empty optional byte.
   *
   * @return an empty instance.
   */
  @NotNull
  public static OptionalByte empty() {
    return OptionalByte.EMPTY;
  }

  /**
   * creates a new optional byte instance.
   *
   * @param value the value to create.
   *
   * @return a newly created optional byte.
   */
  @NotNull
  public static OptionalByte of(final byte value) {
    return new OptionalByte(value);
  }

  /**
   * gets the value as byte.
   *
   * @return value as byte.
   */
  public byte getAsByte() {
    if (!this.isPresent) {
      throw new NoSuchElementException("No value present");
    }
    return this.value;
  }

  @Override
  public int hashCode() {
    return this.isPresent ? Byte.hashCode(this.value) : 0;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    return (
      obj instanceof OptionalByte other &&
      (
        this.isPresent && other.isPresent
          ? this.value == other.value
          : this.isPresent == other.isPresent
      )
    );
  }

  @Override
  public String toString() {
    return this.isPresent
      ? String.format("OptionalByte[%s]", this.value)
      : "OptionalByte.empty";
  }

  /**
   * runs the action if the value is present.
   *
   * @param action the action to run.
   */
  public void ifPresent(final ByteConsumer action) {
    if (this.isPresent) {
      action.accept(this.value);
    }
  }

  /**
   * runs the action if the value is present otherwise runs the empty action..
   *
   * @param action the action to run.
   * @param emptyAction the empty action to run.
   */
  public void ifPresentOrElse(
    final ByteConsumer action,
    final Runnable emptyAction
  ) {
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
   * @return {@link #value} if the value is present otherwise the given byte.
   */
  public byte orElse(final byte other) {
    return this.isPresent ? this.value : other;
  }

  /**
   * {@link #value} if {@link #isPresent} otherwise {@code other}.
   *
   * @param supplier the supplier to return.
   *
   * @return {@link #value} if the value is present otherwise the given supplier's value.
   */
  public byte orElseGet(final ByteSupplier supplier) {
    return this.isPresent ? this.value : supplier.get();
  }

  /**
   * {@link #value} if presents or throw no value present exception.
   *
   * @return {@link #value}.
   */
  public byte orElseThrow() {
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
  public <X extends Throwable> byte orElseThrow(
    final Supplier<? extends X> exceptionSupplier
  ) throws X {
    if (this.isPresent) {
      return this.value;
    } else {
      throw exceptionSupplier.get();
    }
  }
}
