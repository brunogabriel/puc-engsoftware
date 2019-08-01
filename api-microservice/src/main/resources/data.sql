-- product_types
INSERT INTO product_type (name) VALUES ('Shampoo');
INSERT INTO product_type (name) VALUES ('Ração');
INSERT INTO product_type (name) VALUES ('Brinquedo');

-- manufactures
INSERT INTO manufacturer (name) VALUES ('Golden');
INSERT INTO manufacturer (name) VALUES ('Whiskas');
INSERT INTO manufacturer (name) VALUES ('Chalesco');
INSERT INTO manufacturer (name) VALUES ('Agener União');

-- products
INSERT INTO product (name, specifications, indication, flavor, manufacturer_id, product_type_id)
VALUES (
    'Ração Golden Especial = 20 KG',
    'Livre de corantes e aromatizantes artificiais',
    'Cães adultos',
    'Frango e Carne',
    (SELECT id FROM manufacturer WHERE name = 'Golden'),
    (SELECT id FROM product_type WHERE name = 'Ração'),
);

INSERT INTO product (name, specifications, indication, flavor, manufacturer_id, product_type_id)
VALUES (
    'Ração Golden Fórmula Senior - 15 KG',
    'Livre de corantes e aromatizantes artificiais',
    'Cães adultos',
    'Frango e Arroz',
    (SELECT id FROM manufacturer WHERE name = 'Golden'),
    (SELECT id FROM product_type WHERE name = 'Ração'),
);

INSERT INTO product (name, specifications, indication, flavor, manufacturer_id, product_type_id)
VALUES (
    'Ração Úmida Whiskas Sachê - 85g',
    'Livre de corantes e aromatizantes artificiais',
    'Gatos adultos',
    'Cordeiro ao molho',
    (SELECT id FROM manufacturer WHERE name = 'Whiskas'),
    (SELECT id FROM product_type WHERE name = 'Ração'),
);

INSERT INTO product (name, specifications, indication, material, manufacturer_id, product_type_id)
VALUES (
    'Brinquedo Chalesco Bola para Cães',
    'Tem textura especial e flexível',
    'Cães de todas as idades',
    'Material super resistente',
    (SELECT id FROM manufacturer WHERE name = 'Chalesco'),
    (SELECT id FROM product_type WHERE name = 'Brinquedo'),
);

INSERT INTO product (name, specifications, indication, manufacturer_id, product_type_id)
VALUES (
    'Shampoo Cloresten Agener União',
    'Age contra infecções causadas por bactérias e fungos',
    'Cães e gatos',
    (SELECT id FROM manufacturer WHERE name = 'Agener União'),
    (SELECT id FROM product_type WHERE name = 'Shampoo'),
);

INSERT INTO product (name, specifications, indication, manufacturer_id, product_type_id)
VALUES (
    'Dermogen Shampoo - 500 mL',
    'Recomendado no banho de pets com grau leve de seborreia',
    'Cães e gatos',
    (SELECT id FROM manufacturer WHERE name = 'Agener União'),
    (SELECT id FROM product_type WHERE name = 'Shampoo'),
);



