package Utility.SourceCode;

import java.util.HashMap;

/** The Parser class analyse a string block and generates Tracker objects to store boundary information pertaining
 * to certain special regions within the string such as the areas containing String, key-words, identifiers etc.*/

public interface Parser {
    HashMap<FormattingKey, Tracker> parseString(String str);
}