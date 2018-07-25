package com.cefalo.school.model;

public class Content {
    public ContentType contentType;
    public String value; // value could be image url, external url or video url for medias
    public String description; // image/video caption, in case link description

    public Content(ContentType contentType, String value, String description) {
        this.contentType = contentType;
        this.value = value;
        this.description = description;
    }
}
