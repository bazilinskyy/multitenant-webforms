/*
 * UITextHandler.java
 *
 * Created on 23. marraskuuta 2007, 12:53
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.mhgsystems.commons;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 *
 * @author Veli-Matti Plosila
 */
public class UITextHandler implements Serializable {
    
    
    private ResourceBundle bundle;
    private String language;
    /** Creates a new instance of UITextHandler */
    public UITextHandler() {
    }
    public UITextHandler(String language) {
        this.language = language;
               
    }
    
    
    
    public ResourceBundle loadBundle(String bundleName){
        LanguageHandler lh = new LanguageHandler();
        try{
            
            if(language==null){
                language = lh.getLanguage();
            }
            return ResourceBundle.getBundle(bundleName,new Locale(language));
            
        }catch(Exception ex){
            Logger.getInstance().log(ex);
            
            return null;
        }finally{
            lh=null;
        }
    }

    public String getText(String code){
        try{
            
            if(bundle==null){
                bundle = loadBundle("com/mhgsystems/ui/resources/UIResources");
            }
            
            return (String)bundle.getString(code);
            
        }catch(Exception ex){
            Logger.getInstance().log(ex);
            
            return "?? "+code+" ??";
        }
    }
}
