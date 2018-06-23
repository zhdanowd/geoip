package com.zhdanov.geoip.services;

import com.zhdanov.geoip.dao.IpToLocationDao;
import com.zhdanov.geoip.dto.GeoIpDTO;
import com.zhdanov.geoip.entity.IpToLocation;
import org.apache.commons.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zhdanov.geoip.converter.CanonicalIPv4ToIPv4Converter.convert;

@Service
public class GeoIpServiceImpl implements GeoIpService {

    @Autowired
    private IpToLocationDao ipToLocationDao;

    @Transactional
    @Override
    public GeoIpDTO getIpToLocation(String canonicalIPv4) throws ValidatorException {
        Long ip = convert(canonicalIPv4);
        System.out.println(ip);
        Long before = System.currentTimeMillis();
        IpToLocation ipToLocation = ipToLocationDao.findByIp(ip, PageRequest.of(0, 1)).get(0);
        Long after = System.currentTimeMillis();
        System.out.println(after - before);

        return new GeoIpDTO(canonicalIPv4, ipToLocation);
    }


}
