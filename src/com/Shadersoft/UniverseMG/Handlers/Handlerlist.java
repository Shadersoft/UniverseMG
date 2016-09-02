package com.Shadersoft.UniverseMG.Handlers;

import com.Shadersoft.UniverseMG.UniverseMG;

public class Handlerlist
{
    public PlayerHandler playerHandler;
    public ChatHandler chatHandler;
    public CommandHandler commandHandler;
    public ServerListener serverHandler;
    public BlockListener blockHandler;

    public Handlerlist() 
    {
        this.playerHandler = new PlayerHandler(UniverseMG.plugin);
        this.chatHandler = new ChatHandler(UniverseMG.plugin);
        this.commandHandler = new CommandHandler(UniverseMG.plugin);
        this.serverHandler = new ServerListener(UniverseMG.plugin);
        this.blockHandler = new BlockListener();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
