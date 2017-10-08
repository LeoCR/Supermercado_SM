  SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS supermarket_sm;
CREATE SCHEMA IF NOT EXISTS supermarket_sm;

use supermarket_sm; 

#START TO CREATE TABLES
CREATE TABLE prooveedor(
	cod_provedr INTEGER NOT NULL AUTO_INCREMENT, 
	nombre  VARCHAR(250)  NOT NULL,
	correo VARCHAR(250) NOT NULL,
	telef VARCHAR(250) NOT NULL ,
	direccion VARCHAR(250) NOT NULL ,
	logo TEXT NOT NULL ,
	web VARCHAR(250) NOT NULL,
	CONSTRAINT cnstr_proovedor_pk PRIMARY KEY (cod_provedr)
) DEFAULT CHARSET=utf8;

CREATE TABLE articulo(
	cod_articl  INTEGER NOT NULL AUTO_INCREMENT,
	nombre MEDIUMTEXT NOT NULL,
	prec_unitar SMALLINT  NOT NULL, 
    impuesto DECIMAL(6,4) NOT NULL , 
	categoria VARCHAR(230) NOT NULL ,
    codProvedor INTEGER NOT NULL,
	CONSTRAINT cnstr_articulo_pk PRIMARY KEY (cod_articl )
) DEFAULT CHARSET=utf8;

CREATE TABLE imagen_articulo(
	idImagenArticulo INTEGER NOT NULL AUTO_INCREMENT,
    imagen MEDIUMTEXT NOT NULL ,
    descripcion MEDIUMTEXT ,
    codArtic INTEGER NOT NULL,
    CONSTRAINT cnstr_imagen_articulo_pk PRIMARY KEY (idImagenArticulo )
) DEFAULT CHARSET=utf8;

CREATE TABLE sucursal(
	cod_sucursl INTEGER NOT NULL AUTO_INCREMENT,
	nombre TEXT  NOT NULL,
	direccion TEXT NOT NULL,
	telef VARCHAR(200) NOT NULL, 
	CONSTRAINT cnstr_sucursal_pk PRIMARY KEY (cod_sucursl)
) DEFAULT CHARSET=utf8;

CREATE TABLE articulo_de_sucursal(
	idArtSuc INTEGER NOT NULL AUTO_INCREMENT,
    stock INTEGER  ,
    codSucursl INTEGER NOT NULL,
    codArticulo INTEGER NOT NULL,
    CONSTRAINT articulo_de_sucursal_pk PRIMARY  KEY(idArtSuc)
);

CREATE TABLE departamento(
	cod_departmt INTEGER NOT NULL AUTO_INCREMENT ,
    nombre VARCHAR(240)  NOT NULL,
	correo TEXT NOT NULL,
	telef VARCHAR(230) NOT NULL ,
    codSucrsl INTEGER NOT NULL,
	CONSTRAINT cnstr_deprtmnt_pk PRIMARY KEY(cod_departmt)
) DEFAULT CHARSET=utf8;

CREATE TABLE empleado(
	id_empled INTEGER AUTO_INCREMENT ,
	nombre VARCHAR(240) NOT NULL, 
	clave LONGTEXT NOT NULL,
	role VARCHAR(240) NOT NULL,
	correo TEXT NOT NULL,
    salario FLOAT NOT NULL,
    codDepart INTEGER NOT NULL  ,
    CONSTRAINT cnstr_emplead_pk PRIMARY KEY(id_empled)
) DEFAULT CHARSET=utf8;

CREATE TABLE cliente(
	id_client INTEGER NOT NULL AUTO_INCREMENT ,
    nombre VARCHAR(240) NOT NULL,
    cedula VARCHAR(240) ,
	cantidadDeCompras INTEGER,
    CONSTRAINT cnstr_client_pk PRIMARY KEY(id_client)
) DEFAULT CHARSET=utf8;

CREATE TABLE detalle_factura (
    no_detall_fact INTEGER NOT NULL AUTO_INCREMENT, 
    cantTotDeArtic INTEGER NOT NULL,
    codArticulo INTEGER NOT NULL ,
    CONSTRAINT cnstr_detalle_fact_pk PRIMARY KEY(no_detall_fact)
) DEFAULT CHARSET=utf8;

CREATE TABLE encabezado_factura(
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
INSERT INTO prooveedor VALUES
/* cod_provedr INTEGER  AUTO_INCREMENT, nombre  VARCHAR(250) ,correo VARCHAR(250) ,
telef VARCHAR(250) ,direccion VARCHAR(250) ,logo TEXT ,web VARCHAR(250) */
(1,'Dos Pinos','info@dospinos.com','+506 2437 3000','Del Aeropuerto Internacional Juan Santa Maria 7k al Oeste','logo_dos-pinos.png','http://www.dospinos.com/'),
(2,'Café 1820','info@cafe1820.com','2233-8544','Heredia,Hotel de Paso La Valencia, 106','logo-cafe1820.png','http://cafe1820.com/es/'),
(3,'Sardimar','info@sardimar.com','2742-4810',' Parque Empresarial Forum 1, Edificio C, Tercer Piso San José, Costa Rica.','logo-sardimar.png','www.sardimar.com'),
(4,'Don Pedro','servicioalcliente@kani.cr','(506) 2537-0102',' Alto de Ochomogo, Cartago. De la entrada al plantel de carga de combustibles de Recope, 500 metros norte, 200 metros noroeste y 200 metros oeste.','logo-frijoles-don-pedro.png','http://www.donpedro.co.cr/'),
(5,'Coca Cola','info@Coca-cola.com','+506 8805 2922','San José, Calle Blancos, Costa Rica','logo-coca-cola.png','http://www.femsa.com'),
(6,'Bimbo-Costa Rica','www.bimboamericacentral.com','+506 2290 0667','Bimbo, San José Province, San José, Costa Rica','Logo_Bimbo_Osito.jpg','https://www.facebook.com/BimboCostaRica/'),
(7,'Numar','http://www.numar.net/contactanos','+506 2284 1000','Frente a la Clínica Dr. Ricardo Moreno Cañas., Av 26, San José, Costa Rica','logo_numar.jpg','http://www.numar.net/'),
(8,'kellogs','info.cr@tecoloco.com','+506 2276 1574','Kativo Costa Rica, S.A. Ochomogo, Cartago','logo_k.png','https://www.facebook.com/pages/Kellogg-Costa-Rica/');

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
(9,'Jugo de Naranja',2600,0.4000,'Bebidas',1),
(10,'Atún Sardimar en Lomos',1500,0.6000,'Alimentos Enlatados',3),
(11,'Café Britt',1650,0.6200,'Bebidas en Polvo',2),
(12,'Atún Sardimar en Lomos Grande',1850,0.7000,'Alimentos Enlatados',3),
(13,'Colacola light',1450,0.5000,'Gaseosas',5),
(14, 'Colacola',1600,0.6000,'Gaseosas',5),
(15,'Hi c',800,0.2000,'Jugos',5),
(16,'Pan Breddy',1600,0.6000,'Pan',6),
(17,'Pan Bimbo',1850,0.7000,'Pan',6),
(18,'Pan Bimbo Integral Grande',2000,0.3100,'Pan',6),
(19,'Mantiquilla',400,0.1300,'Alimento Graso',7),
(20,'Mantequilla de Ajo',1800,0.6800,'Alimento Graso',7),
(21,'Mantequilla Suave',1700,0.6500,'Alimento Graso',7),
(22,'Cereal de Trigo',1400,0.4850,'Cereales',8),
(23,'Cereal de Trigo Azucarado',1625,0.6075,'Cereales',8),
(24,'Barra Dietetica',650,0.1500,'Alimentos Energeticos',8);

UNLOCK TABLES;

LOCK TABLES `imagen_articulo`  WRITE;

INSERT INTO imagen_articulo VALUES
(1,'deleite-naranja-01.png','Delicioso Helado de Naranja con Chispas de Chocolate',1),
(2,'cafe-1820-molido-tueste-claro-01.png','Delicioso Helado de Naranja con Chispas de Chocolate',2),
(3,'atun-con-vegetales-peque-01.png','Atún con Vegetales Pequeño',3),
(4,'frijoles-negros-01.png','Bolsa Pequeña de Frijoles Negros,900g de granos 100% naturales. ',4),
(5,'Queso_crema.png',' Fresco, cremoso, con su inconfundible sabor y aroma',5),
(6,'yogurt_fresa.jpg','Elaborado con leche fresca',6),
(7,'queso_crema2.png','El queso crema es un alimento riquísimo, ideal para untar y acompañar muchas comidas',7),
(8,'queso_crema3.jpg','El queso crema es un alimento riquísimo, ideal para untar y acompañar muchas comidas',8),
(9,'jugo_de_naranja.jpg','El jugo de naranja o zumo de naranja es un jugo de frutas',9),
(10,'atun_sardimar.png','Exquisitos trocitos de atún con vegetales en una salsa especial para resaltar el sabor de su comida',10),
(11,'cafe_britt.jpg','Una buena taza de café en Costa Rica',11),
(12,'atun_sardimar2.png','Exquisitos trocitos de atún con vegetales en una salsa especial para resaltar el sabor de su comida',12),
(13,'cocacola_light.jpg','Coca-Cola zero azúcar es sabor Coca-Cola sin aportar nada de azúcar',13),
(14,'cocacola.jpg','Coca-Cola es una bebida gaseosa y refrescante',14),
(15,'hi_c.jpg','Jugo de Frutas exotico',15),
(16,'pan_breddy.jpg','Pan Blanco elaborado con harina de trigo refinada',16),
(17,'pan_bimbo.jpg','Pan Blanco elaborado con harina de trigo refinada',17),
(18,'pan_bimbo2.png','Pan Blanco elaborado con harina de trigo refinada',18),
(19,'mantequilla.png',' elaborado principalmente a base de crema de leche pasteurizada',19);

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
/* id_client INTEGER AUTO_INCREMENT ,nombre VARCHAR(240) ,
cedula VARCHAR(240) ,cantidadDeCompras INTEGER */
(1,'Juan Gonzáles Sanchez','1145-05-364',1),
(2,'Jhonny Duarte Badilla','1144-02-654',1),
(3,'Arturo Solano Gutierrez','1142-12-742',1),
(4,'María Ortega Díaz','1149-23-350',1),
(5,'Marta Moreira Segura','1153-67-489',1),
(6,'Mónica Barquero Segura','1135-70-457',1),
(7,'Luis Saenz Sandoval','1146-75-325',1),
(8,'Carlos Castro Segura','1144-19-634',1),
(9,'Jose Pablo Ruiz Hernandez','1143-73-592',1);

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

#END TO INSERT DATA 

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

#Consultas 

#muestra los empleados que pertenencen al departamento 2
SELECT dep.cod_departmt,dep.nombre,em.nombre,em.salario
from departamento dep, empleado em
WHERE em.codDepart=dep.cod_departmt and em.codDepart='2';

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