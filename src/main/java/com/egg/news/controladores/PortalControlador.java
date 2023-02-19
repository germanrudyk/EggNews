/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.news.controladores;

import com.egg.news.entidades.Noticia;
import com.egg.news.repositorios.NoticiaRepositorio;
import com.egg.news.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author German
 */
@Controller
@RequestMapping("/")
public class PortalControlador {

    @GetMapping("/")
    public String index() {

        return "redirect:/usuario/noticias";

    }

}
