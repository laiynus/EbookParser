package ebookparser.parser.full;

import ebookparser.book.children.Chapter;
import ebookparser.book.Ebook;
import ebookparser.book.children.Person;
import ebookparser.book.children.Publisher;
import ebookparser.other.SOP;
import ebookparser.parser.Base64Decoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;

public class FullParserFb2 {

    private final int MAX_XMLINFO_SIZE = 80;
    private final int MAX_FB2_SIZE = 2097152;
    private Ebook eBook;
    private String source;
    private InputStream input;


    FullParserFb2(Ebook eBook, InputStream input) throws IOException {
        this.eBook = eBook;
        this.source = this.createSource(input);
        this.input = input;
    }

    private String createSource(InputStream stream) throws IOException, NullPointerException {
        byte[] buffer = readInputStream(stream);
        eBook.setEncoding(this.getXmlEncoding(buffer));
        String preparedInput = new String(buffer, eBook.getEncoding());
        Matcher matcher = SOP.getFb2Annotation().matcher(preparedInput);
        if (matcher.find()) {
            eBook.setAnnotation(extractAnotation(matcher.group(1)));
            preparedInput = matcher.replaceFirst("");
        }
        return preparedInput;
    }

    private String extractAnotation(String input) {
        Matcher matcher;
        String anotation = new String();
        matcher = SOP.getFb2P().matcher(input);
        while (matcher.find())
            anotation += matcher.group(1).trim() + "\n";
        return anotation;
    }

    private byte[] readInputStream(InputStream input) throws IOException {
        byte[] buffer = new byte[MAX_FB2_SIZE];
        int counter = 0;
        int amount = 0;
        int stopCounter = 0;
        boolean stop = false;
        while (!stop & (amount < MAX_FB2_SIZE) && (counter != -1)) {
            counter = input.read(buffer, amount, MAX_FB2_SIZE - amount);
            amount += counter;
            while (stopCounter < amount) {
                if (buffer[stopCounter] == '>')
                    if (buffer[stopCounter - 1] == 'y')
                        if (buffer[stopCounter - 6] == '<')
                            if (buffer[stopCounter - 4] == 'b') {
                                stop = true;
                                break;
                            }
                stopCounter++;
            }
        }
        if (amount <= 0)
            throw new IOException("Epmty input stream");
        byte[] output = new byte[stopCounter];
        System.arraycopy(buffer, 0, output, 0, stopCounter);
        return output;
    }

    private String getXmlEncoding(byte[] input) throws IOException {
        String encoding = null;
        String xmlHeader = new String(input, 0, MAX_XMLINFO_SIZE, "ISO-8859-1");
        Matcher matcher = SOP.getXmlEncoding().matcher(xmlHeader.toString());
        if (matcher.find())
            encoding = matcher.group(1);
        else
            throw new IOException("Unknown encoding");
        return encoding;
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

    private Chapter extractListChapters(String input, int i) {
        Matcher matcher;
        Chapter chapter = new Chapter();
        matcher = SOP.getFb2ChapterTitle().matcher(input);
        if (matcher.find())
            chapter.setName(matcher.group(1).trim());
        chapter.setNumber(i);
        return chapter;
    }


    protected void parse() {
        Matcher matcher;
        matcher = SOP.getFb2Author().matcher(source);
        while (matcher.find())
            eBook.getAuthors().add(extractPerson(matcher.group(1)));
        matcher = SOP.getFb2Title().matcher(source);
        if (matcher.find())
            eBook.setTitle(matcher.group(1));
        matcher = SOP.getFb2Publisher().matcher(source);
        if (matcher.find())
            eBook.setPublisher(extractPublisher(matcher.group(1)));
        matcher = SOP.getFb2genre().matcher(source);
        while (matcher.find())
            eBook.getFb2Genres().add(matcher.group(1));
        matcher = SOP.getFb2Language().matcher(source);
        if (matcher.find())
            eBook.setLanguage(matcher.group(1));
        matcher = SOP.getFb2ChapterTitle().matcher(source);
        if (matcher.find()) {
            String sequence = matcher.group(1);
            matcher = SOP.getFb2SequenceName().matcher(sequence);
            if (matcher.find())
                eBook.setSequenceName(matcher.group(1));
            matcher = SOP.getFb2SequenceNumber().matcher(sequence);
            if (matcher.find())
                eBook.setSequenceNumber(matcher.group(1));
        }
        if (eBook.isDoExtractCover() == true) {
            matcher = SOP.getFb2CoverName().matcher(source);
            if (matcher.find()) {
                matcher.group(1);
                eBook.setCover(getCover());
            }
        }
        if (eBook.getCurrentChapter().getNumber() != -1) {
            if (eBook.getCurrentChapter().getNumber() == 0) {
                matcher = SOP.getFb2Section().matcher(source);
                int i = 0;
                while (matcher.find()) {
                    i++;
                    eBook.getListChapters().add(extractListChapters(matcher.group(1), i));
                }
            } else {
                eBook.setCurrentChapter(setChapter(eBook.getCurrentChapter().getNumber()));
                matcher = SOP.getFb2Section().matcher(source);
                int i = 0;
                while (matcher.find()) {
                    i++;
                    eBook.getListChapters().add(extractListChapters(matcher.group(1), i));
                }
            }

        }

        eBook.setIsOk(true);
    }


    public Chapter setChapter(int numberChapter) {
        Chapter chapter = new Chapter();
        Matcher matcher;
        matcher = SOP.getFb2Section().matcher(source);
        int i = 0;
        while (matcher.find()) {
            chapter.setTextChapter(matcher.group(1).trim());
            i++;
            chapter.setNumber(i);
            if (i == numberChapter) break;
        }
        return chapter;
    }


    private byte[] getCover() {
        // int q = this.input.available();
        byte[] buffer = new byte[MAX_FB2_SIZE];
        byte[] cover64;
        int amount = 0;
        int count = 0;
        try {
            while ((amount < MAX_FB2_SIZE) && (count != -1)) {
                count = this.input.read(buffer, amount, MAX_FB2_SIZE - amount);
                if (count != -1)
                    amount += count;
            }
        } catch (IOException e) {
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
}
