// Broken - Inappropriate use of inheritance!
public class InstrumentedHashSet<E> extends HashSet<E> {
	// The number of attempted element insertions
	private int addCount = 0;
	public InstrumentedHashSet() {
	}

	public InstrumentedHashSet(int initCap, float loadFactor) {
		super(initCap, loadFactor);
	}

	@Override
	public boolean add(E e) {
		addCount++;
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return super.addAll(c);
	}

	public int getAddCount() {
		return addCount;
	}
}

// 这个类看起来非常合理，但是它并不能正常工作。
// 假设我们创建了一个实例，并利用addAll方法添加了三个元素：
InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
s.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
// 这时候，我们期望getAddCount方法返回3，但是它实际上返回的是6。
// 因为在HashSet的内部，addAll方法是基于它的add方法来实现的





