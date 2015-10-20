package ebookparser.book.children;

/**
 * Class containing the data of the selected chapter
 */
public class Chapter {

    /**
     * Name of the chapter
     */
    private String name;

    /**
     * Number of the chapter
     */
    private int number;

    /**
     * Text of the chapter
     */
    private String textChapter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTextChapter() {
        return textChapter;
    }

    public void setTextChapter(String textChapter) {
        this.textChapter = textChapter;
    }

    /**
     * The class constructor, fills the fields with null values.
     */
    public Chapter() {
        name = null;
        number = -1;
        textChapter = null;
    }

    /**
     * The class constructor, fills the field number from param
     *
     * @param number - The number of selected chapter
     */
    public Chapter(int number) {
        name = null;
        this.number = number;
        textChapter = null;
    }
}
