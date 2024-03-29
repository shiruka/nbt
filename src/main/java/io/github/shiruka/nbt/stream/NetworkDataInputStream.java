package io.github.shiruka.nbt.stream;

import io.github.shiruka.nbt.VarInts;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link LittleEndianDataInputStream}.
 */
public final class NetworkDataInputStream extends LittleEndianDataInputStream {

  /**
   * ctor.
   *
   * @param stream the stream.
   */
  public NetworkDataInputStream(@NotNull final InputStream stream) {
    super(stream);
  }

  /**
   * ctor.
   *
   * @param stream the stream.
   */
  public NetworkDataInputStream(@NotNull final DataInputStream stream) {
    super(stream);
  }

  @Override
  public int readInt() throws IOException {
    return VarInts.readInt(this.stream);
  }

  @Override
  public long readLong() throws IOException {
    return VarInts.readLong(this.stream);
  }

  @NotNull
  @Override
  public String readUTF() throws IOException {
    final int length = VarInts.readUnsignedInt(this.stream);
    final byte[] bytes = new byte[length];
    this.readFully(bytes);
    return new String(bytes, StandardCharsets.UTF_8);
  }
}
