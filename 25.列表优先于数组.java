// 下面的代码片段是合法的：
// Fails at runtime!
Object[] objectArray = new Long[1];
objectArray[0] = "I don't fit in"; // Throws ArrayStoreException

// 但下面这段代码则不合法：
// Won't compile!
List<Object> ol = new ArrayList<Long>(); // Incompatible types
ol.add("I don't fit in");

// 这其中无论哪种方法，都不能将String放进Long容器中
// 但是利用数组，会在运行时发现所犯的错误；利用列表，则可以在编译时发现错误。