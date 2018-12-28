package com.unava.dia.dotapedia.data.model;

public class UpdateArticle {
    private String titleStr;
    private String descriptionStr;
    private String dateStr;
    private String urlToFullStr;

    public String getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public String getDescriptionStr() {
        return descriptionStr;
    }

    public void setDescriptionStr(String descriptionStr) {
        this.descriptionStr = descriptionStr;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getUrlToFullStr() {
        return urlToFullStr;
    }

    public void setUrlToFullStr(String urlToFullStr) {
        this.urlToFullStr = urlToFullStr;
    }
}
