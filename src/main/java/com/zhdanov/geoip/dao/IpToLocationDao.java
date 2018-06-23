package com.zhdanov.geoip.dao;

import com.zhdanov.geoip.entity.IpToLocation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IpToLocationDao extends CrudRepository<IpToLocation, Long> {
    @Query("select i from IpToLocation i where i.ipFrom <= :ip order by i.ipFrom desc")
    List<IpToLocation> findByIp(@Param("ip") Long ip, Pageable pageable);
}
