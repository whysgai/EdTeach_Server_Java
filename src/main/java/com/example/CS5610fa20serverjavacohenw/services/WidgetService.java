package com.example.CS5610fa20serverjavacohenw.services;

import com.example.CS5610fa20serverjavacohenw.models.Widget;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WidgetService {

    // Temporary hard-coded data
    List<Widget> widgets = new ArrayList<Widget>();
    {
        widgets.add(new Widget("001", "5f8a045dba6bf60017a5023c", "Widget1", "HEADING", 0));
        widgets.add(new Widget("002", "5f8a045dba6bf60017a5023c", "Widget2", "PARAGRAPH", 1));
        widgets.add(new Widget("003", "5f8a045dba6bf60017a5023c", "Widget3", "PARAGRAPH", 2));
        widgets.add(new Widget("004", "topic2", "Widget3", "HEADING", 0));
    }

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

    private void updateHelper(Widget oldWidget, Widget newWidget) {
        oldWidget.setTitle(newWidget.getTitle());
        oldWidget.setType(newWidget.getType());
        oldWidget.setWidgetOrder(newWidget.getWidgetOrder());
        oldWidget.setText(newWidget.getText());
        oldWidget.setUrl(newWidget.getUrl());
        oldWidget.setCssClass(newWidget.getCssClass());
        oldWidget.setStyle(newWidget.getStyle());
        oldWidget.setValue(newWidget.getValue());
        oldWidget.setHeading(newWidget.getHeading());
    }

    public Integer updateWidget(String widgetId, Widget newWidget) {
        for (Widget widget: widgets) {
            if (widget.getId().equals(widgetId)) {
                updateHelper(widget, newWidget);
                return 1;
            }
        }
        return 0;
    }

    // O 2n + 2n^2 This isn't great, but I cannot think of a way to simplify it.
    // Also concurrent modification, should use iterators instead of for: each
    public List<Widget> updateWidgetsForTopic(String topicId, List<Widget> updateWidgets) {
        // Narrow list down to widgets for this topic
        // Normally decreasing N isn't the important part, but in this case I think it helps
        List<Widget> topicWidgets = findWidgetsForTopic(topicId);
        // For each widget on the server...
        for (Widget widgetServer: topicWidgets) {
            boolean update = false;
            // ... check against widgets from the client...
            for (Widget widgetClient: updateWidgets) {
                // ... and if they match, update them
                if (widgetServer.getId().equals(widgetClient.getId())) {
                    updateHelper(widgetServer, widgetClient);
                    update = true;
                }
            }
            // If this server-side widget was not matched, it no longer exists on the client
            if (!update) {
                widgets.remove(widgetServer);
            }
        }
        // Now, for each widget from the client...
        for (Widget widgetClient: updateWidgets) {
            boolean match = false;
            System.out.println(widgetClient.getId());
            // ... check it against the widgets on the server ...
            for (Widget widgetServer: topicWidgets) {
                // ... and if they match, mark it ...
                if (widgetServer.getId().equals(widgetClient.getId())) {
                    match = true;
                    break;
                }
            }
            // ... so that unmatched widgets can be added.
            if (!match) {
                createWidget(topicId, widgetClient);
            }
        }
        // Return the updated list of server-side widgets
        return findWidgetsForTopic(topicId);
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
