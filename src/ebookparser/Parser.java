package ebookparser;

import java.text.ParseException;

abstract public class Parser {
	protected Ebook eBook;

	/**
	 * Handles the e-book extracts contained therein meta-information
	 * @param fileName - the name of the file to be processed
	 * @return - instance of the class EBook with the fields filled with
	 * e-book meta-information
	 * @throws ParseException 
	 */
	public Ebook parse(String fileName)  {
		return this.parse(fileName, -1,false);
	}
	
	/**
	 * @param fileName
	 * @param extractCover
	 * @return
	 * @throws ParseException 
	 */
	public Ebook parse(String fileName, int chapter,boolean extractCover)  {
		this.eBook = new Ebook();
		this.eBook.fileName = fileName;
		Chapter chapt = new Chapter();
		chapt.setNumber(chapter);
		this.eBook.doExtractCover = extractCover;
		this.eBook.currentChapter = chapt;
		this.eBook.isOk = false;
		this.parseFile();
		return this.eBook;
	}
	
	
	abstract protected void parseFile() ;

	/**
	 * Returns instance of the class EBook with the fields filled with
	 * e-book meta-information 
	 * @return - instance of the class EBook 
	 */
	public Ebook getEbook() {
		return this.eBook;
	}
}
