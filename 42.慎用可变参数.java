static int sum(int... args) {
	int sum = 0;;
	for (int arg : args) {
		sum += arg;
	}
	return sum;
}

static int min(int firstArg, int... remainingArgs) {
	int min = firstArg;
	for (int args : remainingArgs) {
		if (arg < min) {
			min = arg;
		}
	}
	return min;
}