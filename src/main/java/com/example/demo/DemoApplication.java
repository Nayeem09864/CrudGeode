package com.example.demo;

import com.example.demo.region.Author;
import com.example.demo.region.Book;
import com.example.demo.region.Customer;
import com.example.demo.region.ForeignStudent;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CustomerRepository;
import java.time.LocalDate;
import java.util.Date;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.execute.FunctionContext;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.geode.config.annotation.EnableClusterAware;
import org.springframework.geode.config.annotation.UseMemberName;

@SpringBootApplication
@EnableClusterAware
@EnableEntityDefinedRegions(basePackageClasses = Customer.class)
@UseMemberName("DemoApplication")
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  ApplicationRunner runner(GemFireCache cache, CustomerRepository customerRepository,
                           AuthorRepository authorRepository, BookRepository bookRepository) {
    return args -> {
      Customer jonDoe = new Customer(1L, "Jon Doe");

      System.out.println("----+++++------: John Doe: " + jonDoe);
      System.out.println("--------saving data---------");
      customerRepository.save(jonDoe);

      Author author = new Author(1L, "firstName", "One", "Book One", new Date(), "2020-05-05",
          LocalDate.now(), 300, 9.5);

      authorRepository.save(author);

      Book book = new Book(1L,"Book One", "Coauthor one");
      bookRepository.save(book);



//      CacheTransactionManager txManager =
//          cache.getCacheTransactionManager();
//
//      try {
//        txManager.begin();
//
//        txManager.commit();
//      } catch (CommitConflictException conflict) {
//        // ... do necessary work for a transaction that failed on commit
//      } finally {
//        // All other exceptions will be handled by the caller.
//        // Examples of some exceptions: the data is not colocated, a rebalance
//        // interfered with the transaction, or the server is gone.
//        // Any exception thrown by a method other than commit() needs
//        // to do a rollback to avoid leaking the transaction state.
//        if(txManager.exists()) {
//          txManager.rollback();
//        }
//      }

      // inputs needed for this transaction; shown as variables for simplicity
//      final String customer = "Customer1";
//      final Integer purchase = 1000;
//
//// region set up shown to promote understanding
//      Cache cache1 = new CacheFactory().create();
//      Pool pool = PoolManager.createFactory()
//          .addLocator("localhost", 10334)
//          .create("pool-name");
//      Region<String, Integer> cash =
//          cache1.createClientRegionFactory(ClientRegionShortcut.PROXY)
//              .setPoolName(pool.getName())
//              .create("cash");
////      Region<String, Integer> cash = cache1.createRegionFactory(ClientRegionShortcut.PROXY).setPoolName(pool);
//
//      Region<String, Integer> trades =
//          cache1.createClientRegionFactory(ClientRegionShortcut.PROXY)
//              .setPoolName(pool.getName())
//              .create("trades");
//
//// transaction code
//      CacheTransactionManager txManager = cache.getCacheTransactionManager();
//      boolean retryTransaction = false;
//      do {
//        try {
//          txManager.begin();
//
//          // Subtract out the cost of the trade for this customer's balance
//          Integer cashBalance = cash.get(customer);
//          Integer newBalance = (cashBalance != null ? cashBalance : 0) - purchase;
//          cash.put(customer, newBalance);
//
//          // Add in the cost of the trade for this customer
//          Integer tradeBalance = trades.get(customer);
//          newBalance = (tradeBalance != null ? tradeBalance : 0) + purchase;
//          trades.put(customer, newBalance);
//
//          txManager.commit();
//          retryTransaction = false;
//        }
//        catch (CommitConflictException conflict) {
//          // entry value changed causing a conflict for this customer, so try again
//          retryTransaction = true;
//        } finally {
//          // All other exceptions will be handled by the caller.
//          // Any exception thrown by a method other than commit() needs
//          // to do a rollback to avoid leaking the transaction state.
//          if(txManager.exists()) {
//            txManager.rollback();
//          }
//        }
//
//      } while (retryTransaction);


      /**
       * Atomically reduce inventory quantity
       */


      ForeignStudent foreignStudent = new ForeignStudent(1,"ForeignStudentOne");
      TransactionalFunction transactionalFunction =new TransactionalFunction();
      FunctionContext<ForeignStudent> functionContext;
//      transactionalFunction.execute(functionContext);


    };
  }
}