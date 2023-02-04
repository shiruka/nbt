package io.github.shiruka.nbt.misc;

/**
 * a functional interface consumer for primitive {@link Byte}.
 */
@FunctionalInterface
public interface ByteConsumer {
  /**
   * accepts the value.
   *
   * @param b the byte value.
   */
  void accept(byte b);
}
