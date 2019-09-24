package Utility.SourceCode;

import Utility.Key;

/**
 * FormattingKey is a key that is used to categorize formatting information such as Tracker objects, Parser objects
 * etc when rendering SourceCode. Each key can only be used once so there is the provision of different unique keys
 * for independent formatting information that comes under the same category such as Key-Words or Miscellaneous.
 */

public enum FormattingKey implements Key {
    Line_Count,
    Base,
    Strings,

    Highlighting_A,
    Highlighting_B,
    Highlighting_C,

    Key_Words_A,
    Key_Words_B,
    Key_Words_C,

    Identifiers_A,
    Identifiers_B,

    Misc_A,
    Misc_B,
    Misc_C;
}