package com.zhdanov.geoip.converter;

import org.apache.commons.validator.ValidatorException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class CanonicalIPv4ToIPv4ConverterTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testThatConvertsCorrect() throws ValidatorException {
        String canonicalIPv4Representation = "8.8.8.8";
        Long expected = 134744072l;

        assertEquals(expected, CanonicalIPv4ToIPv4Converter.convert(canonicalIPv4Representation));
    }

    @Test
    public void testThatThrowsExceptionIfIpIsInvalid() throws ValidatorException {
        String canonicalIPv4Representation = "266.0.0.0";

        thrown.expect(ValidatorException.class);
        thrown.expectMessage("Invalid ip address: " + canonicalIPv4Representation);

        CanonicalIPv4ToIPv4Converter.convert(canonicalIPv4Representation);
    }
}
