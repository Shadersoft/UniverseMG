package com.Shadersoft.UniverseMG.httpd.modules;

import com.Shadersoft.UniverseMG.UniverseMG;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import com.Shadersoft.UniverseMG.httpd.HTTPDPageBuilder;
import com.Shadersoft.UniverseMG.httpd.NanoHTTPD.HTTPSession;
import com.Shadersoft.UniverseMG.httpd.NanoHTTPD.Method;
import com.Shadersoft.UniverseMG.httpd.NanoHTTPD.Response;

/*
// Thanks to the TotalFreedomMod developers for this bit of code
*/


public abstract class HTTPDModule
{

    protected final String uri;
    protected final Method method;
    protected final Map<String, String> headers;
    protected final Map<String, String> params;
    protected final Socket socket;
    protected final HTTPSession session;

    public HTTPDModule(UniverseMG plugin, HTTPSession session)
    {
        this.uri = session.getUri();
        this.method = session.getMethod();
        this.headers = session.getHeaders();
        this.params = session.getParms();
        this.socket = session.getSocket();
        this.session = session;
    }

    public String getBody()
    {
        return null;
    }

    public String getTitle()
    {
        return null;
    }

    public String getStyle()
    {
        return null;
    }

    public String getScript()
    {
        return null;
    }

    public Response getResponse()
    {
        return new HTTPDPageBuilder(getBody(), getTitle(), getStyle(), getScript()).getResponse();
    }

    protected final Map<String, String> getFiles()
    {
        Map<String, String> files = new HashMap<>();

        try
        {
            session.parseBody(files);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }

        return files;
    }
}
