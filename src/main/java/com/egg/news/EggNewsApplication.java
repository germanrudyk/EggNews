package com.egg.news;

import com.egg.news.entidades.Noticia;
import com.egg.news.repositorios.NoticiaRepositorio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EggNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EggNewsApplication.class, args);

    }

}
