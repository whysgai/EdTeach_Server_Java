package com.example.CS5610fa20serverjavacohenw.controllers;

import com.example.CS5610fa20serverjavacohenw.models.Widget;
import com.example.CS5610fa20serverjavacohenw.services.WidgetService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Java annotation to add specialized behavior -- in this case RESTful "powers"
@RestController
public class WidgetController {

    WidgetService service = new WidgetService();

    // CRUD operations
    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets() {
        return service.findAllWidgets();
    }

    @GetMapping("/api/widgets/{wid}")
    public Widget findWidgetById(
            @PathVariable("wid") String widgetId) {
        return service.findWidgetById(widgetId);
    }

    @PostMapping("/api/widgets")
    public Widget createWidget(
            @RequestBody Widget newWidget) {
        return service.createWidget(newWidget);
    }

    @PostMapping("/api/topics/{tid}/widgets")
    public Widget createWidget(
            @PathVariable("tid") String topicId,
            @RequestBody Widget newWidget) {

        return service.createWidget(topicId, newWidget);
    }

    @PutMapping("/api/widgets/{wid}")
    public Integer updateWidget(
            @PathVariable("wid") String widgetId,
            @RequestBody Widget newWidget) {
        return service.updateWidget(widgetId, newWidget);
    }

    @DeleteMapping("/api/widgets/{wid}")
    public Integer deleteWidget(
            @PathVariable("wid") String widgetId) {
        return service.deleteWidget(widgetId);
    }
}
