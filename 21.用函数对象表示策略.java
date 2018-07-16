// 有些语言支持函数指针，代理，lambda表达式，或类似机制，允许程序把“调用特殊函数的能力”存储起来并传递这种能力。
// 这种机制通畅允许函数的调用者通过传入第二个函数，来指定自己的行为。
// C语言标准库的qsort函数要求用一个只想comparator函数的指针作为参数，它用这个函数来比较待排序的元素。
// 通过传递不同的比较器函数，就可以获得各种不同的排列顺序。这正是策略模式的一个例子。
// 比较器函数代表一种为元素排序的策略。

// Java没有提供函数指针，但可以使用函数对象。
// 可以通过定义一种对象，用它的方法来执行其他对象上的操作（其他对象被显示传递给这些方法）。
// 这个方法的实例实际上就等同于一个指向该方法的指针。

// 考虑下面的类：
class StringLengthComparator {
	public int compare(String s1, String s2) {
		return s1.length() - s2.length();
	}
}
// 指向该对象的引用可以被当作是一个指向该比较器的“函数指针”
// 换句话说，这个类的实例适用于字符串比较操作的具体策略。
// 那么它的所有实例在功能上都是相互等价的。因此，作为一个Singleton是非常合适的。
class StringLengthComparator {
	private StringLengthComparator() {}
	public static final StringLengthComparator INSTANCE = new StringLengthComparator();
	public int compare(String s1, String s2) {
		return s1.length() - s2.length();
	}
}

// 我们在设计具体的策略类时，还需要定义一个策略接口：
// Strategy interface
public interface Comparator<T> {
	public int compare(T t1, T t2);
}

// 具体的策略类往往使用匿名类声明：
Arrays.sort(stringArray, new Comparator<String>() {
	public int compare(String s1, String s2) {
		return s1.length() - s2.length();
	}
})
// 注意，如果它被重复执行，考虑将函数对象存储到一个私有的静态final域里，并重用它。

// 下面的例子使用静态成员类，而不是匿名类，以便允许具体的策略类实现第二个接口Serializable
// Exporting a concrete strategy
class Host {
	private static class StrLenCmp implements Comparator<String>, Serializable {
		public int compare(String s1, String s2) {
			return s1.length() - s2.length();
		}
	}

	// Returned comparator is serializable
	public static final Comparable<String> STRING_LENGTH_COMPARATOR =new StrLenCmp();

	... // Bulk of class omitted
}

// 简而言之，函数指针的只要用途就是实现策略模式。为了在Java中实现这种模式，要声明一个接口来表示该策略，
// 并且为每个具体策略声明一个实现了该接口的类。
