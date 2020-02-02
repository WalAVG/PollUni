package controller;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gigan
 */
public class CasePattern {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_WORD_ADDRESS_REGEX = 
    Pattern.compile("^[a-zA-Z0-9,.!? ]*$", Pattern.CASE_INSENSITIVE);
    
    public static final Pattern VALID_PHRASE_ADDRESS_REGEX = 
    Pattern.compile("^[a-zA-Z0-9,.!? ]*$", Pattern.CASE_INSENSITIVE);
    

    public static boolean validateEmail (String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
    
    public static boolean validateWord (String word) {
        Matcher matcher = VALID_WORD_ADDRESS_REGEX .matcher(word);
        return matcher.find();
    }
    
    public static boolean validatePhrase (String phrase) {
        Matcher matcher = VALID_PHRASE_ADDRESS_REGEX .matcher(phrase);
        return matcher.find();
    }
}
