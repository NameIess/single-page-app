package com.training.epam.service.impl.template.impl;

import com.training.epam.entity.Composite;
import com.training.epam.entity.dto.request.JsonDto;
import com.training.epam.entity.dto.request.SearchingCriteria;
import com.training.epam.service.impl.template.AbstractTemplateCacheManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("cacheSearchEngine")
public class CacheSearchEngine extends AbstractTemplateCacheManager {

    @Override
    protected Composite execute(Composite node, JsonDto jsonDto) {
        Composite composite = null;
        SearchingCriteria searchCriteria = (SearchingCriteria) jsonDto;
        String prefix = searchCriteria.getId();
        String nodeName = node.getName();

        if (StringUtils.startsWithIgnoreCase(nodeName, prefix)) {
            composite = node;
        }

        return composite;
    }


}
