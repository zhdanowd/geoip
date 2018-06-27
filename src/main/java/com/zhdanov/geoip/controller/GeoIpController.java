package com.zhdanov.geoip.controller;

import com.zhdanov.exception.InvalidIPv4AddressException;
import com.zhdanov.geoip.dto.GeoIpDto;
import com.zhdanov.geoip.services.GeoIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/geoip")
public class GeoIpController {

    private GeoIpService geoIpService;

    @Autowired
    public GeoIpController(GeoIpService geoIpService) {
        this.geoIpService = geoIpService;
    }

    @GetMapping("/{ip}")
    GeoIpDto getIp(final @PathVariable String ip) {
        try {
            return geoIpService.getIpToLocation(ip);
        } catch (InvalidIPv4AddressException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getCause());
        }
    }

}
