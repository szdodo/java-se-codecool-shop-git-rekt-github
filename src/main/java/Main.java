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
            Integer cartSize = cart.getCartContent().size();
            String size = cart.getCartSize();
            return (size + " items");

        });

        get("/getCartSize", (req, res) -> {
            String cartSize = cart.getCartSize();
            return (cartSize + " items");
        });

        get("/getTotalPrice", (req, res) -> {
            String cartTotalPrice = cart.getTotalPrice();
            return ("Total: " + cartTotalPrice + " $");
        });

        get("/getCartContent", (Request req, Response res) -> {
            HashMap<String, ArrayList> products = new HashMap<>();
            products = cart.getCartContent();
            ArrayList<LineItem> items=new ArrayList<>();
            items = cart.getCartContent().get("products");
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<String> result = new ArrayList<>();
            for (int i = 0; i < items.size(); i++) {
                LineItem prod = items.get(i);
                String jsonAsd = mapper.writeValueAsString(prod);
                result.add(jsonAsd);
            }
            return result;
        });

        get("/checkout", (req, res) -> {
            HashMap<String, HashMap> cartContent = new HashMap<>();
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

        get("/updateShoppingCart", (req, res) -> {
            String productName = req.queryParams("productName");
            String productQuantity = req.queryParams("quantity");
            Integer quantity= Integer.parseInt(productQuantity);
            cart.updateCart(productName,quantity);
            return "succes";
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
        Supplier lenovo = new Supplier("Lenovo", "Computers and laptops");
        supplierDataStore.add(lenovo);
        Supplier htc = new Supplier("HTC", "Mobile phones and accessories");
        supplierDataStore.add(htc);
        Supplier coverYourFur = new Supplier("Cover-Your-Fur", "High-quality animal shelters");
        supplierDataStore.add(coverYourFur);
        Supplier lg = new Supplier("LG", "Mobile phones and accessories");
        supplierDataStore.add(lg);
        Supplier apple = new Supplier("Apple", "Computers, laptops, tablets, mobile phones and accessories");
        supplierDataStore.add(apple);
        Supplier cornerVendor = new Supplier("CornerVendor", "Guess you'll have to figure this out yourself");
        supplierDataStore.add(cornerVendor);


        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "Portable computers used for a variety of purposes.");
        productCategoryDataStore.add(laptop);
        ProductCategory mobile = new ProductCategory("Mobile", "Hardware", "Portable telephones used for a variety of purposes.");
        productCategoryDataStore.add(mobile);
        ProductCategory dogShelter = new ProductCategory("Dog shelter", "Outdoor appliances", "Outdoor structures to protect your pets from the weather.");
        productCategoryDataStore.add(dogShelter);
        ProductCategory drugs = new ProductCategory("Drugs", "Recreational use", "u w4nt some djanga, m8");
        productCategoryDataStore.add(drugs);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Asus XT98", 109, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", laptop, amazon));
        productDataStore.add(new Product("HTC H5", 120, "USD", "HTC's latest H5 mobile is a great value for media consumption.", mobile, htc));
        productDataStore.add(new Product("Lenovo K3", 80, "USD", "Lenovo's latest K3 mobile is a great value for media consumption.", mobile, lenovo));
        productDataStore.add(new Product("Papillon Mark II", 30, "USD", "Basic dog shelter made from sturdy wood. 5 year warranty.", dogShelter, coverYourFur));
        productDataStore.add(new Product("Weed", 30, "USD", "you have the money, i have the means smoek weeeed evry daaaay", drugs, cornerVendor));
        productDataStore.add(new Product("LG G6", 30, "USD", "Big screen. Small Phone. Blue sky. Green planet.", mobile, lg));
        productDataStore.add(new Product("LG G5", 30, "USD", "5.3\" IPS Quantum QHD Display & Metal Body and shit", mobile, lg));
        productDataStore.add(new Product("Apple iPhone", 30, "USD", "I don't have enough money but I still wanna look rich", mobile, apple));
        productDataStore.add(new Product("Apple MacBook", 30, "USD", "I have too much money and I have no idea how to spend it responsibly", laptop, apple));

    }

    private static String renderTemplate(String view, HashMap model) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, view));
    }

}