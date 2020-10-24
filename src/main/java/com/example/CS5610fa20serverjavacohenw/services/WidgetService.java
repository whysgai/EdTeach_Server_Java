package com.example.CS5610fa20serverjavacohenw.services;

import com.example.CS5610fa20serverjavacohenw.models.Widget;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WidgetService {

    // Temporary hard-coded data
    List<Widget> widgets = new ArrayList<Widget>();
    {
        widgets.add(new Widget("001", "5f8a045dba6bf60017a5023c", "Widget1", "HEADING", 1));
        widgets.add(new Widget("002", "5f8a045dba6bf60017a5023c", "Widget2", "PARAGRAPH", 2));
        widgets.add(new Widget("003", "topic2", "Widget3", "PARAGRAPH", 1));
    }

    // Helper methods
//    private List<Widget> sortWidgetsByOrder() {
//        return widgets;
//    }

    // CRUD operations
    public List<Widget> findAllWidgets() {
        return widgets;
    }

    public List<Widget> findWidgetsForTopic(String topicId) {
        List<Widget> returnList = new ArrayList<Widget>();
        for (Widget widget: widgets) {
            if (widget.getTopicId().equals(topicId)) {
                returnList.add(widget);
            }
        }
        return returnList;
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


    public Widget createWidget(String topicId, Widget widget) {
        widget.setId((new Date()).toString());
        widget.setTopicId(topicId);
        widgets.add(widget);
        return widget;
    }

    public Integer updateWidget(String widgetId, Widget newWidget) {
        for (Widget widget: widgets) {
            if (widget.getId().equals(widgetId)) {
                widget.setTitle(newWidget.getTitle());
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
