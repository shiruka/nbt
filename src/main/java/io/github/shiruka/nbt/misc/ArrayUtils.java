package io.github.shiruka.nbt.misc;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public class ArrayUtils {

  private static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];

  private static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];

  private static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];

  @Contract("null -> null; !null -> !null")
  public Byte@Nullable[] toObject(final byte@Nullable[] array) {
    if (array == null) {
      return null;
    }
    if (array.length == 0) {
      return ArrayUtils.EMPTY_BYTE_OBJECT_ARRAY;
    }
    final var result = new Byte[array.length];
    for (var index = 0; index < array.length; index++) {
      result[index] = array[index];
    }
    return result;
  }

  @Contract("null -> null; !null -> !null")
  public Integer@Nullable[] toObject(final int@Nullable[] array) {
    if (array == null) {
      return null;
    }
    if (array.length == 0) {
      return ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY;
    }
    final var result = new Integer[array.length];
    for (var index = 0; index < array.length; index++) {
      result[index] = array[index];
    }
    return result;
  }

  @Contract("null -> null; !null -> !null")
  public Long@Nullable[] toObject(final long@Nullable[] array) {
    if (array == null) {
      return null;
    }
    if (array.length == 0) {
      return ArrayUtils.EMPTY_LONG_OBJECT_ARRAY;
    }
    final var result = new Long[array.length];
    for (var index = 0; index < array.length; index++) {
      result[index] = array[index];
    }
    return result;
  }
}
