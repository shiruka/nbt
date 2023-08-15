package io.github.shiruka.nbt;

import io.github.shiruka.nbt.misc.Misc;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine compound tags which contain map of {@link Tag}.
 */
public interface CompoundTag extends Tag, ContainerTag<String, CompoundTag> {
  /**
   * obtains compound tag's map as unmodifiable..
   *
   * @return compound tag's map.
   */
  @NotNull
  Map<String, Tag> all();

  @NotNull
  @Override
  default CompoundTag asCompound() {
    return this;
  }

  @NotNull
  @Override
  default TagTypes getType() {
    return TagTypes.COMPOUND;
  }

  @Override
  default boolean isCompound() {
    return true;
  }

  /**
   * checks if the given {@code key} contains and the id of the key's value equals the given {@code id}.
   *
   * @param key the key to check.
   * @param id the id to check.
   *
   * @return {@code true} if the id of the key's value equals the given {@code id}.
   */
  default boolean hasKeyOfType(@NotNull final String key, @NotNull final TagTypes id) {
    final TagTypes type = this.get(key).map(Tag::getType).orElse(TagTypes.END);
    return id == type || id.getId() == 99 && Misc.isNumber(type);
  }

  @Override
  default boolean isEmpty() {
    return this.all().isEmpty();
  }
}
