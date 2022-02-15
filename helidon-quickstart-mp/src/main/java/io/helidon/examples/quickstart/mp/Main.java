package io.helidon.examples.quickstart.mp;

import io.helidon.config.Config;
import static io.helidon.config.ConfigSources.classpath;
import io.helidon.microprofile.server.Server;
import java.io.IOException;

public final class Main {

    private Main() { } 

    public static void main(final String[] args) throws IOException {
        Server server = startServer();
        
    
        System.out.println("http://localhost:" + server.port() + "/greet");
    }
    static Server startServer() {
        return Server.builder()
            .config(buildConfig()) 
            .build()
            .start();
    }

    private static Config buildConfig() {
    return Config.builder().disableEnvironmentVariablesSource() 
        .sources(
//            classpath("config.properties"), //this file is present under resources and since defined before has highest priority
            classpath("META-INF/mp-config.yaml").optional(), //this file is optional
            classpath("META-INF/microprofile-config.properties")) //this is mandatory
        .build();
}
}