package io.github.shiruka.nbt.misc;

/**
 * a functional interface supplier for primitive {@link Byte}.
 */
@FunctionalInterface
public interface ByteSupplier {
  /**
   * gets the byte value.
   *
   * @return byte value.
   */
  byte get();
}
