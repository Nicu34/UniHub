package com.websystique.springmvc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicu on 6/30/2017.
 */
@Entity
public class Document implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "document")
    private List<Template> templatesList = new ArrayList<>();

    public Document() {
    }

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

    public List<Template> getTemplatesList() {
        return templatesList;
    }

    public void setTemplatesList(List<Template> templatesList) {
        this.templatesList = templatesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (id != null ? !id.equals(document.id) : document.id != null) return false;
        if (title != null ? !title.equals(document.title) : document.title != null) return false;
        return templatesList != null ? templatesList.equals(document.templatesList) : document.templatesList == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (templatesList != null ? templatesList.hashCode() : 0);
        return result;
    }
}
