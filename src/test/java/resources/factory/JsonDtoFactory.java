package resources.factory;

import com.training.epam.entity.dto.request.JsonDto;

public interface JsonDtoFactory<T extends JsonDto> {
    T create(String id);

    T create();

    T create(String id, String target);
}
