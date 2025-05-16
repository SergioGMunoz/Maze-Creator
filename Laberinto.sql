CREATE DATABASE IF NOT EXISTS Laberinto;

USE Laberinto;
DROP TABLE IF EXISTS Ranking;
DROP TABLE IF EXISTS Disposition;
DROP TABLE IF EXISTS Maze;
DROP TABLE IF EXISTS Questions;

CREATE TABLE Questions (
    ID_Questions INT AUTO_INCREMENT PRIMARY KEY ,
    Question VARCHAR(255) NOT NULL,
    AnswerCorrect VARCHAR(255) NOT NULL,
    Answer2 VARCHAR(255)NOT NULL ,
    Answer3 VARCHAR(255) NOT NULL,
    Answer4 VARCHAR(255) NOT NULL
);

CREATE TABLE Maze (
    ID_Maze INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Num_Col INT NOT NULL,
    Num_Row INT NOT NULL,
    NumCrocodiles INT NOT NULL,
    NumMedKit INT NOT NULL,
    DmgCrocodiles INT NOT NULL,
    HpMedKit INT NOT NULL,
    TimeQuestions INT NOT NULL,
    DmgQuestions INT NOT NULL,
    EnableHelp BOOLEAN NOT NULL
);

CREATE TABLE Ranking (
    ID_Ranking INT AUTO_INCREMENT PRIMARY KEY,
    ID_Maze INT,
    User VARCHAR(100) NOT NULL,
    Win BOOLEAN NOT NULL,
    Hp INT NOT NULL,
    FOREIGN KEY (ID_Maze) REFERENCES Maze(ID_Maze) ON DELETE CASCADE
);

CREATE TABLE Disposition (
    ID_Disposition INT NOT NULL,
    ID_Maze INT NOT NULL,
    Col_Maze INT NOT NULL,
    Row_Maze INT NOT NULL,
    Cell_Type ENUM('Free', 'Block', 'Crocodile', 'Medkit') NOT NULL,
    PRIMARY KEY (ID_Disposition, ID_Maze, Col_Maze, Row_Maze),
    FOREIGN KEY (ID_Maze) REFERENCES Maze(ID_Maze) ON DELETE CASCADE
);


-- Crear un usuario para la conexion de java
CREATE USER IF NOT EXISTS  'JavaLaberinto'@'localhost' IDENTIFIED BY 'Java12345';
GRANT ALL PRIVILEGES ON Laberinto.* TO 'JavaLaberinto'@'localhost';
FLUSH PRIVILEGES;

INSERT INTO Questions (Question, AnswerCorrect, Answer2, Answer3, Answer4) VALUES
('¿Cuántos colores tiene el arcoíris?', '7', '5', '10', '3'),
('¿Qué animal dice "Muuu"?', 'La vaca', 'El perro', 'El gato', 'El pato'),
('¿Qué crece en los árboles?', 'Manzanas', 'Zapatos', 'Libros', 'Carros'),
('¿Qué usamos para ver en la oscuridad?', 'Linterna', 'Radio', 'Espejo', 'Reloj'),
('¿Qué planeta es conocido como el "planeta rojo"?', 'Marte', 'Venus', 'Júpiter', 'Saturno'),
('¿Qué hacemos con los oídos?', 'Oír', 'Ver', 'Oler', 'Saborear'),
('¿Qué animal es el rey de la selva?', 'León', 'Elefante', 'Jirafa', 'Mono'),
('¿Cuántos días tiene una semana?', '7', '5', '10', '12'),
('¿Qué bebemos que viene de las vacas?', 'Leche', 'Agua', 'Jugo', 'Refresco'),
('¿Qué forma tiene una pelota de fútbol?', 'Redonda', 'Cuadrada', 'Triangular', 'Rectangular'),
('¿Qué usamos para escribir?', 'Lápiz', 'Cuchara', 'Martillo', 'Tenedor'),
('¿Qué sale de día y se esconde de noche?', 'El sol', 'La luna', 'Las estrellas', 'Las nubes'),
('¿Qué animal tiene una joroba?', 'Camello', 'Tigre', 'León', 'Oso'),
('¿Qué fruta es amarilla y curvada?', 'Plátano', 'Manzana', 'Uva', 'Fresa'),
('¿Qué construyen los pájaros para vivir?', 'Nido', 'Casa', 'Edificio', 'Puente'),
('¿Qué usamos para cortar papel?', 'Tijeras', 'Cuchara', 'Lápiz', 'Libro'),
('¿Qué hace "miau"?', 'El gato', 'El perro', 'El pato', 'La gallina'),
('¿Qué mes celebra la Navidad?', 'Diciembre', 'Julio', 'Abril', 'Septiembre'),
('¿Qué necesitamos para respirar?', 'Aire', 'Agua', 'Tierra', 'Fuego'),
('¿Qué parte del cuerpo usamos para caminar?', 'Pies', 'Manos', 'Ojos', 'Oídos'),
('¿Qué instrumento tiene cuerdas y se toca con las manos?', 'Guitarra', 'Trompeta', 'Tambor', 'Flauta'),
('¿Qué fruta es roja y tiene semillas afuera?', 'Fresa', 'Manzana', 'Naranja', 'Pera'),
('¿Qué animal nada en el agua y tiene aletas?', 'Pez', 'Pájaro', 'Perro', 'Gato'),
('¿Qué usamos para limpiar los dientes?', 'Cepillo de dientes', 'Jabón', 'Toalla', 'Peine'),
('¿Qué estación viene después del invierno?', 'Primavera', 'Verano', 'Otoño', 'Invierno'),
('¿Qué objeto usamos para abrir una puerta?', 'Llave', 'Cuchara', 'Libro', 'Zapato'),
('¿Qué animal da huevos de Pascua?', 'Conejo (simbólico)', 'Gallina', 'Pato', 'Pavo'),
('¿Qué bebida caliente se hace con hojas?', 'Té', 'Leche', 'Jugo', 'Agua'),
('¿Qué planeta es el más cercano al Sol?', 'Mercurio', 'Venus', 'Tierra', 'Marte'),
('¿Qué parte del cuerpo cubrimos con guantes?', 'Manos', 'Pies', 'Cabeza', 'Orejas'),
('¿Qué fruta es verde por fuera y roja por dentro?', 'Sandía', 'Manzana', 'Pera', 'Uva'),
('¿Qué animal tiene rayas blancas y negras?', 'Cebra', 'León', 'Elefante', 'Jirafa'),
('¿Qué usamos para secarnos después de bañarnos?', 'Toalla', 'Cepillo', 'Jabón', 'Peine'),
('¿Qué objeto volamos en el cielo en un día ventoso?', 'Cometa', 'Avión', 'Pájaro', 'Globo'),
('¿Qué hacemos con la nariz?', 'Oler', 'Ver', 'Oír', 'Saborear'),
('¿Qué animal vive en el Ártico y es blanco?', 'Oso polar', 'Tigre', 'León', 'Elefante'),
('¿Qué bebemos cuando tenemos sed?', 'Agua', 'Leche', 'Jugo', 'Refresco'),
('¿Qué forma tiene un cubo de hielo?', 'Cuadrada', 'Redonda', 'Triangular', 'Rectangular'),
('¿Qué usamos para peinarnos?', 'Peine', 'Cepillo de dientes', 'Jabón', 'Toalla'),
('¿Qué animal es el más grande del mundo?', 'Ballena azul', 'Elefante', 'Jirafa', 'Rinoceronte'),
('¿Qué fruta es naranja y jugosa?', 'Naranja', 'Manzana', 'Pera', 'Plátano'),
('¿Qué objeto usamos para ver la hora?', 'Reloj', 'Libro', 'Radio', 'Espejo'),
('¿Qué hacemos con los ojos?', 'Ver', 'Oír', 'Oler', 'Saborear'),
('¿Qué animal tiene un cuello muy largo?', 'Jirafa', 'Elefante', 'León', 'Tigre'),
('¿Qué usamos para protegernos de la lluvia?', 'Paraguas', 'Sombrero', 'Guantes', 'Bufanda'),
('¿Qué planeta es el nuestro?', 'Tierra', 'Marte', 'Venus', 'Júpiter'),
('¿Qué fruta es pequeña, redonda y morada o verde?', 'Uva', 'Manzana', 'Naranja', 'Sandía'),
('¿Qué animal dice "Quiquiriquí"?', 'Gallo', 'Pato', 'Pavo', 'Paloma'),
('¿Qué usamos para cortar el césped?', 'Cortadora de césped', 'Tijeras', 'Cuchillo', 'Hacha'),
('¿Qué objeto usamos para hablar por teléfono?', 'Teléfono', 'Libro', 'Radio', 'Televisor'),
('¿Qué animal vive en el agua y tiene ocho patas?', 'Pulpo', 'Pez', 'Tiburón', 'Ballena'),
('¿Qué fruta es amarilla y ácida?', 'Limón', 'Manzana', 'Pera', 'Plátano'),
('¿Qué usamos para iluminar una habitación?', 'Bombilla', 'Radio', 'Espejo', 'Libro'),
('¿Qué animal es el mejor amigo del hombre?', 'Perro', 'Gato', 'Pájaro', 'Pez'),
('¿Qué bebida se hace con granos tostados?', 'Café', 'Té', 'Leche', 'Jugo'),
('¿Qué objeto usamos para enviar cartas?', 'Sobre', 'Libro', 'Cuaderno', 'Bolígrafo'),
('¿Qué animal tiene una concha dura?', 'Tortuga', 'León', 'Tigre', 'Elefante'),
('¿Qué fruta es roja y tiene un hueso dentro?', 'Durazno', 'Manzana', 'Pera', 'Uva'),
('¿Qué usamos para medir la temperatura?', 'Termómetro', 'Reloj', 'Cinta métrica', 'Báscula'),
('¿Qué animal produce miel?', 'Abeja', 'Mosca', 'Mariposa', 'Araña'),
('¿Qué objeto usamos para cortar pan?', 'Cuchillo', 'Tenedor', 'Cuchara', 'Cucharón'),
('¿Qué planeta tiene anillos?', 'Saturno', 'Marte', 'Venus', 'Tierra'),
('¿Qué fruta es verde por fuera y blanca por dentro con semillas negras?', 'Kiwi', 'Manzana', 'Pera', 'Uva'),
('¿Qué animal es conocido por saltar?', 'Canguro', 'Elefante', 'León', 'Tigre'),
('¿Qué usamos para limpiar el piso?', 'Escoba', 'Cepillo de dientes', 'Peine', 'Toalla'),
('¿Qué objeto usamos para ver programas?', 'Televisor', 'Radio', 'Libro', 'Teléfono'),
('¿Qué animal vive en el agua y tiene branquias?', 'Pez', 'Pájaro', 'Perro', 'Gato'),
('¿Qué fruta es morada y crece en racimos?', 'Uva', 'Manzana', 'Pera', 'Plátano'),
('¿Qué usamos para abrir una lata?', 'Abrelatas', 'Cuchillo', 'Tijeras', 'Martillo'),
('¿Qué animal tiene rayas negras y naranjas?', 'Tigre', 'León', 'Elefante', 'Jirafa'),
('¿Qué objeto usamos para escuchar música?', 'Radio', 'Televisor', 'Libro', 'Espejo'),
('¿Qué planeta es el más grande?', 'Júpiter', 'Tierra', 'Marte', 'Venus'),
('¿Qué fruta es amarilla y alargada?', 'Plátano', 'Manzana', 'Naranja', 'Pera'),
('¿Qué animal vive en una madriguera?', 'Conejo', 'León', 'Tigre', 'Elefante'),
('¿Qué usamos para lavar la ropa?', 'Jabón', 'Cepillo', 'Peine', 'Toalla'),
('¿Qué objeto usamos para sentarnos?', 'Silla', 'Mesa', 'Cama', 'Armario'),
('¿Qué animal tiene un tronco largo?', 'Elefante', 'Jirafa', 'León', 'Tigre'),
('¿Qué fruta es roja y tiene hojas verdes?', 'Fresa', 'Manzana', 'Pera', 'Uva'),
('¿Qué usamos para clavar un clavo?', 'Martillo', 'Destornillador', 'Alicate', 'Llave'),
('¿Qué objeto usamos para dormir?', 'Cama', 'Silla', 'Mesa', 'Armario'),
('¿Qué animal es conocido por su lenta velocidad?', 'Tortuga', 'Liebre', 'León', 'Tigre'),
('¿Qué planeta es conocido como el "planeta azul"?', 'Tierra', 'Marte', 'Venus', 'Júpiter'),
('¿Qué fruta es verde o roja y crujiente?', 'Manzana', 'Pera', 'Plátano', 'Uva'),
('¿Qué animal tiene plumas y puede volar?', 'Pájaro', 'Pez', 'Perro', 'Gato'),
('¿Qué usamos para cubrirnos del frío?', 'Abrigo', 'Sombrero', 'Guantes', 'Bufanda'),
('¿Qué objeto usamos para guardar ropa?', 'Armario', 'Mesa', 'Silla', 'Cama'),
('¿Qué animal vive en el agua y tiene pinzas?', 'Cangrejo', 'Pez', 'Tiburón', 'Ballena'),
('¿Qué fruta es amarilla y muy ácida?', 'Limón', 'Naranja', 'Manzana', 'Pera'),
('¿Qué usamos para sujetar papeles?', 'Clip', 'Cinta', 'Pegamento', 'Tijeras'),
('¿Qué objeto usamos para cocinar?', 'Sartén', 'Plato', 'Taza', 'Cuchara'),
('¿Qué animal es conocido por cambiar de color?', 'Camaleón', 'León', 'Tigre', 'Elefante'),
('¿Qué planeta es el más caliente?', 'Venus', 'Marte', 'Tierra', 'Júpiter'),
('¿Qué fruta es grande, verde por fuera y roja por dentro?', 'Sandía', 'Melón', 'Piña', 'Mango'),
('¿Qué animal tiene alas y hace miel?', 'Abeja', 'Mosca', 'Mariposa', 'Araña'),
('¿Qué usamos para medir cosas?', 'Regla', 'Termómetro', 'Reloj', 'Báscula'),
('¿Qué objeto usamos para escribir en una pizarra?', 'Tiza', 'Lápiz', 'Bolígrafo', 'Marcador'),
('¿Qué animal es el más rápido en tierra?', 'Guepardo', 'León', 'Tigre', 'Elefante'),
('¿Qué fruta es marrón por fuera y verde por dentro?', 'Kiwi', 'Manzana', 'Pera', 'Plátano'),
('¿Qué usamos para cortar madera?', 'Sierra', 'Cuchillo', 'Tijeras', 'Martillo'),
('¿Qué objeto usamos para guardar dinero?', 'Alcancía', 'Cartera', 'Bolso', 'Mochila'),
('¿Qué animal vive en Australia y salta?', 'Canguro', 'Oso', 'León', 'Tigre'),
('¿Qué planeta tiene más lunas?', 'Júpiter', 'Tierra', 'Marte', 'Venus'),
('¿Qué fruta es pequeña, roja y tiene hoyitos?', 'Fresa', 'Manzana', 'Pera', 'Uva'),
('¿Qué animal tiene un caparazón?', 'Tortuga', 'Caracol', 'Cangrejo', 'Langosta'),
('¿Qué usamos para pintar?', 'Pincel', 'Lápiz', 'Bolígrafo', 'Marcador'),
('¿Qué objeto usamos para limpiar los zapatos?', 'Cepillo de zapatos', 'Toalla', 'Jabón', 'Peine'),
('¿Qué animal es el símbolo de Estados Unidos?', 'Águila', 'León', 'Tigre', 'Oso'),
('¿Qué fruta es amarilla y crece en racimos?', 'Plátano', 'Manzana', 'Pera', 'Uva'),
('¿Qué usamos para abrir una botella?', 'Destapador', 'Cuchillo', 'Tijeras', 'Martillo'),
('¿Qué objeto usamos para ver cosas pequeñas?', 'Lupa', 'Espejo', 'Microscopio', 'Telescopio'),
('¿Qué animal vive en el Polo Norte?', 'Oso polar', 'Pingüino', 'Foca', 'Morsa'),
('¿Qué planeta gira más rápido?', 'Júpiter', 'Tierra', 'Marte', 'Venus'),
('¿Qué fruta es roja y tiene semillas afuera?', 'Fresa', 'Manzana', 'Pera', 'Uva');


INSERT INTO Maze (
    Name, Num_Col, Num_Row, NumCrocodiles, NumMedKit, 
    DmgCrocodiles, HpMedKit, TimeQuestions, DmgQuestions, EnableHelp
) VALUES (
    'LaberintoMini', 2, 2, 1, 1, 
    10, 5, 30, 15, FALSE
);

INSERT INTO Disposition (ID_Disposition, ID_Maze, Col_Maze, Row_Maze, Cell_Type) VALUES
(1, 1, 0, 0, 'Free'),
(1, 1, 1, 0, 'Crocodile'),
(1, 1, 0, 1, 'Medkit'),
(1, 1, 1, 1, 'Free');



SELECT * FROM Disposition;
SELECT * FROM Maze;

