package io.github.shiruka.nbt.primitive;

import io.github.shiruka.nbt.TagTypes;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link NumberTagEnvelope}.
 */
public final class LongTag extends NumberTagEnvelope {

  /**
   * ctor.
   *
   * @param number the number.
   */
  public LongTag(final long number) {
    super(number);
  }

  @NotNull
  @Override
  public LongTag asLong() {
    return this;
  }

  @NotNull
  @Override
  public TagTypes getType() {
    return TagTypes.LONG;
  }

  @Override
  public boolean isLong() {
    return true;
  }
}
