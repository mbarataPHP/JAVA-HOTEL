package Enum;

public enum FactureType {
	EN_COURS("en cours"),
	PAYER("payer");
	
	private String text;

	FactureType(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public static FactureType fromString(String text) {
		for (FactureType b : FactureType.values()) {
			if (b.text.equalsIgnoreCase(text)) {
				return b;
			}
		}
		return null;
	}
}
