/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.news.servicios;

import com.egg.news.controladores.AdminControlador;
import com.egg.news.entidades.Noticia;
import com.egg.news.excepciones.MiException;
import com.egg.news.repositorios.NoticiaRepositorio;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author German
 */
@Service
public class NoticiaServicio {

    @Autowired
    private NoticiaRepositorio noticiaRepositorio;

    public void crearNoticia(String titulo, String encabezilla, String cuerpo, String imagen) throws MiException {

        validar(titulo, encabezilla, cuerpo, imagen);

        Noticia noticia = new Noticia();

        noticia.setTitulo(titulo);
        noticia.setEncabezilla(encabezilla);
        noticia.setCuerpo(cuerpo);
        noticia.setImagen(imagen);
        noticia.setAlta(new Date());

        noticiaRepositorio.save(noticia);

    }

    public void validar(String titulo, String encabezilla, String cuerpo, String imagen) throws MiException {

        if (titulo.isEmpty()) {
            throw new MiException("Debe llevar un titulo");
        }
        if (encabezilla.isEmpty()) {
            throw new MiException("Debe llevar una encabezilla");
        }
        if (cuerpo.isEmpty() || cuerpo.length() < 200) {
            throw new MiException("El cuerpo debe tener como mínimo 200 caracteres");
        }
        if (imagen.isEmpty()) {
            throw new MiException("Debe elegir una imagen");
        }

    }

    public List<Noticia> listarNoticiasenOrdenAscendente() {

        List<Noticia> noticias = new ArrayList();
        noticias = noticiaRepositorio.findAll();
        return noticias;

    }

    public void modificarNoticia(String id, String titulo, String encabezilla,
            String cuerpo, String imagen) throws MiException {

        validar(titulo, encabezilla, cuerpo, imagen);

        Noticia noticia = buscarNoticiaPorId(id);

        noticia.setTitulo(titulo);
        noticia.setEncabezilla(encabezilla);
        noticia.setCuerpo(cuerpo);
        noticia.setImagen(imagen);

        noticiaRepositorio.save(noticia);

    }

    public Noticia buscarNoticiaPorId(String id) {

        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);

        if (respuesta.isPresent()) {

            return respuesta.get();

        }

        return null;

    }

    public void eliminar(String id) {

        Noticia noticia = buscarNoticiaPorId(id);

        noticiaRepositorio.delete(noticia);

    }

    public List<Noticia> listarNoticiasEnOrdenDescendente() {

        List<Noticia> noticias = new ArrayList();
        noticias = noticiaRepositorio.noticiasOrdenadasEnFormaDescendente();
        return noticias;

    }

}
