import java.util.UUID;

public class KartenStatus {
	private UUID uid;
	private int fach;

	public KartenStatus(UUID uid, int fach ) {
		this.uid = uid;
		this.fach = fach;
	}

	public UUID getUid() {
		return uid;
	}

	public void setUid(UUID uid) {
		this.uid = uid;
	}

	public int getFach() {
		return fach;
	}

	public void setFach(int fach) {
		this.fach = fach;
	}

	@Override
	public String toString() {
		return "KartenStatus [uid=" + uid + ", fach=" + fach + "]";
	}
	
	
	
	
	
	

}
