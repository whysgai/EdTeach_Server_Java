package com.example.CS5610fa20serverjavacohenw.models;

public class Widget {
    private String id;
    private String name;
    private String type;
    private Integer widgetOrder;
    private String text;
    private String url;
    private Integer size;
    private String cssClass;
    private String style;
    private String value;

    public Widget(String id, String name, String type, Integer widgetOrder, String text, String url, Integer size, String cssClass, String style, String value) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.widgetOrder = widgetOrder;
        this.text = text;
        this.url = url;
        this.size = size;
        this.cssClass = cssClass;
        this.style = style;
        this.value = value;
    }

    public Widget(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Widget() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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



}
