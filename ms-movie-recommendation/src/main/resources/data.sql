-- Insertar recomendaciones
INSERT INTO recomendaciones (usuario_id, pelicula_id, razon_recomendacion, puntuacion_predicha, visualizada, fecha_recomendacion) VALUES
(1, 101, 'Te gusta la ciencia ficción y Christopher Nolan', 4.5, false, NOW()),
(1, 102, 'Película clásica de ciencia ficción similar a tus preferencias', 4.2, false, NOW()),
(2, 103, 'Tu género favorito: crimen y thriller', 4.8, true, NOW()),
(2, 104, 'Obra maestra del cine que todo cinéfilo debe ver', 4.9, false, NOW()),
(3, 105, 'Combina ciencia ficción con drama familiar', 4.6, false, NOW()),
(3, 106, 'Director reconocido por narrativas complejas', 4.4, true, NOW());