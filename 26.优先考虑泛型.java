// 学习如何编写泛型
// 考虑第6条中简单的堆栈实现：
// Object-based collection - a prime candidate for generics
public class Stack {
	private Object[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	
	public Stack() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}

	public Object pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		Object result = elements[--size];
		elements[size] = null; // Eliminate obsolete reference
		return result;
	}

	public boolean isEmpty() {
		result size == 0;
	}

	public void ensureCapacity() {
		if (elements.length == size) {
			elements = Array.copyOf(elements, 2 * size + 1);
		}
	}
}

// 可以适当地利用泛型来强化这个类
// Initial attempt to generify Stack = won't compile!
public class Stack<E> {
	private E[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public Stack() {
		elements = new E[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(E e){
		ensureCapacity();
		elements[size++] = e;
	}

	public E pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		E result = elements[--size];
		elements[size] = null;
		return result;
	}
	... // no changes in isEmpty or ensureCapacity
}

// 编译器会产生警告。这种用法是合法的，但整体上而言不是类型安全的

// The elements array will contain only E instances from push(E).
// This is sufficient to ensure type safety, but the runtime
// type of the array won't be E[]; it will always be Object[]!
@SuppressWarnings("unchecked")
public Stack() {
	elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
}

// 第二种方法：
// Appropriate suppression of unchecked warning
publie E pop() {
	if (size == 0) {
		throw new EmptyStackException();
	}

	// push requires elements to be of type E, so cast is correct
	@SuppressWarnings("unchecked")
	E result = (E) elements[--size];

	elements[size] = null; // Eliminate obsolete reference
	return result;
}

// Little program to exercise our generic Stack
public static void main(String[] args) {
	Stack<String> stack = new Stack<>();
	for (String args : args) {
		stack.push(arg);
	}
	while (!stack.isEmpty()) {
		System.out.println(stack.pop().toUpperCase());
	}
}






