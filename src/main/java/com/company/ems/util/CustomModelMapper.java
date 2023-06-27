package com.company.ems.util;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomModelMapper {

    private final Logger logger = LoggerFactory.getLogger(CustomModelMapper.class);

    private ModelMapper modelMapper;

    public Object map(Object dto, Class<?> modelClass) {
        if (modelMapper == null) {
            logger.trace("...Created new ModelMapper instance...");
            modelMapper = new ModelMapper();
        }
        return modelMapper.map(dto, modelClass);
    }
}
