package br.com.marsalsilveira.readr.service.command;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CommandResponse {

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    private final List<String> _messages;
    public List<String> messages() { return _messages; }

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    public CommandResponse() {

        _messages = new ArrayList<>();
    }

    public CommandResponse(List<String> messages) {

        this();
        _messages.addAll(messages);
    }

    //******************************************************************************************************************
    //* Utils
    //******************************************************************************************************************

    public void addMessage(String message) {

        _messages.add(message);
    }
}