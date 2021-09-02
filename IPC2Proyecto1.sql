CREATE DATABASE muebles;
USE muebles;
#check
CREATE TABLE empleado(
	codigo INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100),
    area INT,
    contraseña VARCHAR(100),
    dpi CHAR(13),
    telefono CHAR(8),
    direccion VARCHAR(200),
    fecha_nacimiento DATE,
    salario DOUBLE,
    fecha_contratacion DATE,
    CONSTRAINT pk_empleado PRIMARY KEY (codigo)
);


#check
CREATE TABLE punto_venta(
    codigo INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100),
    direccion VARCHAR(100),
    telefono VARCHAR (8),
    CONSTRAINT pk_punto_venta PRIMARY KEY (codigo)
);

CREATE TABLE caja(
    codigo INT NOT NULL AUTO_INCREMENT,
    capital DOUBLE,
    punto_venta_codigo INT NOT NULL,
    CONSTRAINT pk_caja PRIMARY KEY (codigo),
    CONSTRAINT fk_punto_caja FOREIGN KEY (punto_venta_codigo)
    REFERENCES punto_venta(codigo)
);
# check
CREATE TABLE cliente(
    codigo INT NOT NULL AUTO_INCREMENT, 
    nit CHAR(8),
    nombre VARCHAR(100),
    telefono CHAR(8),
    direccion VARCHAR(200),
    CONSTRAINT pk_cliente PRIMARY KEY (codigo)
);

CREATE TABLE venta(
    codigo INT NOT NULL AUTO_INCREMENT,
    total DOUBLE,
    fecha DATE,
    punto_venta_codigo INT NOT NULL,
    empleado_codigo INT NOT NULL,
    cliente_codigo INT NOT NULL,
    CONSTRAINT pk_venta PRIMARY KEY (codigo),
    CONSTRAINT fk_venta_punto FOREIGN KEY (punto_venta_codigo) 
    REFERENCES punto_venta(codigo),
    CONSTRAINT fk_venta_empleado FOREIGN KEY (empleado_codigo)  
    REFERENCES empleado(codigo),
    CONSTRAINT fk_venta_cliente FOREIGN KEY (cliente_codigo) 
    REFERENCES cliente(codigo)
);

CREATE TABLE devolucion(
    codigo INT NOT NULL AUTO_INCREMENT,
    fecha DATE,
    total DOUBLE,
    venta_codigo INT NOT NULL,
    CONSTRAINT pk_devolucion PRIMARY KEY (codigo),
    CONSTRAINT fk_devolucion_venta FOREIGN KEY (venta_codigo)
    REFERENCES venta(codigo) 
);

CREATE TABLE pieza(
    codigo INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100),
    CONSTRAINT pk_pieza PRIMARY KEY (codigo)
);
ALTER TABLE mueble DROP PRIMARY KEY, ADD PRIMARY KEY(nombre);
CREATE TABLE mueble(
	modelo VARCHAR(8),
    nombre VARCHAR(100),
    precio DOUBLE,
    costo DOUBLE,
    CONSTRAINT pk_mueble PRIMARY KEY (nombre)
);


CREATE TABLE mueble_ensamblado (
    codigo INT NOT NULL AUTO_INCREMENT,
    empleado_codigo INT NOT NULL,
    punto_venta_codigo INT,
    mueble_modelo VARCHAR(8),
    costo INT,
    CONSTRAINT pk_mueble_ensamblado PRIMARY KEY (codigo),
    CONSTRAINT fk_mueble_empleado FOREIGN KEY (empleado_codigo)
    REFERENCES empleado(codigo), 
    CONSTRAINT fk_mueble_punto FOREIGN KEY (punto_venta_codigo)
    REFERENCES punto_venta(codigo),
    CONSTRAINT fk_mueble_modelo FOREIGN KEY (mueble_modelo)
    REFERENCES mueble(modelo) 
);

CREATE TABLE compra(
    codigo INT NOT NULL AUTO_INCREMENT,
    fecha DATE,
    total DOUBLE,
    punto_venta_codigo INT NOT NULL, 
    CONSTRAINT pk_compra PRIMARY KEY (codigo),
    CONSTRAINT fk_punto_compra FOREIGN KEY (punto_venta_codigo) 
    REFERENCES punto_venta(codigo)
);

CREATE TABLE pieza_almacenada(
    codigo INT NOT NULL AUTO_INCREMENT,
    costo DOUBLE,
    pieza_codigo INT NOT NULL,
    compra_codigo INT,
    mueble_ensamblado_codigo INT,
    CONSTRAINT pk_pieza_almacenada PRIMARY KEY (codigo),
    CONSTRAINT fk_pieza_tipo FOREIGN KEY (pieza_codigo)
    REFERENCES pieza(codigo),
    CONSTRAINT fc_almacenada_compra FOREIGN KEY (compra_codigo)
    REFERENCES compra(codigo),
    CONSTRAINT fk_pieza_mueble FOREIGN KEY (mueble_ensamblado_codigo)
    REFERENCES mueble_ensamblado(codigo)   
);

CREATE TABLE movimiento(
    codigo INT NOT NULL AUTO_INCREMENT,
    monto DOUBLE,
    resultado DOUBLE,
    venta_codigo INT,
    compra_codigo INT,
    caja_codigo INT,
    CONSTRAINT pk_movimiento PRIMARY KEY (codigo),
    CONSTRAINT fk_movimiento_venta FOREIGN KEY (venta_codigo)
    REFERENCES venta(codigo), 
    CONSTRAINT fk_movimiento_compra FOREIGN KEY (compra_codigo)
    REFERENCES compra(codigo), 
    CONSTRAINT fk_movimiento_caja FOREIGN KEY (caja_codigo)
    REFERENCES caja(codigo) 
);

CREATE TABLE lote_venta(
    codigo INT NOT NULL AUTO_INCREMENT,
    mueble_ensamblado_codigo INT,
    venta_codigo INT,
    CONSTRAINT pk_lote PRIMARY KEY (codigo),
    CONSTRAINT fk_ensamblado_codigo FOREIGN KEY (mueble_ensamblado_codigo)
    REFERENCES mueble_ensamblado(codigo), 
    CONSTRAINT fk_lote_venta FOREIGN KEY (venta_codigo)
    REFERENCES venta(codigo) 
);

CREATE TABLE diseño (
	codigo INT NOT NULL AUTO_INCREMENT,
    modelo_mueble VARCHAR(8),
    pieza_codigo INT,
    cantidad INT,
    CONSTRAINT pk_diseño PRIMARY KEY(codigo),
    CONSTRAINT fk_diseño_mueble FOREIGN KEY (modelo_mueble) REFERENCES mueble(modelo),
    CONSTRAINT fk_diseño_pieza FOREIGN KEY (pieza_codigo) REFERENCES pieza(codigo)
);

INSERT INTO caja (capital, punto_venta_codigo) VALUES (10000, 2);

ALTER TABLE devolucion ADD 
mueble_devuelto INT;
ALTER TABLE devolucion ADD CONSTRAINT fk_mueble_devuelto 
FOREIGN KEY (mueble_devuelto) REFERENCES lote_venta(mueble_ensamblado_codigo);

use muebles;
create view empleados_fabrica AS
select * from empleado where area = 1;

CREATE VIEW empleados_finanzas AS
select * from empleado where area = 3;

CREATE VIEW piezas_listas AS
select a.codigo as codigo, p.codigo as pieza, p.nombre as tipo,a.costo as costo, a.compra_codigo as compra, a.mueble_ensamblado_codigo as mueble from pieza as p right join pieza_almacenada as a on p.codigo = a.pieza_codigo WHERE a.mueble_ensamblado_codigo IS NULL;


CREATE VIEW diseño_listo AS
SELECT p.codigo, p.nombre, d.cantidad, d.modelo_mueble FROM pieza as p INNER JOIN diseño as d ON p.codigo = d.pieza_codigo;

CREATE VIEW piezas_disponibles AS
SELECT pieza, tipo, COUNT(costo) AS cantidad, costo FROM piezas_listas WHERE mueble IS NULL GROUP BY costo;
select * from diseño;

CREATE VIEW coincidencias AS
select p.pieza, p.tipo, p.cantidad as disponibles, p.costo, d.modelo_mueble, d.cantidad as necesarias from piezas_disponibles as p inner join diseño as d on p.pieza = d.pieza_codigo;

select * from coincidencias;
select * from pieza_almacenada;
select * from piezas_disponibles;
select * from piezas_listas;

CREATE VIEW muebles_disponibles AS
SELECT v.codigo, v.cantidad, v.venta_codigo, m.codigo as ensamble_codigo, m.empleado_codigo, m.punto_venta_codigo, m.mueble_modelo FROM lote_venta as v RIGHT JOIN mueble_ensamblado m ON v.mueble_ensamblado_codigo = m.codigo WHERE v.mueble_ensamblado_codigo IS NULL;

CREATE VIEW muebles_disponibles AS
SELECT  m.nombre, m.precio, m.modelo, e.costo, m.costo as costo_default , e.codigo, e.empleado_codigo, e.punto_venta_codigo FROM mueble as m INNER JOIN mueble_ensamblado as e ON m.modelo = e.mueble_modelo LEFT JOIN lote_venta as l ON l.mueble_ensamblado_codigo = e.codigo WHERE l.mueble_ensamblado_codigo IS NULL;

CREATE VIEW mueble_venta AS
SELECT COUNT(modelo) AS disponibles , nombre, precio, modelo, costo, costo_default, codigo, empleado_codigo, punto_venta_codigo FROM muebles_disponibles;


select * from muebles_disponibles;
SELECT * FROM mueble_venta;
select * from pieza_almacenada;
SELECT * FROM mueble_ensamblado ORDER BY codigo;
SELECT * FROM muebles_disponibles;		
SELECT * FROM cliente;
SELECT * FROM movimiento;
SELECT * FROM muebles_disponibles;
SELECT * FROM venta;

