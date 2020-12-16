package com.zwzw.warehouse.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;

@SuppressWarnings("unchecked")
public class JSONUtil {
    private static Logger logger = LoggerFactory.getLogger(JSONUtil.class);
    private static JsonFactory jsonFactory;
    private static ObjectMapper objectMapper;

    static {

        jsonFactory = new JsonFactory();
        jsonFactory.setCharacterEscapes(new CharacterEscapes() {
            @Override
            public int[] getEscapeCodesForAscii() {
                return CharacterEscapes.standardAsciiEscapesForJSON();
            }

            @Override
            public SerializableString getEscapeSequence(int ch) {
                switch (ch) {
                    case 0x2028:
                        return new SerializedString("");
                    case 0x2029:
                        return new SerializedString("");
                    default:
                        return null;
                }
            }
        });

        objectMapper = new ObjectMapper(jsonFactory);
        objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);  //bug fix, alow control chars.
        objectMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);

    }

    public static String toJsonString(Map<String, Object> map) {
        String jsonResult;
        try {
            jsonResult = objectMapper.writeValueAsString(map);
            if (logger.isDebugEnabled()) {
                logger.debug(jsonResult);
            }
        } catch (Exception e) {
            logger.error(StringUtils.EMPTY, e);
            jsonResult = "{}";
        }
        return jsonResult;
    }

    public static String toJsonString(Object sourceObject) {
        String jsonResult;

        try {
            jsonResult = objectMapper.writeValueAsString(sourceObject);
            if (logger.isDebugEnabled()) {
                logger.debug(jsonResult);
            }
        } catch (Exception e) {
            logger.error(StringUtils.EMPTY, e);
            jsonResult = "{}";
        }
        return jsonResult;
    }

    public static String toJsonString(FilterProvider filters,
                                      Object sourceObject) {
        String jsonResult;

        try {
            jsonResult = objectMapper.writer(filters).writeValueAsString(
                    sourceObject);
        } catch (Exception e) {
            logger.error("", e);
            jsonResult = "{}";
        }
        return jsonResult;
    }

    public static Map<String, Object> toMap(Object obj) {
        return toMap(toJsonString(obj));
    }

    public static Map<String, Object> toMap(String jsonString) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap = objectMapper.readValue(jsonString, Map.class);
        } catch (Exception e) {
            logger.error("jsonString = " + jsonString);
            logger.error(StringUtils.EMPTY, e);
        }
        return resultMap;
    }

    public static List<Object> toList(String jsonString) {
        List<Object> result = new ArrayList<Object>();

        try {
            result = objectMapper.readValue(jsonString, List.class);
        } catch (Exception e) {
            logger.error(StringUtils.EMPTY, e);
        }
        return result;
    }

    public static Set<Object> toSet(String jsonString) {
        Set<Object> result = new HashSet<Object>();

        try {
            result = objectMapper.readValue(jsonString, Set.class);
        } catch (Exception e) {
            logger.error(StringUtils.EMPTY, e);
        }
        return result;
    }

    public static <T> List<T> toList(String jsonString, Class<T[]> t) {
        try {
            T[] arrays = objectMapper.readValue(jsonString, t);
            return Arrays.asList(arrays);
        } catch (Exception e) {
            logger.error(StringUtils.EMPTY, e);
        }
        return null;
    }

    public static <T> T toObject(String jsonString, Class<T> clazz) {
        T resultObj = null;

        try {
            resultObj = objectMapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            logger.error(StringUtils.EMPTY, e);
        }
        return resultObj;
    }

    public static <T> T toObject(String jsonString, TypeReference<T> c) {
        T result = null;
        try {
            result = objectMapper.readValue(jsonString, c);
        } catch (JsonParseException e) {
            logger.error(StringUtils.EMPTY, e);
        } catch (JsonMappingException e) {
            logger.error(StringUtils.EMPTY, e);
        } catch (Exception e) {
            logger.error(StringUtils.EMPTY, e);
        }
        return result;
    }

    public static String toJsonStringForPrettyPrinting(Map<String, Object> map) {
        String jsonResult;
        try {
            ObjectMapper mapper = objectMapper.copy();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            jsonResult = mapper.writeValueAsString(map);
        } catch (Exception e) {
            logger.error("", e);
            jsonResult = "{}";
        }
        return jsonResult;
    }

    public static String toJsonStringForPrettyPrinting(Object sourceObject) {
        String jsonResult;
        try {
            ObjectMapper mapper = objectMapper.copy();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            jsonResult = mapper.writeValueAsString(sourceObject);
        } catch (Exception e) {
            logger.error("", e);
            jsonResult = "{}";
        }
        return jsonResult;
    }

    public static Map<String, Object> newMapWithData(String key, Object o) {
        Map<String, Object> tmp = new HashMap<>();
        tmp.put(key, o);
        return tmp;
    }
}
