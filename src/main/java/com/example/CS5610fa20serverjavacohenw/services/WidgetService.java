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

    // O 2n + 2n^2
    // Also concurrent modification
    public List<Widget> updateWidgetsForTopic(String topicId, List<Widget> updateWidgets) {
        List<Widget> topicWidgets = findWidgetsForTopic(topicId);
        for (Widget widgetServer: topicWidgets) {
            boolean update = false;
            for (Widget widgetClient: updateWidgets) {
                // update
                if (widgetServer.getId().equals(widgetClient.getId())) {
                    updateHelper(widgetServer, widgetClient);
                    update = true;
                }
            }
            // this server widget was never found, so delete
            if (update == false) {
                widgets.remove(widgetServer);
            }
        }
        for (Widget widgetClient: updateWidgets) {
            boolean match = false;
            System.out.println("Check for ID match: ");
            System.out.println(widgetClient.getId());
            for (Widget widgetServer: topicWidgets) {
                System.out.println("...against...");
                System.out.println(widgetServer.getId());
                if (widgetServer.getId().equals(widgetClient.getId())) {
                    System.out.println("Match!");
                    match = true;
                    break;
                }
            }
            // the client widget was never found, so add
            if (match == false) {
                System.out.println("No match, creating new in server");
                createWidget(topicId, widgetClient);
            }
        }
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
