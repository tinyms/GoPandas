package com.scriptlte.gopandas.config;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Profile("dev") // Only activate this in the "dev" profile
public class H2ServerConfig {

    @Value("${h2.tcp.port:9092}")
    private String h2TcpPort;

    //配置TCP远程访问
    @Bean
    public Server h2TcpServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", h2TcpPort).start();
    }
}
