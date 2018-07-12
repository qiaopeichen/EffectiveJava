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

//导致子类脆弱的另一个相关原因是，它们的父类在后续的发型版本中可以获得新的方法。


// 有一种办法可以避免前面提到的所有问题。不用扩展现有的泪，而是在新的类中增加一个私有域，它引用现有类的一个实例。
// 这种设计被称作“复合”。因为现有的类变成了新类的一个组件。

// 新类中的每个实例方法都可以调用被包含的现有类实例中对应的方法，并返回它的结果。
// 这被称为转发，新类中的方法被称为转发方法。

// 下面的例子，用复合／转发的方法来代替InstrumentedHashSet类。
// 注意这个实现分为两部分：类本身和可重用的转发类，包含了所有的转发方法，没有其他方法。
// Wrapper class - uses composition in place of inheritance
public class InstrumentedSet<E> extends ForwardingSet<E> {
	private int addCount = 0;
	public InstrumentedSet(Set<E> s) {
		super(s);
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

	// Reusable forwarind class
	public class ForwardingSet<E> implements Set<E> {
		private final Set<E> s;
		public ForwardingSet(Set<E> s) { this.s = s; }

		public void clear() {
			s.clear(); 
		}
		public boolean contains(Object o) { 
			return s.contains(o); 
		}
		public boolean isEmpty() {
			return s.isEmpty(); 
		}
		public int size() { 
			return s.size(); 
		}
		public Iterator<E> iterator() { 
			return s.iterator(); 
		}
		public boolean add(E e) { 
			return s.add(e); 
		}
		public boolean remove(Object o) { 
			return s.remove(o); 
		}
		public boolean containsAll(Collection<?> c) { 
			return s.containsAll(c); 
		}
		public boolean addAll(Collection< ? extends E> c) {
			return s.addAll(c);
		}
		public boolean removeAll(Collection<?> c) {
			return s.removeAll(c);
		}
		public boolean retainAll(Collection<?> c) {
			return s.retainAll(c);
		}
		public Object[] toArray() {
			return s.toArray();
		}
		public <T> T[] toArray(T[] a) {
			return s.toArray();
		}
		@Override
		public public boolean equals(Object o) {
			return s.equals(o);
		}
		@Override
		public public int HashCode() {
			return s.HashCode();
		}
		@Override
		public String toString() {
			return s.toString();
		}
	}
}

// 这里的包装类可以被用来包装任何Set实现，并且可以结合任何先前存在的构造器一起工作：
Set<Date> s = new InstrumentedSet<Date>(new TreeSet<Date>(cmp));
Set<E> s2 = new InstrumentedSet<E>(new HashSet<E>(capacity));

// InstrumentedSet类甚至也可以用来临时替换一个原本没有计数特性的Set实例：
static void walk(Set<Dog> dogs) {
	InstrumentedSet<Dog> iDogs = new InstrumentedSet<Dog>(dogs);
	... // Within this method use iDogs instead of dogs
}

// 因为每一个InstrumentedSet实例都把另一个Set实例包装起来了，所以InstrumentedSet类被称作包装类。
// 这也正是Decorator模式，因为InstrumentedSet类对一个集合进行了修饰，为它增加了计数特性。
// 有时复合和转发的结合被错误地称为“委托”。从技术角度讲，这不是委托，除非包装对象把自身传递给被包装对象。
