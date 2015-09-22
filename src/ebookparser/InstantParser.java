package ebookparser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class InstantParser extends Parser {
	protected void parseFile() {
		if (SOP.fb2File.matcher(this.eBook.fileName).matches()) {
			this.eBook.format = EbookFormat.FB2;
			this.parseFb2();
		}
		if (SOP.fb2zipFile.matcher(this.eBook.fileName).matches()) {
			this.eBook.format = EbookFormat.FB2;
			this.parseFb2Zip();
		}

	}

	private void parseFb2() {
		try {
			InputStream inputStream = new FileInputStream(this.eBook.fileName);
			Fb2InstantParser parser = new Fb2InstantParser(this.eBook,
					inputStream);
			parser.parse();
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void parseFb2Zip() {
		try {
			ZipFile zipFile = new ZipFile(this.eBook.fileName);
			ZipEntry entry = zipFile.entries().nextElement();
			InputStream inputStream = zipFile.getInputStream(entry);
			Fb2InstantParser parser = new Fb2InstantParser(this.eBook,
					inputStream);
			parser.parse();
			inputStream.close();
			zipFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}