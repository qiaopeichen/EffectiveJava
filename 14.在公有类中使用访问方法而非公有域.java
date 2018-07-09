// 例如，这个类确保了每个实例都表示一个有效的时间
// Public class with exposed immutable fields - questionable
public final class Time {
	private static final int HOURS_PRE_DAY = 24;
	private static final int MINUTES_PRE_HOUR = 60;

	public final int hour;
	public final int minute;

	public Time(int hour, int minute) {
		if (hour < 0 || hour >= HOURS_PRE_DAY) 
			throw new IllegalArgumentException("Hour: " + hour);
		if (minute < 0 || minute >= MINUTES_PRE_HOUR)
			throw new IllegalArgumentException("Min: " + minute);
		this.hour = hour;
		this.minute = minute;
	}
	... // Remainder omitted
}

// 总之，公有类永远都不应该暴露可变的域。虽然还是有问题，但是让公有类暴露不可变的域其危害比较小。
// 但是，有时候会需要用包级私有的或者私有的嵌套类来暴露域，无论这个类是可变还是不可变的。