package io.github.shiruka.nbt.list;

import io.github.shiruka.nbt.ListTag;
import io.github.shiruka.nbt.Tag;
import io.github.shiruka.nbt.TagTypes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link ListTag}.
 */
public final class ListTagBasic implements ListTag {

  /**
   * the list id.
   */
  @NotNull
  private TagTypes listType;

  /**
   * the original.
   */
  @NotNull
  private List<Tag> original;

  /**
   * ctor.
   *
   * @param original the original.
   * @param listType the list type.
   */
  public ListTagBasic(@NotNull final List<Tag> original, @NotNull final TagTypes listType) {
    this.original = Collections.unmodifiableList(original);
    this.listType = listType;
  }

  @NotNull
  @Override
  public ListTag add(@NotNull final Tag tag) {
    this.edit(
        tags -> {
          final var endType = TagTypes.END;
          if (tag.getType() == endType) {
            throw new IllegalArgumentException(
              "Cannot add a %s to a %s".formatted(endType, TagTypes.LIST)
            );
          }
          if (this.getListType() != endType) {
            if (tag.getType() != this.listType) {
              throw new IllegalArgumentException(
                "Trying to add tag of type %s to list of %s".formatted(tag.getType(), this.listType)
              );
            }
          }
          tags.add(tag);
        },
        tag.getType()
      );
    return this;
  }

  @NotNull
  @Override
  public List<Tag> all() {
    return Collections.unmodifiableList(this.original);
  }

  @NotNull
  @Override
  public TagTypes getListType() {
    return this.listType;
  }

  @NotNull
  @Override
  public Stream<Tag> stream() {
    return this.original.stream();
  }

  @Override
  public boolean contains(@NotNull final Tag o) {
    return this.original.contains(o);
  }

  @Override
  public boolean containsKey(@NotNull final Integer key) {
    throw new UnsupportedOperationException();
  }

  @NotNull
  @Override
  public Optional<Tag> get(@NotNull final Integer key) {
    return Optional.ofNullable(this.original.get(key));
  }

  @NotNull
  @Override
  public ListTag remove(@NotNull final Integer key) {
    this.edit(tags -> tags.remove(key), TagTypes.NONE);
    return this;
  }

  @NotNull
  @Override
  public ListTag set(@NotNull final Integer key, @NotNull final Tag tag) {
    this.edit(tags -> tags.set(key, tag), tag.getType());
    return this;
  }

  @Override
  public int hashCode() {
    return this.original.hashCode();
  }

  @Override
  public boolean equals(final Object obj) {
    return (
      this == obj || obj instanceof final ListTagBasic list && this.original.equals(list.original)
    );
  }

  @Override
  public String toString() {
    return this.original.toString();
  }

  @NotNull
  @Override
  public Iterator<Tag> iterator() {
    final var iterator = this.original.iterator();
    return new Iterator<>() {
      @Override
      public boolean hasNext() {
        return iterator.hasNext();
      }

      @Override
      public Tag next() {
        return iterator.next();
      }

      @Override
      public void forEachRemaining(final Consumer<? super Tag> action) {
        iterator.forEachRemaining(action);
      }
    };
  }

  @Override
  public void forEach(@NotNull final Consumer<? super Tag> action) {
    this.original.forEach(action);
  }

  @NotNull
  @Override
  public Spliterator<Tag> spliterator() {
    return Spliterators.spliterator(this.original, Spliterator.ORDERED | Spliterator.IMMUTABLE);
  }

  /**
   * edits with the given consumer.
   *
   * @param consumer the consumer to edit.
   * @param type the type to edit.
   */
  private void edit(@NotNull final Consumer<List<Tag>> consumer, @NotNull final TagTypes type) {
    final var tags = new ArrayList<>(this.original);
    consumer.accept(tags);
    if (type != TagTypes.NONE && this.listType == TagTypes.END) {
      this.original = tags;
      this.listType = type;
    } else {
      this.original = tags;
    }
  }
}
