package org.example.domain.proxy;

import java.util.List;
import java.util.Map;

public interface CacheableConnection extends DBConnection {
    List<Map<String, Object>> getCachedData(String query);
}
