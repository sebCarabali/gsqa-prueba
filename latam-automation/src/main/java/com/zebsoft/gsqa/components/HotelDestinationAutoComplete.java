package com.zebsoft.gsqa.components;

import com.microsoft.playwright.Page;

public class HotelDestinationAutoComplete extends AutoComplete {

    private static final String HOTEL_INPUT_SELECTOR = "input[role='combobox']";
    private static final String HOTEL_OPTIONS_LIST_SELECTOR = "#hotel-searchbox-destination-autocompleteinput--autocomplete__list";
    private static final String HOTEL_OPTION_SELECTOR = "li[role='option']";    

    public HotelDestinationAutoComplete(Page page) {
        super(page, HOTEL_INPUT_SELECTOR, HOTEL_OPTIONS_LIST_SELECTOR, HOTEL_OPTION_SELECTOR);
    }
}
