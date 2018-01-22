import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Karte")
@XmlAccessorType(XmlAccessType.FIELD)

public class Karte{
	
	private UUID id;
	private String wortA;
	private String wortB;
	private Sprache sprache;
	
	public Karte() {
		
	}
	
	public Karte(Sprache sp, String wa, String wb) {
		this.wortA = wa;
		this.wortB = wb;
		this.id = UUID.randomUUID();
		this.sprache = sp;
	}
	
	public String getSprache() {
		String sp = sprache.getSprachPaar();
		return sp;
	}

	public String getWortA() {
		return wortA;
	}

	public void setWortA(String wortA) {
		this.wortA = wortA;
	}

	public String getWortB() {
		return wortB;
	}

	public void setWortB(String wortB) {
		this.wortB = wortB;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Karte [id=" + id + ", wortA=" + wortA + ", wortB=" + wortB + ", sprache=" + sprache + "]";
	}
		
	
}


