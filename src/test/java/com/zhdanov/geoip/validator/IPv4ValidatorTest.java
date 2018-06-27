package com.zhdanov.geoip.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(JUnit4.class)
public class IPv4ValidatorTest {

    @Test
    public void testThatDecimalIpIsValid() {
        assertTrue(IPv4Validator.isValid("432432234"));
    }

    @Test
    public void testThatCanonicalIpIsValid() {
        assertTrue(IPv4Validator.isValid("123.123.123.123"));
    }


    @Test
    public void testThatNegativeDecimalIpIsInvalid() {
        assertFalse(IPv4Validator.isValid("-2134"));
    }

    @Test
    public void testThatDecimalIpBiggerThanMaxIpIsInvalid(){
        assertFalse(IPv4Validator.isValid("4294967296"));
    }

    @Test
    public void testThatIpWithMoreThenFourOctetsIsInvalid() {
        assertFalse(IPv4Validator.isValid("3.3.3.3.3"));
    }

    @Test
    public void testThatIpWithOctetThatIsBiggerThenMaxIsInvalid() {
        assertFalse(IPv4Validator.isValid("300.23.32.33"));
    }

}
