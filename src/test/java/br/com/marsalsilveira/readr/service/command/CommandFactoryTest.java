package br.com.marsalsilveira.readr.service.command;

import br.com.marsalsilveira.readr.exception.InvalidCommandException;
import br.com.marsalsilveira.readr.service.command.command.CountAll;
import br.com.marsalsilveira.readr.service.command.command.CountDistinct;
import br.com.marsalsilveira.readr.service.command.command.FilterPropertyValue;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;

/**
 * Unit test for CommandFactoryTest.
 */
public class CommandFactoryTest {

    @Test
    public void testCommands() {

        List<Class<? extends ReadrCommand>> commandsExpected = Arrays.asList(
                CountAll.class,
                CountDistinct.class,
                FilterPropertyValue.class
        );
        List<Class<? extends ReadrCommand>> commandsResponse = CommandFactory.commands().stream().map(command -> command.getClass()).collect(Collectors.toList());
        Assert.assertThat(commandsExpected , is(commandsResponse));
    }

    @Test
    public void testCommandCountAll() {

        try {
            Assert.assertEquals(CountAll.class, CommandFactory.command("count *").getClass());
            Assert.assertEquals(CountAll.class, CommandFactory.command(" CoUnt  *").getClass());
        } catch (InvalidCommandException e) {
            Assert.fail();
        }
    }

    @Test
    public void testExecCommandCountDistinct() {

        try {
            Assert.assertEquals(CountDistinct.class, CommandFactory.command("count distinct uf").getClass());
            Assert.assertEquals(CountDistinct.class, CommandFactory.command(" CoUnt  DisTinCt Uf").getClass());
        } catch (InvalidCommandException e) {
            Assert.fail();
        }
    }

    @Test
    public void testExecCommandFilterPropertyValue() {

        try {
            Assert.assertEquals(FilterPropertyValue.class, CommandFactory.command("filter name são josé").getClass());
            Assert.assertEquals(FilterPropertyValue.class, CommandFactory.command("filter name SÃO JOSÉ").getClass());
            Assert.assertEquals(FilterPropertyValue.class, CommandFactory.command("filter name sao jose").getClass());
            Assert.assertEquals(FilterPropertyValue.class, CommandFactory.command("filter name SAO JOSE").getClass());
            Assert.assertEquals(FilterPropertyValue.class, CommandFactory.command("  fILTer  namE sÃo    JoSe  ").getClass());
        } catch (InvalidCommandException e) {
            Assert.fail();
        }
    }

    @Test(expected = InvalidCommandException.class)
    public void testExecCommandInvalidInputException() throws InvalidCommandException {

        CommandFactory.command("[count *]");
    }
}