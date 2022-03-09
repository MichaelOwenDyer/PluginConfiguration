package com.monst.pluginconfiguration.impl;

import com.monst.pluginconfiguration.ConfigurationValue;
import com.monst.pluginconfiguration.exception.ArgumentParseException;
import com.monst.pluginconfiguration.exception.UnreadableValueException;
import com.monst.pluginconfiguration.exception.ValueOutOfBoundsException;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A configuration value of the type {@link Boolean}.
 * This class avoids the use of {@link Boolean#parseBoolean(String)} in favor of a more strict parser.
 */
public class BooleanConfigurationValue extends ConfigurationValue<Boolean> {

    public BooleanConfigurationValue(JavaPlugin plugin, String path, Boolean defaultValue) {
        super(plugin, path, defaultValue);
    }

    @Override
    protected Boolean parse(String input) throws ArgumentParseException {
        if (input.equalsIgnoreCase("true"))
            return true;
        if (input.equalsIgnoreCase("false"))
            return false;
        throw createArgumentParseException(input);
    }

    protected ArgumentParseException createArgumentParseException(String input) {
        return new ArgumentParseException();
    }

    @Override
    protected Boolean convert(Object o) throws ValueOutOfBoundsException, UnreadableValueException {
        if (o instanceof Boolean)
            return (Boolean) o;
        try {
            throw new ValueOutOfBoundsException(parse(o.toString()));
        } catch (ArgumentParseException e) {
            throw new UnreadableValueException();
        }
    }

}