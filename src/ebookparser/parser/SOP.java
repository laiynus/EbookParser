package ebookparser.parser;

import java.util.regex.Pattern;

/**
 * Store of Patterns
 */
class SOP {
    protected static Pattern fb2File;
    protected static Pattern fb2zipFile;
    protected static Pattern xmlEncoding;
    protected static Pattern fb2FirstName;
    protected static Pattern fb2MiddleName;
    protected static Pattern fb2LastName;
    protected static Pattern fb2Author;
    protected static Pattern fb2Title;
    protected static Pattern fb2genre;
    protected static Pattern fb2Language;
    protected static Pattern fb2Sequence;
    protected static Pattern fb2SequenceName;
    protected static Pattern fb2SequenceNumber;
    protected static Pattern fb2Annotation;
    protected static Pattern fb2CoverName;
    protected static Pattern fb2PublisherInfo;
    protected static Pattern fb2Publisher;
    protected static Pattern fb2City;
    protected static Pattern fb2Year;
    protected static Pattern fb2ISBN;
    protected static Pattern fb2Body;
    protected static Pattern fb2Section;
    protected static Pattern fb2P;
    protected static Pattern fb2ChapterTitle;


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
        fb2CoverName = Pattern.compile("(?s)<coverpage>.*href=\"#(.*?)\".*</coverpage>");

    }

}