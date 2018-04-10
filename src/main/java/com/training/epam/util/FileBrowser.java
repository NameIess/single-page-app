package com.training.epam.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;

@Component("fileBrowser")
public class FileBrowser {

    public File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);

        File file = null;
        if (resource != null) {
            String path = resource.getFile();
            file = new File(path);
        }

        return file;
    }
}
