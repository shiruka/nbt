package io.github.shiruka.nbt.primitive;

import io.github.shiruka.nbt.PrimitiveTag;
import io.github.shiruka.nbt.TagTypes;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link PrimitiveTag}.
 *
 * @param original the original.
 */
public record StringTag(
  @NotNull String original
) implements PrimitiveTag<String> {

  @NotNull
  @Override
  public StringTag asString() {
    return this;
  }

  @NotNull
  @Override
  public TagTypes getType() {
    return TagTypes.STRING;
  }

  @Override
  public boolean isString() {
    return true;
  }

  @NotNull
  @Override
  public String value() {
    return this.original;
  }
}
