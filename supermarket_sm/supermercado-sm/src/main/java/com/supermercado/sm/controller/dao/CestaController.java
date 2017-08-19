package com.supermercado.sm.controller.dao;

import com.supermercado.sm.model.pojo.Articulo;
import com.supermercado.sm.model.pojo.Cliente;
import com.supermercado.sm.model.pojo.DetalleFactura;
import com.supermercado.sm.model.pojo.EncabezadoFactura;
import java.util.ArrayList;
import java.util.List;

public class CestaController {
    private static Cliente clienteRegistrado;
    private static List<Articulo> listaDeArticulos = new ArrayList<>();
    private static List<DetalleFactura> listaDeDetalleFactura = new ArrayList<>();
    private static List<EncabezadoFactura> listaDeEncabezadoFactura = new ArrayList<>();
     public static void agregarArticulo(Articulo clienteRegistrado){
         listaDeArticulos.add(clienteRegistrado);
     }
     public static void eliminarArticulo(Articulo clienteRegistrado){
         listaDeArticulos.remove(clienteRegistrado);
     }
}
