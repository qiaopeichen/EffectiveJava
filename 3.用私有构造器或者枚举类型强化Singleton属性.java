//  第一种方法，共有静态成员是个final域
//  享有特权的客户端可以借助AccessibleObject.setAccessible方法，
//	通过反射机制调用私有构造器。如果需要抵御这种攻击，可以修改构造器，让它在被要求创建第二个实例的时候抛出异常。
// Singleton with public final field
public class Elvis {
	public static final Elvis INSTANCE = new Elvis();
	private Elvis() { ... }
	public void leaveTheBuilding() { ... }
}


// 第二种方法，公有成员是个静态工厂方法
// Singleton with static factory
public class Elvis {
	private static final Elvis INSTANCE = new Elvis();
	private Elvis() { ... }
	public static Elvis getInstance() { return INSTANCE; }
	public void leaveTheBuilding() { ... }
}
// 为了使Singleton类变成可序列化的，仅仅 "implements Serializable" 是不够的
// 为了维护并保证Singleton，必须声明所有实例域都是瞬时（transient）的，
// 并提供一个 readResolve 方法。否则每次反序列化的时候，都会创建新的实例。
// readResolve method to preserve singleton property
private Object readResolve() {
	// Return the one true Elvis and let the garbage collector
	// take care of the Elvis impersonator.
	return INSTANCE;
}


// 第三种方法，编写包含单个元素的枚举类型。
// 该方法无偿提供序列化机制，绝对防止多次实例化以及反射攻击。
// 虽然这种方法还没有被广泛采用
// Enum singleton - the preferred approach.
public enum Elvis {
	INSTANCE;
	public void leaveTheBuilding() { ... }
}

