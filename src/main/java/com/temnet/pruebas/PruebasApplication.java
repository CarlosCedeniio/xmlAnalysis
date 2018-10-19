package com.temnet.pruebas;

import com.temnet.pruebas.services.CanvasjsChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static java.nio.file.StandardWatchEventKinds.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchService;
import java.util.List;
import java.util.Map;

@SpringBootApplication(scanBasePackages={"com.temnet.pruebas.controllers","com.temnet.pruebas.daos","com.temnet.pruebas.data","com.temnet.pruebas.services","com.temnet.pruebas.views"})
public class PruebasApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(PruebasApplication.class, args);
        Path incoming = Paths.get("C:\\Users\\carlo\\Dropbox\\Espol\\Investigacion\\Coding\\first\\src\\main\\resources\\incoming");
        Path backup = Paths.get("C:\\Users\\carlo\\Dropbox\\Espol\\Investigacion\\Coding\\first\\src\\main\\resources\\backup");
        Path temp = Paths.get("C:\\Users\\carlo\\Dropbox\\Espol\\Investigacion\\Coding\\first\\src\\main\\resources\\temp");
        WatchService watcher = FileSystems.getDefault().newWatchService();
        DirectoryWatcher.EnableDirectoryWatcher(incoming,backup,temp,watcher);

    }

}
