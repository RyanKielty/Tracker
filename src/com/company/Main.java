package com.company;


import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;

public class Main {

    static HashMap<String, User> usersMap = new HashMap<>();

    public static void main(String[] args) {
        Spark.init();
        Spark.get(
                "/",
                ((request, response) -> {
                    Session session = request.session();
                    String userName = session.attribute("userName");

                    User user = usersMap.get(userName);

                    HashMap data = new HashMap();

                    if(userName == null) {
                        return new ModelAndView(data, "login.html");
                    } else {
                        data.put("userName", userName);
                        data.put("albums", user.albumsList);
                        return new ModelAndView(data, "home.html");
                    }
                }),
                new MustacheTemplateEngine()
        );
        Spark.post(
                "/login",
                ((request, response) -> {
                    String userName = request.queryParams("loginName");
                    if (userName == null || userName.isEmpty()) {
                        throw new Exception("Login name not found.");
                    }
                    User user = usersMap.get(userName);
                    if (user == null) {
                        user = new User(userName);
                        usersMap.put(userName, user);
                    }
                    Session session = request.session();
                    session.attribute("userName", userName);
                    response.redirect("/");
                    return "";
                })
        );
        Spark.post(
                "/logout",
                ((request, response) -> {
                    Session session = request.session();
                    session.invalidate();
                    response.redirect("/");
                    return "";
                })
        );
        Spark.post(
                "/add-album",
                ((request, response) -> {
                    Session session = request.session();
                    String userName = session.attribute("userName");
                    User user = usersMap.get(userName);

                    if (userName == null) {
                        throw new Exception("Shame on you, login!");
                    }
//                    int id = Integer.parseInt(request.queryParams("id"));

                    String albumTitle = request.queryParams("albumTitle");
                    if (albumTitle == null) {
                        throw new Exception("Title required for tracking");
                    }
                    String artist = request.queryParams("artist");
                    if (artist == null) {
                        throw new Exception("Artist required for tracking");
                    }
                    int releaseYear = Integer.parseInt(request.queryParams("releaseYear"));
                    if (releaseYear < 1930 && releaseYear > 2017) {
                        throw new Exception("Invalid release year");
                    }

                    Albums addAlbum = new Albums(albumTitle, artist, releaseYear);
                    user.albumsList.add(addAlbum);

                    response.redirect("/");
                    return "";
                })
        );
        Spark.post(
                "/remove-album",
                ((request, response) -> {
                    Session session = request.session();
                    String userName = session.attribute("userName");
                    User user = usersMap.get(userName);

                    String removeAlbum = request.queryParams("removeAlbum");

                    user.albumsList.remove(Integer.parseInt(removeAlbum)-1);

                    response.redirect("/");
                    return "";
                })
        );
        Spark.post(
                "/edit-album",
                ((request, response) -> {
                    Session session = request.session();
                    String userName = session.attribute("userName");
                    User user = usersMap.get(userName);

                    String albumTitle = request.queryParams("albumTitle");
                    if (albumTitle == null) {
                        throw new Exception("Title required for tracking");
                    }
                    String artist = request.queryParams("artist");
                    if (artist == null) {
                        throw new Exception("Artist required for tracking");
                    }
                    int releaseYear = Integer.parseInt(request.queryParams("releaseYear"));
                    if (releaseYear < 1930 && releaseYear > 2017) {
                        throw new Exception("Invalid release year");
                    }

                    String editAlbum = request.queryParams("editAlbum");

                    user.albumsList.remove(Integer.parseInt(editAlbum)-1);
                    user.albumsList.add(Integer.parseInt(editAlbum)-1, new Albums(albumTitle, artist, releaseYear));

                    response.redirect("/");
                    return "";
                })
        );

    }
}
