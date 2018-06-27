package com.zhdanov.geoip.validator;

public class IPv4Validator {

    private static final int ZERO = 0;
    private static final long MAX_IP = 4294967295L;
    private static final String ONLY_DIGITS = "[0-9]+";
    private static final String DIGITS_SEPARATED_WITH_DOTS = "^\\d+(\\.\\d+)*$";
    private static final String DOT_REGEX = "\\.";
    private static final int MAX_OCTETS_NUMBER = 4;
    private static final int MAX_OCTET = 255;
    private static final int MAX_IP_LENGTH = 10;
    private static final int MAX_DECIMAL_OCTET_LENGTH = 3;

    private IPv4Validator(){}

    public static boolean isValid(final String ip) {
        if (ip.matches(ONLY_DIGITS) && ip.length() <= MAX_IP_LENGTH) {
            final Long decimalIp = Long.parseLong(ip);
            return decimalIp >= ZERO && decimalIp <= MAX_IP;
        }
        if (ip.matches(DIGITS_SEPARATED_WITH_DOTS)) {
            final String[] decimalOctets = ip.split(DOT_REGEX);
            return areOctetsValid(decimalOctets);
        }
        return false;
    }

    private static boolean areOctetsValid(final String[] decimalOctets) {
        if (decimalOctets.length > MAX_OCTETS_NUMBER) {
            return false;
        }
        for (String octet : decimalOctets) {
            if (octet.length() > MAX_DECIMAL_OCTET_LENGTH) {
                return false;
            }
            final int decimalOctet = Integer.parseInt(octet);
            if (decimalOctet < ZERO || decimalOctet > MAX_OCTET) {
                return false;
            }
        }
        return true;
    }
}
