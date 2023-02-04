package io.github.shiruka.nbt.misc;

/**
 * a functional interface consumer for primitive {@link Short}.
 */
@FunctionalInterface
public interface ShortConsumer {
  /**
   * accepts the value.
   *
   * @param s the short value.
   */
  void accept(short s);
}
