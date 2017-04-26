import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import spark.ModelAndView;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        populateData();
        ShoppingCart cart = ShoppingCart.getInstance();

        // Always add generic routes to the end
        //get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());
        // Equivalent with above
        get("/", (Request req, Response res) -> {
            try {
                String category = req.queryParams("category");
                return new ThymeleafTemplateEngine().render(ProductController.renderProductsByCategory(req, res, category));
            }
            catch (Exception e) {
                System.out.println("Error: invalid category");
            }
            try {
                String supplier = req.queryParams("supplier");
                return new ThymeleafTemplateEngine().render(ProductController.renderProductsBySupplier(req, res, supplier));
            }
            catch (Exception e) {
                System.out.println("Error: invalid supplier");
            }
            return new ThymeleafTemplateEngine().render(ProductController.renderAllProducts(req, res));
        });

        get("/addToCart", (req, res) -> {

            Integer productId =  Integer.parseInt(req.queryParams("productId"));
            Product product = ProductDaoMem.getInstance().find(productId);
            cart.addToCart(product);
            System.out.println(cart.getCartContent());
            return "success";

        });

        get("/getCartContent", (Request req, Response res) -> {

            ArrayList<LineItem> products = new ArrayList<>();
            products = cart.getCartContent();
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<String> result = new ArrayList<>();

            for (int i = 0; i < products.size(); i++) {

                LineItem prod = products.get(i);
                String jsonAsd = mapper.writeValueAsString(prod);
                result.add(jsonAsd);
            }
            return result;
        });

        get("/checkout", (req, res) -> {
            HashMap<String, ArrayList> cartContent = new HashMap<>();
            cartContent.put("cartContent", cart.getCartContent());
            return renderTemplate("product/checkout", cartContent);
        });

        get("/payment", (req, res) -> {
            HashMap<String, ArrayList> dummyHashMap = new HashMap<>();
            return renderTemplate("product/payment", dummyHashMap);
        });

        post("/payment", (req, res) -> {
            String name = req.queryParams("name");
            String email = req.queryParams("email");
            res.redirect("/paid");
            return "success";
        });

        get("/paid", (req, res) -> {
            HashMap<String, ArrayList> dummyHashMap = new HashMap<>();
            return renderTemplate("product/paid", dummyHashMap);
        });


        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }

    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier HTC = new Supplier("HTC", "mobiles");
        supplierDataStore.add(HTC);
        Supplier shelter = new Supplier("HTC", "animals");
        supplierDataStore.add(shelter);


        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "Portable computers used for a variety of purposes.");
        productCategoryDataStore.add(laptop);
        ProductCategory mobile = new ProductCategory("Mobile", "Hardware", "Portable telephones used for a variety of purposes.");
        productCategoryDataStore.add(mobile);
        ProductCategory puppy = new ProductCategory("Puppy", "Software", "Portable animals used for a variety of purposes.");
        productCategoryDataStore.add(puppy);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Asus XT98", 109, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", laptop, amazon));
        productDataStore.add(new Product("H5", 120, "USD", "HTC's latest H5 mobile is a great value for media consumption.", mobile, HTC));
        productDataStore.add(new Product("K3 lala", 80, "USD", "Lenovo's latest K3 mobile is a great value for media consumption.", mobile, lenovo));
        productDataStore.add(new Product("Papillon", 30, "USD", "Lost puppy.", puppy, shelter));



    }

    private static String renderTemplate(String view, HashMap model) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, view));
    }

}