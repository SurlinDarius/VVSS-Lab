package inventory.repository;
import inventory.model.Part;
import inventory.model.Product;
import inventory.service.InventoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

public class ProductIntegrationTest {

    private InventoryService service;
    private InventoryRepository repo;

    @BeforeEach
    void setUp() {
        repo = new InventoryRepository();
        service = new InventoryService(repo);
    }

    @Test
    void testAddRealProductAndRetrieve() throws Exception {
        Product realProduct = new Product(1, "ProdX", 5, 5, 1, 10, FXCollections.observableArrayList());
        service.addProduct(realProduct.getName(), realProduct.getPrice(), realProduct.getInStock(),
                realProduct.getMin(), realProduct.getMax(), realProduct.getAssociatedParts());

        Product found = service.lookupProduct("ProdX");
        assertEquals("ProdX", found.getName());
        service.deleteProduct(found);
    }

//    @Test
//    void testIntegrationThroughServiceToRepo() {
//        Product product = new Product(2, "ProdY", 3, 2, 1, 5, FXCollections.observableArrayList());
//        service.addProduct(product.getName(), product.getPrice(), product.getStock(),
//                product.getMin(), product.getMax(), product.getAllAssociatedParts());
//
//        ObservableList<Product> all = service.getAllProducts();
//        assertEquals(1, all.size());
//        assertEquals("ProdY", all.get(0).getName());
//    }
}


