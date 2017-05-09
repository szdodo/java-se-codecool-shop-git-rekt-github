INSERT INTO category(id, name, department, description)
    VALUES
      (1,'Tablet', 'Hardware',
            'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.'),
      (2,'Laptop','Hardware','Portable computers used for a variety of purposes.'),
      (3,'Mobile','Hardware','Portable telephones used for a variety of purposes.'),
      (4,'Dog Shelter', 'Outdoor appliances', 'Outdoor structures to protect your pets from the weather'),
      (5,'Drugs','Recreational use','u w4nt some djanga, m8'),
      (6,'JustWat','Fun','Totally retarded things since 97.'),
      (7,'Memes','Fun','no explanation, fak u m8'),
      (8,'FoodSupplement','Health','Protein without limits.');

INSERT INTO supplier(id,name)
    VALUES
      (1,'Amazon'),
      (2,'Lenovo'),
      (3,'HTC'),
      (4,'Cover-Your-Fur'),
      (5,'LG'),
      (6,'Apple'),
      (7,'CornerVendor'),
      (8,'StupidMemes'),
      (9,'Pfizer');

INSERT INTO product(name, defaultprice, defaultcurrency, description,category_id,supplier_id)
    VALUES ('Amazon Fire', 49.9, 'USD', 'Fantastic price. Large content ecosystem. Good parental controls.', 1, 1),
        ('Lenovo IdeaPad', 479, 'USD', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports.', 1, 2),
        ('Amazon Fire HD 8', 220, 'USD', 'Amazon"s latest Fire HD 8 tablet is a great value for media consumption.', 1, 1),
        ('Asus R13', 190, 'USD', 'Amazon"s latest Fire HD 8 tablet is a great value for media consumption.', 2, 1),
        ('HTC H5', 520, 'USD', 'HTC"s latest H5 mobile is a great value for media consumption.', 3, 3),
        ('Lenovo K3', 410, 'USD', 'Lenovo"s latest K3 mobile is a great value for media consumption.', 3, 2),
        ('Papillon Mark II', 99.9, 'USD', 'Basic dog shelter made from sturdy wood. 5 year warranty.', 4, 4),
        ('Weed', 30, 'USD', 'you have the money, i have the means smoek weeeed evry daaaay', 5, 7),
        ('LG G6', 560, 'USD', 'Big screen. Small Phone. Blue sky. Green planet.', 3, 5),
        ('LG G5', 440, 'USD', '5.3\ IPS Quantum QHD Display & Metal Body and shit', 3, 5),
        ('Apple iPhone', 800, 'USD', 'I dont have enough money but I still wanna look rich', 3, 6),
        ('Apple MacBook', 1199, 'USD', 'I have too much money and I have no idea how to spend it responsibly', 2, 6),
        ('Flector Rapid', 10, 'USD', 'yo dawg i herd u liek pain so i brought yo soem painkillaz', 5, 9),
        ('Unsureness', 3333, 'USD', 'General Inability To Decide, fresh from Murica', 6, 8),
        ('Poo Puff', 0.5, 'USD', 'wat are you talking about, it definitely wasnt me', 6, 8),
        ('Obi-Wan Kenobi', 11.5, 'USD', 'these are definitely not the droids you are looking for', 7, 8),
        ('BIO WHEY 100', 12, 'USD', 'BiotechUSA protein at its finest. 100% refined beef collagen whey', 8, 7),
        ('Ultra-Super Pack', 122, 'USD', 'A fine selection of quality protein products. Arnold would surely buy this', 8, 7);

