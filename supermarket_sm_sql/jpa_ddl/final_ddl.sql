SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL'; 
/*
LOCK TABLES `prooveedor`  WRITE;
DROP TABLE IF EXISTS prooveedor;

LOCK TABLES `articulo`  WRITE;
DROP TABLE IF EXISTS `articulo`;

LOCK TABLES `imagen_articulo`  WRITE;
DROP TABLE IF EXISTS `imagen_articulo`;

LOCK TABLES `sucursal`  WRITE;
DROP TABLE IF EXISTS `sucursal`;

LOCK TABLES `articulo_de_sucursal`  WRITE;
DROP TABLE IF EXISTS `articulo_de_sucursal`;


LOCK TABLES `departamento`  WRITE;

DROP TABLE IF EXISTS `departamento`;

LOCK TABLES `empleado`  WRITE;
DROP TABLE IF EXISTS `empleado`;

LOCK TABLES `cliente`  WRITE;
DROP TABLE IF EXISTS `cliente`;

LOCK TABLES `detalle_factura`  WRITE;
DROP TABLE IF EXISTS `detalle_factura`;
LOCK TABLES `encabezado_factura`  WRITE;
DROP TABLE IF EXISTS `encabezado_factura`;
#START TO CREATE TABLES
DROP DATABASE IF EXISTS supermarket_sm;
 
CREATE SCHEMA IF NOT EXISTS supermarket_sm; */
use supermarket_sm;
CREATE TABLE IF NOT EXISTS prooveedor(
	cod_provedr INTEGER NOT NULL AUTO_INCREMENT, 
	nombre  VARCHAR(250)  NOT NULL,
	correo VARCHAR(250) NOT NULL,
	telef VARCHAR(250) NOT NULL ,
	direccion VARCHAR(250) NOT NULL ,
	logo TEXT NOT NULL ,
	web VARCHAR(250) NOT NULL,
	CONSTRAINT cnstr_proovedor_pk PRIMARY KEY (cod_provedr)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS articulo(
	cod_articl  INTEGER NOT NULL AUTO_INCREMENT,
	nombre MEDIUMTEXT NOT NULL,
	prec_unitar SMALLINT  NOT NULL, 
    impuesto DECIMAL(6,4) NOT NULL , 
	categoria VARCHAR(230) NOT NULL ,
    codProvedor INTEGER NOT NULL,
	CONSTRAINT cnstr_articulo_pk PRIMARY KEY (cod_articl )
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS imagen_articulo(
	idImagenArticulo INTEGER NOT NULL AUTO_INCREMENT,
    imagen MEDIUMTEXT NOT NULL ,
    descripcion MEDIUMTEXT ,
    codArtic INTEGER NOT NULL,
    CONSTRAINT cnstr_imagen_articulo_pk PRIMARY KEY (idImagenArticulo )
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS sucursal(
	cod_sucursl INTEGER NOT NULL AUTO_INCREMENT,
	nombre TEXT  NOT NULL,
	direccion TEXT NOT NULL,
	telef VARCHAR(200) NOT NULL, 
	CONSTRAINT cnstr_sucursal_pk PRIMARY KEY (cod_sucursl)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS articulo_de_sucursal(
	idArtSuc INTEGER NOT NULL AUTO_INCREMENT,
    stock INTEGER  ,
    codSucursl INTEGER NOT NULL,
    codArticulo INTEGER NOT NULL,
    CONSTRAINT articulo_de_sucursal_pk PRIMARY  KEY(idArtSuc)
);

CREATE TABLE IF NOT EXISTS departamento(
	cod_departmt INTEGER NOT NULL AUTO_INCREMENT ,
    nombre VARCHAR(240)  NOT NULL,
	correo TEXT NOT NULL,
	telef VARCHAR(230) NOT NULL ,
    codSucrsl INTEGER NOT NULL,
	CONSTRAINT cnstr_deprtmnt_pk PRIMARY KEY(cod_departmt)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS empleado(
	id_empled INTEGER AUTO_INCREMENT ,
	nombre VARCHAR(240) NOT NULL, 
	clave LONGTEXT NOT NULL,
	role VARCHAR(240) NOT NULL,
	correo TEXT NOT NULL,
    salario FLOAT NOT NULL,
    codDepart INTEGER NOT NULL  ,
    CONSTRAINT cnstr_emplead_pk PRIMARY KEY(id_empled)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS cliente(
	id_client INTEGER NOT NULL AUTO_INCREMENT ,
    nombre VARCHAR(240) NOT NULL,
    cedula VARCHAR(240) ,
    correo MEDIUMTEXT ,
    nombreDeUsuario MEDIUMTEXT ,
    clave MEDIUMTEXT ,
    telefono MEDIUMTEXT ,
	cantidadDeCompras INTEGER,
    CONSTRAINT cnstr_client_pk PRIMARY KEY(id_client)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS detalle_factura (
    no_detall_fact INTEGER NOT NULL AUTO_INCREMENT, 
    cantTotDeArtic INTEGER NOT NULL,
    codArticulo INTEGER NOT NULL ,
    CONSTRAINT cnstr_detalle_fact_pk PRIMARY KEY(no_detall_fact)
) DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS encabezado_factura(
	id_encbzd INTEGER NOT NULL AUTO_INCREMENT,
	precioTotal INTEGER NOT NULL , 
    impuestoDeVenta DECIMAL(6,4) NOT NULL,
	fechaCompra TIMESTAMP NOT NULL,
	idCliente INTEGER  NOT NULL, 
    empleadAtiend INTEGER NOT NULL,  
    noDetallFactr INTEGER NOT NULL,
    CONSTRAINT cnstr_encabezado_fact_pk PRIMARY KEY(id_encbzd  )	
) DEFAULT CHARSET=utf8;

ALTER TABLE articulo ADD CONSTRAINT constr_article_provider_fk FOREIGN KEY (codProvedor) REFERENCES prooveedor (cod_provedr) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE articulo_de_sucursal ADD CONSTRAINT constr_articulo_de_sucursal_codigo_sucursal_fk FOREIGN KEY (codSucursl) REFERENCES sucursal (cod_sucursl) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE articulo_de_sucursal ADD CONSTRAINT constr_articulo_de_sucursal_codigo_articulo_fk FOREIGN KEY (codArticulo) REFERENCES articulo (cod_articl) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE departamento ADD CONSTRAINT constr_deprtm_sucrsl_fk FOREIGN KEY (codSucrsl) REFERENCES sucursal(cod_sucursl) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE empleado ADD CONSTRAINT constr_empld_deprt_fk FOREIGN KEY (codDepart) REFERENCES departamento(cod_departmt) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE detalle_factura ADD CONSTRAINT constr_detalle_fact_fk FOREIGN KEY (codArticulo) REFERENCES articulo(cod_articl);
ALTER TABLE encabezado_factura ADD CONSTRAINT constr_encbz_fact_id_cliente_fk FOREIGN KEY (idCliente) REFERENCES cliente(id_client) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE encabezado_factura ADD CONSTRAINT constr_encbz_fact_id_empleado_fk FOREIGN KEY (empleadAtiend) REFERENCES empleado(id_empled) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE encabezado_factura ADD CONSTRAINT constr_encbz_fact_no_detalle_factura_fk FOREIGN KEY (noDetallFactr) REFERENCES detalle_factura(no_detall_fact) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE imagen_articulo ADD CONSTRAINT constr_imagen_articulo_id_articulo_fk FOREIGN KEY (codArtic) REFERENCES articulo (cod_articl) ON DELETE CASCADE ON UPDATE CASCADE;
#END CREATE TABLES
LOCK TABLES `prooveedor`  WRITE;
#START TO INSERT DATA 
INSERT INTO `prooveedor` (`cod_provedr`,`nombre`,`correo`,`telef`,`direccion`,`logo`,`web`) VALUES (1,'Dos Pinos','info@dospinos.com','+506 2437 3000','Del Aeropuerto Internacional Juan Santa Maria 7k al Oeste','logo_dos-pinos.png','http://www.dospinos.com/');
INSERT INTO `prooveedor` (`cod_provedr`,`nombre`,`correo`,`telef`,`direccion`,`logo`,`web`) VALUES (2,'Café 1820','info@cafe1820.com','2233-8544','Heredia,Hotel de Paso La Valencia, 106','logo-cafe1820.png','http://cafe1820.com/es/');
INSERT INTO `prooveedor` (`cod_provedr`,`nombre`,`correo`,`telef`,`direccion`,`logo`,`web`) VALUES (3,'Sardimar','info@sardimar.com','2742-4810',' Parque Empresarial Forum 1, Edificio C, Tercer Piso San José, Costa Rica.','logo-sardimar.png','www.sardimar.com');
INSERT INTO `prooveedor` (`cod_provedr`,`nombre`,`correo`,`telef`,`direccion`,`logo`,`web`) VALUES (4,'Don Pedro','servicioalcliente@kani.cr','(506) 2537-0102',' Alto de Ochomogo, Cartago. De la entrada al plantel de carga de combustibles de Recope, 500 metros norte, 200 metros noroeste y 200 metros oeste.','logo-frijoles-don-pedro.png','http://www.donpedro.co.cr/');
INSERT INTO `prooveedor` (`cod_provedr`,`nombre`,`correo`,`telef`,`direccion`,`logo`,`web`) VALUES (5,'Nestle','info@nestle.com','(506) 2490-98-23','Del Parque de la Democracia 900m este ,San José','logo-nestle.png','www.nestle.com');
INSERT INTO `prooveedor` (`cod_provedr`,`nombre`,`correo`,`telef`,`direccion`,`logo`,`web`) VALUES (6,'Tesoro del Mar','info@tesorodelmar.com','(506) 2490-23-75','Curridabat Centro,del Epa 500m sur y 100 norte','logo-tesoro-del-mar.png','www.tesorodelmar.com');
INSERT INTO `prooveedor` (`cod_provedr`,`nombre`,`correo`,`telef`,`direccion`,`logo`,`web`) VALUES (7,'Jacks','info@jacks.com','(506) 2850-80-40','Del Banco Popular de San Pedro ,100m este ,San José','logo-jacks.png','www.jacks.com');
INSERT INTO `prooveedor` (`cod_provedr`,`nombre`,`correo`,`telef`,`direccion`,`logo`,`web`) VALUES (8,'Riviana Pozuelo','info@rivianapozuelo.com','(506) 4560-86-23','Del Puente Juan Pablo Segiun 300m al Norte ,carretera a Heredia','logo-riviana-pozuelo.png','www.rivianapozuelo.com');
INSERT INTO `prooveedor` (`cod_provedr`,`nombre`,`correo`,`telef`,`direccion`,`logo`,`web`) VALUES (9,'Lizano','info@lizano.com','(506)4509-98-93','Del Estadio Ricardo Saprisa 300m este y 25 norte','logo-lizano.png','www.lizano.com');
INSERT INTO `prooveedor` (`cod_provedr`,`nombre`,`correo`,`telef`,`direccion`,`logo`,`web`) VALUES (10,'Atún Calvo','info@atun-calvo.com','(506)2374-95-86','Del Hospital San Juan de De Dios 800 oeste','logo-atun-calvo.png','www.atuncalvo.png');
INSERT INTO `prooveedor` (`cod_provedr`,`nombre`,`correo`,`telef`,`direccion`,`logo`,`web`) VALUES (11,'Pastas Roma','info@pastas-roma.com','(506) 2645-75-90','300 sur y 50 norte de la Torre Mercedes Benz,San Jose','logo-pastas-roma.png','www.pastasroma.com');
UNLOCK TABLES;
 
LOCK TABLES `articulo`  WRITE;

INSERT INTO `articulo` VALUES (1,'Helado Dos Pinos Deleite de Naranja',2700,0.5000,'Lácteos',1),
(2,'Café 1820 Molido Tueste Claro',2300,0.3500,'Bebidas en Polvo',2),
(3,'Atún Sardimar con Vegetales Pequeño',1900,0.7500,'Alimentos Enlatados',3),
(4,'Bolsa Pequeña de Frijoles Negros',2300,0.6500,'Alimentos Enlatados',4),
(5,'Queso Crema Light',2500,0.1500,'Lácteos',1),
(6,'Yougurt de Fresa',2300,0.3000,'Lácteos',1),
(7,'Queso Crema Pequeño',1900,0.3500,'Lácteos',1),
(8,'Queso Crema Grande',2900,0.2500,'Lácteos',1),
(9,'Jugo de Naranja',2600,0.4000,'Bebidas',1);

UNLOCK TABLES;

LOCK TABLES `imagen_articulo`  WRITE;

INSERT INTO imagen_articulo VALUES
(1,'deleite-naranja-01.png','Delicioso Helado de Naranja con Chispas de Chocolate',1),
(2,'cafe-1820-molido-tueste-claro-01.png','Delicioso Helado de Naranja con Chispas de Chocolate',2),
(3,'atun-con-vegetales-peque-01.png','Atún con Vegetales Pequeño',3),
(4,'frijoles-negros-01.png','Bolsa Pequeña de Frijoles Negros,900g de granos 100% naturales. ',4);

UNLOCK TABLES;

LOCK TABLES `sucursal`  WRITE;
 
INSERT INTO sucursal VALUES
(1,'Sucursal de San Padro de Montes de Oca','Del Mall San Pedro 100 metros este','2860-76-89'),
(2,'Sucursal de Tres ríos en Cartago','Del Parque Central 500 metros oeste','2394-57-49'),
(3,'Sucursal de Heredia Centro','Del Parque del Carmen 300 metros norte y 50 sur','2564-16-73'),
(4,'Sucursal de Tibas en San José','Del Banco Nacional 100 metros sur y 500 este','2347-68-03');

UNLOCK TABLES;

LOCK TABLES `articulo_de_sucursal`  WRITE;

INSERT INTO articulo_de_sucursal VALUES/* idArtSuc INTEGER AUTO_INCREMENT,stock INTEGER  ,codSucursl INTEGER , codArticulo INTEGER */
(1,10,1,1),
(2,70,1,2),
(3,200,1,3),
(4,90,1,4);

UNLOCK TABLES;
 
LOCK TABLES `departamento`  WRITE;
 
INSERT INTO departamento VALUES
(1,'Distribuidores','distribuidores@supersm.cr','2564-74-23',1),
(2,'Cajeros','cajeros@supersm.cr','2586-62-42',1),
(3,'Bodegas','bodega@supersm.cr','2473-48-77',1),
(4,'Gerencias','gerencia@supersm.cr','2541-89-04',1);

 UNLOCK TABLES;
 
LOCK TABLES `empleado` WRITE;

INSERT INTO empleado VALUES /* id_empled INTEGER AUTO_INCREMENT ,nombre VARCHAR(240) , clave LONGTEXT ,role VARCHAR(240) ,
correo TEXT , salario FLOAT ,codDepart INTEGER  */
(1,'Oscar Madrigal Sandoval','loji#6%(&hhV53595','Distribuidor','oscarmadrigal@supersm.cr',400000,1),
(2,'Gabriela Badilla Gonzales','gch$453^#LKEDR59','Cajero','gabrielabadilla@supersm.cr',500000,2),
(3,'Olman Guzman Arias','53t3l3t1@KsdfEDR59','Distribuidor','olmanguzman@supersm.cr',400000,1),
(4,'Marcela Duarte Saenz','@#repr3t3l45398R59','Cajero','marceduartesaenz@supersm.cr',500000,2),
(5,'Allan Rivera Molina','jlsr3t3lsdp8R63','Cajero','allanriveramolina@supersm.cr',800000,2),
(6,'Diego Morera Ruiz','husr657#$3lhjodp834hkopl','Supervisor','diegomoreraruiz@supersm.cr',700000,4),
(7,'Francisco Hernandez Piedra','isfsdf546#$3lhjod9&%baoppkopl','Empacador','franciscohernandezpiedra@supersm.cr',380000,3);

UNLOCK TABLES;

LOCK TABLES `cliente` WRITE;
 

INSERT INTO cliente VALUES
/* id_client INTEGER  , nombre VARCHAR(240),cedula VARCHAR(240) , correo MEDIUMTEXT ,nombreDeUsuario MEDIUMTEXT , clave MEDIUMTEXT , telefono MEDIUMTEXT ,	cantidadDeCompras INTEGER, */
(1,'Juan Gonzáles Sanchez','1145-05-364','juangozl12@gmail.com','juangonzalezCR','juangwedf12','853-56-43',1),
(2,'Jhonny Duarte Badilla','1144-02-654','jhonyduart245@gmail.com','jhonyDuarte1832','fdgj879sff','843-57-65',1),
(3,'Arturo Solano Gutierrez','1142-12-742','arturo32409sd@gmail.com','arturoSolanoSJ98','ghf923hfs','878-45-03',1),
(4,'María Ortega Díaz','1149-23-350','maria72345@gmail.com','mariaOrtega874H','1444jfsjd','853-56-43',1),
(5,'Marta Moreira Segura','1153-67-489','martamore874@gmail.com','martaMoreiraSeg09','1r4dfggozl12','883-56-43',1),
(6,'Mónica Barquero Segura','1135-70-457','monicabar87@gmail.com','monicaBarquero1232','pgn42zl12','893-56-43',1),
(7,'Luis Saenz Sandoval','1146-75-325','luissaen9765l12@gmail.com','luisSaenz2343','276jzl12','886-29-97',1),
(8,'Carlos Castro Segura','1144-19-634','carloscastr134@gmail.com','carlosCastro97324','sdf32df','829-64-53',1),
(9,'Jose Pablo Ruiz Hernandez','1143-73-592','josepruiz678@hotmail.com','josePabloRui8612','80hdf876','801-20-03',1);

UNLOCK TABLES;


LOCK TABLES `detalle_factura` WRITE;

INSERT INTO detalle_factura VALUES/*
no_detall_fact INTEGER  AUTO_INCREMENT, cantTotDeArtic INTEGER ,codArticulo INTEGER   */
(1,1,1),
(2,2,2),
(3,1,3),
(4,2,4),
(5,1,1),
(6,2,2),
(7,1,3),
(8,2,4),
(9,3,3);

UNLOCK TABLES;
 
LOCK TABLES `encabezado_factura` WRITE;

INSERT INTO encabezado_factura VALUES/*
id_encbzd INTEGER AUTO_INCREMENT,precioTotal INTEGER  , impuestoDeVenta DECIMAL(6,4) ,
fechaCompra TIMESTAMP ,idCliente INTEGER , empleadAtiend INTEGER ,   noDetallFactr INTEGER */
(1,2700,0.60,'2017-04-05 18:19:03',1,1,1),
(2,4600,0.60,'2017-04-05 18:19:03',2,2,2),
(3,2700,0.60,'2017-04-05 18:19:03',3,3,3),
(4,4600,0.60,'2017-04-05 18:19:03',4,4,4),
(5,2700,0.60,'2017-04-05 18:19:03',5,5,5),
(6,4600,0.60,'2017-04-05 18:19:03',6,1,6),
(7,1900,0.60,'2017-04-05 18:19:03',7,2,7),
(8,4600,0.60,'2017-04-05 18:19:03',8,3,8),
(9,5700,0.60,'2017-04-05 18:19:03',9,4,9);
UNLOCK TABLES;

UPDATE `supermarket_sm`.`prooveedor` SET `logo`='logo-dos-pinos.jpg' WHERE `cod_provedr`='1';
UPDATE `supermarket_sm`.`prooveedor` SET `logo`='logo-cafe_1820.jpg' WHERE `cod_provedr`='2';
UPDATE `supermarket_sm`.`prooveedor` SET `logo`='logo_Don_Pedro.png' WHERE `cod_provedr`='4';
UPDATE `supermarket_sm`.`prooveedor` SET `logo`='logo-lizano.jpg' WHERE `cod_provedr`='9';
UPDATE `supermarket_sm`.`prooveedor` SET `logo`='logo-calvo.png' WHERE `cod_provedr`='10';
UPDATE `supermarket_sm`.`prooveedor` SET `logo`='logo-Pastas-Roma.jpg' WHERE `cod_provedr`='11';


#END TO INSERT DATA 

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

#Consultas 

#muestra los empleados que pertenencen al departamento 2
SELECT dep.cod_departmt,dep.nombre,em.nombre,em.salario
from departamento dep, empleado em
WHERE em.codDepart=dep.cod_departmt and dep.nombre='Cajeros';

#muestra a todos los empleados que pertenecen a la sucursal de sanpedro
SELECT su.nombre,dep.cod_departmt,dep.nombre,em.nombre,su.cod_sucursl
from departamento dep, empleado em, sucursal su
WHERE em.codDepart=dep.cod_departmt and su.cod_sucursl= dep.codSucrsl and  su.cod_sucursl='1';

#muestra el promedio del salario de los empleados del departamento 2
SELECT dep.cod_departmt,dep.nombre, AVG(salario)promedio
from departamento dep, empleado em
WHERE em.codDepart=dep.cod_departmt and em.codDepart='2';

#muestra el stock y los articulos de la sucursal 1 
SELECT su.cod_sucursl,su.nombre, ar.nombre,ar.prec_unitar,ars.stock
from sucursal su, articulo ar, articulo_de_sucursal ars
WHERE su.cod_sucursl=ars.codSucursl and ars.codArticulo=ar.cod_articl and su.cod_sucursl='1';

#muestra la factura numero 1
SELECT enc.id_encbzd,enc.fechaCompra,em.nombre, cl.nombre,cl.cedula, det.cantTotDeArtic,ar.nombre,enc.precioTotal
from encabezado_factura enc, cliente cl, empleado em,detalle_factura det, articulo ar
WHERE enc.idCliente=cl.id_client and enc.empleadAtiend=em.id_empled and enc.noDetallFactr=det.no_detall_fact
and det.codArticulo=ar.cod_articl and enc.id_encbzd='1';



#INSERTS ARTICULOS
INSERT INTO `articulo` VALUES (10,'Leche Condesada',1500,0.5000,'Lácteos',5),
(11,'Atún Tesoro Del Mar',1200,0.3500,'Alimentos Enlatados',6),
(12,'Meneitos',300,0.1500,'Golosinas',7),
(13,'Cremas',800,0.6500,'Golosinas',8),
(14,'Salsa Lizano',900,0.1500,'Salsas',9),
(15,'Atún Calvo',1200,0.3000,'Alimentos Enlatados',10),
(16,'Canelones',1100,0.3500,'Pastas',11),

#INSERTS articulo_sucursal
INSERT INTO articulo_de_sucursal VALUES/* idArtSuc INTEGER AUTO_INCREMENT,stock INTEGER  ,codSucursl INTEGER , codArticulo INTEGER */
(1,10,1,1),
(2,70,1,2),
(3,200,1,3),
(4,90,1,4),
(5,10,1,5),
(6,70,1,6),
(7,200,1,7),
(8,90,1,8),
(9,10,1,9),
(10,70,1,10),
(11,200,1,11),
(12,90,1,12),
(13,10,1,13),
(14,70,1,14),
(15,200,1,15),
(16,90,1,16),
(17,10,2,1),
(18,70,3,2),
(19,200,4,3),
(20,90,3,4),
(21,10,4,5),
(22,70,2,6),
(23,200,3,7),
(24,90,4,8),
(25,10,2,9),
(26,70,3,10),
(27,200,4,11),
(28,90,3,12),
(29,10,4,13),
(30,70,2,14),
(31,200,2,15),
(32,90,3,16);

#INSERTS Empleados
INSERT INTO empleado VALUES /* id_empled INTEGER AUTO_INCREMENT ,nombre VARCHAR(240) , clave LONGTEXT ,role VARCHAR(240) ,
correo TEXT , salario FLOAT ,codDepart INTEGER  */
(8,'Pablo Madrigal Gonzales','loji#6%(&hhV535','Distribuidor','pablo@supersm.cr',400000,3),
(9,'Gabriel Sancho Zamora','gsz$453^#LKEDR59','Cajero','gabriel@supersm.cr',500000,4),
(10,'Minor Badilla Núñez','mbn$453^#LKEDR59','Cajero','minor@supersm.cr',500000,3),

#inserts encabezado factura

INSERT INTO encabezado_factura VALUES/*
id_encbzd INTEGER AUTO_INCREMENT,precioTotal INTEGER  , impuestoDeVenta DECIMAL(6,4) ,
fechaCompra TIMESTAMP ,idCliente INTEGER , empleadAtiend INTEGER ,   noDetallFactr INTEGER */
(11,3700,0.60,'2017-04-05 18:19:03',3,4,11),
(12,8600,0.60,'2017-04-05 18:19:03',4,2,12),
(13,6500,0.60,'2017-04-05 18:19:03',1,3,13),
(14,8400,0.60,'2017-04-05 18:19:03',2,5,14),
(15,6700,0.60,'2017-04-05 18:19:03',5,3,15),

#Inserts detalle factura
INSERT INTO detalle_factura VALUES/*
no_detall_fact INTEGER  AUTO_INCREMENT, cantTotDeArtic INTEGER ,codArticulo INTEGER   */
(11,1,1),
(12,2,2),
(13,3,3),
(14,4,4),
(15,5,1),