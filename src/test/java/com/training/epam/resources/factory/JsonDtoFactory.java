package com.training.epam.resources.factory;

import com.training.epam.entity.dto.request.JsonDto;

public interface JsonDtoFactory<T extends JsonDto> {
    T create(String name);

    T create();
}
