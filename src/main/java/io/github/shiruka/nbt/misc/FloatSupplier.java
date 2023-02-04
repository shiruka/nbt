package io.github.shiruka.nbt.misc;

/**
 * a functional interface supplier for primitive {@link Float}.
 */
@FunctionalInterface
public interface FloatSupplier {
  /**
   * gets the float value.
   *
   * @return float value.
   */
  float get();
}
