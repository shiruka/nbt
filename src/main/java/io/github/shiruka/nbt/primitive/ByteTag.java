package io.github.shiruka.nbt.primitive;

import io.github.shiruka.nbt.TagTypes;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link NumberTagEnvelope}.
 */
public final class ByteTag extends NumberTagEnvelope {

  /**
   * ctor.
   *
   * @param number the number.
   */
  public ByteTag(final byte number) {
    super(number);
  }

  @NotNull
  @Override
  public ByteTag asByte() {
    return this;
  }

  @NotNull
  @Override
  public TagTypes getType() {
    return TagTypes.BYTE;
  }

  @Override
  public boolean isByte() {
    return true;
  }
}
