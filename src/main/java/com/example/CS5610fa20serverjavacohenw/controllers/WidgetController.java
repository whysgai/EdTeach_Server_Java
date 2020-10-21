package com.example.CS5610fa20serverjavacohenw.controllers;

import com.example.CS5610fa20serverjavacohenw.models.Widget;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Java annotation to add specialized behavior -- in this case RESTful "powers"
@RestController
public class WidgetController {

    // Temporary hard-coded data
    List<Widget> widgets = new ArrayList<Widget>();
    {
        widgets.add(new Widget("001", "Widget1", "HEADING"));
        widgets.add(new Widget("002", "Widget2", "PARAGRAPH"));
        widgets.add(new Widget("003", "Widget3", "PARAGRAPH"));
    }

//    @GetMapping("/hello") // Tied to GET
//    public String sayHello() {
//        return "Hello World!";
//    }

    // CRUD operations
    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets() {
        return widgets;
    }

    @GetMapping("/api/widgets/{wid}")
    public Widget findWidgetById(
            @PathVariable("wid") String widgetId) {
        for (Widget widget: widgets) {
            if (widget.getId().equals(widgetId)) {
                return widget;
            }
        }
        return null;
    }

    @PostMapping("/api/widgets")
    public Widget createWidget(
            @RequestBody Widget widget) {
        widget.setId((new Date()).toString());
        widgets.add(widget);
        return widget;
    }


    @PutMapping("/api/widgets/{wid}")
    public Widget updateWidget(Widget deltaWidget) {
        for (Widget widget: widgets) {
            if (widget.getId().equals(deltaWidget.getId())) {
                widget = deltaWidget;
                return widget;
            }
        }
        return null;
    }

    public void deleteWidget(String widgetId) {
        widgets.removeIf(widget -> widget.getId().equals(widgetId));
    }
}
