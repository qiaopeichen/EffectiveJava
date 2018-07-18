public class Statkc<E> {
	public Stack();
	public void push(E e);
	public E pop();
	public boolean isEmpty();
}

// pushAll method without wildcard type - deficient!
public void pushAll(Iterable<E> src) {
	for (E e : src) {
		push(e);
	}
}

Stack<Number> numberStack = new Stack<Number>();
Iterable<Integer> integers = ... ;
numberStack.pushAll(integers); // 报错，参数化类型是不可变的 Integer -> Number

// Wildcard type for parameter that serves as an E producer
public void pushAll(Iterable<? extends E> src) {
	for (E e : src) {
		push(e);
	}
}

// popAll method without wildcard type - deficient!
public void popAll(Collection<E> dst) {
	while (!isEmpty()) {
		dst.add(pop());
	}
}

Stack<Number> numberStack = new Stack<Number>();
Collection<Object> Object = ... ;
numberStack.popAll(Object); // 报错，Object 不是 Number 的子类型

// Wildcard type for parameter that serves as an E consumer
public void popAll(Collection<? super E> dst) {
	while (!isEmpty()) {
		dst.add(pop());
	}
}

// PECS: producer-extends, customer-super

static <E> E reduce(List<E> list, Function<E> f, E initVal)

// Wildcard type for parameter that serves as an E producer
static <E> E reduce(List<? extends E> list, Function<E> f, E initVal)

public static <E> Set<E> union(Set<E> s1, Set<E> s2)

public static <E> Set<E> union(SET<? extends E>, Set<? extends E> s2)

Set<Number> number = Union.<Number>union(integers, doubles);

public static <T extends Comparable<T>> T max(List<T> list)

public static <T extends Comparable<? super T>> T max(List<? extends T> list)

List<ScheduledFuture<?>> ScheduledFuture = ... ;

// Won't compile - wildcards can require change in method body!
public static <T extends Comparable<? super T>> T max(
	List<? extends T> list) {
	Iterator<T> i = list.iterator(); // 报错
	T result = i.next();
	while (i.hasNext()) {
		T t = i.next();
		if (t.compareTo(result) > 0) {
			result = t;
		}
	}
	return result;
}

// 修改
Iterator<? extends T> i = list.iterator();

// Two possible declarations for the swap method
public static <E> void swap(List<E> list, int i, int j);
public static void swap(List<?> list, int i, int j);

public static void swap(List<?> list, int i, int j) {
	list.set(i, list.set(j, list.get(i))); // 报错，不能把null之外的任何值放到List<?>中
}

// 修改
public static void swap(List<?> list, int i, int j) {
	swapHelper(list, i, j);
}
// private helper method for wildcard capture
private static <E> void swapHelper(list<E> list, int i, int j) {
	list.set(i, list.set(j, list.get(i)));
}

// 记住基本的原则： producer-extends, consumer-super(PECS)。
// 还要记住所有的comparable和comparator都是消费者














