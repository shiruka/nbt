package io.github.shiruka.nbt.primitive;

import io.github.shiruka.nbt.TagTypes;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link NumberTagEnvelope}.
 */
public final class IntTag extends NumberTagEnvelope {

  /**
   * ctor.
   *
   * @param number the number.
   */
  public IntTag(final int number) {
    super(number);
  }

  @NotNull
  @Override
  public IntTag asInt() {
    return this;
  }

  @NotNull
  @Override
  public TagTypes getType() {
    return TagTypes.INT;
  }

  @Override
  public boolean isInt() {
    return true;
  }
}
