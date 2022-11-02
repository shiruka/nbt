package io.github.shiruka.nbt.primitive;

import io.github.shiruka.nbt.TagTypes;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link NumberTagEnvelope}.
 */
public final class FloatTag extends NumberTagEnvelope {

  /**
   * ctor.
   *
   * @param number the number.
   */
  public FloatTag(final float number) {
    super(number);
  }

  @NotNull
  @Override
  public FloatTag asFloat() {
    return this;
  }

  @NotNull
  @Override
  public TagTypes getType() {
    return TagTypes.FLOAT;
  }

  @Override
  public boolean isFloat() {
    return true;
  }
}
