package inventory.repository;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.model.Product;
import inventory.service.InventoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

public class InventoryServiceMockTest {

    @Mock
    private InventoryRepository repo;

    @InjectMocks
    private InventoryService service;

    public ObservableList<Part> lista;
    public Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        lista = FXCollections.observableArrayList();
    }

    @Test
    void testLookupProduct() throws Exception {
        Product product1 = new Product(3,"abcdef",5,5,1,10, lista);
        Mockito.when(repo.lookupProduct("abcdef")).thenReturn(product1);

        Product result = service.lookupProduct("abcdef");

        Mockito.verify(repo, times(1)).lookupProduct("abcdef");
        assertEquals(product1, result);
    }


    @Test
    void testAddProduct() throws Exception {
        Product product1 = new Product(3,"abcdef",5,5,1,10, lista);

        service.addProduct("abcdef", 5, 5, 1, 10, lista);

        Mockito.verify(repo, times(1)).addProduct(Mockito.any(Product.class));

        Mockito.when(repo.getAllProducts()).thenReturn(FXCollections.observableArrayList(product1));
        assertEquals(1, service.getAllProducts().size());

        Mockito.verify(repo, times(1)).getAllProducts();
    }
}
