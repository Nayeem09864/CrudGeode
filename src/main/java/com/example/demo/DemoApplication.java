package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import org.apache.geode.cache.GemFireCache;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableClusterConfiguration;
import org.springframework.data.gemfire.config.annotation.EnableContinuousQueries;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnableIndexing;
import org.springframework.data.gemfire.mapping.annotation.ReplicateRegion;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.geode.config.annotation.EnableClusterAware;
import org.springframework.geode.config.annotation.UseMemberName;
import org.springframework.mail.MailParseException;

//@ClientCacheApplication
//@EnableEntityDefinedRegions(basePackageClasses = Author.class)
//@EnableGemfireRepositories(basePackageClasses = AuthorRepository.class)
//@EnableClusterConfiguration(useHttp = true)
//@ReplicateRegion

//@SpringBootApplication
//@ClientCacheApplication(subscriptionEnabled = true)
//@EnableEntityDefinedRegions(basePackageClasses = Author.class)
//@EnableIndexing
//@EnableGemfireRepositories(basePackageClasses = AuthorRepository.class)
//@ComponentScan(basePackageClasses = {AppController.class, AuthorService.class})
//@EnableClusterConfiguration(useHttp = true, requireHttps=false)
//@EnableContinuousQueries

@SpringBootApplication
@EnableClusterAware
@EnableEntityDefinedRegions(basePackageClasses = Customer.class)
@UseMemberName("DemoApplication")
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	@SuppressWarnings("unused")
	ApplicationRunner runner(GemFireCache cache, CustomerRepository customerRepository) {

		return args -> {

			//assertClientCacheAndConfigureMappingPdxSerializer(cache);
			//assertThat(customerRepository.count()).isEqualTo(0);

			Customer jonDoe = new Customer(1L, "Jon Doe");

//			log("Saving Customer [%s]...%n", jonDoe);
			System.out.println("----+++++------: John Doe: "+jonDoe);
			System.out.println("--------saving data---------");
			jonDoe = customerRepository.save(jonDoe);

			Customer customer = new Customer(2L, "Customer");

			customerRepository.save(customer);
			customer = new Customer(3L, "Customer 3");
			customerRepository.save(customer);

//			Map<Long, Customer> map = new HashMap<>();


			customer = new Customer(4L, "Customer 4");
			customerRepository.save(customer);

			customer = new Customer(5L, "Customer 5");
			customerRepository.save(customer);

			customer = new Customer(6L, "Customer 6");
			customerRepository.save(customer);


//            assertThat(jonDoe).isNotNull();
//            assertThat(jonDoe.getId()).isEqualTo(1L);
//            assertThat(jonDoe.getName()).isEqualTo("Jon Doe");
//            assertThat(customerRepository.count()).isEqualTo(1);

//			log("Querying for Customer [SELECT * FROM /Customers WHERE name LIKE '%s']...%n", "%Doe");

			Customer queriedJonDoe = customerRepository.findByNameLike("%Doe");
			System.out.println("------Fetch data--------+  "+queriedJonDoe.getName());

			//assertThat(queriedJonDoe).isEqualTo(jonDoe);

		};
	}
}