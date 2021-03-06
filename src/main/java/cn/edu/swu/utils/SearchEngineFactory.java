package cn.edu.swu.utils;

import java.io.IOException;

public class SearchEngineFactory {

    private static SearchEngine searchEngine = null;

    public static SearchEngine getInstance() {
        return getSearchEngine();
    }

    private static SearchEngine getSearchEngine() {
        if (searchEngine == null) {
            synchronized (SearchEngineFactory.class) {
                if (searchEngine != null) {
                    return searchEngine;
                }

                searchEngine = new SearchEngine();
                try {
                    searchEngine.startup();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return searchEngine;
    }
}
