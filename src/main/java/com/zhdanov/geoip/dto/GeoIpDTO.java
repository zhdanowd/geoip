package com.zhdanov.geoip.dto;

import com.zhdanov.geoip.entity.IpToLocation;
import lombok.Data;
import org.apache.commons.validator.ValidatorException;

import static com.zhdanov.geoip.converter.CanonicalIPv4ToIPv4Converter.convert;

@Data
public class GeoIpDTO {
    private String canonicalIPv4Representation;
    private String cityName;
    private String countryCode;
    private String countryName;
    private Long IPv4;
    private Double latitude;
    private Double longitude;
    private String regionName;

    public GeoIpDTO(String canonicalIPv4, IpToLocation ipToLocation) throws ValidatorException {
        this.canonicalIPv4Representation = canonicalIPv4;
        this.cityName = ipToLocation.getCityName();
        this.countryCode = ipToLocation.getCountryCode();
        this.countryName = ipToLocation.getCountryName();
        this.IPv4 = convert(canonicalIPv4);
        this.latitude = ipToLocation.getLatitude();
        this.longitude = ipToLocation.getLongitude();
        this.regionName = ipToLocation.getRegionName();
    }
}
