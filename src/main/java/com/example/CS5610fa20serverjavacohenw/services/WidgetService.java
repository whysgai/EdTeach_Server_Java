package com.example.CS5610fa20serverjavacohenw.services;

import com.example.CS5610fa20serverjavacohenw.models.Widget;
import com.example.CS5610fa20serverjavacohenw.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WidgetService {

    @Autowired
    WidgetRepository widgetRepository;

    // Temporary hard-coded data
//    List<Widget> widgets = new ArrayList<Widget>();
//    {
//        widgets.add(new Widget(001, "5f8a045dba6bf60017a5023c", "Widget1", "HEADING", 0));
//        widgets.add(new Widget(002, "5f8a045dba6bf60017a5023c", "Widget2", "PARAGRAPH", 1));
//        widgets.add(new Widget(003, "5f8a045dba6bf60017a5023c", "Widget3", "PARAGRAPH", 2));
//        widgets.add(new Widget(004, "topic2", "Widget3", "HEADING", 0));
//    }

    // CRUD operations
    public List<Widget> findAllWidgets() {
        return (List<Widget>) widgetRepository.findAll();
    }

    public List<Widget> findWidgetsForTopic(String topicId) {
        return widgetRepository.findWidgetsForTopic(topicId);
//        List<Widget> returnList = new ArrayList<Widget>();
//        for (Widget widget: widgets) {
//            if (widget.getTopicId().equals(topicId)) {
//                returnList.add(widget);
//            }
//        }
//        return returnList;
    }

    public Widget findWidgetById(Integer widgetId) {
        return widgetRepository.findById(widgetId).get();
//        for (Widget widget: widgets) {
//            if (widget.getId().equals(widgetId)) {
//                return widget;
//            }
//        }
//        return null;
    }

    public Widget createWidget(Widget widget) {
        return widgetRepository.save(widget);
//        All this to get the current time in ms from a long primitive to an Integer object
//        Date date = new Date();
//        long time = date.getTime();
//        Long timeLong = Long.valueOf(time);
//        Integer intTime = timeLong.intValue();
//        widget.setId(Long.valueOf(new Date().getTime()).intValue());
//        widgets.add(widget);
//        return widget;
    }


    public Widget createWidget(String topicId, Widget widget) {
        widget.setTopicId(topicId);
        return widgetRepository.save(widget);
    }

    private void updateHelper(Widget oldWidget, Widget deltaWidget) {
        oldWidget.setTitle(deltaWidget.getTitle());
        oldWidget.setType(deltaWidget.getType());
        oldWidget.setWidgetOrder(deltaWidget.getWidgetOrder());
        oldWidget.setText(deltaWidget.getText());
        oldWidget.setUrl(deltaWidget.getUrl());
        oldWidget.setCssClass(deltaWidget.getCssClass());
        oldWidget.setStyle(deltaWidget.getStyle());
        oldWidget.setValue(deltaWidget.getValue());
        oldWidget.setHeading(deltaWidget.getHeading());
        oldWidget.setList(deltaWidget.getList());
    }

    public Integer updateWidget(Integer widgetId, Widget deltaWidget) {
        Widget oldWidget = widgetRepository.findById(widgetId).get();
        updateHelper(oldWidget, deltaWidget);
        widgetRepository.save(oldWidget);
//        for (Widget widget: widgets) {
//            if (widget.getId().equals(widgetId)) {
//                updateHelper(widget, deltaWidget);
//                return 1;
//            }
//        }
        return 1;
    }

    public Integer deleteWidget(Integer widgetId) {
        widgetRepository.deleteById(widgetId);
//        for (Widget widget: widgets) {
//            if (widget.getId().equals(widgetId)) {
//                widgets.remove(widget);
//                return 1;
//            }
//        }
        return 0;
    }

    // O 2n + 2n^2 This isn't great, but I cannot think of a way to simplify it.
    // Also concurrent modification, should use iterators instead of for: each
    public List<Widget> updateWidgetsForTopic(String topicId, List<Widget> updateWidgets) {
        System.out.println("Calling update all widgets SERVICE");

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
                    widgetRepository.save(widgetServer);
                    update = true;
                }
            }
            // If this server-side widget was not matched, it no longer exists on the client
            if (!update) {
                widgetRepository.deleteById(widgetServer.getId());
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


}
