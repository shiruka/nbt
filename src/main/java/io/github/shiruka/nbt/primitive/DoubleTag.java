package io.github.shiruka.nbt.primitive;

import io.github.shiruka.nbt.TagTypes;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link NumberTagEnvelope}.
 */
public final class DoubleTag extends NumberTagEnvelope {

  /**
   * ctor.
   *
   * @param number the number.
   */
  public DoubleTag(final double number) {
    super(number);
  }

  @NotNull
  @Override
  public DoubleTag asDouble() {
    return this;
  }

  @NotNull
  @Override
  public TagTypes getType() {
    return TagTypes.DOUBLE;
  }

  @Override
  public boolean isDouble() {
    return true;
  }
}
