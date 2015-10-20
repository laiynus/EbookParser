package ebookparser.parser.infofull;

import ebookparser.book.EBook;
import ebookparser.book.children.Person;
import ebookparser.other.SOP;
import ebookparser.parser.Parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class EPUBParserInfoFull implements Parser{

    private final int MAX_EPUBINFO_SIZE = 4096;
    private final int MAX_XMLINFO_SIZE = 80;
    private EBook eBook = new EBook();
    private String source;
    private ZipFile zipFile;
    private Enumeration<? extends ZipEntry> zipEntries;

    @Override
    public EBook toParse(String fileName) {
        return null;
    }

    private String createSource() throws IOException {
        ZipEntry entry = null;
        zipFile = new ZipFile(this.eBook.getFileName());
        zipEntries = zipFile.entries();
        while (zipEntries.hasMoreElements()) {
            entry = zipEntries.nextElement();
            if (entry.getName().matches("(?i).*\\.opf$"))
                break;
        }
        InputStream inputStream = zipFile.getInputStream(entry);
        byte[] buffer = readInputStream(inputStream);
        String encoding = getXmlEncoding(buffer);
        String preparedInput = new String(buffer, encoding);
        Matcher matcher = SOP.getEpubDescription().matcher(preparedInput);
        if (matcher.find()) {
            this.eBook.setAnnotation(matcher.group(1));
            preparedInput = matcher.replaceFirst("");
        }
        return preparedInput;
    }

    private byte[] readInputStream(InputStream input) throws IOException {
        byte[] buffer = new byte[MAX_EPUBINFO_SIZE];
        int counter = 0;
        int amount = 0;
        int stopCounter = 0;
        boolean stop = false;
        while (!stop & (amount < MAX_EPUBINFO_SIZE) && (counter != -1)) {
            counter = input.read(buffer, amount, MAX_EPUBINFO_SIZE - amount);
            amount += counter;
            while (stopCounter < amount) {
                if (buffer[stopCounter] == '>')
                    if (buffer[stopCounter - 1] == 'a')
                        if (buffer[stopCounter - 10] == '<')
                            if (buffer[stopCounter - 9] == '/') {
                                stop = true;
                                break;
                            }
                stopCounter++;
            }
        }
        if (amount <= 0)
            throw new IOException("Empty input stream");
        byte[] output = new byte[stopCounter];
        System.arraycopy(buffer, 0, output, 0, stopCounter);
        return output;
    }

    private String getXmlEncoding(byte[] input) throws IOException {
        String encoding = null;
        String xmlHeader = new String(input, 0, MAX_XMLINFO_SIZE, "ISO-8859-1");
        Matcher matcher = SOP.getXmlEncoding().matcher(xmlHeader);
        if (matcher.find())
            encoding = matcher.group(1);
        else
            throw new IOException("Unknown encoding");
        return encoding;
    }

    private byte[] getCover(String fileName) throws IOException {
        ZipEntry entry = null;
        zipEntries = zipFile.entries();
        while (zipEntries.hasMoreElements()) {
            entry = zipEntries.nextElement();
            if (entry.getName().matches("(?i).*" + fileName))
                break;
        }
        int fileLength = (int) entry.getSize();
        InputStream inputStream = zipFile.getInputStream(entry);
        byte[] output = new byte[fileLength];
        int counter = 0;
        int amount = 0;
        while (amount < fileLength) {
            counter = inputStream.read(output, amount, fileLength - amount);
            amount += counter;
        }
        return output;
    }

    protected EBook extractMeta() throws IOException {
        Matcher matcher;
        matcher = SOP.getEpubTitle().matcher(this.source);
        if (matcher.find())
            this.eBook.setTitle(matcher.group(1));
        matcher = SOP.getEpubAuthor().matcher(this.source);
        while (matcher.find()) {
            this.eBook.getAuthors().add(new Person(matcher.group(1)));
        }
        matcher = SOP.getEpubLanguage().matcher(this.source);
        if (matcher.find())
            this.eBook.setLanguage(matcher.group(1));
        matcher = SOP.getEpubGenre().matcher(this.source);
        while (matcher.find())
            this.eBook.getGenres().add(matcher.group(1));
        if (this.eBook.isDoExtractCover()) {
            matcher = SOP.getEpubCover().matcher(this.source);
            if (matcher.find())
                this.eBook.setCover(getCover(matcher.group(1)));
        }
        this.eBook.setIsOk(true);
        return eBook;
    }
}
