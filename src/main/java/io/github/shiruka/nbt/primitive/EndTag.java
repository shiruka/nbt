package io.github.shiruka.nbt.primitive;

import io.github.shiruka.nbt.Tag;
import io.github.shiruka.nbt.TagTypes;
import org.jetbrains.annotations.NotNull;

/**
 * a tag that represents ends.
 */
public final class EndTag implements Tag {

  @NotNull
  @Override
  public TagTypes getType() {
    return TagTypes.END;
  }
}
