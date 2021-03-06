package com.apps.web;

import com.apps.entity.UserDao;
import com.apps.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Data
@Slf4j
public class MainVM {

    @SuppressWarnings("rawtypes")
    Map<String, PageModel> menus = new HashMap<>();
    private PageModel<String> currentPage;

    @WireVariable("userService")
    private UserService service;

    private static final String ROOT = "root";

    @SuppressWarnings("hiding")
    @Init
    public void init(@ContextParam(ContextType.APPLICATION) WebApp webapp) {
        log.info("*** MainViewModel.init() ***");
        try {
            List<UserDao> list = service.getCurrentUserList();
            log.info("資料長度：{}", list.size());
            //	List<MenuDao> list = service.findMenuBySystemId((long) 1);
            final URL url = webapp.getServletContext().getResource("/page/system-paths.xml");
            menus = loadSystemMultiModules(url);
            currentPage = new PageModel<>("/page/welcome.zul", "");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Map<String, PageModel> loadSystemMultiModules(URL url) {
        if (null == url) {
            return null;
        }
        try {
            final String data = IOUtils.toString(url);
            Document document = DocumentHelper.parseText(data);
            Element rootNode = document.getRootElement();
            String rootElement = rootNode.getName();
            log.info("rootElement = " + rootElement);
            if (!ROOT.equals(rootElement)) {
                log.error("Multiple modules must be started with a root element");
            }
            for (Iterator<Element> i = rootNode.elementIterator(); i.hasNext(); ) {
                Element child = i.next();
                String value = "";
                String src = "";
                for (Iterator<?> it = child.attributeIterator(); it.hasNext(); ) {
                    Attribute attribute = (Attribute) it.next();
                    if ("label".equals(attribute.getName())) {
                        value = attribute.getText();
                    } else if ("src".equals(attribute.getName())) {
                        src = attribute.getText();
                    }
                }
                log.info("label = " + value + ",src = " + src);
                menus.put(value, new PageModel<String>(src, ""));
            }
            return menus;
        } catch (IOException | DocumentException e) {
            log.error(e.getMessage(), e);
            return menus;
        }
    }

    @SuppressWarnings("unchecked")
    @Command
    @NotifyChange("currentPage")
    public void navigate(@BindingParam("page") String page) {
        this.currentPage = menus.get(page);
    }

    public PageModel<String> getCurrentPage() {
        return currentPage;
    }

}
