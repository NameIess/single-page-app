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
    private static final Logger Log = LogManager.getLogger(CacheSearchEngine.class.getSimpleName());

    @Override
    protected Composite execute(Composite node, JsonDto jsonDto) {
        Composite composite = null;
        SearchingCriteria searchCriteria = (SearchingCriteria) jsonDto;
        String criteriaName = searchCriteria.getId();
        String nodeName = node.getName();
        Log.info("COMPARATION NODE NAME: " + nodeName + " AND CRITERIA: " + criteriaName);

        if (StringUtils.startsWithIgnoreCase(nodeName, criteriaName)) {
            composite = node;
//            compositeList.add(node);        // VARIOUS IMPLEMENTATIONS. TEMPLATE COMMAND?
        }

        return composite;
    }


}
