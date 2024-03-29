package com.petrushin.ui;


import javafx.scene.text.Font;

/**
 * Created by Petrushin Alexey on 03.12.2014.
 */
public class FontMetrizeIcons{
    public static Font getFont(Object ob, double size){
        return Font.loadFont(ob.getClass()
                .getResourceAsStream("/resources/Metrize-Icons.ttf"), size);
    }

    public static final String ARROW_RIGHT = "\uE109";

    public static final String ARROW_LEFT = "\uE117";

    public static final String QUESTION = "\uE065";

    public static final String BIND = "\uE0AD";

    public static final String EXCLAMATION = "\uE0B7";

    public static final String NEW = "\uE0CC";

    public static final String COPY = "\uE0CB";

    public static final String INFO = "\uE0A4";

    public static final String CROSS = "\uE0cf";

    public static final String EXPORT = "\uE00C";

    public static final String IMPORT = "\uE0C3";



}
