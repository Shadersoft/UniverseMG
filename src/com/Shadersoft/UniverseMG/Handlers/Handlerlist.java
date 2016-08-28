package com.Shadersoft.UniverseMG.Handlers;

import com.Shadersoft.UniverseMG.UniverseMG;

public class Handlerlist
{
    public PlayerHandler playerHandler;

    public Handlerlist() {
        this.playerHandler = new PlayerHandler(UniverseMG.plugin);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
