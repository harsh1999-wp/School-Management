package com.harsh.School.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.metamodel.Attribute;

public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {


    @Override
    public String convertToDatabaseColumn(Boolean attribute) {

        if(attribute == null) return  null;
        if(attribute == true)
            return "Yes";
        else
            return  "No";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        if(dbData == null) return  null;
        if(dbData.equals("yes")){
            return true;
        }
        else
            return false;
    }
}
