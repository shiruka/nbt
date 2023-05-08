package io.github.shiruka.nbt.stream;

import io.github.shiruka.nbt.CompoundTag;
import io.github.shiruka.nbt.ListTag;
import io.github.shiruka.nbt.Tag;
import io.github.shiruka.nbt.TagTypes;
import io.github.shiruka.nbt.array.ByteArrayTag;
import io.github.shiruka.nbt.array.IntArrayTag;
import io.github.shiruka.nbt.array.LongArrayTag;
import io.github.shiruka.nbt.primitive.ByteTag;
import io.github.shiruka.nbt.primitive.DoubleTag;
import io.github.shiruka.nbt.primitive.FloatTag;
import io.github.shiruka.nbt.primitive.IntTag;
import io.github.shiruka.nbt.primitive.LongTag;
import io.github.shiruka.nbt.primitive.ShortTag;
import io.github.shiruka.nbt.primitive.StringTag;
import java.io.Closeable;
import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * an input stream to read named binary tags.
 */
@Getter
@Accessors(fluent = true)
public final class NBTInputStream implements Closeable {

  /**
   * the input.
   */
  @NotNull
  private final DataInput input;

  /**
   * if the stream closed.
   */
  private boolean closed = false;

  /**
   * ctor.
   *
   * @param input the input.
   */
  public NBTInputStream(@NotNull final DataInput input) {
    this.input = input;
  }

  @Override
  public void close() throws IOException {
    if (this.closed) {
      return;
    }
    this.closed = true;
    if (this.input instanceof final Closeable closeable) {
      closeable.close();
    }
  }

  /**
   * reads the given input using the id.
   *
   * @param id the id to read.
   *
   * @return a new tag instance depends on the given id.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public Tag read(final byte id) throws IOException {
    if (this.closed) {
      throw new IllegalStateException("Trying to read from a closed reader!");
    }
    return switch (id) {
      case 1 -> this.readByte();
      case 2 -> this.readShort();
      case 3 -> this.readInt();
      case 4 -> this.readLong();
      case 5 -> this.readFloat();
      case 6 -> this.readDouble();
      case 7 -> this.readByteArray();
      case 8 -> this.readString();
      case 9 -> this.readListTag();
      case 10 -> this.readCompoundTag();
      case 11 -> this.readIntArray();
      case 12 -> this.readLongArray();
      default -> throw new IllegalArgumentException("Unknown type " + id);
    };
  }

  /**
   * reads the given input and converts it into the {@link ByteTag}.
   *
   * @return an instance of {@link ByteTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public ByteTag readByte() throws IOException {
    return Tag.createByte(this.input.readByte());
  }

  /**
   * reads the given input and converts it into the {@link ByteArrayTag}.
   *
   * @return an instance of {@link ByteArrayTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public ByteArrayTag readByteArray() throws IOException {
    final var length = this.input.readInt();
    final var value = new byte[length];
    this.input.readFully(value);
    return Tag.createByteArray(value);
  }

  /**
   * reads the given input and converts it into the {@link CompoundTag}.
   *
   * @return an instance of {@link CompoundTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public CompoundTag readCompoundTag() throws IOException {
    final var compoundTag = Tag.createCompound();
    while (true) {
      final byte id;
      try {
        id = this.input.readByte();
      } catch (final EOFException e) {
        break;
      }
      if (id == TagTypes.END.getId()) {
        break;
      }
      final var key = this.input.readUTF();
      final var tag = this.read(id);
      compoundTag.set(key, tag);
    }
    return compoundTag;
  }

  /**
   * reads the given input and converts it into the {@link DoubleTag}.
   *
   * @return an instance of {@link DoubleTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public DoubleTag readDouble() throws IOException {
    return Tag.createDouble(this.input.readDouble());
  }

  /**
   * reads the given input and converts it into the {@link FloatTag}.
   *
   * @return an instance of {@link FloatTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public FloatTag readFloat() throws IOException {
    return Tag.createFloat(this.input.readFloat());
  }

  /**
   * reads the given input and converts it into the {@link IntTag}.
   *
   * @return an instance of {@link IntTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public IntTag readInt() throws IOException {
    return Tag.createInt(this.input.readInt());
  }

  /**
   * reads the given input and converts it into the {@link IntArrayTag}.
   *
   * @return an instance of {@link IntArrayTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public IntArrayTag readIntArray() throws IOException {
    final var length = this.input.readInt();
    final var value = new int[length];
    for (var i = 0; i < length; i++) {
      value[i] = this.input.readInt();
    }
    return Tag.createIntArray(value);
  }

  /**
   * reads the given input and converts it into the {@link ListTag}.
   *
   * @return an instance of {@link ListTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public ListTag readListTag() throws IOException {
    final var id = this.input.readByte();
    final var length = this.input.readInt();
    final var tags = new ArrayList<Tag>(length);
    for (var i = 0; i < length; i++) {
      final var read = this.read(id);
      tags.add(read);
    }
    return Tag.createList(tags);
  }

  /**
   * reads the given input and converts it into the {@link LongTag}.
   *
   * @return an instance of {@link LongTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public LongTag readLong() throws IOException {
    return Tag.createLong(this.input.readLong());
  }

  /**
   * reads the given input and converts it into the {@link LongArrayTag}.
   *
   * @return an instance of {@link LongArrayTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public LongArrayTag readLongArray() throws IOException {
    final var length = this.input.readInt();
    final var value = new long[length];
    for (var i = 0; i < length; i++) {
      value[i] = this.input.readLong();
    }
    return Tag.createLongArray(value);
  }

  /**
   * reads the given input and converts it into the {@link ShortTag}.
   *
   * @return an instance of {@link ShortTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public ShortTag readShort() throws IOException {
    return Tag.createShort(this.input.readShort());
  }

  /**
   * reads the given input and converts it into the {@link StringTag}.
   *
   * @return an instance of {@link StringTag}.
   *
   * @throws IOException if something went wrong when reading the given input.
   */
  @NotNull
  public StringTag readString() throws IOException {
    return Tag.createString(this.input.readUTF());
  }
}
