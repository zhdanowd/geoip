package com.zhdanov.exception;

public class InvalidIPv4AddressException extends RuntimeException {
    public InvalidIPv4AddressException(final String ip) {
        super("Invalid ip address: " + ip);
    }
}
