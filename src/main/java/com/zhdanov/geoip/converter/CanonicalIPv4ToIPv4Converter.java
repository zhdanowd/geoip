package com.zhdanov.geoip.converter;

import org.apache.commons.validator.ValidatorException;
import org.apache.commons.validator.routines.InetAddressValidator;

import java.util.stream.Stream;

public class CanonicalIPv4ToIPv4Converter {

    private static final int BINARY_BASE = 2;

    private CanonicalIPv4ToIPv4Converter() {
    }

    public static Long convert(final String canonicalIPv4) throws ValidatorException {
        if (InetAddressValidator.getInstance().isValidInet4Address(canonicalIPv4)) {
            final String ipv4 = Stream.of(canonicalIPv4.split("\\."))
                    .map(Integer::parseInt)
                    .map(Integer::toBinaryString)
                    .map(binaryString -> getLeadingZeros(binaryString) + binaryString)
                    .reduce("", (s1, s2) -> s1 + s2);

            return Long.parseLong(ipv4, BINARY_BASE);
        }
        throw new ValidatorException("Invalid ip address: " + canonicalIPv4);
    }

    private static String getLeadingZeros(final String binaryString) {
        return Stream.generate(() -> "0")
                .limit(8 - binaryString.length())
                .reduce("", (s1, s2) -> s1 + s2);
    }
}
