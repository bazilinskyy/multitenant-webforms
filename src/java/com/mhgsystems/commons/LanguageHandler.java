/*
 * MHG Systems Oy Ltd
 */
package com.mhgsystems.commons;

import com.mhgsystems.ui.SessionBean;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Veli-Matti Plosila
 */
public class LanguageHandler extends ThreadLocal implements Serializable {

    public final static String DEFAULT = "en";
    //private static String instanceLanguage = null;
    private static ThreadLocalLanguage instanceLanguageLocal = new ThreadLocalLanguage();

    public LanguageHandler() {
    }

    public LanguageHandler(String language) {
        if (language != null) {
            this.setLanguage(language);
        } else {
            this.setLanguage(LanguageHandler.DEFAULT);
        }
    }
    private String language;

    public String getLanguage() {
        if (language == null) {
            this.language = this.getSessionLanguage();
        }
        return language;
    }

    public static void setInstanceLanguage(String language) {
        //instanceLanguage = language;
        LanguageHandler.setThreadLocalLanguage(language);
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Locale getLocale() {
        return new Locale(this.getLanguage());
    }

    public static LanguageHandler getNewInstance() {
        return new LanguageHandler();
    }

    private String getSessionLanguage() {
        try {
            SessionBean sessionBean = null;

            if (FacesContext.getCurrentInstance() != null) {

                HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                sessionBean = (SessionBean) req.getSession(true).getAttribute("sessionBean");

                if (sessionBean != null) {
                    if (sessionBean.getLanguage() == null) {
                        sessionBean.setLanguage(FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
                    }
                }

            } else if (LanguageHandler.getThreadLocalLanguage() != null) {
                return LanguageHandler.getThreadLocalLanguage();
            }

            if (sessionBean != null) {
                return sessionBean.getLanguage();
            } else {
                return LanguageHandler.DEFAULT;
            }

        } catch (Exception ex) {
            Logger.getInstance().log("Using default language." + LanguageHandler.DEFAULT, ex);

            return LanguageHandler.DEFAULT;
        }
    }

    public boolean isSupported(String language) {

        boolean supported = false;

        for (Iterator it = FacesContext.getCurrentInstance().getApplication().getSupportedLocales(); it.hasNext();) {
            Locale l = (Locale) it.next();

            if (l.getLanguage().equals(language)) {
                supported = true;
                l = null;
                break;
            }
            l = null;
        }

        return supported;

    }

    public boolean isSupported(String language, String[] languages) {

        boolean supported = false;

        for (int i = 0; i < languages.length; i++) {
            if (language.equals(languages[i])) {
                supported = true;
                break;
            }
        }

        return supported;

    }

    private static class ThreadLocalLanguage extends ThreadLocal {

        @Override
        public Object initialValue() {
            return null;
        }
    }

    public static String getThreadLocalLanguage() {
        if (instanceLanguageLocal.get() != null) {
            return (String) instanceLanguageLocal.get();
        } else {
            return null;
        }
    }

    public static void setThreadLocalLanguage(String language) {
        instanceLanguageLocal.set(language);
    }
}
