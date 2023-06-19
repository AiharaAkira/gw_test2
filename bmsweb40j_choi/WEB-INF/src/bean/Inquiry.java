package bean;

import java.util.Date;

public class Inquiry {

	private int inquiryno;
	private String cotitle;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String comment;
	private Date date;


	public Inquiry() {

		this.inquiryno = 0;
		this.cotitle = null;
		this.name = null;
		this.email = null;
		this.phone = null;
		this.address = null;
		this.comment = null;
		this.date = null;
	}


	public int getInquiryno() {
		return inquiryno;
	}


	public void setInquiryno(int inquiryno) {
		this.inquiryno = inquiryno;
	}


	public String getCotitle() {
		return cotitle;
	}


	public void setCotitle(String cotitle) {
		this.cotitle = cotitle;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Inquiry(int inquiryno, String cotitle, String name, String email, String phone, String address,
			String comment, Date date) {
		super();
		this.inquiryno = inquiryno;
		this.cotitle = cotitle;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.comment = comment;
		this.date = date;
	}



}
