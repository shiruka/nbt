package io.github.shiruka.nbt.misc;

import io.github.shiruka.nbt.TagTypes;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class Misc {

  /**
   * checks if the given {@code type} is a number.
   *
   * @param type the type to check.
   *
   * @return {@code true} if the type is a number.
   */
  public boolean isNumber(@NotNull final TagTypes type) {
    return (
      type == TagTypes.BYTE ||
      type == TagTypes.SHORT ||
      type == TagTypes.INT ||
      type == TagTypes.LONG ||
      type == TagTypes.FLOAT ||
      type == TagTypes.DOUBLE
    );
  }
}
