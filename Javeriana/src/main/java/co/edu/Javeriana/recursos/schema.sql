CREATE TABLE IF NOT EXISTS estudiante (
                                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                          nombre VARCHAR(255),
    apellido VARCHAR(255),
    correo VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS materia (
                                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                       nombre VARCHAR(255),
    creditos INT
    );

CREATE TABLE IF NOT EXISTS nota (
                                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                    estudiante_id BIGINT,
                                    materia_id BIGINT,
                                    observacion VARCHAR(255),
    valor DOUBLE,
    porcentaje DOUBLE
    );
