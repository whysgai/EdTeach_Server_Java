package com.example.CS5610fa20serverjavacohenw.controllers;

import com.example.CS5610fa20serverjavacohenw.models.Widget;

import java.util.ArrayList;
import java.util.Date;
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
        return widgets;
    }

    public Widget findWidgetById(Integer widgetId) {
        for (Widget widget: widgets) {
            if (widget.getId().equals(widgetId)) {
                return widget;
            }
        }
        return null;
    }

    public Widget createWidget(Widget widget) {
        widget.setId((new Date()).toString());
        widgets.add(widget);
        return widget;
    }

    public void deleteWidget(Integer widgetId) {
        for (Widget widget: widgets) {
            if (widget.getId().equals(widgetId)) {
                widgets.remove(widget);
            }
        }
    }

    public Widget updateWidget(Widget deltaWidget) {
        for (Widget widget: widgets) {
            if (widget.getId().equals(deltaWidget.getId())) {
                widget = deltaWidget;
                return widget;
            }
        }
        return null;
    }
}
