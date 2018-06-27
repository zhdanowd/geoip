package com.zhdanov.geoip.converter;

import com.zhdanov.exception.InvalidIPv4AddressException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class IPv4ConverterTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testThatConvertToDecimalRepresentation() {
        final String canonicalIPv4Representation = "8.8.8.8";
        final Long expected = 134744072L;

        assertEquals(expected, IPv4Converter.convertToDecimalRepresentation(canonicalIPv4Representation));
    }

    @Test
    public void testThatConvertMinCanonicalToDecimalRepresentation() {
        final String canonicalIPv4Representation = "0.0.0.0";
        final Long expected = 0L;

        assertEquals(expected, IPv4Converter.convertToDecimalRepresentation(canonicalIPv4Representation));
    }

    @Test
    public void testThatConvertMaxCanonicalToDecimalRepresentation() {
        final String canonicalIPv4Representation = "255.255.255.255";
        final Long expected = 4294967295L;

        assertEquals(expected, IPv4Converter.convertToDecimalRepresentation(canonicalIPv4Representation));
    }

    @Test
    public void testThatThrowsExceptionIfIpIsInvalidWhenConvertToCanonicalRepresentation() {
        final String ip = "266.0.0.0";

        thrown.expect(InvalidIPv4AddressException.class);
        thrown.expectMessage("Invalid ip address: " + ip);

        IPv4Converter.convertToCanonicalRepresentation(ip);
    }


    @Test
    public void testThatConvertMaxDecimalIpToCanonicalRepresentation() {
        final String decimal = "4294967295";
        final String canonical = "255.255.255.255";

        assertEquals(canonical, IPv4Converter.convertToCanonicalRepresentation(decimal));
    }
    @Test
    public void testThatConvertMinDecimalIpToCanonicalRepresentation() {
        final String decimal = "0";
        final String canonical = "0.0.0.0";

        assertEquals(canonical, IPv4Converter.convertToCanonicalRepresentation(decimal));
    }

    @Test
    public void testThatConvertIpWithoutOneOctetToCanonicalRepresentation() {
        final String decimal = "0.3.233";
        final String canonical = "0.3.0.233";

        assertEquals(canonical, IPv4Converter.convertToCanonicalRepresentation(decimal));

    }

    @Test
    public void testThatConvertIpWithoutToOctetsToCanonicalRepresentation() {
        final String decimal = "255.233";
        final String canonical = "255.0.0.233";

        assertEquals(canonical, IPv4Converter.convertToCanonicalRepresentation(decimal));

    }
}
