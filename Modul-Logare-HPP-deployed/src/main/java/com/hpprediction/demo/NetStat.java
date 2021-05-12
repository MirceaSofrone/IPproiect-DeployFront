package com.hpprediction.demo;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NetStat {


    public Environment environment;

    public String getCurrentHost(){
        String host;

        host = String.format("%s:%s", environment.getProperty("server.address"),
                    environment.getProperty("server.port"));

        return host;
    }
}
