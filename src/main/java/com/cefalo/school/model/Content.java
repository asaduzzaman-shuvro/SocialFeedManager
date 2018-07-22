package com.cefalo.school.model;

public class Content {
    public ContentType contentType;
    public String value; // value could be text, image url, external url or video url

    public Content(ContentType contentType, String value) {
        this.contentType = contentType;
        this.value = value;
    }
}
