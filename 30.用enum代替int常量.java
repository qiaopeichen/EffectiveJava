// 在编程语言还没有引入枚举类型之前，表示枚举类型常用模式是
// 声明一组具名的int常量，每个类型成员一个常量：
// The int enum pattern - severely deficient!
public static final int APPLE_FUJI = 0;
public static final int APPLE_PIPPIN = 1;
// 这种方法称作int枚举模式，存在诸多不足
// 类型安全性和使用方便性方面没有任何帮助。如果你将apple传入orange方法中
// 编译器也不会出现警告，还会用==操作符将apple和orange进行对比,甚至更糟：
// Tasty citrus flavored applesauce! 美味的柑橘味苹果酱!
int i = (APPLE_FUJI - ORANGE_TEMPLE) // APPLE_PIPPIN

//枚举模式：
public enum Apple { FUJI, PIPPIN, GRANNY_SMITH}
public enum Orange {NAVEL, TEMPLE, BLOOD}
