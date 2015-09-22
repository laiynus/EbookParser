package ebookparser;

/**
 * Class containing the data of publisher
 */
public class Publisher {
	/**
	 * Name of the publisher
	 */
	public String publisher;
	/**
	 * City of publish-house
	 */
	public String city;
	/**
	 * Date of the publish
	 */
	public String dateOfPublish;
	/**
	 * Isbn of book
	 */
	public String isbn;
	
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDateOfPublish() {
		return dateOfPublish;
	}

	public void setDateOfPublish(String dateOfPublish) {
		this.dateOfPublish = dateOfPublish;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	/**
	 * The class constructor, fills the fields with null values.
	 */
	public Publisher() {
		publisher = city =  isbn = dateOfPublish = null;
	}
	
	

	/**
	 * The class constructor, fills the fields from parsing publisher
	 * full name
	 * @param name - The publisher name
	 */
	public Publisher(String name) {
		name.trim();
		String[] nameParts = name.split("[\\s]+");
		int count = nameParts.length;
		this.publisher = nameParts[0];
		this.city = nameParts[count - 1];
		this.dateOfPublish = nameParts[count - 1];
		this.isbn = nameParts[count - 1];
	}

}
