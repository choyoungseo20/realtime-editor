package com.realtimeeditor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SwaggerConfig {

    @Bean
    public OpenAPI realtimeEditorAPI() {
        Info info = new Info()
                .title("Realtime Editor API")
                .version("1.0");

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(info);
    }
}
