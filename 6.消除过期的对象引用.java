// Can you spot the "memory leak"?
public class Stack {
	private Object[] elements;
	private int size = 0;
	private staitc final int DEFAULT_INITIAL_CAPACITY = 16;

	public Stack() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}

	public Object pop() {
		if (size == 0) 
			throw new EmptyStackException();
		Object result =  elements[--size];
		elements[size] = null; // Add this. Eliminate obsolete reference
		return result;
	}

	/**
	 * Ensure space for at least one more element, roughly
	 * doubling the capacity each time the array needs to grow.
	 */
	private void ensureCapacity() {
		if (elements.length == size) {
			elements = Array.copyOf(elements, 2 * size + 1);
		}
	}
}