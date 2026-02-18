package utils;

public class Autenticazione {
	private boolean isGranted;
	private boolean isAdmin;
	
	
	
	public Autenticazione(boolean isGranted, boolean isAdmin) {
		super();
		this.isGranted = isGranted;
		this.isAdmin = isAdmin;
	}
	public boolean isGranted() {
		return isGranted;
	}
	public void setGranted(boolean granted) {
		this.isGranted = granted;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
}
