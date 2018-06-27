package com.zhdanov.geoip.dao;

import com.zhdanov.geoip.entity.IpToLocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IpToLocationDao extends CrudRepository<IpToLocation, Long> {
    IpToLocation findFirstByIpFromLessThanEqualOrderByIpFromDesc(@Param("ipTo") Long ip);
}
