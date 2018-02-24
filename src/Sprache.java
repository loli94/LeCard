/* @autor Lars Weder,Martin Heinzle,Roman Vorburger, Marvin Kündig
 * @version 0.2
 * Datum:24.02.2018
 */
public class Sprache {
	
	private String sprachPaar;
	private String spracheA;
	private String spracheB;
	
	public Sprache() {
	
	}


	public Sprache(String spPaar, String spA, String spB) {
		this.spracheA = spA;
		this.spracheB = spB;
		this.sprachPaar = spPaar;
	}

	
	public String getSprachPaar() {
		return sprachPaar;
	}

	
	public void setSprachPaar(String sprachPaar) {
		this.sprachPaar = sprachPaar;
	}

	public String getSpracheA() {
		return spracheA;
	}

	public void setSpracheA(String spracheA) {
		this.spracheA = spracheA;
	}

	public String getSpracheB() {
		return spracheB;
	}

	public void setSpracheB(String spracheB) {
		this.spracheB = spracheB;
	}


}
