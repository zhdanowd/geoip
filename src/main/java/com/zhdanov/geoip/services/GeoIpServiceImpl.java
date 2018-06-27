package com.zhdanov.geoip.services;

import com.zhdanov.geoip.dao.IpToLocationDao;
import com.zhdanov.geoip.dto.GeoIpDto;
import com.zhdanov.geoip.entity.IpToLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.zhdanov.geoip.converter.IPv4Converter.convertToCanonicalRepresentation;
import static com.zhdanov.geoip.converter.IPv4Converter.convertToDecimalRepresentation;

@Service
public class GeoIpServiceImpl implements GeoIpService {

    private IpToLocationDao ipToLocationDao;

    @Autowired
    public GeoIpServiceImpl(IpToLocationDao ipToLocationDao) {
        this.ipToLocationDao = ipToLocationDao;
    }

    @Override
    public GeoIpDto getIpToLocation(final String ip) {
        final String canonicalIPv4 = convertToCanonicalRepresentation(ip);
        final Long decimalIPv4 = convertToDecimalRepresentation(canonicalIPv4);
        final IpToLocation ipToLocation = ipToLocationDao.findFirstByIpFromLessThanEqualOrderByIpFromDesc(decimalIPv4);

        return new GeoIpDto(canonicalIPv4, decimalIPv4, ipToLocation);
    }


}
