package com.websystique.springmvc.controller;

import com.websystique.springmvc.model.Document;
import com.websystique.springmvc.model.Keyword;
import com.websystique.springmvc.model.Template;
import com.websystique.springmvc.service.DocumentService;
import com.websystique.springmvc.service.KeywordService;
import com.websystique.springmvc.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nicu on 6/7/2017.
 */
@Controller
public class AppController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private KeywordService keywordService;

    private boolean wereAdded = false;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getStartPage(ModelMap modelMap) {
        modelMap.put("documents", getDocumentsList());

        return "documents";
    }

    @RequestMapping(value = "/addKeyword", method = RequestMethod.GET)
    public String getAddKeywordPage(ModelMap modelMap) {
        modelMap.put("keyword", new Keyword());

        return "addKeyword";
    }

    @RequestMapping(value = "/addKeyword", method = RequestMethod.POST)
    public String addKeyword(@ModelAttribute Keyword keyword) {
        keywordService.save(keyword);

        return "keywords";
    }

    private List<Document> getDocumentsList() {
        if (!wereAdded) {
            Template template1 = new Template();
            template1.setName("template1");
            template1.setPrivate(true);
            template1.setTextContent("content from template1");

            Template template2 = new Template();
            template2.setName("template2");
            template2.setPrivate(false);
            template2.setTextContent("content from template2");

            Template template3 = new Template();
            template3.setName("template3");
            template3.setPrivate(false);
            template3.setTextContent("content from template3");

            Template template4 = new Template();
            template4.setName("template4");
            template4.setPrivate(false);
            template4.setTextContent("content from template4");

            Document document1 = new Document();
            document1.setTitle("Document1");
            document1.setTemplatesList(Arrays.asList(template1, template2));

            Document document2 = new Document();
            document2.setTitle("Document2");
            document2.setTemplatesList(Arrays.asList(template3, template4));

            Document document3 = new Document();
            document3.setTitle("Document3");
            document3.setTemplatesList(Arrays.asList(template1, template3));

            Document document4 = new Document();
            document4.setTitle("Document4");
            document4.setTemplatesList(Arrays.asList(template2, template4));

            documentService.save(document1);
            documentService.save(document2);
            documentService.save(document3);
            documentService.save(document4);

            wereAdded = true;
        }

        return documentService.findAll();
    }
}
