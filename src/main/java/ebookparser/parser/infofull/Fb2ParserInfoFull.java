package ebookparser.parser.infofull;

import ebookparser.book.EBook;
import ebookparser.book.children.Person;
import ebookparser.book.children.Publisher;
import ebookparser.other.SOP;
import ebookparser.parser.Parser;
import ebookparser.parser.Base64Decoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;

public class Fb2ParserInfoFull implements Parser {

    private final int MAX_FB2INFO_SIZE = 4096;
    private final int MAX_XMLINFO_SIZE = 80;
    private final int MAX_FB2_SIZE = 2097152;
    private EBook eBook = new EBook();
    private InputStream inputStream;
    private String source = null;

    @Override
    public EBook toParse(String fileName) {
        try {
            inputStream = new FileInputStream(fileName);
            eBook = extractMeta();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return eBook;
    }

    private String createSource() throws IOException,
            NullPointerException {
        byte[] buffer = readInputStream();
        this.eBook.setEncoding(getXmlEncoding(buffer));
        source = new String(buffer, this.eBook.getEncoding());
        return source;
    }

    private String extractAnnotation(String input) {
        Matcher matcher;
        String annotation = null;
        matcher = SOP.getFb2P().matcher(input);
        while (matcher.find())
            annotation += matcher.group(1).trim() + "\n";
        return annotation;
    }

    private Person extractPerson(String input) {
        Matcher matcher;
        Person person = new Person();
        matcher = SOP.getFb2FirstName().matcher(input);
        if (matcher.find())
            person.setFirstName(matcher.group(1).trim());
        matcher = SOP.getFb2MiddleName().matcher(input);
        if (matcher.find())
            person.setMiddleName(matcher.group(1).trim());
        matcher = SOP.getFb2LastName().matcher(input);
        if (matcher.find())
            person.setLastName(matcher.group(1).trim());
        return person;
    }

    private Publisher extractPublisher(String input) {
        Matcher matcher;
        Publisher publisher = new Publisher();
        matcher = SOP.getFb2Publisher().matcher(input);
        if (matcher.find())
            publisher.setPublisher(matcher.group(1).trim());
        matcher = SOP.getFb2City().matcher(input);
        if (matcher.find())
            publisher.setCity(matcher.group(1).trim());
        matcher = SOP.getFb2Year().matcher(input);
        if (matcher.find())
            publisher.setDateOfPublish(matcher.group(1).trim());
        matcher = SOP.getFb2ISBN().matcher(input);
        if (matcher.find())
            publisher.setIsbn(matcher.group(1).trim());
        return publisher;
    }

    private byte[] readInputStream() throws IOException {
        byte[] buffer = new byte[MAX_FB2INFO_SIZE];
        int counter = 0;
        int amount = 0;
        int stopCounter = 0;
        boolean stop = false;
        while (!stop & (amount < MAX_FB2INFO_SIZE) && (counter != -1)) {
            counter = inputStream.read(buffer, amount, MAX_FB2INFO_SIZE - amount);
            amount += counter;
            while (stopCounter < amount) {
                if (buffer[stopCounter] == '>')
                    if (buffer[stopCounter - 1] == 'n')
                        if (buffer[stopCounter - 13] == '<')
                            if (buffer[stopCounter - 11] == 'd') {
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
        String encoding;
        String xmlHeader = new String(input, 0, MAX_XMLINFO_SIZE, "ISO-8859-1");
        Matcher matcher = SOP.getXmlEncoding().matcher(xmlHeader);
        if (matcher.find())
            encoding = matcher.group(1);
        else
            throw new IOException("Unknown encoding");
        return encoding;
    }

    private byte[] getCover() {
        byte[] buffer = new byte[MAX_FB2_SIZE];
        byte[] cover64;
        int amount = 0;
        int count = 0;
        try {
            while ((amount < MAX_FB2_SIZE) && (count != -1)) {
                count = inputStream.read(buffer, amount, MAX_FB2_SIZE - amount);
                if (count != -1)
                    amount += count;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (amount == MAX_FB2_SIZE) {
            return null;
        } else {
            int stop = -1;
            int start = -1;
            int counter = amount - 1;
            while (counter >= 0) {
                if (buffer[counter] == '<')
                    if (buffer[counter + 1] == '/')
                        if (buffer[counter + 2] == 'b')
                            if (buffer[counter + 7] == 'y')
                                if (buffer[counter + 8] == '>') {
                                    stop = counter - 1;
                                    break;
                                }
                counter--;
            }
            while (counter >= 0) {
                if (buffer[counter] == '<')
                    if (buffer[counter + 1] == 'b')
                        if (buffer[counter + 3] == 'n')
                            if (buffer[counter + 5] == 'r')
                                if (buffer[counter + 6] == 'y') {
                                    start = counter;
                                    break;
                                }
                counter--;
            }
            if ((start == -1) || (stop == -1)) {
                return null;
            }
            while (counter < stop) {
                if (buffer[counter] == '>') {
                    start = counter + 1;
                    break;
                }
                counter++;
            }
            int newSize = stop - start + 1;
            cover64 = new byte[newSize];
            System.arraycopy(buffer, start, cover64, 0, newSize);
        }
        return Base64Decoder.decode(cover64);
    }

    private EBook extractMeta() {
        try {
            source = createSource();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Matcher matcher;
        matcher = SOP.getFb2genre().matcher(source);
        while (matcher.find())
            eBook.getGenres().add(matcher.group(1));
        matcher = SOP.getFb2Author().matcher(source);
        while (matcher.find())
            eBook.getAuthors().add(extractPerson(matcher.group(1)));
        matcher = SOP.getFb2Title().matcher(source);
        if (matcher.find())
            eBook.setTitle(matcher.group(1));
        matcher = SOP.getFb2Annotation().matcher(source);
        if(matcher.find())
            eBook.setAnnotation(extractAnnotation(source));
        matcher = SOP.getFb2PublisherInfo().matcher(source);
        if (matcher.find())
            eBook.setPublisher(extractPublisher(matcher.group(1)));
        matcher = SOP.getFb2Language().matcher(source);
        if (matcher.find())
            eBook.setLanguage(matcher.group(1));
        try {
            System.out.println(inputStream.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (eBook.isDoExtractCover()) {
            matcher = SOP.getFb2Cover().matcher(source);
            if (matcher.find()) {
                matcher.group(1);
                eBook.setCover(getCover());
            }
        }
        eBook.setIsOk(true);
        return this.eBook;
    }

}
