package com.example.CS5610fa20serverjavacohenw.controllers;

import com.example.CS5610fa20serverjavacohenw.models.Widget;
import com.example.CS5610fa20serverjavacohenw.services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Java annotation to add specialized behavior -- in this case RESTful "powers"
@RestController
@CrossOrigin(origins = "*")
public class WidgetController {

    @Autowired
    WidgetService service;// = new WidgetService();

    // CRUD operations
    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets() {
        return service.findAllWidgets();
    }

    @GetMapping("/api/topics/{topicId}/widgets")
    public List<Widget> findWidgetsForTopic(
            @PathVariable("topicId") String topicId) {
        return service.findWidgetsForTopic(topicId);
    }

    @GetMapping("/api/widgets/{wid}")
    public Widget findWidgetById(
            @PathVariable("wid") Integer widgetId) {
        return service.findWidgetById(widgetId);
    }

    @PostMapping("/api/widgets")
    public Widget createWidget(
            @RequestBody Widget newWidget) {
        return service.createWidget(newWidget);
    }

    @PostMapping("/api/topics/{topicId}/widgets")
    public Widget createWidget(
            @PathVariable("topicId") String topicId,
            @RequestBody Widget newWidget) {
        newWidget.setTopicId(topicId);
//        newWidget.setId(null);
        return service.createWidget(newWidget);
    }

    @PutMapping("/api/topics/{topicId}/widgets")
    public List<Widget> updateWidgetsForTopic(
            @PathVariable("topicId") String topicId,
            @RequestBody List<Widget> widgets) {
        return service.updateWidgetsForTopic(topicId, widgets);
    }

    @PutMapping("/api/widgets/{widgetId}")
    public Integer updateWidget(
            @PathVariable("widgetId") Integer widgetId,
            @RequestBody Widget deltaWidget) {
        return service.updateWidget(widgetId, deltaWidget);
    }

    @DeleteMapping("/api/widgets/{widgetId}")
    public Integer deleteWidget(
            @PathVariable("widgetId") Integer widgetId) {
        return service.deleteWidget(widgetId);
    }
}
