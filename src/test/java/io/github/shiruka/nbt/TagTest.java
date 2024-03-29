package io.github.shiruka.nbt;

import io.github.shiruka.nbt.array.ByteArrayTag;
import io.github.shiruka.nbt.primitive.ByteTag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

final class TagTest {

  @Test
  void asArray() {
    final ByteTag byteTag = Tag.createByte();
    Assertions.assertThrows(IllegalStateException.class, byteTag::asArray, "Byte tag IS an array!");
    final ByteArrayTag arrayTag = Tag.createByteArray((byte) 1);
    Assertions.assertDoesNotThrow(arrayTag::asArray, "Array tag IS NOT an array!");
  }
}
