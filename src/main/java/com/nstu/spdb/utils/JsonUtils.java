package com.nstu.spdb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

@Service
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        objectMapper.setTimeZone(TimeZone.getDefault());
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        T result = null;
        if (clazz == null || json == null) {
            return null;
        }
        try {
            result = objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.warn("Can't parse json to clazz = {}", clazz.getSimpleName(), e);
        }
        return result;
    }

    public <T> List<T> jsonToListWithGeneric(String json, Class<T> clazz) {
        List<T> result = null;
        try {
            result = objectMapper.readValue(json,
                    constructParametricType(List.class, clazz));
        } catch (IOException e) {
            logger.warn("Can't parse json to list with class = {}", clazz, e);
        }
        return result;

    }

    public <K, V> Map<K, V> jsonToMapWithGeneric(String json, Class<K> keyClass, Class<V> valueClass) {
        return jsonToMapWithGeneric(json, Map.class, keyClass, valueClass);
    }

    public <K, V> Map<K, V> jsonToLinkedHashMapWithGeneric(String json, Class<K> keyClass, Class<V> valueClass) {
        return jsonToMapWithGeneric(json, LinkedHashMap.class, keyClass, valueClass);
    }

    public <K, V> Map<K, V> jsonToHashMapWithGeneric(String json, Class<K> keyClass, Class<V> valueClass) {
        return jsonToMapWithGeneric(json, HashMap.class, keyClass, valueClass);
    }

    private <T, K, V> Map<K, V> jsonToMapWithGeneric(String json, Class<T> mapClass, Class<K> keyClass, Class<V> valueClass) {
        Map<K, V> result = null;
        try {
            result = objectMapper.readValue(json,
                    constructParametricType(mapClass, keyClass, valueClass));
        } catch (IOException e) {
            logger.warn("Can't parse json to map with key class = {} and value class = {}",
                    keyClass.getSimpleName(),
                    valueClass.getSimpleName());
        }
        return result;
    }

    private JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
        return objectMapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
    }

    public String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.warn("Can't convert {} to JSON. ERROR: {}", obj.getClass().getSimpleName(), e);
        }
        return null;
    }

    public <T> T fromJson(String json, TypeReference typeReference) {
        if (json == null) {
            return null;
        }

        T result = null;
        try {
            result = (T) objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            logger.warn("Can't parse json by type reference", e);
        }
        return result;
    }

    public Map<String, Object> fromJson(String json) {
        return fromJson(json, new TypeReference<Map<String, Object>>() {
        });
    }

    public String toString(Object rawData) throws JsonProcessingException {
        return objectMapper.writeValueAsString(rawData);
    }

    public <T> T prepareDTO(Object rawData, Class type) {
        return (T) objectMapper.convertValue(rawData, type);
    }

    public <T> T prepareDTO(Object rawData, TypeReference type) {
        return (T) objectMapper.convertValue(rawData, type);
    }

    public <K, V> Map<K, V> prepareMapDTO(Object rawData, Class<K> keyClass, Class<V> valueClass) {
        return objectMapper.convertValue(rawData, constructParametricType(Map.class, keyClass, valueClass));
    }

    public <T> T jsonToObject(String json, TypeReference<T> typeReference) {
        T result = null;
        if (json == null) {
            return null;
        }
        try {
            result = (T) objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            logger.warn("Can't parse json by type reference", e);
        }
        return result;
    }

    public void toFile(Path path, Object obj) throws IOException {
        objectMapper.writeValue(path.toFile(), obj);
    }

    public <T> T fromFile(Path path, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(path.toFile(), typeReference);
        } catch (IOException e) {
            logger.warn("Can't parse json from file", e);
        }

        return null;
    }

    public JsonNode readTree(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (IOException e) {
            logger.error("An error occurred during the formation", e);
        }
        return null;
    }
}
