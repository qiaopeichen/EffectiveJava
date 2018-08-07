// Invocation with checked exception
try {
	obj.action(args);
} catch(TheCheckedException e) {
	// Handle exceptional condition
	...
}

// Invocation with state-testing method and unchecked exception
if (obj.actionPermitted(args)) {
	obj.action(args);
} else {
	// Handle exceptional condition
	...
}