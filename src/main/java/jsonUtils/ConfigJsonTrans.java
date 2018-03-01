package jsonUtils;

import com.google.gson.Gson;
import config.RunConfig;

/**
 * Created by lch on 2018/3/1.
 */
public class ConfigJsonTrans {
    private  String configJson;
    private  RunConfig runConfig;
    private Gson gson;

    public ConfigJsonTrans(RunConfig runConfig){
        this.runConfig = runConfig;
        gson = new Gson();
    }

    public ConfigJsonTrans(String configJson){
        this.configJson = configJson;
        gson = new Gson();
    }

    public String getConfigJsonString() {
        return gson.toJson(runConfig);
    }

    public RunConfig getRunConfigObj() {
        return gson.fromJson(configJson,RunConfig.class);
    }
}
