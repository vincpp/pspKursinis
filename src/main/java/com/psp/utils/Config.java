package com.psp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
@NoArgsConstructor
@Getter
@Setter
public class Config {
    public String logFile = this.logFile = "src/main/java/com/psp/logs/app.log";;
    public int seed=1;
    public int logLevel=2;



    public static Config loadFromFile(String configPath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Config cfg = mapper.readValue(new File(configPath), Config.class);
        if (cfg.logFile == null) cfg.logFile = "src/main/java/com/psp/logs/app.log";
        if (cfg.seed == 0) cfg.seed = 12345;
        if (cfg.logLevel == 0) cfg.logLevel = 2;
        return cfg;
    }

}