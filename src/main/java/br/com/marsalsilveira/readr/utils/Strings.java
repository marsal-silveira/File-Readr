package br.com.marsalsilveira.readr.utils;

/**
 *
 */
public final class Strings {

    // Avoid to create it
    private Strings() { }

    //******************************************************************************************************************
    //* Welcome | Initial
    //******************************************************************************************************************

    private static final String welcome_description = "Readr is a command line tool that allows you to get some data from a file executing specifics commands.";

    private static final String welcome_1 = "  ____                _      _";
    private static final String welcome_2 = " |  _ \\ ___  __ _  __| |_ __| |";
    private static final String welcome_3 = " | |_) / _ \\/ _` |/ _` | '__| |";
    private static final String welcome_4 = " |  _ <  __/ (_| | (_| | |  |_|";
    private static final String welcome_5 = " |_| \\_\\___|\\__,_|\\__,_|_|  (_)";
    public static final String welcome = welcome_1 + "\n" + welcome_2 + "\n" + welcome_3 + "\n" + welcome_4 + "\n" + welcome_5 + "\n\n" + welcome_description;

//     private static final String welcome_1 = "                                                                      dddddddd";
//     private static final String welcome_2 = "  RRRRRRRRRRRRRRRRR                                                   d::::::d                        !!!";
//     private static final String welcome_3 = "  R::::::::::::::::R                                                  d::::::d                       !!:!!";
//     private static final String welcome_4 = "  R::::::RRRRRR:::::R                                                 d::::::d                       !:::!";
//     private static final String welcome_5 = "  RR:::::R     R:::::R                                                d:::::d                        !:::!";
//     private static final String welcome_6 = "    R::::R     R:::::R     eeeeeeeeeeee      aaaaaaaaaaaa       ddddddddd:::::d rrrrr   rrrrrrrrr    !:::!";
//     private static final String welcome_7 = "    R::::R     R:::::R   ee::::::::::::ee   a::::::::::::a    dd::::::::::::::d r::::rrr:::::::::r   !:::!";
//     private static final String welcome_8 = "    R::::RRRRRR:::::R   e::::::eeeee:::::ee aaaaaaaaa:::::a  d::::::::::::::::d r:::::::::::::::::r  !:::!";
//     private static final String welcome_9 = "    R::::RRRRRR:::::R  e:::::::eeeee::::::e   aaaaaaa:::::ad ::::::d    d:::::d  r:::::r     r:::::r !:::!";
//    private static final String welcome_10 = "    R::::R     R:::::R e:::::::::::::::::e  aa::::::::::::ad :::::d     d:::::d  r:::::r     rrrrrrr !:::!";
//    private static final String welcome_11 = "    R::::R     R:::::R e::::::eeeeeeeeeee  a::::aaaa::::::ad :::::d     d:::::d  r:::::r             !!:!!";
//    private static final String welcome_12 = "    R::::R     R:::::R e:::::::e          a::::a    a:::::ad :::::d     d:::::d  r:::::r              !!!";
//    private static final String welcome_13 = "  RR:::::R     R:::::R e::::::::e         a::::a    a:::::ad ::::::ddddd::::::dd r:::::r";
//    private static final String welcome_14 = "  R::::::R     R:::::R  e::::::::eeeeeeee a:::::aaaa::::::a  d:::::::::::::::::d r:::::r              !!!";
//    private static final String welcome_15 = "  R::::::R     R:::::R   ee:::::::::::::e  a::::::::::aa:::a  d:::::::::ddd::::d r:::::r             !!:!!";
//    private static final String welcome_16 = "  RRRRRRRR     RRRRRRR    eeeeeeeeeeeeee    aaaaaaaaaa  aaaa   ddddddddd   ddddd rrrrrrr              !!!";
//
//    public static final String welcome = welcome_1 + "\n" + welcome_2 + "\n" + welcome_3 + "\n" + welcome_4 + "\n" + welcome_5 + "\n" + welcome_6 + "\n" +
//                                         welcome_7 + "\n" + welcome_8 + "\n" + welcome_9 + "\n" + welcome_10 + "\n" + welcome_11 + "\n" + welcome_12 + "\n" +
//                                         welcome_13 + "\n" + welcome_14 + "\n" + welcome_15 + "\n" + welcome_16 + "\n\n" + welcome_description;

    // Version
    public static final String version = "VERSION\n" + Strings.tab + "%s";

    // Author
    public static final String author = "AUTHOR\n" + Strings.tab + "Marsal Silveira (https://github.com/marsal-silveira)";

    //******************************************************************************************************************
    //* File Fields
    //******************************************************************************************************************

    private static final String fileFields_1 = "FILE FIELDS:\n";
    private static final String fileFields_2 = Strings.tab + "%s";

    public static final String fileFields = fileFields_1 + fileFields_2;

    //******************************************************************************************************************
    //* Commands
    //******************************************************************************************************************

    private static final String commands_1 = "COMMANDS:\n";
    private static final String commands_2 = Strings.tab + "%s\n";
    private static final String commands_3 = Strings.tab + "exit -> close program.\n\n";
    private static final String commands_4 = "Please type the command and press <ENTER>.";

    public static final String commands = commands_1 + commands_2 + commands_3 + commands_4;
    public static final String exit = "exit";

    //******************************************************************************************************************
    //* Utils
    //******************************************************************************************************************

    // two spaces
    public static final String tab = "  ";

    public static final String finishing = "Finishing Readr...";

    //******************************************************************************************************************
    //* Exceptions
    //******************************************************************************************************************

    public static final String invalidCommandExceptionDefaultMessage = "commad not found.";

}