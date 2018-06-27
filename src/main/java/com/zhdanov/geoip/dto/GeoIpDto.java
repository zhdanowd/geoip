package com.zhdanov.geoip.dto;

import com.zhdanov.geoip.entity.IpToLocation;
import lombok.Data;

@Data
public class GeoIpDto {
    private String canonicalIPv4Representation;
    private String cityName;
    private String countryCode;
    private String countryName;
    private Long IPv4;
    private Double latitude;
    private Double longitude;
    private String regionName;

    public GeoIpDto(String canonicalIPv4, Long decimalIPv4, IpToLocation ipToLocation) {
        this.canonicalIPv4Representation = canonicalIPv4;
        this.cityName = ipToLocation.getCityName();
        this.countryCode = ipToLocation.getCountryCode();
        this.countryName = ipToLocation.getCountryName();
        this.IPv4 = decimalIPv4;
        this.latitude = ipToLocation.getLatitude();
        this.longitude = ipToLocation.getLongitude();
        this.regionName = ipToLocation.getRegionName();
    }
}
