package br.com.marsalsilveira.readr.service;

import br.com.marsalsilveira.readr.exception.InvalidFileException;

import java.io.FileNotFoundException;

/**
 *
 */
public enum ServicePool {

    // we didn't use SHARED to looks like more `elegant` when call it. e.g: ServicePool.shared.service().
    shared;

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    private ReadrService _service = null;
    public ReadrService getService() {

        if (_service ==  null) {

            throw new RuntimeException("ServicePool must be initialized before use it. Please call `ServicePool.setup(service)`.");
        }

        return _service;
    }

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    ServicePool() {}

    //******************************************************************************************************************
    //* Setup | Assert
    //******************************************************************************************************************

    public void setup(ReadrService service) throws FileNotFoundException, InvalidFileException {

        _service = service;
    }
}