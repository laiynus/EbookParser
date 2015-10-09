package ebookparser.parser;

import ebookparser.book.*;

import java.text.ParseException;

abstract public class Parser {
    protected Ebook eBook;

    /**
     * Handles the e-book extracts contained therein meta-information
     *
     * @param fileName - the name of the file to be processed
     * @return - instance of the class EBook with the fields filled with
     * e-book meta-information
     * @throws ParseException
     */
    public Ebook parse(String fileName) {
        return this.parse(fileName, -1, false);
    }

    /**
     * @param fileName - the name of the file to be processed
     * @param chapter - Selected chapter of the e-book
     * @param extractCover - True, if need to extract cover of the e-book.
     * @return - instance of the class EBook with the fields filled with
     * e-book meta-information
     * @throws ParseException
     */
    public Ebook parse(String fileName, int chapter, boolean extractCover) {

        eBook = new Ebook();

        eBook.setFileName(fileName);

        Chapter ebookChapter = new Chapter(chapter);
        eBook.setCurrentChapter(ebookChapter);

        eBook.setDoExtractCover(extractCover);

        eBook.setIsOk(false);

        parseFile();

        return eBook;
    }


    abstract protected void parseFile();

    /**
     * Returns instance of the class EBook with the fields filled with
     * e-book meta-information
     *
     * @return - instance of the class EBook
     */
    public Ebook getEbook() {
        return eBook;
    }
}
