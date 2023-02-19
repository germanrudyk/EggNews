/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.news.controladores;

import com.egg.news.entidades.Noticia;
import com.egg.news.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author German
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/noticias")
    public String verNoticias(ModelMap modelo) {

        List<Noticia> noticias = noticiaServicio.listarNoticiasEnOrdenDescendente();

        modelo.addAttribute("noticias", noticias);

        return "noticias_usuario";

    }
    
    @GetMapping("/noticias/{id}")
    public String verNoticia(@PathVariable String id, ModelMap modelo){
        
        Noticia noticia = noticiaServicio.buscarNoticiaPorId(id);
        
        modelo.put("noticia", noticia);
        
        return "ver_noticia";
        
    }

}
