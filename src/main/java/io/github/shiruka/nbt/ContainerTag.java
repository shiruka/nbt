package io.github.shiruka.nbt;

import io.github.shiruka.nbt.array.ByteArrayTag;
import io.github.shiruka.nbt.array.IntArrayTag;
import io.github.shiruka.nbt.array.LongArrayTag;
import io.github.shiruka.nbt.misc.OptionalByte;
import io.github.shiruka.nbt.misc.OptionalFloat;
import io.github.shiruka.nbt.misc.OptionalShort;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine container tags.
 *
 * @param <K> type of the key.
 * @param <S> type of the implementation.
 */
public interface ContainerTag<K, S extends ContainerTag<K, S>> extends Tag {
  /**
   * checks if the container tag has the given tag.
   *
   * @param tag the tag to check.
   *
   * @return {@code true} if the container tag has the given tag.
   */
  boolean contains(@NotNull Tag tag);

  /**
   * checks if the container tag has the given key.
   *
   * @param key the key to check.
   *
   * @return {@code true} if the container tag has the given key.
   */
  boolean containsKey(@NotNull K key);

  /**
   * gets the tag at the given key.
   *
   * @param key the key to get.
   *
   * @return the tag.
   */
  @NotNull
  Optional<Tag> get(@NotNull K key);

  /**
   * gets the byte from the tag container.
   *
   * @param key the key to get.
   *
   * @return a byte instance from the tag container.
   */
  @NotNull
  default OptionalByte getByte(@NotNull final K key) {
    return this.get(key)
      .filter(Tag::isByte)
      .map(Tag::asByte)
      .map(tag -> OptionalByte.of(tag.byteValue()))
      .orElseGet(OptionalByte::empty);
  }

  /**
   * gets the byte array from the tag container.
   *
   * @param key the key to get.
   *
   * @return a byte array instance from the tag container.
   */
  @NotNull
  default Optional<byte[]> getByteArray(@NotNull final K key) {
    return this.get(key)
      .filter(Tag::isByteArray)
      .map(Tag::asByteArray)
      .map(ByteArrayTag::primitiveValue);
  }

  /**
   * gets the compound tag from the tag container.
   *
   * @param key the key to get.
   *
   * @return a compound tag instance from the tag container.
   */
  @NotNull
  default Optional<CompoundTag> getCompoundTag(@NotNull final K key) {
    return this.get(key).filter(Tag::isCompound).map(Tag::asCompound);
  }

  /**
   * gets the double from the tag container.
   *
   * @param key the key to get.
   *
   * @return a double instance from the tag container.
   */
  @NotNull
  default OptionalDouble getDouble(@NotNull final K key) {
    return this.get(key)
      .filter(Tag::isDouble)
      .map(Tag::asDouble)
      .map(doubleTag -> OptionalDouble.of(doubleTag.doubleValue()))
      .orElseGet(OptionalDouble::empty);
  }

  /**
   * gets the float from the tag container.
   *
   * @param key the key to get.
   *
   * @return a float instance from the tag container.
   */
  @NotNull
  default OptionalFloat getFloat(@NotNull final K key) {
    return this.get(key)
      .filter(Tag::isFloat)
      .map(Tag::asFloat)
      .map(doubleTag -> OptionalFloat.of(doubleTag.floatValue()))
      .orElseGet(OptionalFloat::empty);
  }

  /**
   * gets the int array from the tag container.
   *
   * @param key the key to get.
   *
   * @return a int array instance from the tag container.
   */
  @NotNull
  default Optional<int[]> getIntArray(@NotNull final K key) {
    return this.get(key)
      .filter(Tag::isIntArray)
      .map(Tag::asIntArray)
      .map(IntArrayTag::primitiveValue);
  }

  /**
   * gets the integer from the tag container.
   *
   * @param key the key to get.
   *
   * @return a integer instance from the tag container.
   */
  @NotNull
  default OptionalInt getInteger(@NotNull final K key) {
    return this.get(key)
      .filter(Tag::isInt)
      .map(Tag::asInt)
      .map(intTag -> OptionalInt.of(intTag.intValue()))
      .orElseGet(OptionalInt::empty);
  }

  /**
   * gets the list from the tag container.
   *
   * @param key the key to get.
   *
   * @return a list instance from the tag container.
   */
  @NotNull
  default Optional<List<Tag>> getList(@NotNull final K key) {
    return this.getListTag(key).map(ListTag::all);
  }

  /**
   * gets the list from the tag container.
   *
   * @param key the key to get.
   * @param listType the list type to get.
   *
   * @return a list instance from the tag container.
   */
  @NotNull
  default Optional<List<Tag>> getList(@NotNull final K key, @NotNull final TagTypes listType) {
    return this.getListTag(key, listType).map(ListTag::all);
  }

  /**
   * gets the list tag from the tag container.
   *
   * @param key the key to get.
   *
   * @return a list tag instance from the tag container.
   */
  @NotNull
  default Optional<ListTag> getListTag(@NotNull final K key) {
    return this.get(key).filter(Tag::isList).map(Tag::asList);
  }

  /**
   * gets the list tag from the tag container.
   *
   * @param key the key to get.
   * @param listType the list type to get.
   *
   * @return a list tag instance from the tag container.
   */
  @NotNull
  default Optional<ListTag> getListTag(@NotNull final K key, @NotNull final TagTypes listType) {
    return this.get(key)
      .filter(Tag::isList)
      .map(Tag::asList)
      .filter(tags -> tags.getListType() == listType);
  }

  /**
   * gets the long from the tag container.
   *
   * @param key the key to get.
   *
   * @return a long instance from the tag container.
   */
  @NotNull
  default OptionalLong getLong(@NotNull final K key) {
    return this.get(key)
      .filter(Tag::isLong)
      .map(Tag::asLong)
      .map(longTag -> OptionalLong.of(longTag.longValue()))
      .orElseGet(OptionalLong::empty);
  }

  /**
   * gets the long array from the tag container.
   *
   * @param key the key to get.
   *
   * @return a long array instance from the tag container.
   */
  @NotNull
  default Optional<long[]> getLongArray(@NotNull final K key) {
    return this.get(key)
      .filter(Tag::isLongArray)
      .map(Tag::asLongArray)
      .map(LongArrayTag::primitiveValue);
  }

  /**
   * gets the map from the tag container.
   *
   * @param key the key to get.
   *
   * @return a map instance from the tag container.
   */
  @NotNull
  default Optional<Map<String, Tag>> getMap(@NotNull final K key) {
    return this.getCompoundTag(key).map(CompoundTag::all);
  }

  /**
   * gets the short from the tag container.
   *
   * @param key the key to get.
   *
   * @return a short instance from the tag container.
   */
  @NotNull
  default OptionalShort getShort(@NotNull final K key) {
    return this.get(key)
      .filter(Tag::isShort)
      .map(Tag::asShort)
      .map(shortTag -> OptionalShort.of(shortTag.shortValue()))
      .orElseGet(OptionalShort::empty);
  }

  /**
   * gets the string from the tag container.
   *
   * @param key the key to get.
   *
   * @return a string instance from the tag container.
   */
  @NotNull
  default Optional<String> getString(@NotNull final K key) {
    return this.get(key).filter(Tag::isString).map(Tag::asString).map(PrimitiveTag::value);
  }

  /**
   * checks if the container tag has not any element in it.
   *
   * @return {@code true} if the container tag has not any element in it.
   */
  boolean isEmpty();

  /**
   * removes the tag at the given key.
   *
   * @param key the key to remove.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  S remove(@NotNull K key);

  /**
   * sets the given tag into the given key.
   *
   * @param key the key to set.
   * @param tag the tag to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  S set(@NotNull K key, @NotNull Tag tag);

  /**
   * sets the byte to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setByte(@NotNull final K key, final byte value) {
    return this.set(key, Tag.createByte(value));
  }

  /**
   * sets the byte array to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setByteArray(@NotNull final K key, final byte... value) {
    return this.set(key, Tag.createByteArray(value));
  }

  /**
   * sets the double to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setDouble(@NotNull final K key, final double value) {
    return this.set(key, Tag.createDouble(value));
  }

  /**
   * sets the float to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setFloat(@NotNull final K key, final float value) {
    return this.set(key, Tag.createFloat(value));
  }

  /**
   * sets the int array to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setIntArray(@NotNull final K key, final int... value) {
    return this.set(key, Tag.createIntArray(value));
  }

  /**
   * sets the integer to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setInteger(@NotNull final K key, final int value) {
    return this.set(key, Tag.createInt(value));
  }

  /**
   * sets the list to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setList(@NotNull final K key, @NotNull final List<Tag> value) {
    return this.set(key, Tag.createList(value));
  }

  /**
   * sets the long to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setLong(@NotNull final K key, final long value) {
    return this.set(key, Tag.createLong(value));
  }

  /**
   * sets the long array to the tag container.
   *
   * @param key the key to set.
   * @param value the value set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setLongArray(@NotNull final K key, final long@NotNull[] value) {
    return this.set(key, Tag.createLongArray(value));
  }

  /**
   * sets the map to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setMap(@NotNull final K key, @NotNull final Map<String, Tag> value) {
    return this.set(key, Tag.createCompound(value));
  }

  /**
   * sets the short to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setShort(@NotNull final K key, final short value) {
    return this.set(key, Tag.createShort(value));
  }

  /**
   * sets the string to the tag container.
   *
   * @param key the key to set.
   * @param value the value to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  default S setString(@NotNull final K key, @NotNull final String value) {
    return this.set(key, Tag.createString(value));
  }

  /**
   * gets the size.
   *
   * @return the size.
   */
  int size();
}
