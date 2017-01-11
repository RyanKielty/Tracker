package com.company;

import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static HashMap<String, User> users = new HashMap<>();
    static ArrayList<FootballDefenseSeasonStatistics> statistics = new ArrayList<>();

    public static void main(String[] args) {
        Spark.init();
        Spark.get(
                "/",
                ((request, response) -> {
                    HashMap data = new HashMap();
                    data.put("stats", statistics);

                    Session session = request.session();
                    String userName = session.attribute("userName");

                    data.put("userName", userName);
                    return new ModelAndView(data, "home.html");
                }),
                new MustacheTemplateEngine()
        );

        Spark.post(
                "/login",
                ((request, response) -> {
                    String userName = request.queryParams("loginName");
                    if (userName == null) {
                        throw new Exception("Login name not found.");
                    }

                    User user = users.get(userName);
                    if (user == null) {
//                        user = new User(userName);
                        users.put(userName, user);
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
                "/add-statistics",
                ((request, response) -> {
                    Session session = request.session();
                    String userName = session.attribute("userName");

                    if (userName == null) {
                        throw new Exception ("Shame on you, login!");
                    }

                    int uniformNumber = Integer.parseInt(request.queryParams("uniformNumber"));
                    if (uniformNumber < 1 && uniformNumber > 99) {
                        throw new Exception("Not a valid jersey number.");
                    }

                    String lastName = request.queryParams("lastName");
                    if (lastName == null) {
                        throw new Exception("Player's Last name is required for stat entry.");
                    }

                    String team = request.queryParams("team");
                    if (team == null) {
                        throw new Exception("Team name is required for stat entry");
                    }

                    int totalTackles = Integer.parseInt(request.queryParams("totalTackles"));
                    if (totalTackles < 0) {
                        throw new Exception("Invalid entry");
                    }

                    Double sacks = Double.parseDouble(request.queryParams("sacks"));
                    if (sacks < 0) {
                        throw new Exception("Invalid entry");
                    }

                    int interceptions = Integer.parseInt(request.queryParams("interceptions"));
                    if (interceptions < 0) {
                        throw new Exception("Invalid entry");
                    }

                    FootballDefenseSeasonStatistics seasonStatistics = new FootballDefenseSeasonStatistics
                            (uniformNumber, lastName, team, totalTackles, sacks, interceptions);
                    statistics.add(seasonStatistics);


                    response.redirect("/");
                    return "";
                })
        );



    }
}
