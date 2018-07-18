// 编写泛型方法与编写泛型类型相类似。
// Use raw types - unacceptable! 
public static Set union(Set s1, Set s2) {
	Set result = new HashSet(s1);
	result.addAll(s2);
	return result;
}

// 这个方法可以编译，但是有warning:[unchecked]警告
warning: [unchecked] unchecked call to 
HashSet(Collection<? extends E) as a member of raw type HashSet
	Set result = new HashSet(s1);
warning: [unchecked] unchecked call to
addAll(Collection<? extends E>) as a member of raw type Set
	result.addAll(s2);

// 修正警告：
// Generic method
public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
	Set<E> result = new HashSet<>(s1);
	result.addAll(s2);
	return result;
}

// Simple program to exercise generic method
public static void main(String[] args) {
	Set<String> guys = new HashSet<String>(Arrays.asList("Tom", "Dick", "Harry"));
	Set<String> stooges = new HashSet<String>(Arrays.asList("Larry", "Moe", "Curly"));
	Set<String> aflCio = union(guys, stooges);
	System.out.println(aflCio);
}
// 运行这段程序时，会打印出[Moe, Harry, Tom, Curly, Larry, Dick]。元素的顺序是依赖于实现的。

// union方法的局限性在于，三个集合的类型（两个输入参数和一个返回值）必须全部相同。
// 利用有限制的通配符类型(bounded wildcard type)，可以使这个方法更灵活。

public interface UnaryFunction<T> {
	T apply(T arg);
}

//Generic singleton factory pattern
private static UnaryFunction<Object> IDENTITY_FUNCTION =
	new UnaryFunction<Object>() {
		public Object apply(Object arg) {
			return arg;
		}
	}

// IDENTITY_FUNCTION is stateless and its type parameter is
// unbounded so it's safe to share one instance across all types.
@SuppressWarnings("unchecked")
public static <T> UnaryFunction<T> identityFunction() {
	return (UnaryFunction<T>) IDENTITY_FUNCTION;
}

// Sample program to execise generic singleton
public static void main(String[] args) {
	String[] strings = {"jute", "hemp", "nylon"};
	UnaryFunction<String> sameString = identityFunction();
	for (String s : strings) {
		System.out.println(sameString.apply(s));
	}

	Number[] numbers = { 1, 2.0, 3L };
	UnaryFunction<Number> sameNumber = identityFunction();
	for (Number n : numbers) {
		System.out.println(sameNumber.apply(n));
	}
}

public interface Comparable<T> {
	int compareTo(T o);
}

// Using a recursive type bound to express mutual comparability
public static <T extends Comparable<T>> T max(List<T> list) {...}

// 类型限制 <T extends Comparable<T>> ，可以读作：
// “针对可以与自身进行比较的每个类型T”

// Returns the maximum value in a list - uses recursive type bound
public static <T extends Comparable<T>> T max (List<T> list) {
	Iterator<T> i = list.iterator();
	T result = i.next();
	while (i.hasNext()) {
		T t = i.next();
		if (t.compareTo(result) > 0) {
			result = t;
		}
		return result;
	}
}






