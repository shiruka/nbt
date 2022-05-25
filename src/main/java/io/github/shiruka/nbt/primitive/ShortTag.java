package io.github.shiruka.nbt.primitive;

import io.github.shiruka.nbt.TagTypes;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link NumberTagEnvelope}.
 */
public final class ShortTag extends NumberTagEnvelope {

  /**
   * ctor.
   *
   * @param original the original.
   */
  public ShortTag(final short original) {
    super(original);
  }

  @NotNull
  @Override
  public ShortTag asShort() {
    return this;
  }

  @NotNull
  @Override
  public TagTypes getType() {
    return TagTypes.SHORT;
  }

  @Override
  public boolean isShort() {
    return true;
  }
}
