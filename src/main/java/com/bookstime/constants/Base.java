package com.bookstime.constants;

public class Base {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer";
    public static final String EMPTY_SPACE = " ";
    public static final String START_PROMPT_PART = "Here is an email:";
    public static final String END_PROMPT_PART = "Completely rewrite it to fix all grammar, spelling, and stylistic mistakes. Make sure the rewritten email is clear, well-written, and succinct." +
        " Ensure the tone is professional, collegial, friendly and positive. Make sure the email is structured and formatted well. Don't change the underlying meaning." +
        " Don't modify any quotations or numbers. Don't include the email signature section at the bottom. Provide your answer in .txt format.";
    public static final double TEMPERATURE = 0;
    public static final int MAX_TOKENS = 3000;
    public static final double FREQUENCY_PENALTY = 2;
    public static final double PRESENCE_PENALTY = -2;
    public static final int AVERAGE_CHARS_IN_TOKEN = 4;
    public static final int MIN_DRAFT_SIZE = 50;
    public static final int MAX_DRAFT_SIZE = MAX_TOKENS * AVERAGE_CHARS_IN_TOKEN;
}
