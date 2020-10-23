package com.example.CS5610fa20serverjavacohenw.services;

import com.example.CS5610fa20serverjavacohenw.models.Widget;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WidgetService {

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


    public Widget findWidgetById(String widgetId) {
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


    public Widget createWidget(String topicId, Widget newWidget) {

        return newWidget;
    }

    public Integer updateWidget(String widgetId, Widget newWidget) {
        for (Widget widget: widgets) {
            if (widget.getId().equals(widgetId)) {
                widget.setName(newWidget.getName());
                widget.setType(newWidget.getType());
                return 1;
            }
        }
        return 0;
    }

     public Integer deleteWidget(String widgetId) {
        for (Widget widget: widgets) {
            if (widget.getId().equals(widgetId)) {
                widgets.remove(widget);
                return 1;
            }
        }
        return 0;
    }
}
