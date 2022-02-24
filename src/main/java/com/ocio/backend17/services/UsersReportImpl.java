package com.ocio.backend17.services;

import com.ocio.backend17.dto.UsersReportDto;
import com.ocio.backend17.reports.UsersReportManager;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class UsersReportImpl implements IUsersReport {
    @Autowired
    UsersReportManager usersReportManager;
    @Autowired
    DataSource dataSource;

    @Override
    public UsersReportDto usersReportDto() throws JRException, IOException {

        String fileName = "Informe_de_Usuarios.pdf";
        UsersReportDto usersReportDto = new UsersReportDto();
        usersReportDto.setFileName(fileName);

        ByteArrayOutputStream stream = usersReportManager.export(fileName);
        byte[] bs = stream.toByteArray();
        usersReportDto.setStream(new ByteArrayInputStream(bs));
        usersReportDto.setLenght(bs.length);
        return usersReportDto;

    }
}
