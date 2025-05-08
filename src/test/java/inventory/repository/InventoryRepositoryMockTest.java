package inventory.repository;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

public class InventoryRepositoryMockTest {

    @Mock
    private Product mockProduct;

    @InjectMocks
    private InventoryService service;

    private ObservableList<Part> lista;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        lista = FXCollections.observableArrayList();
    }

    @Test
    void testLookupProduct() throws Exception {
        Product expectedProduct = mockProduct;
        Mockito.when(mockProduct.getName()).thenReturn("abcdef");
        Mockito.when(mockProduct.getProductId()).thenReturn(3);

        InventoryRepository repo = Mockito.mock(InventoryRepository.class);
        Mockito.when(repo.lookupProduct("abcdef")).thenReturn(expectedProduct);

        InventoryService testService = new InventoryService(repo);

        Product result = testService.lookupProduct("abcdef");

        Mockito.verify(repo, times(1)).lookupProduct("abcdef");
        assertEquals(expectedProduct, result);
    }

    @Test
    void testAddProduct() throws Exception {
        InventoryRepository repo = Mockito.mock(InventoryRepository.class);
        InventoryService testService = new InventoryService(repo);

        Product actualProduct = new Product(3, "abcdef", 5, 5, 1, 10, lista);
        testService.addProduct("abcdef", 5, 5, 1, 10, lista);

        Mockito.verify(repo, times(1)).addProduct(Mockito.any(Product.class));

        Mockito.when(repo.getAllProducts()).thenReturn(FXCollections.observableArrayList(actualProduct));
        assertEquals(1, testService.getAllProducts().size());

        Mockito.verify(repo, times(1)).getAllProducts();
    }
}
