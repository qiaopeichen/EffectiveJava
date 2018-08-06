// Preferred idiom for nested iteration on collecctions and arrays
for (Suit suit : suits) {
	for (Rank rank : ranks) {
		deck.add(new Card(suit, rank));
	}
}
// for-each循环在简洁性和预防Bug方面有着传统的for循环无法比拟的优势，并且没有性能损失。
