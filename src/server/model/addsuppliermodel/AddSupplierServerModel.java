package server.model.addsuppliermodel;

import shared.transfer.Supplier;
import shared.util.Subject;

public interface AddSupplierServerModel extends Subject
{
  String createNewSupplier(Supplier supplierToCreate);
}
