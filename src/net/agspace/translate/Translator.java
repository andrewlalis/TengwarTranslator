package net.agspace.translate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by Andrew's Computer on 23-Apr-17.
*/
public class Translator {

    private enum CHAR_TYPE {
        CONSONANT,
        COMPOUND,
        ALTERNATE,
        VOWEL,
        NUMBER,
        CARRIER,
        PUNCTUATION,
        BAR,
        S_CURL,
        UNKNOWN,
    }

    //Consonant variables.
    private static final char B = 'w';
    private static final char C = 'c';
    private static final char D = '2';
    private static final char F = 'e';
    private static final char G = 'x';
    private static final char H = '9';
    private static final char J = 's';
    private static final char K = 'z';
    private static final char L = 'j';
    private static final char M = 't';
    private static final char N = '5';
    private static final char P = 'q';
    private static final char Q = 'n';
    private static final char R = '6';
    private static final char S = '8';
    private static final char T = '1';
    private static final char V = 'r';
    private static final char W = 'y';
    private static final char X = '¦';
    private static final char Y = 'h';
    private static final char Z = 'k';

    //Compound chars.
    private static final char TH = '3';
    private static final char CH = 'a';
    private static final char SH = 'd';
    private static final char ZH = 'f';
    private static final char GH = 'v';
    private static final char WH = 'o';
    private static final char CK = 'Z';
    private static final char NY = 'g';
    private static final char NG = 'b';
    private static final char RD = 'u';
    private static final char LD = 'm';

    //Vowels.
    private static final char A_1 = '#';
    private static final char A_2 = 'E';
    private static final char A_3 = 'D';
    private static final char A_4 = 'C';
    private static final char E_1 = '$';
    private static final char E_2 = 'R';
    private static final char E_3 = 'F';
    private static final char E_4 = 'V';
    private static final char E_UNDER_1 = 'È';
    private static final char E_UNDER_2 = 'É';
    private static final char E_UNDER_3 = 'Ê';
    private static final char E_UNDER_4 = 'Ë';
    private static final char E_LAMBE = 'L';
    private static final char I_1 = '%';
    private static final char I_2 = 'T';
    private static final char I_3 = 'G';
    private static final char I_4 = 'B';
    private static final char O_1 = '^';
    private static final char O_2 = 'Y';
    private static final char O_3 = 'H';
    private static final char O_4 = 'N';
    private static final char U_1 = '&';
    private static final char U_2 = 'U';
    private static final char U_3 = 'J';
    private static final char U_4 = 'M';

    //Carriers.
    private static final char CARRIER_LONG = '~';
    private static final char CARRIER_SHORT = '`';

    //Numbers.
    private static final char NUMBER_0 = 'ð';
    private static final char NUMBER_1 = 'ñ';
    private static final char NUMBER_2 = 'ò';
    private static final char NUMBER_3 = 'ó';
    private static final char NUMBER_4 = 'ô';
    private static final char NUMBER_5 = 'õ';
    private static final char NUMBER_6 = 'ö';
    private static final char NUMBER_7 = '÷';
    private static final char NUMBER_8 = 'ø';
    private static final char NUMBER_9 = 'ù';

    //Punctuation
    private static final char PERIOD = '-';
    private static final char COMMA = '=';
    private static final char EXCLAMATION = 'Á';
    private static final char QUESTION = 'À';
    private static final char SEMICOLON = 'Ã';
    private static final char COLON = 'ˆ';
    private static final char PARENTHESES_RIGHT = 'œ';
    private static final char PARENTHESES_LEFT = 'Œ';
    private static final char QUOTE = '«';
    private static final char APOSTROPHE = '±';
    private static final char SPACE = ' ';
    private static final char EQUALS = '¬';
    private static final char MINUS = '\\';
    private static final char NEWLINE = '\n';

    //Doubles Bars
    private static final char BAR_SHORT_TOP = '[';
    private static final char BAR_SHORT_BOTTOM = '\'';
    private static final char BAR_LONG_TOP = '{';
    private static final char BAR_LONG_BOTTOM = '"';

    //Alternate forms.
    private static final char S_ALT = 'i';
    private static final char Z_ALT = ',';
    private static final char W_ALT = '.';
    private static final char R_ALT = '7';
    private static final char Y_ALT = 'l';

    //S-Curls
    private static final char S_CURL_1 = '+';
    private static final char S_CURL_2 = '_';
    private static final char S_CURL_3 = '¢';

    //Mappings of english chars to tengwars.
    private static final Map<Character, Character> consonantChars = new HashMap<>();
    static {
        consonantChars.put('b', B);
        consonantChars.put('c', C);
        consonantChars.put('d', D);
        consonantChars.put('f', F);
        consonantChars.put('g', G);
        consonantChars.put('h', H);
        consonantChars.put('j', J);
        consonantChars.put('k', K);
        consonantChars.put('l', L);
        consonantChars.put('m', M);
        consonantChars.put('n', N);
        consonantChars.put('p', P);
        consonantChars.put('q', Q);
        consonantChars.put('r', R);
        consonantChars.put('s', S);
        consonantChars.put('t', T);
        consonantChars.put('v', V);
        consonantChars.put('w', W);
        consonantChars.put('x', X);
        consonantChars.put('y', Y);
        consonantChars.put('z', Z);
    }

    //Mapping for compound chars.
    private static final Map<String, Character> compoundChars = new HashMap<>();
    static {
        compoundChars.put("th", TH);
        compoundChars.put("ch", CH);
        compoundChars.put("sh", SH);
        compoundChars.put("zh", ZH);
        compoundChars.put("gh", GH);
        compoundChars.put("wh", WH);
        compoundChars.put("ck", CK);
        compoundChars.put("ny", NY);
        compoundChars.put("ng", NG);
        compoundChars.put("rd", RD);
        compoundChars.put("ld", LD);
    }

    //Mapping consonants to alternates.
    private static final Map<Character, Character> alternateChars = new HashMap<>();
    static {
        alternateChars.put('s', S_ALT);
        alternateChars.put('z', Z_ALT);
        alternateChars.put('w', W_ALT);
        //alternateChars.put('r', R_ALT); R alt is used if followed by a vowel.
        alternateChars.put('y', Y_ALT);
    }

    //Mapping for numbers. No vowel size needed.
    private static final Map<Character, Character> numberChars = new HashMap<>();
    static {
        numberChars.put('0', NUMBER_0);
        numberChars.put('1', NUMBER_1);
        numberChars.put('2', NUMBER_2);
        numberChars.put('3', NUMBER_3);
        numberChars.put('4', NUMBER_4);
        numberChars.put('5', NUMBER_5);
        numberChars.put('6', NUMBER_6);
        numberChars.put('7', NUMBER_7);
        numberChars.put('8', NUMBER_8);
        numberChars.put('9', NUMBER_9);
    }

    //Mapping for punctuation. No vowel size needed.
    private static final Map<Character, Character> punctuationChars = new HashMap<>();
    static {
        punctuationChars.put('.', PERIOD);
        punctuationChars.put(',', COMMA);
        punctuationChars.put('!', EXCLAMATION);
        punctuationChars.put('?', QUESTION);
        punctuationChars.put(';', SEMICOLON);
        punctuationChars.put(':', COLON);
        punctuationChars.put(')', PARENTHESES_RIGHT);
        punctuationChars.put('(', PARENTHESES_LEFT);
        punctuationChars.put('"', QUOTE);
        punctuationChars.put('\'', APOSTROPHE);
        punctuationChars.put(' ', SPACE);
        punctuationChars.put('=', EQUALS);
        punctuationChars.put('-', MINUS);
        punctuationChars.put('\n', NEWLINE);
    }

    //Mapping for vowels. Each value is an array, with A_1 to A_4 in indices 0 - 3.
    private static final Map<Character, Character[]> vowelChars = new HashMap<>();
    static {
        vowelChars.put('a', new Character[]{A_1, A_2, A_3, A_4});
        vowelChars.put('e', new Character[]{E_1, E_2, E_3, E_4, E_UNDER_1, E_UNDER_2, E_UNDER_3, E_UNDER_4});
        vowelChars.put('i', new Character[]{I_1, I_2, I_3, I_4});
        vowelChars.put('o', new Character[]{O_1, O_2, O_3, O_4});
        vowelChars.put('u', new Character[]{U_1, U_2, U_3, U_4});
    }

    //For the characters which allow a vowel above them, this tells which vowel type to use.
    private static final Map<Character, Integer> charSizes = new HashMap<>();
    static {
        charSizes.put(B, 1);
        charSizes.put(C, 2);
        charSizes.put(D, 1);
        charSizes.put(F, 3);
        charSizes.put(G, 1);
        charSizes.put(H, 3);
        charSizes.put(J, 1);
        charSizes.put(K, 2);
        charSizes.put(L, 1);
        charSizes.put(M, 1);
        charSizes.put(N, 1);
        charSizes.put(P, 2);
        charSizes.put(Q, 2);
        charSizes.put(R, 2);
        charSizes.put(S, 1);
        charSizes.put(T, 2);
        charSizes.put(V, 1);
        charSizes.put(W, 2);
        charSizes.put(X, 1);
        charSizes.put(Y, 2);
        charSizes.put(Z, 1);
        charSizes.put(S_ALT, 2);
        charSizes.put(Z_ALT, 2);
        charSizes.put(W_ALT, 2);
        charSizes.put(R_ALT, 2);
        charSizes.put(Y_ALT, 2);
        charSizes.put(TH, 3);
        charSizes.put(CH, 2);
        charSizes.put(SH, 2);
        charSizes.put(ZH, 1);
        charSizes.put(GH, 1);
        charSizes.put(WH, 1);
        charSizes.put(CK, 1);
        charSizes.put(NY, 1);
        charSizes.put(NG, 1);
        charSizes.put(RD, 2);
        charSizes.put(LD, 1);
        charSizes.put(CARRIER_LONG, 4);
        charSizes.put(CARRIER_SHORT, 4);
    }

    //Sets bar preferences, 0 for long top, 1 for long bottom, 2 for short top, 3 for short bottom.
    private static final Map<Character, Integer> barSizes = new HashMap<>();
    static {
        barSizes.put(B, 0);
        barSizes.put(C, 2);
        barSizes.put(D, 1);
        barSizes.put(F, 2);
        barSizes.put(G, 1);
        barSizes.put(H, 1);
        barSizes.put(J, 0);
        barSizes.put(K, 2);
        barSizes.put(L, 1);
        barSizes.put(M, 0);
        barSizes.put(N, 0);
        barSizes.put(P, 3);
        barSizes.put(Q, 3);
        barSizes.put(R, 2);
        barSizes.put(S, 1);
        barSizes.put(T, 3);
        barSizes.put(V, 0);
        barSizes.put(W, 2);
        barSizes.put(X, 0);
        barSizes.put(Y, 2);
        barSizes.put(Z, 1);
        barSizes.put(S_ALT, 0);
        barSizes.put(Z_ALT, 1);
        barSizes.put(W_ALT, 2);
        barSizes.put(R_ALT, 2);
        barSizes.put(Y_ALT, 2);
    }

    //List of s-curls.
    private static final List<Character> sCurls = new ArrayList<>();
    static{
        sCurls.add(S_CURL_1);
        sCurls.add(S_CURL_2);
        sCurls.add(S_CURL_3);
    }

    //List of carriers.
    private static final List<Character> carriers = new ArrayList<>();
    static {
        carriers.add(CARRIER_LONG);
        carriers.add(CARRIER_SHORT);
    }

    //List of bars.
    private static final List<Character> bars = new ArrayList<>();
    static {

        bars.add(BAR_LONG_TOP);
        bars.add(BAR_LONG_BOTTOM);
        bars.add(BAR_SHORT_TOP);
        bars.add(BAR_SHORT_BOTTOM);
    }

    /**
     * Gives the properly sized vowel for a particular tengwar character.
     * Uses indices from the charSizes map to find the proper character.
     * @param tengwarChar tengwar char to be checked.
     * @param englishVowel the english vowel type to be returned.
     * @return tengwar vowel char corresponding to the letter, or default of left-most.
     */
    private static char getAppropriateVowel(char tengwarChar, char englishVowel){
        if (charSizes.containsKey(tengwarChar)){
            return vowelChars.get(englishVowel)[charSizes.get(tengwarChar)-1];
        } else {
            return vowelChars.get(englishVowel)[0];
        }
    }

    /**
     * Gives the properly sized silent e vowel for a particular character.
     * @param tengwarChar tengwar char to be checked.
     * @return silent e vowel corresponding to the letter, or default of left-most.
     */
    private static char getAppropriateSilentE(char tengwarChar){
        if (tengwarChar == L){
            return E_LAMBE;
        }
        if (charSizes.containsKey(tengwarChar)){
            return vowelChars.get('e')[charSizes.get(tengwarChar)+3];
        } else {
            return vowelChars.get('e')[4];
        }
    }

    /**
     * Determines if a character is a vowel.
     * @param c english character.
     * @return true if vowel, false if not.
     */
    private static boolean isVowel(char c){
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }

    /**
     * Translate an english string to tengwar.
     * @param englishString english string.
     * @return tengwar string.
     */
    public static String translateToTengwar(String englishString){
        StringBuilder result = new StringBuilder(englishString.length());
        String input = englishString.toLowerCase().trim();

        for (int i = 0; i < input.length(); i++){
            //Attempt to get previous, current, and next char.
            char currentChar = input.charAt(i);
            char previousChar = 0;
            char nextChar = 0;
            try{
                previousChar = input.charAt(i-1);
            } catch (IndexOutOfBoundsException e){
            }
            try{
                nextChar = input.charAt(i+1);
            } catch (IndexOutOfBoundsException e){
            }
            //Test if the current char is a vowel.
            if (isVowel(currentChar)){
                if (consonantChars.containsKey(nextChar)){
                    //If the next character can have a vowel placed on it, then go here.
                    //Temporary storage for the character that will be added before the vowel.
                    char tengwarCharToBeAdded;
                    boolean needsBar = false;
                    char secondNextChar = 0;
                    //Check if the next char is a compound first.
                    try{
                        secondNextChar = input.charAt(i+2);
                    } catch (IndexOutOfBoundsException e){
                        //Do nothing. second next char doesn't exist.
                    }
                    if (compoundChars.containsKey(""+nextChar+secondNextChar)) {
                        tengwarCharToBeAdded = compoundChars.get("" + nextChar + secondNextChar);
                        i++;
                    } else if (alternateChars.containsKey(nextChar)){
                        //Not a compound, so now check for alternates.
                        tengwarCharToBeAdded = alternateChars.get(nextChar);
                    } else {
                    //Finally, add a literal consonant.
                        tengwarCharToBeAdded = consonantChars.get(nextChar);
                    }
                    //Check to see if a bar should be added, and the next character skipped.
                    if (nextChar == secondNextChar){
                        //Bar should be added.
                        needsBar = true;
                        i++;
                    }
                    result.append(tengwarCharToBeAdded);
                    result.append(getAppropriateVowel(tengwarCharToBeAdded, currentChar));
                    if (needsBar)
                        result.append(bars.get(barSizes.get(tengwarCharToBeAdded)));
                    i++;
                } else {
                    //The next character is not able to have a vowel placed on it, so use a carrier.
                    if (currentChar == 'e' && (i == input.length()-1 || punctuationChars.containsKey(nextChar))){
                        //Use a silent e because the next char is a punctuation or the end of the string.
                        try{
                            result.append(getAppropriateSilentE(result.charAt(result.length()-1)));
                        } catch (IndexOutOfBoundsException e){
                            result.append(E_UNDER_1);
                        }
                        continue;
                    }
                    if (currentChar == nextChar){
                        //Add a long carrier, and skip the next character, because it is the same.
                        result.append(CARRIER_LONG);
                        result.append(getAppropriateVowel(CARRIER_LONG, currentChar));
                        i++;
                    } else {
                        //Add a short carrier.
                        result.append(CARRIER_SHORT);
                        result.append(getAppropriateVowel(CARRIER_SHORT, currentChar));
                    }
                }
                //Done with vowel checking.
            } else {
                //Character is not a vowel, so consonant, compound, number, or punctuation.
                if (compoundChars.containsKey(""+currentChar+nextChar)){
                    //current char and next char are a compound, so add it and skip the next iteration.
                    result.append(compoundChars.get(""+currentChar+nextChar));
                    i++;
                } else if (isVowel(nextChar) && currentChar == 'r') {
                    //Special case for r vowel.
                    result.append(R_ALT);
                } else {
                    //Character can be literally translated from numbers or consonants or punctuation.
                    if (consonantChars.containsKey(currentChar)) {
                        result.append(consonantChars.get(currentChar));
                        if (currentChar == nextChar) {
                            //double consonant found, add a bar.
                            result.append(bars.get(barSizes.get(consonantChars.get(currentChar))));
                            i++;
                        }
                    } else if (punctuationChars.containsKey(currentChar)) {
                        result.append(punctuationChars.get(currentChar));
                    } else if (numberChars.containsKey(currentChar)) {
                        result.append(numberChars.get(currentChar));
                    }
                }
            }
        }
        return result.toString();
    }
}
