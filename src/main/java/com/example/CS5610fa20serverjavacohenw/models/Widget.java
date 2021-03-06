package com.example.CS5610fa20serverjavacohenw.models;

import javax.persistence.*;

@Entity
@Table(name="widgets")
public class Widget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String topicId;
    private String title;
    private String type;
    private Integer widgetOrder;
    private String text;
    private String url;
    private Integer size;
    private String cssClass;
    private String style;
    private String value;
    private String heading;
    private String list;

    public Widget(Integer id, String topicId, String title, String type, Integer widgetOrder, String text, String url, Integer size, String cssClass, String style, String value, String heading, String list) {
        this.id = id;
        this.topicId = topicId;
        this.title = title;
        this.type = type;
        this.widgetOrder = widgetOrder;
        this.text = text;
        this.url = url;
        this.size = size;
        this.cssClass = cssClass;
        this.style = style;
        this.value = value;
        this.heading = heading;
        this.list = list;
    }

    public Widget(Integer id, String topicId, String title, String type, Integer widgetOrder) {
        this.id = id;
        this.topicId = topicId;
        this.title = title;
        this.type = type;
        this.widgetOrder = widgetOrder;
    }

    public Widget(Integer id, String title, String type) {
        this.id = id;
        this.title = title;
        this.type = type;
    }

    public Widget() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getWidgetOrder() {
        return widgetOrder;
    }

    public void setWidgetOrder(Integer widgetOrder) {
        this.widgetOrder = widgetOrder;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getHeading() { return heading; }

    public void setHeading(String heading) { this.heading = heading; }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

}
