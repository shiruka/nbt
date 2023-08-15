package io.github.shiruka.nbt.misc;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * A class that contains utility methods for arrays.
 */
@UtilityClass
public class ArrayUtils {

  /**
   * an empty byte array as object.
   */
  private static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];

  /**
   * an empty integer array as object.
   */
  private static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];

  /**
   * an empty long array as object.
   */
  private static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];

  /**
   * converts the primitive array into object array.
   *
   * @param array the array to convert.
   *
   * @return object array.
   */
  @Contract("null -> null; !null -> !null")
  public Byte@Nullable[] toObject(final byte@Nullable[] array) {
    if (array == null) {
      return null;
    }
    if (array.length == 0) {
      return ArrayUtils.EMPTY_BYTE_OBJECT_ARRAY;
    }
    final Byte[] result = new Byte[array.length];
    for (int index = 0; index < array.length; index++) {
      result[index] = array[index];
    }
    return result;
  }

  /**
   * converts the primitive array into object array.
   *
   * @param array the array to convert.
   *
   * @return object array.
   */
  @Contract("null -> null; !null -> !null")
  public Integer@Nullable[] toObject(final int@Nullable[] array) {
    if (array == null) {
      return null;
    }
    if (array.length == 0) {
      return ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY;
    }
    final Integer[] result = new Integer[array.length];
    for (int index = 0; index < array.length; index++) {
      result[index] = array[index];
    }
    return result;
  }

  /**
   * converts the primitive array into object array.
   *
   * @param array the array to convert.
   *
   * @return object array.
   */
  @Contract("null -> null; !null -> !null")
  public Long@Nullable[] toObject(final long@Nullable[] array) {
    if (array == null) {
      return null;
    }
    if (array.length == 0) {
      return ArrayUtils.EMPTY_LONG_OBJECT_ARRAY;
    }
    final Long[] result = new Long[array.length];
    for (int index = 0; index < array.length; index++) {
      result[index] = array[index];
    }
    return result;
  }
}
