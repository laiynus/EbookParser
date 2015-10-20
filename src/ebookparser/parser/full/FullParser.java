package ebookparser.parser.full;

import ebookparser.other.EbookFormat;
import ebookparser.other.SOP;
import ebookparser.parser.Parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FullParser extends Parser {

    /**
     * Filled fields of the EBook with
     * meta-information of the file
     */
    protected void parseFile() {
        if (SOP.fb2File.matcher(eBook.getFileName()).matches()) {
            eBook.setFormat(EbookFormat.FB2);
            parseFb2();
        }
        if (SOP.fb2zipFile.matcher(eBook.getFileName()).matches()) {
            eBook.setFormat(EbookFormat.FB2);
            parseFb2Zip();
        }

    }

    /**
     * Called from parseFile() if the file is fb2
     */
    private void parseFb2() {
        try {
            InputStream inputStream = new FileInputStream(this.eBook.getFileName());
            FullParserFb2 parser = new FullParserFb2(this.eBook, inputStream);
            parser.parse();
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Called from parseFile() if the file is fb2 in zip
     */
    private void parseFb2Zip() {
        try {
            ZipFile zipFile = new ZipFile(this.eBook.getFileName());
            ZipEntry entry = zipFile.entries().nextElement();
            InputStream inputStream = zipFile.getInputStream(entry);
            FullParserFb2 parser = new FullParserFb2(this.eBook, inputStream);
            parser.parse();
            inputStream.close();
            zipFile.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}