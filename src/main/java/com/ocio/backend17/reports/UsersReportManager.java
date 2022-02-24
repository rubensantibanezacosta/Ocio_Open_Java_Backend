package com.ocio.backend17.reports;


import com.ocio.backend17.services.UsersImpl;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.io.*;



@Component
public class UsersReportManager {
@Autowired
UsersDataSource usersDataSource;
@Autowired
    UsersImpl usersImpl;
    public ByteArrayOutputStream export(String fileName) throws JRException, IOException {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        File file= new File(System.getProperty("user.dir") + "/src/assets/reports/UsersSReport.jasper");
        InputStream inputStream = new DataInputStream(new FileInputStream(file));
        JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream,null, usersDataSource.getUsersDataSource(usersImpl.getAll()));
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        return stream;
    }

}
