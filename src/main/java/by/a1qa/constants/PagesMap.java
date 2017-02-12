package by.a1qa.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tbegu_000 on 11.02.2017.
 */
public class PagesMap {

    public static final Map<String, Boolean> PAGES_MAP;

    static {
        Map<String, Boolean> pages = new HashMap<>();
        pages.put("/", true);
        pages.put("/login", true);
        pages.put("/logout", false);
        pages.put("reportController", false);
        PAGES_MAP = Collections.unmodifiableMap(pages);
    }
}
