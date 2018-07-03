/**
 *  第一种方法，共有静态成员是个final域
 *  享有特权的客户端可以借助AccessibleObject.setAccessible方法，
 *	通过反射机制调用私有构造器。如果需要抵御这种攻击，可以修改构造器，让它在被要求创建第二个实例的时候抛出异常。
 */
// Singleton with public final field
public class Elvis {
	public static final Elvis INSTANCE = new Elvis();
	private Elvis() { ... }
	public void leaveTheBuilding() { ... }
}


/**
 * 第二种方法，公有成员是个静态工厂方法
 */
// Singleton with static factory
public class Elvis {
	private static final Elvis INSTANCE = new Elvis();
	private Elvis() { ... }
	public static Elvis getInstance() { return INSTANCE; }
	public void leaveTheBuilding() { ... }
}

/**
 * 第三种方法，编写包含单个元素的枚举类型
 */
// Enum singleton - the preferred approach.
public enum Elvis {
	INSTANCE;
	public void leaveTheBuilding() { ... }
}

