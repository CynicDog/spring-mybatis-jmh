package com.example.web.view;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

@Component
public class FileDownloadView extends AbstractView {

    private final Logger logger = Logger.getLogger(FileDownloadView.class);

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String directory = (String) model.get("directory");
        String filename = (String) model.get("filename");

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "utf-8"));

        FileInputStream in = new FileInputStream(new File(directory, filename));
        OutputStream out = response.getOutputStream();

        FileCopyUtils.copy(in, out);
    }
}
