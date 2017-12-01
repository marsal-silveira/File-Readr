package br.com.marsalsilveira.readr.utils;

/**
 *
 */
public final class Strings {

    // Avoid to create it
    private Strings() { }

    //******************************************************************************************************************
    //* Utils
    //******************************************************************************************************************

    public static final String separator= "--------------------";
    public static final String tab = "\t";
    public static final String comma = ",";
    public static final String lineBreak = "\n";

    //******************************************************************************************************************
    //* About
    //******************************************************************************************************************

//     private static final String logo_1 = "";
//     private static final String logo_2 = "  RRRRRRRRRRRRRRRRR                                                   dddddddd                        !!!";
//     private static final String logo_3 = "  R::::::::::::::::R                                                  d::::::d                       !!:!!";
//     private static final String logo_4 = "  R::::::RRRRRR:::::R                                                 d::::::d                       !:::!";
//     private static final String logo_5 = "  RR:::::R     R:::::R                                                d:::::d                        !:::!";
//     private static final String logo_6 = "    R::::R     R:::::R     eeeeeeeeeeee      aaaaaaaaaaaa       ddddddddd:::::d rrrrr   rrrrrrrrr    !:::!";
//     private static final String logo_7 = "    R::::R     R:::::R   ee::::::::::::ee   a::::::::::::a    dd::::::::::::::d r::::rrr:::::::::r   !:::!";
//     private static final String logo_8 = "    R::::RRRRRR:::::R   e::::::eeeee:::::ee aaaaaaaaa:::::a  d::::::::::::::::d r:::::::::::::::::r  !:::!";
//     private static final String logo_9 = "    R::::RRRRRR:::::R  e:::::::eeeee::::::e   aaaaaaa:::::ad ::::::d    d:::::d  r:::::r     r:::::r !:::!";
//    private static final String logo_10 = "    R::::R     R:::::R e:::::::::::::::::e  aa::::::::::::ad :::::d     d:::::d  r:::::r     rrrrrrr !:::!";
//    private static final String logo_11 = "    R::::R     R:::::R e::::::eeeeeeeeeee  a::::aaaa::::::ad :::::d     d:::::d  r:::::r             !!:!!";
//    private static final String logo_12 = "    R::::R     R:::::R e:::::::e          a::::a    a:::::ad :::::d     d:::::d  r:::::r              !!!";
//    private static final String logo_13 = "  RR:::::R     R:::::R e::::::::e         a::::a    a:::::ad ::::::ddddd::::::dd r:::::r";
//    private static final String logo_14 = "  R::::::R     R:::::R  e::::::::eeeeeeee a:::::aaaa::::::a  d:::::::::::::::::d r:::::r              !!!";
//    private static final String logo_15 = "  R::::::R     R:::::R   ee:::::::::::::e  a::::::::::aa:::a  d:::::::::ddd::::d r:::::r             !!:!!";
//    private static final String logo_16 = "  RRRRRRRR     RRRRRRR    eeeeeeeeeeeeee    aaaaaaaaaa  aaaa   ddddddddd   ddddd rrrrrrr              !!!";
//    private static final String logo_17 = "";
//
//    private static final String logo =
//        logo_1 + lineBreak + logo_2 + lineBreak + logo_3 + lineBreak + logo_4 + lineBreak + logo_5 + lineBreak +
//        logo_6 + lineBreak + logo_7 + lineBreak + logo_8 + lineBreak + logo_9 + lineBreak + logo_10 + lineBreak +
//        logo_11 + lineBreak + logo_12 + lineBreak + logo_13 + lineBreak + logo_14 + lineBreak + logo_15 + lineBreak +
//        logo_16 + lineBreak + logo_17;

    private static final String logo_1 = "  ____                _      _";
    private static final String logo_2 = " |  _ \\ ___  __ _  __| |_ __| |";
    private static final String logo_3 = " | |_) / _ \\/ _` |/ _` | '__| |";
    private static final String logo_4 = " |  _ <  __/ (_| | (_| | |  |_|";
    private static final String logo_5 = " |_| \\_\\___|\\__,_|\\__,_|_|  (_)";
    private static final String logo_6 = "";
    private static final String logo = logo_1 + lineBreak + logo_2 + lineBreak + logo_3 + lineBreak + logo_4 + lineBreak + logo_5 + lineBreak + logo_6;

    private static final String about_title = "Welcome!!! Readr is a command line tool that allows you to get some data from a file.";
    private static final String author = "AUTHOR" + lineBreak + tab + "Marsal Silveira (https://github.com/marsal-silveira)";
//    private static final String version = "VERSION " + lineBreak + tab + "%s";

    public static final String about = logo + lineBreak + about_title + lineBreak + lineBreak + author;

    //******************************************************************************************************************
    //* About
    //******************************************************************************************************************

    public static final String inputFilePath = "Before continue I need know what file will be used (path) or press <RETURN> to use default [%s]:";

    //******************************************************************************************************************
    //* Available Fields
    //******************************************************************************************************************

    private static final String fields_1 = "FIELDS";
    private static final String fields_2 = lineBreak + tab + "%s";
    public static final String fields = fields_1 + fields_2;

    //******************************************************************************************************************
    //* Available Commands
    //******************************************************************************************************************

    private static final String commands_1 = "COMMANDS";
    private static final String commands_2 = lineBreak + tab + "%s";
    private static final String commands_3 = lineBreak + tab + separator;
    private static final String commands_4 = lineBreak + tab + "exit -> close Readr.";
    private static final String commands_5 = lineBreak + lineBreak + "Please type the command and press <ENTER>.";
    public static final String commands = commands_1 + commands_2 + commands_3 + commands_4 + commands_5;

    public static final String exitCommand = "exit";

    //******************************************************************************************************************
    //* Messages
    //******************************************************************************************************************

    public static final String bye = "Bye!";

    public static final String selectDefaultFile = "OK! I'll use default file `%s`. It has these FIELDS..." + lineBreak + tab + "%s";
    public static final String selectCustomFile = "Alright! I got the file and it seems OK. I found these FIELDS..." + lineBreak + tab + "%s";

    public static final String fileNotFound = "Opss! I can't find the file at `%s`." + lineBreak + lineBreak +
                                              " - Check the file name for capitalization or other typing errors." + lineBreak +
                                              " - Check to see if you can access it or was moved, renamed or deleted and try again." + lineBreak + lineBreak;

    public static final String fileEmpty = "Opss! It seems that the file has no content (empty)." + lineBreak + lineBreak +
                                           " - Check it and try again." + lineBreak + lineBreak;

    public static final String fileExtensionNotFound = "Opss! I can't identify the file type at `%s` because it doesn't have extension." + lineBreak + lineBreak +
                                                       " - Check it and try again." + lineBreak + lineBreak;

    public static final String fileInvalidExtension = "Opss! The given file extension is invalid." + lineBreak +
                                                      "Only these extensions are available:" + lineBreak + tab + ".csv" + lineBreak + lineBreak +
                                                      "Please select a file in this format and try again." + lineBreak + lineBreak;

    public static final String emptyCommand = "Opss! No command was typed. Try again!";

    public static final String invalidCommand = "Opss! An invalid command was typed. Try again!";
}