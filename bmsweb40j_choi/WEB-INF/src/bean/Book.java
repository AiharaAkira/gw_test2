package bean;

public class Book {
	// 書籍番号を格納する変数
	private String isbn;
	// タイトルを格納する変数
	private String title;
	// 価格を格納する変数
	private int price;
	private String img;

	public Book() {
		this.isbn = null;
		this.title = null;
		this.price = 0;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Book(String isbn, String title, int price, String img) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.price = price;
		this.img = img;
	}

}
