import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement (name = "KartenStatus")
public class KartenStatus {
	
	private UUID uid;
	private int fach;
	

	public KartenStatus(UUID uid, int f ) {
		this.uid = uid;
		this.fach = f;
	}
	
	public KartenStatus () {
	}

	@XmlElement
	public UUID getUid() {
		return uid;
	}

	public void setUid(UUID uid) {
		this.uid = uid;
	}

	@XmlElement
	public int getFach() {
		return fach;
	}

	public void setFach(int f) {
		this.fach = f;
	}

	@Override
	public String toString() {
		return "KartenStatus [uid=" + uid + ", fach=" + fach + "]";
	}
	

	
	
	
	
	

}
