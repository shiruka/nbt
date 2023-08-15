package io.github.shiruka.nbt.array;

import io.github.shiruka.nbt.ArrayTag;
import io.github.shiruka.nbt.TagTypes;
import io.github.shiruka.nbt.misc.ArrayUtils;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents long arrays.
 */
public final class LongArrayTag implements ArrayTag<Long> {

  /**
   * the original.
   */
  @NotNull
  private final Long[] original;

  /**
   * the primitive original.
   */
  private final long@NotNull[] primitiveOriginal;

  /**
   * ctor.
   *
   * @param original the original.
   */
  public LongArrayTag(final long... original) {
    this.primitiveOriginal = original.clone();
    this.original = ArrayUtils.toObject(this.primitiveOriginal);
  }

  @NotNull
  @Override
  public LongArrayTag asLongArray() {
    return this;
  }

  @NotNull
  @Override
  public TagTypes getType() {
    return TagTypes.LONG_ARRAY;
  }

  @Override
  public boolean isLongArray() {
    return true;
  }

  @NotNull
  @Override
  public Long get(final int index) {
    ArrayTag.checkIndex(index, this.original.length);
    return this.original[index];
  }

  @Override
  public int size() {
    return this.original.length;
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(this.primitiveOriginal);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    final LongArrayTag that = (LongArrayTag) o;
    return Arrays.equals(this.primitiveOriginal, that.primitiveOriginal);
  }

  @Override
  public String toString() {
    return ("LongArrayTag{" + "primitiveOriginal=" + Arrays.toString(this.primitiveOriginal) + '}');
  }

  /**
   * obtains the primitive original value.
   *
   * @return primitive value.
   */
  public long@NotNull[] primitiveValue() {
    return this.primitiveOriginal;
  }

  @NotNull
  @Override
  public Long@NotNull[] value() {
    return this.original.clone();
  }
}
