// String s = new String("stringette"); // DON'T DO THIS!
String s = "stringette";


pubic class Person {
	private final Date birthDate;
	// Other fields, methods, and constructor omitted
	
	//DON'T DO THIS!
	// public boolean isBabyBommer() {
	// 	// Unnecessary allocation of expensive object
	// 	Calendar gmtCal = 
	// 		Calendar.getInstance(TimeZone.getTimeZone("GMT"));
	// 	gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
	// 	Date boomStart = gmtCal.getTime();
	// 	gmtCal.set(1965, Calendr.JANUARY, 1, 0, 0, 0);
	// 	Date boomEnd = gmtCal.getTime();
	// 	return birthDate.compareTo(boomStart) >= 0 &&
	// 		birthDate.compareTo(boomEnd) < 0;
	// }

	static {
		Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
		BOOM_START = gmtCal.getTime();
		gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
		BOOM_END = gmtCal.getTime();
	}

	public boolean isBabyBommer() {
		return brithDate.compareTo(BOOM_START) >= 0 &&
			birthDate.compareTo(BOOM_END) < 0;
	}
}


//Hideously slow program! Can you spot the object creation?
public static void main(String[] args) {
	// Long sum = 0L; // I find it.
	long sum = 0L;
	for (long i = 0; i < Integer.MAX_VALUE; i++) {
		sum += i;
	}
	System.out.println(sum);
}

// 5.当你应该重用现有对象的时候，请不要创建新的对象。
// 39.当你应该创建新对象的时候，请不要重用现有的对象。