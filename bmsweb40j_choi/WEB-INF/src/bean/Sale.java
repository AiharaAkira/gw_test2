package bean;

public class Sale {

	private String isbn; // ISBN番号
	private String title; // 書籍のタイトル
	private int price; // 価格
	private int quantity; // 数量
	private String img;

	public Sale() {

		this.isbn = null;
		this.title = null;
		this.price = 0;
		this.quantity = 0;
		this.img = null;

	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Sale(String isbn, String title, int price, int quantity, String img) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.price = price;
		this.quantity = quantity;
		this.img = img;
	}

	public Sale(String isbn2, String title2, int price2, int quantity2) {
		super();
		this.isbn = isbn2;
		this.title = title2;
		this.price = price2;
		this.quantity = quantity2;

	}



}
