package com.zhdanov.geoip.converter;

import com.zhdanov.exception.InvalidIPv4AddressException;
import com.zhdanov.geoip.validator.IPv4Validator;

import java.util.stream.Stream;

public class IPv4Converter {

    private static final String ZERO = "0";
    private static final String DOT_REGEX = "\\.";
    private static final String EMPTY_STRING = "";
    private static final String DOT = ".";
    private static final int BINARY_BASE = 2;
    private static final int OCTET_LENGTH = 8;
    private static final int BINARY_IP_LENGTH = 32;

    private IPv4Converter() {
    }

    public static Long convertToDecimalRepresentation(final String canonicalIPv4) {
        final String ipv4 = Stream.of(canonicalIPv4.split(DOT_REGEX))
                .map(Integer::parseInt)
                .map(Integer::toBinaryString)
                .map(binaryOctet -> generateLeadingZeros(binaryOctet.length(), OCTET_LENGTH) + binaryOctet)
                .reduce(EMPTY_STRING, (s1, s2) -> s1 + s2);

        return Long.parseLong(ipv4, BINARY_BASE);
    }

    public static String convertToCanonicalRepresentation(final String ip) {
        if (!IPv4Validator.isValid(ip)) {
            throw new InvalidIPv4AddressException(ip);
        }

        if (!ip.contains(DOT)) {
            return convertDecimalToCanonicalIPv4(ip);
        }

        String[] octets = ip.split(DOT_REGEX);
        if (octets.length < 4) {
            octets = addMissingOctets(octets);
        }

        return convertDecimalOctetsToCanonicalIPv4(octets);

    }

    private static String convertDecimalToCanonicalIPv4(String ip) {
        final Long decimalIPv4 = Long.parseLong(ip);
        final String binaryIPv4 = Long.toBinaryString(decimalIPv4);
        final String fullBinaryIPv4 = generateLeadingZeros(binaryIPv4.length(), BINARY_IP_LENGTH)+ binaryIPv4;
        final String[] octets = getOctets(fullBinaryIPv4);

        return convertBinaryOctetsToCanonicalIPv4(octets);
    }

    private static String generateLeadingZeros(final int actualLength, final int requiredLength) {
        return Stream.generate(() -> ZERO)
                .limit(requiredLength - actualLength)
                .reduce(EMPTY_STRING, (s1, s2) -> s1 + s2);
    }

    private static String[] addMissingOctets(final String[] octets) {
        final String[] fullOctets = new String[4];
        fullOctets[3] = octets[octets.length - 1];
        fullOctets[2] = ZERO;
        fullOctets[1] = octets.length == 2 ? ZERO : octets[1];
        fullOctets[0] = octets[0];

        return fullOctets;
    }


    private static String convertBinaryOctetsToCanonicalIPv4(final String[] octets) {
        return Stream.of(octets)
                .map(octet -> Integer.parseInt(octet, BINARY_BASE))
                .map(octet -> octet.toString())
                .reduce((s1, s2) -> s1 + DOT + s2).get();
    }

    private static String convertDecimalOctetsToCanonicalIPv4(final String[] octets) {
        return Stream.of(octets)
                .reduce((s1, s2) -> s1 + DOT + s2).get();
    }

    private static String[] getOctets(final String binaryIPv4) {
        final String[] octets = new String[4];
        for (int i = 0; i < 4; i++) {
            octets[i] = binaryIPv4.substring(i * OCTET_LENGTH, i * OCTET_LENGTH + OCTET_LENGTH);
        }
        return octets;
    }

}
