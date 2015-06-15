package br.ufpe.cin.if1001.chatoffline.gui.listpeers;

public class Peer {
	
	public enum Status {
    	STATUS_ONLINE,
    	STATUS_OFFLINE
    }
	
	private String message;
	private String name;
	private Status status;

    public Peer() {
    }
    
    public Peer(String name, String message, Status status) {
    	this.name = name;
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public String getName() {
		return name;
	}

    public void setName(String name) {
        this.name = name;
    }

    public void setTypeMessage(String name) {
		this.name = name;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
    
}