package com.ocio.backend17.services;

import com.ocio.backend17.dto.UsersReportDto;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.util.Map;

public interface IUsersReport {
    UsersReportDto usersReportDto() throws JRException, IOException;
}
