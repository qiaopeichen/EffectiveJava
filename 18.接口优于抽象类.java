// 假设我们有一个接口代表一个singer，另一个接口代表一个songwriter
public interface Singer {
	AudioClip sing(Song s);
}
public interface SongWriter {
	Song compose(boolean hit);
}
// 在现实生活中，有些歌唱家本身也是作曲家。所以我们可以定义第三个接口，
// 同时扩展了Singer和Songerwriter。
// 并添加了一些合适的新方法：
public interface SingerSongwriter extends Singer, Songwriter {
	AudioClip strum();
	void  actSensitive();
}

// 通过对你导出的每个重要接口都提供一个抽象的骨架实现类，把接口和抽象类的优点结合起来。
// 接口的作用仍然是定义类型，但是骨架实现类接管了所有与接口实现相关的工作。

// 例如Map.Entry接口的骨架实现类：
// Skeletal Implementation
public abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {
	// Primitive operations
	public abstract K getKey();
	public abstract V getValue();

	// Entries in modifiable maps must override this method
	public V setValue(V value) {
		throw new UnsupportedOperationException();
	}

	// Implements the general contract of Map.Entry.equals
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (! (o instanceof Map.Entry)) {
			return false;
		}
		Map.Entry<?, ?> args = (Map.Entry) o;
		return equals(getKey(), args.getKey()) &&
				equals(getValue(), args.getValue());
	}

	private static boolean equals(Object o1, Object o2) {
		return o1 == null ? o2 == null : o1.equals(o2);
	}

	// Implements the general contract of Map.Entry.hashCode
	@Override
	public int hashCode() {
		return hashCode(getKey()) ^ hashCode(getValue());
	}

	private static int hashCode(Object obj) {
		return obj == null ? 0 : obj.hashCode();
	}
}

// 骨架实现是个抽象类，和骨架实现有小小不同的是简单实现。AbstractMap.SimpleEntry就是个例子
// 简单实现就像个骨架实现，这是因为它实现了接口，并且是为了继承而设计的。
// 区别在于它不是抽象的：它是最简单的可能的有效实现。
// 你可以原封不动地使用，也可以看情况将它子类化。






