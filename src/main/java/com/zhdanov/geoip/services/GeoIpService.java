package com.zhdanov.geoip.services;

import com.zhdanov.geoip.dto.GeoIpDTO;
import org.apache.commons.validator.ValidatorException;

public interface GeoIpService {
    GeoIpDTO getIpToLocation(String ip) throws ValidatorException;
}
