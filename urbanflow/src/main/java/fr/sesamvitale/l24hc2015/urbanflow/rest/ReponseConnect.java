package fr.sesamvitale.l24hc2015.urbanflow.rest;

public class ReponseConnect {

	/**
	 * {"status": "registered",
	 *  "url": "/api/play/32dcb69ff50409296d30c4a5e2f874e8/ea3b31b7956ba470a0545b79e4069d71/verif", 
	 *  "message": "Your bot is now registered! Check the url to see when you will play.", 
	 *  "success": true}
	 */
	
	private String urlVerify;
	private String status;
	
	private String message;
	
	private boolean success;

	public String getURrlVerify() {
		return urlVerify;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setUrlVerify(String urlVerify) {
		this.urlVerify = urlVerify;
	}

}
