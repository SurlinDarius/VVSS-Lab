package inventory.repository;

import inventory.model.Part;
import inventory.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private InventoryRepository repo;
    private ObservableList<Part> lista;

    public Product product;

    @BeforeEach
    void setUp() {
        repo = new InventoryRepository();
        lista = FXCollections.observableArrayList();
        product = new Product(3,"abcdef",5,5,1,10,lista);
        repo.addProduct(product);
    }

    @AfterEach
    void deleteAll() {
        repo.deleteProduct(product);
    }

    @Test
    void testLookupProduct_valid() throws Exception {
        try {
            assert((repo.lookupProduct("abcdef")) == product);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testLookupProduct_invalid() throws Exception {
        try {
            repo.lookupProduct("abcd");
        }
        catch (Exception e) {
            assertTrue(e.equals("Couldn't find the product!"));
        }
    }
}