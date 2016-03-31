package com.commontime.cordova.plugins.duktape;

import android.content.Context;

import com.squareup.duktape.Duktape;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by graham on 31/03/16.
 */
public class DuktapeEngine {

    private static DuktapeEngine instance = new DuktapeEngine();
    private Context context;
    private String extraJSFile = "";

    private DuktapeEngine() {}

    public static DuktapeEngine get(Context context) {
        if (context != null)
            instance.setContext(context);
        return instance;
    }

    private HashMap<String, DuktapeCallback> callbacks = new HashMap<String, DuktapeCallback>();
    private HashMap<String, Duktape> duktapes = new HashMap<String, Duktape>();

    public String execute(String js) {
        Duktape duktape = Duktape.create();

        try {
            if( !extraJSFile.isEmpty() ) {
                InputStream fis = context.getAssets().open(extraJSFile);
                String extraJs = IOUtils.toString(fis);
                fis.close();
                duktape.evaluate(extraJs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return duktape.evaluate(js);
    }

    public void execute(String js, DuktapeCallback callback ) {
        String id = UUID.randomUUID().toString();
        callbacks.put( id, callback );

        Duktape duktape = Duktape.create();

        try {
            if( !extraJSFile.isEmpty() ) {
                InputStream fis = context.getAssets().open(extraJSFile);
                String extraJs = IOUtils.toString(fis);
                fis.close();
                duktape.evaluate(extraJs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        duktape.bind("DuktapeResult", DuktapeResult.class, dt);
        duktape.evaluate("duktapeResultId = \"" + id + "\";");
        duktape.evaluate(js);
        duktapes.put(id, duktape);
    }

    public void setFile(String filename) {
        extraJSFile = filename;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    interface DuktapeResult {
        void finish(String id, String result);
    }

    DuktapeResult dt = new DuktapeResult() {
        @Override public void finish(String id, String result) {
            System.out.println(result);
            callbacks.remove(id).onFinished(result);
            duktapes.remove(id).close();
        }
    };
}
