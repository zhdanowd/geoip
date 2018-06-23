package com.zhdanov.geoip.controller;

import com.zhdanov.geoip.dto.GeoIpDTO;
import com.zhdanov.geoip.services.GeoIpService;
import org.apache.commons.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geoip")
public class GeoIpController {

    @Autowired
    private GeoIpService geoIpService;

    @GetMapping("/{ip}")
    GeoIpDTO getIp(@PathVariable String ip) throws ValidatorException {
        return geoIpService.getIpToLocation(ip);
    }
}
