package ru.chemelson.patterns.gof.structural;

import java.util.HashMap;
import java.util.Map;

public class ProxyDemo {

    public static void main(String[] args) {
        DatabaseQuery query = new CachedDatabaseQuery();
        System.out.println(query.executeQuery("SELECT * FROM users")); // Executes and caches
        System.out.println(query.executeQuery("SELECT * FROM users")); // Returns cached result
    }

    // Subject interface Client will use
    interface DatabaseQuery {
        String executeQuery(String query);
    }

    // Delegate of Proxy
    static class RealDatabaseQuery implements DatabaseQuery {
        @Override
        public String executeQuery(String query) {
            System.out.println("Executing database query: " + query);
            return "Result of " + query;
        }
    }

    // Proxy
    static class CachedDatabaseQuery implements DatabaseQuery {
        private RealDatabaseQuery delegate;
        private Map<String, String> cache = new HashMap<>();

        @Override
        public String executeQuery(String query) {
            if (cache.containsKey(query)) {
                System.out.println("Returning cached result for query: " + query);
                return cache.get(query);
            } else {
                // This is not thread-safe implementation
                if (delegate == null) {
                    delegate = new RealDatabaseQuery();
                }
                var result = delegate.executeQuery(query);
                cache.put(query, result);
                return result;
            }
        }
    }



}
