package com.company.ems.util;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class CustomModelMapper {

    private ModelMapper modelMapper;

    public Object map(Object dto, Class<?> modelClass) {
        if (modelMapper == null) {
            log.trace("...Created new ModelMapper instance...");
            modelMapper = new ModelMapper();
        }
        return modelMapper.map(dto, modelClass);
    }
}
