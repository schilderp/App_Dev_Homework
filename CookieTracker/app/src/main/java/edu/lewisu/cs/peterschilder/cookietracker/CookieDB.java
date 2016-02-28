package edu.lewisu.cs.peterschilder.cookietracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter Schilder on 2/27/2016.
 */
public class CookieDB {
    private List<Cookie>cookieList;
    private static CookieDB cookieDB;

    public static CookieDB get(){
        if(cookieDB == null){
            cookieDB = new CookieDB();
        }
        return cookieDB;
    }

    private CookieDB() {
        cookieList = new ArrayList<>();
    }
}
