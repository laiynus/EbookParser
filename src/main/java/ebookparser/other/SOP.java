package ebookparser.other;

import java.util.regex.Pattern;

/**
 * Store of Patterns
 */
public class SOP {

    private static Pattern fb2File;
    private static Pattern fb2zipFile;
    private static Pattern xmlEncoding;
    private static Pattern fb2FirstName;
    private static Pattern fb2MiddleName;
    private static Pattern fb2LastName;
    private static Pattern fb2Author;
    private static Pattern fb2Title;
    private static Pattern fb2genre;
    private static Pattern fb2Language;
    private static Pattern fb2Sequence;
    private static Pattern fb2SequenceName;
    private static Pattern fb2SequenceNumber;
    private static Pattern fb2Annotation;
    private static Pattern fb2Cover;
    private static Pattern fb2PublisherInfo;
    private static Pattern fb2Publisher;
    private static Pattern fb2City;
    private static Pattern fb2Year;
    private static Pattern fb2ISBN;
    private static Pattern fb2Body;
    private static Pattern fb2Section;
    private static Pattern fb2P;
    private static Pattern fb2ChapterTitle;

    private static Pattern epubFile;
    private static Pattern epubDescription;
    private static Pattern epubTitle;
    private static Pattern epubAuthor;
    private static Pattern epubLanguage;
    private static Pattern epubGenre;
    private static Pattern epubCover;



    static {
        fb2File = Pattern.compile("(?i).*fb2$");
        fb2zipFile = Pattern.compile("(?i).*fb2\\.zip$");
        xmlEncoding = Pattern.compile("(?i).*encoding=[\"'](.*?)[\"'].*");
        fb2FirstName = Pattern.compile("(?s)<first-name>(.*)</first-name>");
        fb2MiddleName = Pattern.compile("(?s)<middle-name>(.*)</middle-name>");
        fb2LastName = Pattern.compile("(?s)<last-name>(.*)</last-name>");
        fb2Author = Pattern.compile("(?s)<author>(.*?)</author>");
        fb2Title = Pattern.compile("(?s)<book-title>(.*?)</book-title>");
        fb2PublisherInfo = Pattern.compile("(?s)<publish-info>(.*?)</publish-info>");
        fb2Publisher = Pattern.compile("(?s)<publisher>(.*)</publisher>");
        fb2City = Pattern.compile("(?s)<city>(.*)</city>");
        fb2Year = Pattern.compile("(?s)<year>(.*)</year>");
        fb2ISBN = Pattern.compile("(?s)<isbn>(.*)</isbn>");
        fb2genre = Pattern.compile("(?s)<genre>(.*?)</genre>");
        fb2Body = Pattern.compile("(?s)<body>(.*?)</body>");
        fb2Section = Pattern.compile("(?s)<section>(.*?)</section>");
        fb2ChapterTitle = Pattern.compile("(?s)<title>(.*?)</title>");
        fb2P = Pattern.compile("(?s)<p>(.*?)</p>");
        fb2Language = Pattern.compile("(?s)<lang>(.*?)</lang>");
        fb2Sequence = Pattern.compile("(?s)<sequence(.*)>");
        fb2SequenceName = Pattern.compile("name=\"(.*?)\"");
        fb2SequenceNumber = Pattern.compile("number=\"(.*?)\"");
        fb2Annotation = Pattern.compile("(?s)<annotation>(.*?)</annotation>");
        fb2Cover = Pattern.compile("(?s)<coverpage>.*href=\"#(.*?)\".*</coverpage>");
        fb2Annotation = Pattern.compile("(?s)<annotation>(.*?)</annotation>");
        epubFile = Pattern.compile("(?i).*epub$");
        epubDescription = Pattern.compile("(?s)<dc:description>(.*?)</dc:description>");
        epubTitle = Pattern.compile("(?s)<dc:title>(.*?)</dc:title>");
        epubAuthor = Pattern.compile("(?s)<dc:creator.*?>(.*?)</dc:creator>");
        epubLanguage = Pattern.compile("(?s)<dc:language.*?>(.*?)</dc:language>");
        epubGenre = Pattern.compile("(?s)<dc:subject.*?>(.*?)</dc:subject>");
        epubCover = Pattern.compile("(?s)<embeddedcover>(.*?)</embeddedcover>");
    }

    public static Pattern getFb2File() {
        return fb2File;
    }

    public static void setFb2File(Pattern fb2File) {
        SOP.fb2File = fb2File;
    }

    public static Pattern getFb2zipFile() {
        return fb2zipFile;
    }

    public static void setFb2zipFile(Pattern fb2zipFile) {
        SOP.fb2zipFile = fb2zipFile;
    }

    public static Pattern getXmlEncoding() {
        return xmlEncoding;
    }

    public static void setXmlEncoding(Pattern xmlEncoding) {
        SOP.xmlEncoding = xmlEncoding;
    }

    public static Pattern getFb2FirstName() {
        return fb2FirstName;
    }

    public static void setFb2FirstName(Pattern fb2FirstName) {
        SOP.fb2FirstName = fb2FirstName;
    }

    public static Pattern getFb2MiddleName() {
        return fb2MiddleName;
    }

    public static void setFb2MiddleName(Pattern fb2MiddleName) {
        SOP.fb2MiddleName = fb2MiddleName;
    }

    public static Pattern getFb2LastName() {
        return fb2LastName;
    }

    public static void setFb2LastName(Pattern fb2LastName) {
        SOP.fb2LastName = fb2LastName;
    }

    public static Pattern getFb2Author() {
        return fb2Author;
    }

    public static void setFb2Author(Pattern fb2Author) {
        SOP.fb2Author = fb2Author;
    }

    public static Pattern getFb2Title() {
        return fb2Title;
    }

    public static void setFb2Title(Pattern fb2Title) {
        SOP.fb2Title = fb2Title;
    }

    public static Pattern getFb2genre() {
        return fb2genre;
    }

    public static void setFb2genre(Pattern fb2genre) {
        SOP.fb2genre = fb2genre;
    }

    public static Pattern getFb2Language() {
        return fb2Language;
    }

    public static void setFb2Language(Pattern fb2Language) {
        SOP.fb2Language = fb2Language;
    }

    public static Pattern getFb2Sequence() {
        return fb2Sequence;
    }

    public static void setFb2Sequence(Pattern fb2Sequence) {
        SOP.fb2Sequence = fb2Sequence;
    }

    public static Pattern getFb2SequenceName() {
        return fb2SequenceName;
    }

    public static void setFb2SequenceName(Pattern fb2SequenceName) {
        SOP.fb2SequenceName = fb2SequenceName;
    }

    public static Pattern getFb2SequenceNumber() {
        return fb2SequenceNumber;
    }

    public static void setFb2SequenceNumber(Pattern fb2SequenceNumber) {
        SOP.fb2SequenceNumber = fb2SequenceNumber;
    }

    public static Pattern getFb2Annotation() {
        return fb2Annotation;
    }

    public static void setFb2Annotation(Pattern fb2Annotation) {
        SOP.fb2Annotation = fb2Annotation;
    }

    public static Pattern getFb2Cover() {
        return fb2Cover;
    }

    public static void setFb2Cover(Pattern fb2CoverName) {
        SOP.fb2Cover = fb2CoverName;
    }

    public static Pattern getFb2PublisherInfo() {
        return fb2PublisherInfo;
    }

    public static void setFb2PublisherInfo(Pattern fb2PublisherInfo) {
        SOP.fb2PublisherInfo = fb2PublisherInfo;
    }

    public static Pattern getFb2Publisher() {
        return fb2Publisher;
    }

    public static void setFb2Publisher(Pattern fb2Publisher) {
        SOP.fb2Publisher = fb2Publisher;
    }

    public static Pattern getFb2City() {
        return fb2City;
    }

    public static void setFb2City(Pattern fb2City) {
        SOP.fb2City = fb2City;
    }

    public static Pattern getFb2Year() {
        return fb2Year;
    }

    public static void setFb2Year(Pattern fb2Year) {
        SOP.fb2Year = fb2Year;
    }

    public static Pattern getFb2ISBN() {
        return fb2ISBN;
    }

    public static void setFb2ISBN(Pattern fb2ISBN) {
        SOP.fb2ISBN = fb2ISBN;
    }

    public static Pattern getFb2Body() {
        return fb2Body;
    }

    public static void setFb2Body(Pattern fb2Body) {
        SOP.fb2Body = fb2Body;
    }

    public static Pattern getFb2Section() {
        return fb2Section;
    }

    public static void setFb2Section(Pattern fb2Section) {
        SOP.fb2Section = fb2Section;
    }

    public static Pattern getFb2P() {
        return fb2P;
    }

    public static void setFb2P(Pattern fb2P) {
        SOP.fb2P = fb2P;
    }

    public static Pattern getFb2ChapterTitle() {
        return fb2ChapterTitle;
    }

    public static void setFb2ChapterTitle(Pattern fb2ChapterTitle) {
        SOP.fb2ChapterTitle = fb2ChapterTitle;
    }

    public static Pattern getEpubFile() {
        return epubFile;
    }

    public static void setEpubFile(Pattern epubFile) {
        SOP.epubFile = epubFile;
    }

    public static Pattern getEpubDescription() {
        return epubDescription;
    }

    public static void setEpubDescription(Pattern epubDescription) {
        SOP.epubDescription = epubDescription;
    }

    public static Pattern getEpubTitle() {
        return epubTitle;
    }

    public static void setEpubTitle(Pattern epubTitle) {
        SOP.epubTitle = epubTitle;
    }

    public static Pattern getEpubAuthor() {
        return epubAuthor;
    }

    public static void setEpubAuthor(Pattern epubAuthor) {
        SOP.epubAuthor = epubAuthor;
    }

    public static Pattern getEpubLanguage() {
        return epubLanguage;
    }

    public static void setEpubLanguage(Pattern epubLanguage) {
        SOP.epubLanguage = epubLanguage;
    }

    public static Pattern getEpubGenre() {
        return epubGenre;
    }

    public static void setEpubGenre(Pattern epubGenre) {
        SOP.epubGenre = epubGenre;
    }

    public static Pattern getEpubCover() {
        return epubCover;
    }

    public static void setEpubCover(Pattern epubCover) {
        SOP.epubCover = epubCover;
    }
}