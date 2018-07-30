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

// Enum type with data and behavior
public enum Planet {
	MERCURY(3.302e+23, 2.439e6),
	VENUS(4.869e+24, 6.052e6),
	EARTH(5.97e+24, 6.378e6),
	MARS(6.419e+23, 3.393e6),
	JUPITER(1.899e+27, 7.149e7),
	SATURN(5.685e+26, 6.027e7),
	URANUS(8.683e+25, 2.556e7),
	NEPTUNE(1.024e+26, 2.477e7);
	private final double mass; // In kilograms
	private final double radius; // In meters
	private final double surfaceGravity; // In m / s^2

	// Universal gravitational constant in m^3 / kg s^2
	private static final double G = 6.67300e-11;

	// Constructor
	Planet(double mass, double radius) {
		this.mass = mass;
		this.radius = radius;
		surfaceGravity = G * mass / (radius * radius);
	}

	public double mass() {
		return mass;
	}

	public double radius() {
		return radius;
	}

	public double surfaceGravity() {
		return surfaceGravity;
	}

	public double surfaceWeight(double mass) {
		return mass * surfaceGravity; // F = ma
	}
}

public class WeightTable {
	public static void main(String[] args) {
		double earthWeight = Double.parseDouble(args[0]);
		dpuble mass = earthWeight / Planet.EARTH.surfaceGravity();
		for (Planet p : Planet.values()) {
			System.out.printf("Weight on %s is %f%n",
			                  p, p.surfaceWeight(mass));
		}
	}
}

// Enum type with constant-specific method implementations
public enum Operation {
	PLUS {
		double apply(double x, double y) {
			return x + y;
		}
	},
	MINUS {
		double apply(double x, double y) {
			return x - y;
		}
	},
	TIMES {
		double apply(double x, double y) {
			return x * y;
		}
	},
	DIVIDE {
		double apply(double x, double y) {
			return x / y;
		}
	};

	abstract double apply(double x, double y);
}

// Enum type with constant-specific class bodies and data
public enunm Operation {
	PLUS("+") {
		double apply(double x, double y) { return x + y; }
	},
	MINUS("-") {
		double apply(double x, double y) { return x - y; }
	},
	TIMES("*") {
		double apply(double x, double y) { return x * y; }
	},
	DIVIDE("/") {
		double apply(double x, double y) { return x / y; }
	};
	private final String symbol;
	Operation(String symbol) { this.symbol = symbol; }
	@Override 
	public String toString() { return symbol; }
	abstract double apply (double x, double y);
}

public static void main(String[] args) {
	double x = Double.parseDouble(args[0]);
	double y = Double.parseDouble(args[1]);
	for (Operation op : Operation.values())
		System.out.printf("%f %s %f = %f%n" ,
						  x, op, y, op.apply(x, y));
}

// Implementing a fromString method on an enum type
private static final Map<String, Operation> stringToEnum
	= new HashMap<>();
static { // Initialize map from constant name to enum constant
	for (Operation op : values()) {
		stringToEnum.put(op.toString(), op);
	}
	// Returns Operation for string, or null if string is invalid
	public static Operation fromString(String symbol) {
		return stringToEnum.get(symbol);
	}
}

// The strategy enum pattern
enum PayRollDay {
	MONDAY(PayType.WEEKDAY), TUESDAY(PayType.WEEKDAY),
	WEDNESDAY(PayType.WEEKDAY), THURSDAY(PayType.WEEKDAY),
	FRIDAY(PayType.WEEKDAY),
	SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

	private final PayType PayType;
	PayrollDay(PayType payType) { this.payType = payType; }

	double pay(double hoursWorked, double payRate) {
		return payType.pay(hoursWorked, payRate);
	}

	// The strategy enum type
	private enum PayType {
		WEEKDAY {
			double overtimePay(double hours, double payRate) {
				return hours <= HOURS_PER_SHIFT ? 0 :
					(hours - HOURS_PER_SHIFT) * payRate / 2;
			}
		},
		WEEKEND {
			double overtimePay(double hours, double payRate) {
				return hours * payRate / 2;
			}
		};
		private static final int HOURS_PER_SHIFT = 8;
		abstract double overtimePay(double hrs, double payRate);

		double pay(double hoursWorked, double payRate) {
			double basePay = hoursWorked * payRate;
			return basePay + overtimePay(hoursWorked, payRate);
		}
	}
}

// Switch on an enum to simulate a missing method
public static Operation inverse(Operation op) {
	switch(op) {
		case PLUS: return Operation.MINUS;
		case MINUS: return Operation.PLUS;
		case TIMES: return Operation.DIVIDE;
		case DIVIDE: return Operation.TIMES;
		default: throw new AssertionError("UnKnown op:" + op);
	}
}







