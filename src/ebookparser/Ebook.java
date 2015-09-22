package ebookparser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * EBook - class that contains fields describing the parameters and 
 * properties of the e-book
 */
public class Ebook {
	
	/**
	 * True, if the processing of the e-book was successful. 
	 * False if the e-book could not be processed.
	 */
	public boolean isOk;
	
	public int idBook;
	
	private Date lastDateReading;
	
	public int page;

	/**
	 * The name of the file containing the processing e-book
	 */
	public String fileName;
	/**
	 * The name of the internal format of the e-book
	 */
	public EbookFormat format;
	/**
	 * List of authors of the e-book
	 */
	public ArrayList<Person> authors;
	/**
	 * Title of the-ebook
	 */
	public String title;
	/**
	 * Publisher of the-ebook
	 */
	public Publisher publisher;
	/**
	 * Genre of the book according to fb2 format
	 */
	public List<String> fb2Genres;
	/**
	 * the language in which the e-book was published
	 */
	public String language;
	/**
	 * the language of the e-book source
	 */
	public String srcLanguage;
	/**
	 * List of translators of the e-book
	 */
	public ArrayList<Person> translators;
	/**
	 * The name of the series, which includes the e-book
	 */
	public String sequenceName;
	/**
	 * Serial number of the e-book in the series
	 */
	public String sequenceNumber;
	/**
	 * Charset of the e-book text
	 */
	public String encoding;
	/**
	 * Brief summary of the e-book
	 */
	public String annotation;
	
	/**
	 * Picture of e-book cover
	 */
	
	public Chapter currentChapter;
	
	public List<Chapter> listChapters;

	public byte[] cover;
	public boolean doExtractCover;
	
	public List<Chapter> getListChapters() {
		return listChapters;
	}

	public void setListChapters(List<Chapter> listChapters) {
		this.listChapters = listChapters;
	}
	
	public Chapter getCurrentChapter() {
		return currentChapter;
	}

	public void setCurrentChapter(Chapter currentChapter) {
		this.currentChapter = currentChapter;
	}
		
	public void setDoExtractCover(boolean doExtractCover) {
		this.doExtractCover = doExtractCover;
	}

	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public EbookFormat getFormat() {
		return format;
	}

	public void setFormat(EbookFormat format) {
		this.format = format;
	}

	public ArrayList<Person> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<Person> authors) {
		this.authors = authors;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte[] getCover() {
		return cover;
	}

	public boolean isDoExtractCover() {
		return doExtractCover;
	}
	

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	public Date getLastDateReading() {
		return lastDateReading;
	}

	public void setLastDateReading(Date lastDateReading) {
		this.lastDateReading = lastDateReading;
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public int getIdBook() {
		return idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}

	/**
	 * The class constructor, fills the fields with null values
	 */
	public Ebook() {
		this.authors = new ArrayList<Person>(3);
		this.fb2Genres = new ArrayList<String>(2);
		this.translators = new ArrayList<Person>(2);
		this.listChapters = new ArrayList<Chapter>();
		this.isOk = false;
	}
}
