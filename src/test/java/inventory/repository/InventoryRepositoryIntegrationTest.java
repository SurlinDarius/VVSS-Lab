package inventory.repository;

import inventory.model.Part;
import inventory.model.Product;
import inventory.service.InventoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class InventoryRepositoryIntegrationTest {

    @Mock
    private Product mockProduct;

    @InjectMocks
    private InventoryRepository repo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddProductViaService() {
        Mockito.when(mockProduct.getName()).thenReturn("mocked");

        repo.addProduct(mockProduct);
        ObservableList<Product> all = repo.getAllProducts();
        assertTrue(all.contains(mockProduct));
    }

    @Test
    void testLookupProductFromRepo() throws Exception {
        Mockito.when(mockProduct.getName()).thenReturn("abc");
        repo.addProduct(mockProduct);

        Product found = repo.lookupProduct("abc");
        assertEquals(mockProduct, found);
    }
}
