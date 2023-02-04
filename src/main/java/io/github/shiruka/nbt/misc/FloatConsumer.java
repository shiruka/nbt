package io.github.shiruka.nbt.misc;

/**
 * a functional interface consumer for primitive {@link Float}.
 */
@FunctionalInterface
public interface FloatConsumer {
  /**
   * accepts the value.
   *
   * @param f the byte value.
   */
  void accept(float f);
}
