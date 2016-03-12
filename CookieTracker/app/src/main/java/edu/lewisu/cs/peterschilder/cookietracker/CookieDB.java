package edu.lewisu.cs.peterschilder.cookietracker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        cookieList.add(new Cookie("Chocolate Chunk", "Chocolate", "round", 1, false));
    }

    public List<Cookie>getCookies(){return cookieList;}

    public Cookie getCookie(UUID id){
        Cookie cookie = null;
        for(Cookie c:cookieList){
            if(c.getId().equals(id)){
                cookie = c;
            }
        }
        return cookie;
    }
}
