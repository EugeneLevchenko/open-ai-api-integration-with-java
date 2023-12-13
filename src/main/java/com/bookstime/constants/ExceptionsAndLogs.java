package com.bookstime.constants;

public class ExceptionsAndLogs {
    public static final String SEND_REQUEST_IMPROVE_DRAFT_ERROR_LOG = "Exception occurred while sending request [improve user's draft] with message: {}";
    public static final String SEND_REQUEST_IMPROVE_DRAFT_ERROR_MESSAGE = "Unfortunately our service encountered with the problem or is unavailable now, please wait some time and try again!";
    public static final String INCORRECT_DRAFT_SIZE_ERROR_MESSAGE = "Please, specify correct size of your draft, available range in characters is: [min = 50, max = 12000]";
    public static final String SEND_REQUEST_IMPROVE_DRAFT_SUCCESS_LOG = "Request to OpenAI API was successfully sent";
    public static final String NOT_BLANK_DRAFT_MESSAGE = "Please, provide not empty draft";
}
