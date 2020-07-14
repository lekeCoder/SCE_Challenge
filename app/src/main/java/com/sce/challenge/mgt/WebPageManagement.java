package com.sce.challenge.mgt;

import com.sce.challenge.model.Permission;
import com.sce.challenge.model.WebPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebPageManagement {
    private static WebPageManagement webManagement = null;
    private Map<Integer, WebPage> webs;
    private static int cnt =0;

    private WebPageManagement() {
        webs = new HashMap<>();
        createWebPages();
    }

    public static WebPageManagement getInstance(){
        if(webManagement == null){
            webManagement = new WebPageManagement();
        }
        return webManagement;
    }

    public int addWebPage(String web){
        int newId = ++cnt;
        webs.put(newId,new WebPage(newId, web));
        return newId;

    }

    public List<WebPage> getWebPages() {
        return new ArrayList<>(webs.values());
    }

    private void createWebPages(){
        WebPage web1 = new WebPage(1, "google.com");
        WebPage web2 = new WebPage(2, "amazon.com");
        WebPage web3 = new WebPage(3, "walmart.com");
        WebPage web4 = new WebPage(4, "ebay.com");
        WebPage web5 = new WebPage(5, "discover.com");
        webs.put(1,web1);
        webs.put(2,web2);
        webs.put(3,web3);
        webs.put(4,web4);
        webs.put(5,web5);
        cnt += 5;
    }

    public WebPage getWebPage(int webId){
        for (WebPage web : webs.values()) {
            if(web.getId() == webId){
                return web;
            }
        }
        return null;
    }


    public void updateWebPage(WebPage web) {
        webs.put(web.getId(), web);
    }

    public WebPage removeWebPage(int webId) {
        return webs.remove(webId);
    }

    public int getWebCount() {
        return webs.size();
    }

    public List<Permission> getPermissions(int webId) {
        List<Permission> perms = new ArrayList<>();
        WebPage webPage = getWebPage(webId);
        if(webPage != null){
            return webPage.getPermissions();
        }
        return perms;
    }
}
