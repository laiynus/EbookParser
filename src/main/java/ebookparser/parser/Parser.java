package ebookparser.parser;

import ebookparser.book.EBook;

public interface Parser {
    EBook toParse(String fileName);
}
