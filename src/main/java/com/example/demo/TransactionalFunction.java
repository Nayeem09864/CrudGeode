package com.example.demo;

import org.apache.geode.cache.CacheTransactionManager;
import org.apache.geode.cache.CommitConflictException;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;
import org.apache.geode.cache.execute.RegionFunctionContext;

public class TransactionalFunction implements Function {

  /**
   * Returns true if the function had the requested quantity of
   * inventory and successfully completed the transaction to
   * record the reduced inventory that fulfills the order.
   */
  @Override
  public void execute(FunctionContext context) {
    RegionFunctionContext rfc = (RegionFunctionContext) context;
    Region<Integer, Integer> inventoryRegion = rfc.getDataSet();

    CacheTransactionManager
        txManager = context.getCache().getCacheTransactionManager();

    // single argument will be a ProductId and a quantity
//    ProductRequest request = (ProductRequest) rfc.getArguments();
//    ProductId productRequested = request.getProductId();
//    Integer qtyRequested = request.getQuantity();
    Integer qtyRequested = 50;
    Integer productRequested = 40;

    boolean success = false;
    boolean commitConflict = false;
    do {
      try {
        txManager.begin();

//        Integer qtyAvailable = inventoryRegion.get(productRequested);
        Integer qtyAvailable = 100;
        if (qtyAvailable >= qtyRequested) {
          // enough inventory is available, so process request
          Integer remaining = qtyAvailable - qtyRequested;
          inventoryRegion.put(productRequested, remaining);
          txManager.commit();
          success = true;
        }

      } catch (CommitConflictException conflict) {
        // retry transaction, as another request on this same key succeeded,
        // so this transaction attempt failed
        commitConflict = true;
      } finally {
        // All other exceptions will be handled by the caller; however,
        // any exception thrown by a method other than commit() needs
        // to do a rollback to avoid leaking the transaction state.
        if(txManager.exists()) {
          txManager.rollback();
        }
      }

    } while (commitConflict);

    context.getResultSender().lastResult(success);
  }

  @Override
  public String getId() {
    return "TxFunction";
  }

  /**
   * Returning true causes this function to execute on the server
   * that holds the primary bucket for the given key. It can save a
   * network hop from the secondary to the primary.
   */
  @Override
  public boolean optimizeForWrite() {
    return true;
  }
}
