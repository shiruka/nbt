package io.github.shiruka.nbt.misc;

/**
 * a functional interface supplier for primitive {@link Short}.
 */
@FunctionalInterface
public interface ShortSupplier {
  /**
   * gets the short value.
   *
   * @return short value.
   */
  short get();
}
