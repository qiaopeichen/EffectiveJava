// Typesafe heterogeneous container pattern - API
public class Favorites {
	public <T> void putFavorite(Class<T> type, T instance);
	public <T> T getFavorite(Class<T> type);
}

//Typesafe heterongeneous container pattern - client
public static void main(String[] args) {
	Favorites f = new Favorites();
	f.putFavorite(String.class, "Java");
	f.putFavorite(Integer.class, 0xcafebabe);
	f.putFavorite(Class.class， Favorites.class);
	String favoriteString = f.getFavorite(String.class);
	int favoriteInteger = f.getFavorite(Integer.class);
	Class<?> favoriteClass = f.getFavorite(Class.class);
	System.out.printf("%s %x %s%n", favoriteString,
		favoriteInteger, favoriteClass.getName());
}
//打印的是： Java cafebabe Favorites

// Typesafe heterogeneous container pattern - implementation
public class Favorites {
	private Map<Class<?>， Object> favorites = new HashMap<>();
	public <T> void putFavorite(Class<T> type, T instance) {
		if (type == null) {
			throw new NullPointerException("Type is null");
		}
		favorites.put(type, instance);
	}

	public <T> T getFavorite(Class<T> type) {
		return type.cast(favorites.get(type));
	}
}

public class Class<T> {
	T cast(Object obj);
}
