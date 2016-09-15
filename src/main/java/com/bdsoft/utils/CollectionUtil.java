package com.bdsoft.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 集合类相关操作
 * 
 * @author bdceo
 * 
 */
public class CollectionUtil {

	public static Map deepCopy(Map map) {
		Map result = new HashMap();
		for (Object o : map.keySet()) {
			result.put(o, map.get(o));
		}
		result = Collections.unmodifiableMap(result);
		return result;
	}

	public static Map shallowCopy(Map map) {
		Map result = new HashMap(map);
		result = Collections.unmodifiableMap(result);
		return result;
	}
}
