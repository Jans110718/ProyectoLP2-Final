package com.cibertec.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.modelos.Producto;
import com.cibertec.repositorio.ProductoRepository;

@Controller
public class ProductoEliminar {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/eliminarProducto")
    public String mostrarFormulario(Model model, @RequestParam("id") int idEliminar) {
        Producto producto = productoRepository.buscarPorId(idEliminar);

        model.addAttribute("producto", producto);

        return "vistas/producto/eliminar";
    }

    @PostMapping("/eliminarProducto")
    public String procesarFormulario(RedirectAttributes redirectAttrs, @RequestParam("txtId") int id) {
        productoRepository.eliminar(id);

        //productoRepository.delete(producto);

        redirectAttrs.addFlashAttribute("exito", true);

        return "redirect:/buscarProducto";
    }
}
