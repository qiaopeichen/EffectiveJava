// EnumSet - a modern replacement for bit fileds
public class Text {
	pubic enum Style { BOLD, ITALIC, UNDERLINE, STRIKETHROUGH }

	// Any Set could be passed in, but EnumSet is clearly best
	public void applyStyles(Set<Style> Styles) {
		...
	}
}

text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));