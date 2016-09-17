package com.Shadersoft.UniverseMG.httpd;

import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.httpd.NanoHTTPD.Response;
import com.Shadersoft.UniverseMG.httpd.modules.HTTPDModule;
import com.Shadersoft.UniverseMG.httpd.modules.Module_dump;
import com.Shadersoft.UniverseMG.httpd.modules.Module_file;
import com.Shadersoft.UniverseMG.httpd.modules.Module_help;
import com.Shadersoft.UniverseMG.httpd.modules.Module_list;
import com.Shadersoft.UniverseMG.httpd.modules.Module_players;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

/*
// Thanks to the TotalFreedomMod developers for this bit of code
*/

public class HTTPDaemon
{

    public static String MIME_DEFAULT_BINARY = "application/octet-stream";
    private static final Pattern EXT_REGEX = Pattern.compile("\\.([^\\.\\s]+)$");
    //
    public static int port;
    private static HTTPD httpd;
    public static Map<String, ModuleExecutable> modules = new HashMap<>();

    public static UniverseMG plugin = UniverseMG.plugin;

    public static void start()
    {
        if (!plugin.config.getBoolean("httpd.enabled"))
        {
            return;
        }

        port = plugin.config.getInt("httpd.port");
        httpd = new HTTPD(port);

        // Modules
        modules.clear();
        module("dump", Module_dump.class, true);
        module("file", Module_file.class, true);
        module("help", Module_help.class, false);
        module("list", Module_list.class, false);
        module("players", Module_players.class, false);

        try
        {
            httpd.start();

            if (httpd.isAlive())
            {
                System.out.println("UMG HTTPd started. Listening on port: " + httpd.getListeningPort());
            }
            else
            {
                System.out.println("Error starting UMG HTTPd.");
            }
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    public static void stop()
    {
        if (!plugin.config.getBoolean("httpd.enabled"))
        {
            return;
        }

        httpd.stop();

        System.out.println("UMG HTTPd stopped.");
    }

    private static void module(String name, Class<? extends HTTPDModule> clazz, boolean async)
    {
        modules.put(name, ModuleExecutable.forClass(plugin, clazz, async));
    }

    private static class HTTPD extends NanoHTTPD
    {

        private HTTPD(int port)
        {
            super(port);
        }

        private HTTPD(String hostname, int port)
        {
            super(hostname, port);
        }

        @Override
        public Response serve(HTTPSession session)
        {
            final String[] args = StringUtils.split(session.getUri(), "/");

            ModuleExecutable mex = modules.get("file");
            if (args.length >= 1)
            {
                mex = modules.get(args[0].toLowerCase());
            }

            if (mex == null)
            {
                return new Response(Response.Status.NOT_FOUND, MIME_PLAINTEXT, "Error 404: Not Found - The requested resource was not found on this server.");
            }

            try
            {
                return mex.execute(session);
            }
            catch (Exception ex)
            {
                System.out.println(ex);
                return new Response(Response.Status.INTERNAL_ERROR, MIME_PLAINTEXT, "Error 500: Internal Server Error\r\n" + ex.getMessage() + "\r\n" + ExceptionUtils.getStackTrace(ex));
            }
        }
    }

    public static Response serveFileBasic(File file)
    {
        Response response = null;

        if (file != null && file.exists())
        {
            try
            {
                String mimetype = null;

                Matcher matcher = EXT_REGEX.matcher(file.getCanonicalPath());
                if (matcher.find())
                {
                    mimetype = Module_file.MIME_TYPES.get(matcher.group(1));
                }

                if (mimetype == null || mimetype.trim().isEmpty())
                {
                    mimetype = MIME_DEFAULT_BINARY;
                }

                response = new Response(Response.Status.OK, mimetype, new FileInputStream(file));
                response.addHeader("Content-Length", "" + file.length());
            }
            catch (IOException ex)
            {
                System.out.println(ex);
            }
        }

        return response;
    }

}
