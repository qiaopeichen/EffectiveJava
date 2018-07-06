// 考虑一下PhoneNumber类，它的equals方法是根据第8条中给出的“诀窍”构造出来的
public final class PhoneNumber {
	private final short areaCode;
	private final short prefix;
	private final short lineNumber;

	public PhoneNumber(int areaCode, int prefix, int lineNumber) {
		rangeCheck(areaCode, 999, "area code");
		rangeCheck(prefix, 999, "prefix");
		rangeCheck(lineNumber, 9999, "line number");
		this.areaCode = (short) areaCode;
		this.prefix = (short) prefix;
		this.lineNumber = (short) lineNumber;
	}

	private static void rangeCheck(int arg, int max, string name) {
		if (arg < 0 || arg > max)
			throw new IllegalArgumentException(name + ": " + arg);
	}


	// 这里一定要有@override注解，或参数为Object o ，否则从语法角度上无法重写父类的equals。
	@Override 
	public boolean equals(Object o) {
		if (o == this) 
			return true;
		if (!(o instanceof PhoneNumber)) 
			return false;
		PhoneNumber pn = (PhoneNumber)o;
		return pn.lineNumber == lineNumber
			&& pn.prefix == prefix
			&& pn.areaCode == areaCode;
	}

	// Broken - no hashCode method!

	... // Remainder omitted
}

// 假设你企图将这个类与HashMap一起使用:
Map<PhoneNumber, String> m = new HashMap<PhoneNumber, String>();
m.put(new PhoneNumber(707, 867, 5309), "Jenny");

// 这时候你可能期望m.get(new PhoneNumber(707, 867, 5309))会返回 "Jenny"，
// 但它实际上返回的是null。
// 由于PhoneNumber类没有覆盖hashCode方法，从而导致两个相等的实例具有不相等的散列码。

// 修正这个问题，需要为PhoneNumber类提供一个适当的hashCode方法。
// 下面给出简单的解决办法：

/*
	1.把某个非零的常数值，比如说17，保存在一个名为result的int类型的变量中。
	2.对于对象中的每个关键域f（指equals方法中涉及的每个域），完成以下步骤：
		a.为该域计算int类型的散列码c
			i.如果该域是boolean类型，则计算（f ? 1 : 0）。
			ii.如果该域是byte/char/shot/或者int类型，则计算(int)f。
			iii.如果该域是long类型，则计算(int)(f ^ f(>>>32))。
			iv.如果该域是float类型，则计算Float.floatToIntBits(f)。
			v.如果该域是double类型，则计算Double.doubleToLongBits(f)，然后按照步骤2.a.iii，为得到的long类型值计算散列值。
			vi.如果该域是一个对象引用，并且该类的equals方法通过递归地调用equals的方式来比较这个域，
				则同样为这个域递归地调用hashCode。如果需要更复杂的比较，则为这个域计算一个“范式”，然后针对这个范式调用hashCode。
				如果这个域的值为null，则返回0（或者其他某个常数，但通常是0）。
			vii.如果该域是一个数组，则吧每一个元素当作单独的域来处理。也就是说，递归地应用上述规则，对每个重要的元素计算一个散列码，
				然后根据2.b中的做法把这些散列值组合起来。如果数组域中的每个元素都很重要，可以利用发行版本1.5种增加到其中一个Arrays.hashCode方法。
		b.按照下面的公式，把步骤2.a中计算得到的散列码c合并到result中：
			result = 31 * result + c;
	3.返回result。
	4.写完了hashCode方法之后，问问自己“相等的实例是否都具有相等的散列码”。
*/


