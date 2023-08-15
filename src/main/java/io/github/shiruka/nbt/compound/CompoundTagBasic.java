package io.github.shiruka.nbt.compound;

import io.github.shiruka.nbt.CompoundTag;
import io.github.shiruka.nbt.Tag;
import java.util.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link CompoundTag}.
 */
@Getter
@ToString
@EqualsAndHashCode
public final class CompoundTagBasic implements CompoundTag {

  @NotNull
  private final Map<String, Tag> original;

  /**
   * @param original the original.
   */
  public CompoundTagBasic(@NotNull Map<String, Tag> original) {
    this.original = original;
  }

  /**
   * ctor.
   */
  public CompoundTagBasic() {
    this(new HashMap<>());
  }

  @NotNull
  @Override
  public Map<String, Tag> all() {
    return Collections.unmodifiableMap(this.original);
  }

  @Override
  public boolean contains(@NotNull final Tag tag) {
    return this.original.containsValue(tag);
  }

  @Override
  public boolean containsKey(@NotNull final String key) {
    return this.original.containsKey(key);
  }

  @NotNull
  @Override
  public Optional<Tag> get(@NotNull final String key) {
    return Optional.ofNullable(this.original.get(key));
  }

  @NotNull
  @Override
  public CompoundTag remove(@NotNull final String key) {
    this.original.remove(key);
    return this;
  }

  @NotNull
  @Override
  public CompoundTag set(@NotNull final String key, @NotNull final Tag tag) {
    this.original.put(key, tag);
    return this;
  }

  @Override
  public int size() {
    return this.original.size();
  }
}
