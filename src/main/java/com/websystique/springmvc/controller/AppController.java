package com.websystique.springmvc.controller;

import com.websystique.springmvc.dto.TemplateDto;
import com.websystique.springmvc.model.Document;
import com.websystique.springmvc.model.Keyword;
import com.websystique.springmvc.model.Template;
import com.websystique.springmvc.service.DocumentService;
import com.websystique.springmvc.service.KeywordService;
import com.websystique.springmvc.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/addDocument" ,method = RequestMethod.GET)
    public String getAddDocumentPage(ModelMap modelMap) {
        modelMap.put("document", new Document());

        return "addDocument";
    }

    @RequestMapping(value = "/addDocument", method = RequestMethod.POST)
    public String addDocument(@ModelAttribute Document document) {
        documentService.save(document);

        return "redirect:/";
    }

    @RequestMapping(value = "/addTemplate", method = RequestMethod.GET)
    public String addTemplate(ModelMap modelMap) {
        modelMap.put("template", new TemplateDto());
        modelMap.put("documents", documentService.findAll());

        return "addTemplate";
    }

    @RequestMapping(value = "/templates", method = RequestMethod.GET)
    public String getTemplates(ModelMap modelMap) {
        modelMap.put("templates", templateService.findAll());

        return "templates";
    }


    @RequestMapping(value = "/addTemplate", method = RequestMethod.POST)
    public String addTemplatePost(@ModelAttribute TemplateDto templateDto) {
        Template template = new Template();

        template.setDocument(documentService.findById(templateDto.getDocumentId()));
        template.setTextContent(templateDto.getTextContent());
        template.setName(templateDto.getName());
        template.setIsPrivate(templateDto.getIsPrivate());

        templateService.save(template);

        return "redirect:/templates";
    }

    @RequestMapping(value = "/keywords", method = RequestMethod.GET)
    public String getKeywordsPage(ModelMap modelMap) {
        modelMap.put("keywords", keywordService.findAll());

        return "keywords";
    }

    @RequestMapping(value = "/addKeyword", method = RequestMethod.GET)
    public String getAddKeywordPage(ModelMap modelMap) {
        modelMap.put("keyword", new Keyword());

        return "addKeyword";
    }

    @RequestMapping(value = "/viewDocument-{id}", method = RequestMethod.GET)
    public String viewDocument(@PathVariable Integer id, ModelMap modelMap) {
        modelMap.put("document", documentService.findById(id));

        return "viewDocument";
    }

    @RequestMapping(value = "/addKeyword", method = RequestMethod.POST)
    public String addKeyword(@ModelAttribute Keyword keyword) {
        keywordService.save(keyword);

        return "redirect:/keywords";
    }

    @Transactional
    private List<Document> getDocumentsList() {
        if (!wereAdded) {
            Template template1 = new Template();
            template1.setName("template1");
            template1.setIsPrivate(1);
            template1.setTextContent("content from template1");

            Template template2 = new Template();
            template2.setName("template2");
            template2.setIsPrivate(0);
            template2.setTextContent("content from template2");

            Template template3 = new Template();
            template3.setName("template3");
            template3.setIsPrivate(1);
            template3.setTextContent("content from template3");

            Template template4 = new Template();
            template4.setName("template4");
            template4.setIsPrivate(1);
            template4.setTextContent("content from template4");

            Template template5 = new Template();
            template5.setName("template5");
            template5.setIsPrivate(0);
            template5.setTextContent("content from template5");

            Template template6 = new Template();
            template6.setName("template6");
            template6.setIsPrivate(1);
            template6.setTextContent("content from template6");

            Template template7 = new Template();
            template7.setName("template7");
            template7.setIsPrivate(0);
            template7.setTextContent("content from template7");

            Document document1 = new Document();
            document1.setTitle("Document1");

            Document document2 = new Document();
            document2.setTitle("Document2");

            Document document3 = new Document();
            document3.setTitle("Document3");

            Document document4 = new Document();
            document4.setTitle("Document4");

            documentService.save(document1);
            documentService.save(document2);
            documentService.save(document3);
            documentService.save(document4);

            template1.setDocument(document1);
            template2.setDocument(document1);
            template3.setDocument(document2);
            template4.setDocument(document2);
            template5.setDocument(document3);
            template6.setDocument(document3);
            template7.setDocument(document4);

            templateService.save(template1);
            templateService.save(template2);
            templateService.save(template3);
            templateService.save(template4);
            templateService.save(template5);
            templateService.save(template6);
            templateService.save(template7);

            wereAdded = true;
        }

        return documentService.findAll();
    }
}
