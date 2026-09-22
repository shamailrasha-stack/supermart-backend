package com.supermart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.supermart.entity.Category;
import com.supermart.entity.Product;
import com.supermart.entity.User;
import com.supermart.repository.CategoryRepository;
import com.supermart.repository.ProductRepository;
import com.supermart.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // ================= USERS =================

        if (userRepository.findByEmail("admin@supermart.com") == null) {
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@supermart.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(User.Role.ADMIN);
            admin.setActive(true);
            userRepository.save(admin);
        }

        if (userRepository.findByEmail("cashier@supermart.com") == null) {
            User cashier = new User();
            cashier.setName("Cashier");
            cashier.setEmail("cashier@supermart.com");
            cashier.setPassword(passwordEncoder.encode("cashier123"));
            cashier.setRole(User.Role.CASHIER);
            cashier.setActive(true);
            userRepository.save(cashier);
        }

        // ================= CATEGORIES =================

        if (categoryRepository.count() == 0) {

            Category dairy = new Category();
            dairy.setName("Dairy");
            dairy.setDescription("Milk and dairy products");
            dairy.setActive(true);
            categoryRepository.save(dairy);

            Category snacks = new Category();
            snacks.setName("Snacks");
            snacks.setDescription("Biscuits and snacks");
            snacks.setActive(true);
            categoryRepository.save(snacks);

            Category beverages = new Category();
            beverages.setName("Beverages");
            beverages.setDescription("Drinks and beverages");
            beverages.setActive(true);
            categoryRepository.save(beverages);

            Category groceries = new Category();
            groceries.setName("Groceries");
            groceries.setDescription("Daily grocery essentials");
            groceries.setActive(true);
            categoryRepository.save(groceries);
        }

        // ================= PRODUCTS =================

        if (productRepository.count() == 0) {

            Category dairy = categoryRepository.findByName("Dairy");
            Category snacks = categoryRepository.findByName("Snacks");
            Category beverages = categoryRepository.findByName("Beverages");
            Category groceries = categoryRepository.findByName("Groceries");

            // -------- Dairy --------

            createProduct(
                    "Nandini Good Life Toned Milk 1L",
                    "DAI001",
                    "890100000001",
                    "Nandini toned milk",
                    32.0,
                    35,
                    10,
                    dairy,
                    "https://services.kpnfresh.com/media/v1/products/images/ea4ffd6c-b0df-4e75-8af8-027eadd8cc3d/nandini-good-life-toned-milk.webp?c_type=C1");

            createProduct(
                    "Amul Pasteurized Butter 500g",
                    "DAI002",
                    "890100000002",
                    "Amul pasteurized butter",
                    285.0,
                    18,
                    5,
                    dairy,
                    "https://services.kpnfresh.com/media/v1/products/images/3058f04c-918f-4711-83af-6d420f00280f/amul-pasteurized-butter.webp?c_type=C1");

            createProduct(
                    "Delfrez Nourish White Eggs 12 Pack",
                    "DAI003",
                    "890100000003",
                    "Farm fresh white eggs",
                    90.0,
                    24,
                    8,
                    dairy,
                    "https://services.kpnfresh.com/media/v1/products/images/2e9367f0-162a-43be-9a61-1716d445a194/delfrez-nourish-white-eggs.webp?c_type=C1");

            // -------- Snacks --------

            createProduct(
                    "Parle-G Glucose Biscuits",
                    "SNK001",
                    "890100000004",
                    "Classic glucose biscuits",
                    20.0,
                    40,
                    10,
                    snacks,
                    "https://services.kpnfresh.com/media/v1/products/images/d6c0f1cc-a71a-4bce-b3f0-d6ed60b6623d/parleg-glucose-biscuits.webp?c_type=C1");

            createProduct(
                    "Lays Classic Salted",
                    "SNK002",
                    "890100000005",
                    "Classic salted potato chips",
                    20.0,
                    25,
                    8,
                    snacks,
                    "https://services.kpnfresh.com/media/v1/products/images/230de5c2-b6d3-4feb-95f8-0c6db30407b2/lays-classic-salted-potato-chips.webp?c_type=C1");

            createProduct(
                    "Parle Hide & Seek Chocolate Chip Biscuits",
                    "SNK003",
                    "890100000006",
                    "Chocolate chip biscuits",
                    40.0,
                    15,
                    5,
                    snacks,
                    "https://services.kpnfresh.com/media/v1/products/images/38d0d60a-8250-4d21-bcca-adad965edb5b/parle-hide-seek-chocolate-chip-biscuits.webp?c_type=C1");

            // -------- Beverages --------

            createProduct(
                    "Real Orange Juice 1L",
                    "BEV001",
                    "890100000007",
                    "Real orange fruit juice",
                    120.0,
                    20,
                    5,
                    beverages,
                    "https://services.kpnfresh.com/media/v1/products/images/e906e878-3fe0-4c54-915f-323f04abc817/real-fruit-juice-orange.webp?c_type=C1");

            createProduct(
                    "Kinley Water 1L",
                    "BEV002",
                    "890100000008",
                    "Packaged drinking water",
                    20.0,
                    8,
                    10,
                    beverages,
                    "https://services.kpnfresh.com/media/v1/products/images/359a3259-6f9b-4b4f-9d32-24a648878ad5/kinley-water.webp?c_type=C1");

            createProduct(
                    "Coca Cola 750ml",
                    "BEV003",
                    "890100000009",
                    "Coca-Cola original taste soft drink",
                    40.0,
                    30,
                    10,
                    beverages,
                    "https://services.kpnfresh.com/media/v1/products/images/85d91210-3a82-43c5-acec-9eef19b65b8c/cocacola-soft-drink-bottle.webp?c_type=C1");

            // -------- Groceries --------

            createProduct(
                    "India Gate Tibar Basmati Rice 5kg",
                    "GRO001",
                    "890100000010",
                    "India Gate basmati rice",
                    650.0,
                    12,
                    4,
                    groceries,
                    "https://services.kpnfresh.com/media/v1/products/images/06ae1831-b9c1-495e-afad-5309ae36af9b/india-gate-tibar-basmati-rice.webp?c_type=C1");

            createProduct(
                    "Fortune Sunlite Sunflower Oil 800g",
                    "GRO002",
                    "890100000011",
                    "Fortune Sunlite refined sunflower oil",
                    140.0,
                    20,
                    5,
                    groceries,
                    "https://services.kpnfresh.com/media/v1/products/images/ad2b4c55-55ca-4447-855c-2482c76f58dd/fortune-sunlite-sunflower-refined-oil-pouch.webp?c_type=C1");

            createProduct(
                    "Tata Iodised Crystal Salt 1kg",
                    "GRO003",
                    "890100000012",
                    "Tata iodised crystal salt",
                    30.0,
                    25,
                    8,
                    groceries,
                    "https://www.bbassets.com/media/uploads/p/s/40017970_7-tata-salt-iodised-crystal-salt.jpg");

            createProduct(
                    "bb Royal Refined Sugar 1kg",
                    "GRO004",
                    "890100000013",
                    "Refined sugar",
                    50.0,
                    22,
                    6,
                    groceries,
                    "https://www.bbassets.com/media/uploads/p/s/10000447_17-bb-royal-refined-sugar-sulphurless.jpg");
        }
    }

    private void createProduct(
            String name,
            String sku,
            String barcode,
            String description,
            double price,
            int stockQuantity,
            int lowStockThreshold,
            Category category,
            String imageUrl) {

        Product product = new Product();

        product.setName(name);
        product.setSku(sku);
        product.setBarcode(barcode);
        product.setDescription(description);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        product.setLowStockThreshold(lowStockThreshold);
        product.setActive(true);
        product.setCategory(category);
        product.setImageUrl(imageUrl);

        productRepository.save(product);
    }
}
