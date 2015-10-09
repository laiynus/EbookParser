package ebookparser.book;

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
    private boolean isOk;

    /**
     * The id of the e-book
     */
    private int idBook;

    /**
     * Last date of opening the e-book
     */
    private Date lastDateReading;

    private int page;

    /**
     * The name of the file containing the processing e-book
     */
    private String fileName;

    /**
     * The name of the internal format of the e-book
     */
    private EbookFormat format;

    /**
     * List of authors of the e-book
     */
    private ArrayList<Person> authors;

    /**
     * Title of the-ebook
     */
    private String title;

    /**
     * Publisher of the-ebook
     */
    private Publisher publisher;

    /**
     * Genre of the book according to fb2 format
     */
    private List<String> fb2Genres;

    /**
     * the language in which the e-book was published
     */
    private String language;

    /**
     * the language of the e-book source
     */
    private String srcLanguage;

    /**
     * List of translators of the e-book
     */
    private ArrayList<Person> translators;

    /**
     * The name of the series, which includes the e-book
     */
    private String sequenceName;

    /**
     * Serial number of the e-book in the series
     */
    private String sequenceNumber;

    /**
     * Charset of the e-book text
     */
    private String encoding;

    /**
     * Brief summary of the e-book
     */
    private String annotation;

    /**
     * Selected chapter of the e-book
     */
    private Chapter currentChapter;

    /**
     * List of chapter of the e-book
     */
    private List<Chapter> listChapters;

    /**
     * Picture of e-book cover
     */
    private byte[] cover;

    /**
     * True, if need to extract cover of the e-book.
     */
    private boolean doExtractCover;

    public boolean isOk() {
        return isOk;
    }

    public void setIsOk(boolean isOk) {
        this.isOk = isOk;
    }

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

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
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

    public List<String> getFb2Genres() {
        return fb2Genres;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
        this.authors = new ArrayList<Person>();
        this.fb2Genres = new ArrayList<String>();
        this.translators = new ArrayList<Person>();
        this.listChapters = new ArrayList<Chapter>();
        this.isOk = false;
    }
}
