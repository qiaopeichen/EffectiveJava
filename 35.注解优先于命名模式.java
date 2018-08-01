// Program containing marker annotations
public class Sample {
	@Test
	public static void m1() {} // Test should pass
	public static void m2() {}
	@Test
	public static void m3() { // Test should fail
		throw new RuntimeException("Boom");
	}
	public static void m4() {}
	@Test
	public void m5() {} // INVALID USE: nonstatic method
	public static void m6() {}
	@Test
	public static void m7() { // Test should fail
		throw new RuntimeException("Crash");
	}
	public static void m8() {}
}
// 除了“工具铁匠（toolsmiths）”之外，大多数程序员都不必定义注解类型。
// 但是所有的程序员都应该使用Java平台所提供的预定义的注解类型