package io.github.shiruka.nbt.array;

import io.github.shiruka.nbt.ArrayTag;
import io.github.shiruka.nbt.TagTypes;
import io.github.shiruka.nbt.misc.ArrayUtils;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents int arrays.
 */
public final class IntArrayTag implements ArrayTag<Integer> {

  /**
   * the original.
   */
  @NotNull
  private final Integer@NotNull[] original;

  /**
   * the primitive original.
   */
  private final int@NotNull[] primitiveOriginal;

  /**
   * ctor.
   *
   * @param original the original.
   */
  public IntArrayTag(final int... original) {
    this.primitiveOriginal = original.clone();
    this.original = ArrayUtils.toObject(this.primitiveOriginal);
  }

  @NotNull
  @Override
  public IntArrayTag asIntArray() {
    return this;
  }

  @NotNull
  @Override
  public TagTypes getType() {
    return TagTypes.INT_ARRAY;
  }

  @Override
  public boolean isIntArray() {
    return true;
  }

  @NotNull
  @Override
  public Integer get(final int index) {
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
    final IntArrayTag that = (IntArrayTag) o;
    return Arrays.equals(this.primitiveOriginal, that.primitiveOriginal);
  }

  @Override
  public String toString() {
    return ("IntArrayTag{" + "primitiveOriginal=" + Arrays.toString(this.primitiveOriginal) + '}');
  }

  /**
   * obtains the primitive original value.
   *
   * @return primitive value.
   */
  public int@NotNull[] primitiveValue() {
    return this.primitiveOriginal;
  }

  @NotNull
  @Override
  public Integer@NotNull[] value() {
    return this.original.clone();
  }
}
