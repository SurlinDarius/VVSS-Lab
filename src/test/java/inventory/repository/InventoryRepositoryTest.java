package inventory.repository;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class InventoryRepositoryTest {

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

    @DisplayName("ECP Valid - Add 'Cog'")
    @RepeatedTest(1)
    void testAddPart_ECP_Valid() {
        InhousePart part = new InhousePart(1, "Cog", 3.75, 14, 4, 20, 100);
        String validation = Part.isValidPart(part.getName(), part.getPrice(), part.getInStock(), part.getMin(), part.getMax(), "");
        assertEquals("", validation);
        repo.addPart(part);
        assertTrue(repo.getAllParts().contains(part));
    }

    @Test
    @DisplayName("ECP Invalid - Negative price")
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testAddPart_ECP_InvalidPrice() {
        InhousePart part = new InhousePart(2, "Bull", -10.0, 5, 3, 11, 101);
        String validation = Part.isValidPart(part.getName(), part.getPrice(), part.getInStock(), part.getMin(), part.getMax(), "");
        assertTrue(validation.contains("The price must be greater than 0. "));
    }

    @DisplayName("BVA Valid - Stock between min and max")
    @RepeatedTest(2)
    void testAddPart_BVA_ValidStock() {
        InhousePart part = new InhousePart(3, "Screw", 0.11, 280, 100, 1000, 102);
        String validation = Part.isValidPart(part.getName(), part.getPrice(), part.getInStock(), part.getMin(), part.getMax(), "");
        assertEquals("", validation);
        repo.addPart(part);
        assertTrue(repo.getAllParts().contains(part));
    }

    @Test
    @DisplayName("BVA Invalid - Stock below min")
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testAddPart_BVA_InvalidStockBelowMin() {
        InhousePart part = new InhousePart(4, "Bomb", 123.0, 2, 12, 50, 103);
        String validation = Part.isValidPart(part.getName(), part.getPrice(), part.getInStock(), part.getMin(), part.getMax(), "");
        assertTrue(validation.contains("Inventory level is lower than minimum value"));
    }

    @Test
    @Tag("boundary")
    @DisplayName("BVA - Stock equals min")
    void testAddPart_BVA_StockEqualsMin() {
        InhousePart part = new InhousePart(5, "Limit", 1.0, 10, 10, 100, 104);
        String validation = Part.isValidPart(part.getName(), part.getPrice(), part.getInStock(), part.getMin(), part.getMax(), "");
        assertEquals("", validation);
        repo.addPart(part);
        assertTrue(repo.getAllParts().contains(part));
    }

    @Test
    @Disabled("Pending validation logic implementation")
    void testAddPart_Disabled() {
        fail("Disabled test");
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