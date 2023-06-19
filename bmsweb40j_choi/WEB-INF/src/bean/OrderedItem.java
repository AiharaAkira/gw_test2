package bean;

import java.util.Date;

public class OrderedItem {

	private String userid;
	private String title;
	private int quantity;
	private String date;

	public OrderedItem() {

		this.userid = null;
		this.date = null;
		this.title = null;
		this.quantity = 0;

	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}



	public OrderedItem(String userid, String title, int quantity, String date) {
		super();
		this.userid = userid;
		this.title = title;
		this.quantity = quantity;
		this.date = date;
	}



}
