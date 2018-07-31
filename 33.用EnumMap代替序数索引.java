// Using an EnumMap to associate data with an enum
Map<Herb.Type, Set<Herb>> herbsByType = 
	new EnumMap<Herb.Type, Set<Herb>>(Herb.Type.class);
for (Herb.Type t : Herb.Type.values()) {
	herbsByType.put(t, new HashSet<Herb>());
}
for (Herb h : garden) {
	herbsByType.get(h.type).add(h);
}
System.out.println(herbsByType);

// Using a nested EnumMap to associate data with enum pairs
public enum Phase {
	SOLID, LIQUID, GAS;

	public enum Transition {
		MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
		BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
		SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);

		private final Phase src;
		private final Phase dst;

		Transition(Phase src, Phase dst) {
			this.src = src;
			this.dst = dst;
		}
		// Initialize the phase transition map
		private static final Map<Phase, Map<Phase, Transition>> m =
			new EnumMap<Phase, Map<Phase, Transition>>(Phase.class);
		static {
			for (Phase p : Phase.values()) {
				m.put(p, new EnumMap<Phase, Transition>(Phase.class));
			}
			for (Transition trans : Transition.values()) {
				m.get(trans.src).put(trans.dst, trans);
			}
		}

		public static Transition from(Phase src, Phase dst) {
			return m.get(src).get(dst);
		}
	}
}







