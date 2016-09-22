import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.Arrays;
import java.util.*;
import java.util.Date;
import java.sql.Timestamp;

public class App {
  public static void main(String[] args) {

    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    String header = "templates/header.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("title", "Game Review");
      model.put("platforms", Platform.all());
      model.put("header", header);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/platform/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("title", "Add Platform");
      model.put("header", header);
      model.put("template", "templates/platform-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/platform/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Platform newPlatform = new Platform(name);
      newPlatform.save();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/:name/game/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Platform platform = Platform.findName(request.params(":name"));
      model.put("title", "Add " + platform.getName() + " Game");
      model.put("header", header);
      model.put("platform", platform);
      model.put("template", "templates/game-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:name/game/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Platform platform = Platform.findName(request.params(":name"));
      String gameName = request.queryParams("name");
      String gameType = request.queryParams("game_type");
      String gameDescription = request.queryParams("description");
      String gameYear = request.queryParams("year");
      Game newGame = new Game(gameName, gameType, gameDescription, gameYear, platform.getId());
      newGame.save();
      response.redirect("/");
      model.put("template", "templates/game-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/:name/:gamename", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Platform platform = Platform.findName(request.params(":name"));
      Game game = Game.findName(request.params(":gamename"));
      model.put("title", platform.getName() + ", " + game.getName() + " Details");
      model.put("header", header);
      model.put("platform", platform);
      model.put("game", game);
      model.put("template", "templates/game.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:name/:gamename/review", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Platform platform = Platform.findName(request.params(":name"));
      Game game = Game.findName(request.params(":gamename"));
      String name = request.queryParams("name");
      String content = request.queryParams("content");
      String url = "/" + request.params(":name") + "/" + request.params("gamename");
      java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
      java.sql.Date created_at = new java.sql.Date(utilDate.getTime());//May receive error depending on scope of accuracy
      String now = new java.sql.Timestamp((created_at).getTime()).toString();
      Review review = new Review(name, content, now, game.getId());
      review.save();
      response.redirect(url);
      model.put("template", "templates/game.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/:name/:gamename/review/:id/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Platform platform = Platform.findName(request.params(":name"));
      Game game = Game.findName(request.params(":gamename"));
      Review review = Review.find(Integer.parseInt(request.params(":id")));
      model.put("title", platform.getName() + ", " + game.getName() + " Review Edit by " + review.getName());
      model.put("header", header);
      model.put("platform", platform);
      model.put("game", game);
      model.put("review", review);
      model.put("template", "templates/review-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:name/:gamename/review/:id/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Platform platform = Platform.findName(request.params(":name"));
      Game game = Game.findName(request.params(":gamename"));
      Review review = Review.find(Integer.parseInt(request.params(":id")));
      String content = request.queryParams("content");
      java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
      java.sql.Date created_at = new java.sql.Date(utilDate.getTime());//May receive error depending on scope of accuracy
      String now = new java.sql.Timestamp((created_at).getTime()).toString();
      review.update(content, now);
      String url = "/" + request.params(":name") + "/" + request.params("gamename");
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:name/:gamename/review/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Platform platform = Platform.findName(request.params(":name"));
      Game game = Game.findName(request.params(":gamename"));
      Review review = Review.find(Integer.parseInt(request.params(":id")));
      review.delete();
      String url = "/" + request.params(":name") + "/" + request.params("gamename");
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //GAME STUFF
    get("/:name/:gamename/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Platform platform = Platform.findName(request.params(":name"));
      Game game = Game.findName(request.params(":gamename"));
      model.put("title", "Edit " + platform.getName() + ", " + game.getName());
      model.put("header", header);
      model.put("platform", platform);
      model.put("game", game);
      model.put("template", "templates/game-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:name/:gamename/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Platform platform = Platform.findName(request.params(":name"));
      Game game = Game.findName(request.params(":gamename"));
      String gameName = request.queryParams("name");
      String gameType = request.queryParams("game_type");
      String gameDescription = request.queryParams("description");
      String gameYear = request.queryParams("year");
      game.update(gameName, gameType, gameDescription, gameYear);
      String url = "/" + request.params(":name") + "/" + game.getName();
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:name/:gamename/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Platform platform = Platform.findName(request.params(":name"));
      Game game = Game.findName(request.params(":gamename"));
      game.delete();
      String url = "/";
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
