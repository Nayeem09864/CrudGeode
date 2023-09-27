package com.example.demo;

import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gemfire.config.annotation.CacheServerApplication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("client")
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.data.gemfire.management.use-http=false")
public class IntegrationTest {

//  @BeforeClass
//  public static void startGeodeServer() throws IOException {
////    startGemFireServer(IntegrationTest.class);
//  }
//
//  @CacheServerApplication
//  @Profile("server1")
//  static class TestGeodeServerConfiguration {
//
//    @Test
//    public static void main(String[] args) {
//
//      new SpringApplicationBuilder(TestGeodeServerConfiguration.class)
//          .web(WebApplicationType.NONE)
//          .profiles("server1")
//          .build()
//          .run(args);
//    }
//  }

}
