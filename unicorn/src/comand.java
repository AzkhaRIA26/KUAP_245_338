//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class comand {
//
//    // =============================
//    // Data Produk
//    static class Product {
//        String name;
//        String description;
//        int price;
//        String imagePath;
//        boolean available;
//
//        public Product(String name, String description, int price, String imagePath, boolean available) {
//            this.name = name;
//            this.description = description;
//            this.price = price;
//            this.imagePath = imagePath;
//            this.available = available;
//        }
//    }
//
//    static List<Product> productList = new ArrayList<>();
//    static DefaultTableModel cartTableModel;
//    static JLabel totalPriceLabel;
//    static JTextField searchField;
//    static List<Object[]> cartItems = new ArrayList<>();
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            initializeProducts();
//            new MainApp();
//        });
//    }
//
//    private static void initializeProducts() {
//        productList.add(new Product("Gundam RX-78-2", "Scale 1/144, High Grade", 300000, "src/images/10.jpg", true));
//        productList.add(new Product("Gundam Exia", "Scale 1/100, High Grade", 450000, "src/images/9.jpeg", false));
//        productList.add(new Product("Gundam Unicorn", "Scale 1/144, High Grade", 700000, "src/images/8.jpg", true));
//    }
//
//    // =============================
//    // Frame Utama
//    static class MainApp extends JFrame {
//        JPanel productPanel;
//
//        public MainApp() {
//            setTitle("Toko Gundam Online");
//            setSize(1200, 700);
//            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            setLocationRelativeTo(null);
//            setLayout(new BorderLayout());
//
//            // Header Panel (Logo, Search, Wishlist, Cart)
//            JPanel headerPanel = new JPanel(new BorderLayout());
//            headerPanel.setBackground(new Color(32, 72, 202)); // Warna lebih menarik
//            JLabel logoLabel = new JLabel("TokoGundam", JLabel.CENTER);
//            logoLabel.setFont(new Font("Montserrat", Font.BOLD, 24)); // Font lebih modern
//            logoLabel.setForeground(Color.WHITE);
//
//            // Search Bar
//            searchField = new JTextField(30);
//            JButton searchButton = new JButton("Search");
//            searchButton.addActionListener(e -> searchProduct());
//            searchButton.setBackground(new Color(255, 87, 51));
//            searchButton.setForeground(Color.WHITE);
//
//            JPanel searchPanel = new JPanel();
//            searchPanel.add(searchField);
//            searchPanel.add(searchButton);
//
//            // Wishlist dan Keranjang
//            JButton wishlistButton = new JButton("Wishlist");
//            wishlistButton.setBackground(new Color(3, 218, 198));
//            wishlistButton.setForeground(Color.WHITE);
//            wishlistButton.addActionListener(e -> showWishlist());
//
//            JButton cartButton = new JButton("Keranjang");
//            cartButton.setBackground(new Color(63, 81, 181));
//            cartButton.setForeground(Color.WHITE);
//            cartButton.addActionListener(e -> showCart());
//
//            JPanel buttonPanel = new JPanel();
//            buttonPanel.add(wishlistButton);
//            buttonPanel.add(cartButton);
//
//            headerPanel.add(logoLabel, BorderLayout.WEST);
//            headerPanel.add(searchPanel, BorderLayout.CENTER);
//            headerPanel.add(buttonPanel, BorderLayout.EAST);
//
//            add(headerPanel, BorderLayout.NORTH);
//
//            // Product Panel
//            productPanel = new JPanel(new GridLayout(0, 3, 10, 10));
//            productPanel.setBackground(new Color(0, 0, 0)); // Background panel produk
//            JScrollPane scrollPane = new JScrollPane(productPanel);
//            displayProducts(productList);
//            add(scrollPane, BorderLayout.CENTER);
//
//            JMenuBar menuBar = new JMenuBar(); // Tambahan menu untuk CRUD
//            JMenu productMenu = new JMenu("Produk");
//
//            JMenuItem addProduct = new JMenuItem("Tambah Produk");
//            addProduct.addActionListener(e -> addProductDialog());
//            productMenu.add(addProduct);
//
//            JMenuItem exitApp = new JMenuItem("Keluar");
//            exitApp.addActionListener(e -> System.exit(0));
//            productMenu.add(exitApp);
//
//            menuBar.add(productMenu);
//            setJMenuBar(menuBar);
//
//            setVisible(true);
//        }
//
//        // =============================
//        // Menampilkan Produk
//        private void displayProducts(List<Product> products) {
//            productPanel.removeAll();
//            for (Product product : products) {
//                JPanel productBox = createProductBox(product);
//                productPanel.add(productBox);
//            }
//            productPanel.revalidate();
//            productPanel.repaint();
//        }
//
//        private JPanel createProductBox(Product product) {
//            JPanel box = new JPanel(new BorderLayout());
//            box.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//
//            // Gambar Produk
//            ImageIcon icon;
//            try {
//                icon = new ImageIcon(product.imagePath);
//            } catch (Exception e) {
//                icon = new ImageIcon("src/images/default.jpg"); // Gambar default jika tidak ditemukan
//            }
//
//            JLabel imageLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
//            box.add(imageLabel, BorderLayout.CENTER);
//
//            JLabel nameLabel = new JLabel(product.name, JLabel.CENTER);
//            nameLabel.setFont(new Font("Montserrat", Font.BOLD, 14));
//            JLabel priceLabel = new JLabel("Rp " + product.price, JLabel.CENTER);
//
//            JButton viewDetails = new JButton("Lihat Detail");
//            viewDetails.setBackground(new Color(255, 193, 7));
//            viewDetails.setForeground(Color.BLACK);
//            viewDetails.addActionListener(e -> showProductDetails(product));
//
//            JPanel buttonPanel = new JPanel();
//            buttonPanel.add(viewDetails);
//
//            box.add(nameLabel, BorderLayout.NORTH);
//            box.add(priceLabel, BorderLayout.SOUTH);
//            box.add(buttonPanel, BorderLayout.EAST);
//
//            return box;
//        }
//
//        // =============================
//        // Tambahan CRUD
//        private void addProductDialog() {
//            JDialog addDialog = new JDialog(this, "Tambah Produk", true);
//            addDialog.setSize(400, 400);
//
//            JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
//
//            formPanel.add(new JLabel("Nama Produk:"));
//            JTextField nameField = new JTextField();
//            formPanel.add(nameField);
//
//            formPanel.add(new JLabel("Deskripsi:"));
//            JTextField descriptionField = new JTextField();
//            formPanel.add(descriptionField);
//
//            formPanel.add(new JLabel("Harga:"));
//            JTextField priceField = new JTextField();
//            formPanel.add(priceField);
//
//            formPanel.add(new JLabel("Gambar (Path):"));
//            JTextField imagePathField = new JTextField();
//            formPanel.add(imagePathField);
//
//            formPanel.add(new JLabel("Tersedia (true/false):"));
//            JTextField availableField = new JTextField();
//            formPanel.add(availableField);
//
//            JButton saveButton = new JButton("Simpan");
//            saveButton.addActionListener(e -> {
//                try {
//                    String name = nameField.getText();
//                    String description = descriptionField.getText();
//                    int price = Integer.parseInt(priceField.getText());
//                    String imagePath = imagePathField.getText();
//                    boolean available = Boolean.parseBoolean(availableField.getText());
//
//                    productList.add(new Product(name, description, price, imagePath, available));
//                    displayProducts(productList);
//                    addDialog.dispose();
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(this, "Input tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            });
//
//            addDialog.add(formPanel, BorderLayout.CENTER);
//            addDialog.add(saveButton, BorderLayout.SOUTH);
//            addDialog.setVisible(true);
//        }
//
//        private void searchProduct() {
//            String keyword = searchField.getText().trim().toLowerCase();
//            List<Product> results = new ArrayList<>();
//            for (Product p : productList) {
//                if (p.name.toLowerCase().contains(keyword)) {
//                    results.add(p);
//                }
//            }
//            if (results.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Produk tidak tersedia.", "Info", JOptionPane.INFORMATION_MESSAGE);
//            } else {
//                displayProducts(results);
//            }
//        }
//
//        private void showWishlist() {
//            JOptionPane.showMessageDialog(this, "Fitur Wishlist dalam pengembangan.", "Info", JOptionPane.INFORMATION_MESSAGE);
//        }
//
//        private void showCart() {
//            JOptionPane.showMessageDialog(this, "Fitur Keranjang dalam pengembangan.", "Info", JOptionPane.INFORMATION_MESSAGE);
//        }
//
//        private void showProductDetails(Product product) {
//            JOptionPane.showMessageDialog(this, "Nama: " + product.name + "\nDeskripsi: " + product.description + "\nHarga: Rp " + product.price, "Detail Produk", JOptionPane.INFORMATION_MESSAGE);
//        }
//    }
//}
