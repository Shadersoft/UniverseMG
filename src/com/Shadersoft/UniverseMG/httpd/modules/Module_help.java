package com.Shadersoft.UniverseMG.httpd.modules;

import com.Shadersoft.UniverseMG.Commands.UMGCommand;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import static com.Shadersoft.UniverseMG.httpd.HTMLGenerationTools.heading;
import static com.Shadersoft.UniverseMG.httpd.HTMLGenerationTools.paragraph;
import com.Shadersoft.UniverseMG.httpd.NanoHTTPD;
import java.util.ArrayList;
import net.pravian.aero.command.CommandReflection;
import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.configuration.ConfigurationSection;

public class Module_help extends HTTPDModule
{

    public Module_help(UniverseMG plugin, NanoHTTPD.HTTPSession session)
    {
        super(plugin, session);
    }

    @Override
    public String getBody()
    {
        final CommandMap map = CommandReflection.getCommandMap();
        if (map == null || !(map instanceof SimpleCommandMap))
        {
            return paragraph("Error loading commands.");
        }

        final StringBuilder responseBody = new StringBuilder()
                .append(heading("Command Help", 1))
                .append(paragraph(
                                "This page is an automatically generated listing of all plugin commands that are currently live on the server. "
                                + "Please note that it does not include vanilla server commands."));

        final Collection<Command> knownCommands = ((SimpleCommandMap) map).getCommands();
        final Map<String, List<Command>> commandsByPlugin = new HashMap<>();

        for (Command command : knownCommands)
        {
            String pluginName = "Bukkit";
            if (command instanceof PluginIdentifiableCommand)
            {
                pluginName = ((PluginIdentifiableCommand) command).getPlugin().getName();
            }

            List<Command> pluginCommands = commandsByPlugin.get(pluginName);
            if (pluginCommands == null)
            {
                pluginCommands = Lists.newArrayList();
                commandsByPlugin.put(pluginName, pluginCommands);
            }

            pluginCommands.add(command);
        }

        final Iterator<Map.Entry<String, List<Command>>> it = commandsByPlugin.entrySet().iterator();
        while (it.hasNext())
        {
            final Map.Entry<String, List<Command>> entry = it.next();
            final String pluginName = entry.getKey();
            final List<Command> commands = entry.getValue();

            Collections.sort(commands, new CommandComparator());

            responseBody.append(heading(pluginName, 2)).append("<ul>\r\n");

            List<String> playercommands = new ArrayList();
            List<String> helpercommands = new ArrayList();
            List<String> moderatorcommands = new ArrayList();
            List<String> admincommands = new ArrayList();
            List<String> developercommands = new ArrayList();
            List<String> maindevelopercommands = new ArrayList();
            List<String> coownercommands = new ArrayList();
            List<String> ownercommands = new ArrayList();
            Rank lastUmgCommandLevel = null;
            ConfigurationSection commandBlocker = UniverseMG.plugin.config.getConfigurationSection("commandblocker");
            for (Command command : commands)
            {
                if ("UniverseMG".equals(pluginName) || commandBlocker.contains("/" + command.getName()))
                {
                    Rank umgCommandLevel = Rank.getRank(commandBlocker.getString("/" + command.getName()));
                        
                    switch(umgCommandLevel)
                    {
                        case PLAYER:
                        {
                            playercommands.add(buildDescription(command));
                        }
                        case HELPER:
                        {
                            helpercommands.add(buildDescription(command));
                        }
                        case MODERATOR:
                        {
                            moderatorcommands.add(buildDescription(command));
                        }
                        case ADMIN:
                        {
                            admincommands.add(buildDescription(command));
                        }
                        case DEV:
                        {
                            developercommands.add(buildDescription(command));
                        }
                        case MAINDEV:
                        {
                            maindevelopercommands.add(buildDescription(command));
                        }
                        case COOWNER:
                        {
                            coownercommands.add(buildDescription(command));
                        }
                        case OWNER:
                        {
                            ownercommands.add(buildDescription(command));
                        }
                    }
                        
                    if (lastUmgCommandLevel == null || lastUmgCommandLevel != umgCommandLevel)
                    {
                        responseBody.append("</ul>\r\n").append(heading(umgCommandLevel.getName(), 3)).append("<ul>\r\n");
                    }
                    lastUmgCommandLevel = umgCommandLevel;
                    responseBody.append(buildDescription(command));
                }
                else
                {
                    responseBody.append(buildDescription(command));
                }
            }

            responseBody.append("</ul>\r\n");
        }

        return responseBody.toString();
    }

    private static String buildDescription(Command command)
    {
        StringBuilder sb = new StringBuilder();

        sb.append(
                "<li><span class=\"commandName\">{$CMD_NAME}</span> - Usage: <span class=\"commandUsage\">{$CMD_USAGE}</span>"
                .replace("{$CMD_NAME}", escapeHtml4("/" + command.getName().trim()))
                .replace("{$CMD_USAGE}", escapeHtml4(command.getUsage().trim())));

        if (!command.getAliases().isEmpty())
        {
            sb.append(
                    " - Aliases: <span class=\"commandAliases\">{$CMD_ALIASES}</span>"
                    .replace("{$CMD_ALIASES}", escapeHtml4("/" + StringUtils.join(command.getAliases(), ", /"))));
        }

        sb.append(
                "<br><span class=\"commandDescription\">{$CMD_DESC}</span></li>\r\n"
                .replace("{$CMD_DESC}", escapeHtml4(command.getDescription().trim())));

        return sb.toString();
    }

    @Override
    public String getTitle()
    {
        return UniverseMG.plugin.config.getString("server_name") + " - Commands";
    }

    @Override
    public String getStyle()
    {
        return ".commandName{font-weight:bold;}.commandDescription{padding-left:15px;}li{margin:.15em;padding:.15em;}";
    }

    public static class CommandComparator implements Comparator<Command>
    {

        @Override
        public int compare(Command a, Command b)
        {
            return 0;
            /*UMGCommand ca = (UMGCommand)((PluginCommand)a).getExecutor();
            UMGCommand cb = (UMGCommand)((PluginCommand)b).getExecutor();

            if (ca == null
                    || cb == null
                    || ca.getRank() == null
                    || cb.getRank() == null)
            {
                return a.getName().compareTo(b.getName());
            }

            return ca.getRank().compareTo(cb.getRank());*/
        }
    }

}
