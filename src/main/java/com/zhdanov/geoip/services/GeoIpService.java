package com.zhdanov.geoip.services;

import com.zhdanov.geoip.dto.GeoIpDto;

public interface GeoIpService {
    GeoIpDto getIpToLocation(String ip);
}
