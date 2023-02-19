/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.news.controladores;

import com.egg.news.entidades.Noticia;
import com.egg.news.excepciones.MiException;
import com.egg.news.servicios.NoticiaServicio;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author German
 */
@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/agregar")
    public String agregar() {

        return "agregar_noticia";

    }

    @PostMapping("/registro")
    public String registrar(@RequestParam String titulo, @RequestParam String encabezilla,
            @RequestParam String cuerpo, @RequestParam("imagen") MultipartFile imagen, ModelMap modelo) {

        String rutaImagen = "";

        if (!imagen.isEmpty()) {
            Path directorioImagenes = Paths.get("src//main//resources//static/img");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

                rutaImagen = imagen.getOriginalFilename();

            } catch (IOException ex) {
                Logger.getLogger(AdminControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            noticiaServicio.crearNoticia(titulo, encabezilla, cuerpo, rutaImagen);
            List<Noticia> noticias = noticiaServicio.listarNoticiasEnOrdenDescendente();

            modelo.addAttribute("noticias", noticias);

            return "redirect:/admin/noticias";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "agregar_noticia";
        }

    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {

        Noticia noticia = noticiaServicio.buscarNoticiaPorId(id);

        modelo.addAttribute("noticia", noticia);

        return "modificar_noticia";

    }

    @PostMapping("/modificado/{id}")
    public String modificado(@PathVariable String id, String titulo, String encabezilla,
            String cuerpo, @RequestParam("imagen") MultipartFile imagen, ModelMap modelo) {

        String rutaImagen = "";

        if (!imagen.isEmpty()) {
            Path directorioImagenes = Paths.get("src//main//resources//static/img");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

                rutaImagen = imagen.getOriginalFilename();

            } catch (IOException ex) {
                Logger.getLogger(AdminControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            noticiaServicio.modificarNoticia(id, titulo, encabezilla, cuerpo, rutaImagen);

            List<Noticia> noticias = noticiaServicio.listarNoticiasEnOrdenDescendente();

            modelo.addAttribute("noticias", noticias);

            return "redirect:../noticias";
        } catch (MiException ex) {
            
            Noticia noticia = noticiaServicio.buscarNoticiaPorId(id);

            modelo.addAttribute("noticia", noticia);
            
            modelo.addAttribute("error", ex.getMessage());
            
            return "modificar_noticia";
        }

    }

    @GetMapping("/noticias")
    public String verNoticias(ModelMap modelo) {

        List<Noticia> noticias = noticiaServicio.listarNoticiasEnOrdenDescendente();

        modelo.addAttribute("noticias", noticias);

        return "noticias_admin";

    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) {

        noticiaServicio.eliminar(id);

        List<Noticia> noticias = noticiaServicio.listarNoticiasEnOrdenDescendente();

        modelo.addAttribute("noticias", noticias);

        return "redirect:../noticias";

    }

}
