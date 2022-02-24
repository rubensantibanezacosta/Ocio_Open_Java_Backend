package com.ocio.backend17.reports;

import com.ocio.backend17.entities.Users;
import com.ocio.backend17.services.UsersImpl;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UsersDataSource implements JRDataSource {

    private List<Users> usersList;
    private int index=-1;


    public UsersDataSource(List<Users> usersList) {
        this.usersList = usersList;
    }



    @Override
    public boolean next() throws JRException {
        index++;
        return (index<usersList.size());
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        Object value = null;

        String fieldName = jrField.getName();

        switch (fieldName){
            case "image_url":
                value=usersList.get(index).getImage_url();
                break;
            case "email":
                value=usersList.get(index).getEmail();
                break;
            case "name":
                value=usersList.get(index).getName();
                break;
            case "surname":
                value=usersList.get(index).getSurname();
                break;
            case "createdAt":
                value=usersList.get(index).getCreatedAt();
                break;
            case "lastconnection":
                value=usersList.get(index).getLastconnection();
                break;
        }
        return value;
    }



    public static JRDataSource getUsersDataSource(List<Users> users){

        return new UsersDataSource(users);
    }

}
