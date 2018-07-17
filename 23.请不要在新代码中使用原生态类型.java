// Set<Object> 是一个参数化类型，表示可以包含任何对象类型的一个集合
// Set<?> 则是一个通配符类型，表示只能包含某种未知对象类型的一个集合
// Set 则是个原生态类型，它脱离了泛型系统。
// 前两种是安全的，最后一种不安全

// 术语：
// 参数化的类型 List<String>
// 实际类型参数 String

// 泛型 List<E>
// 形式类型参数 E

// 无限制通配符类型 List<?>
// 原生态类型 List
// 有限制类型参数 <E extends Number>
// 递归类型限制 <T extends Comparable<T>>
// 有限制通配符类型 List<? extends Number>
// 泛型方法 static <E> List<E> asList(E[] a)
// 类型令牌 String.class