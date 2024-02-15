package me.addo6544.frost.config.configs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import me.addo6544.frost.config.Config;
import me.addo6544.frost.core.Frost;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CurrentConfig extends Config {
    private String module = "default";

    @Override
    public Path getPath() {
        return Paths.get(Frost.CLIENT_PATH, "current.json");
    }

    @Override
    public void load() {

        try {
            JsonObject jsonObject = new Gson().fromJson(new String(Files.readAllBytes(getPath()), StandardCharsets.UTF_8), JsonObject.class);
            JsonObject client = jsonObject.getAsJsonObject("client");

            if (client.has("module"))
                module = client.get("module").getAsString();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        JsonObject j = new JsonObject();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("module", module);
        j.add("client", jsonObject);

        try {
            Files.write(getPath(), new GsonBuilder().setPrettyPrinting().create().toJson(j).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCurrentModule(String module) {
        this.module = module;
    }

    public String currentModule(){
        return module;
    }
}
