// The right way to return an array from a collection
private final List<Cheese> cheeseInStock = ...;
private static final Cheese[] EMPTY_CHEESE_ARRAY = new Cheese[0];

/**
 * @return an array containing all of the cheeses in the shop.
 */
public Cheese[] getCheeses() {
	return cheesesInStock.toArray(EMPTY_CHEESE_ARRAY);
}

// The right way to return a copy of a collection
public List<Cheese> getCheesesList() {
	if (cheeseInStock.isEmpty()) {
		return collection.emptyList(); // Always returns same list	
	} else {
		return new ArrayList<Cheese>(cheeseInStock);
	}
}