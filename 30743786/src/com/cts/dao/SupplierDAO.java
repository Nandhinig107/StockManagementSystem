package com.cts.dao;

import com.cts.model.Supplier;
import java.util.List;

public interface SupplierDAO {
    void addSupplier(Supplier supplier);
    Supplier getSupplierById(int supplierId);
    List<Supplier> getAllSuppliers();
    void updateSupplier(Supplier supplier);
    void deleteSupplier(int supplierId);
}
