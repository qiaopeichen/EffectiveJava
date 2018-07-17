public <T> T[] toArray<T[] a) {
	if (a.length < size) {
		return (T[]) Arrays.copyOf(elements, size, a.getClass());
	}
	System.arraycopy(elements, 0, a, 0, size);
	if (a.length > size) {
		a[size] = null;
	}
	return a;
}

// Adding local variable to reduce scope of @SuppressWarnings
public <T> T[] toArray(T[] a) {
	if (a.length < size) {
		// This cast is correct because the array we're creating
		// is of the same type as the one passed in, which is T[].
		@SuppressWarnings("unchecked")
		T[] result = (T[]) Arrays.copyOf(elements, size, a.getClass());
		return result;
	}
}

// 每一条警告都表示可能在运行时抛出ClassCastException异常。要尽最大努力消除这些警告
// 如果无法消除非受检警告，同时可以证明引起警告的代码是类型安全的，就可以在尽可能小的范围中
// 用@SuppressWarnings("unchecked") 注释禁止该警告。
// 用注释把禁止原因记录下来。