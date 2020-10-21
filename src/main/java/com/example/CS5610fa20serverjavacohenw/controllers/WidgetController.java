package com.example.CS5610fa20serverjavacohenw.controllers;

import com.example.CS5610fa20serverjavacohenw.models.Widget;

import java.util.ArrayList;
import java.util.List;

public class WidgetController {
    // Temporary hard-coded data
    List<Widget> widgets = new ArrayList<Widget>();
    {
        widgets.add(new Widget("001", "Widget1", "HEADING"));
        widgets.add(new Widget("002", "Widget2", "PARAGRAPH"));
        widgets.add(new Widget("003", "Widget3", "PARAGRAPH"));
    }


    // CRUD operations
    public List<Widget> findAllWidgets() {
        return null;
    }

    public Widget findWidgetById(Integer widgetId) {
        return null;
    }

    public Widget createWidget(Widget widget) {
        return null;
    }

    public void deleteWidget(Integer widgetId) {}

    public Widget updateWidget(Widget widget) {
        return null;
    }
}
