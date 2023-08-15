package io.github.shiruka.nbt.primitive;

import io.github.shiruka.nbt.PrimitiveTag;
import io.github.shiruka.nbt.TagTypes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link PrimitiveTag}.
 */
@Getter
@ToString
@EqualsAndHashCode
public final class StringTag implements PrimitiveTag<String> {

  @NotNull
  private final String original;

  /**
   * @param original the original.
   */
  public StringTag(@NotNull final String original) {
    this.original = original;
  }

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
