package com.masker.discover.model.entity;

/**
 * CreatedBy: masker
 * Date: 2017/5/3
 * Description: a photo statistics
 */

public class PhotoStats {

    /**
     * downloads : 16450
     * likes : 79
     * views : 1909268
     * links : {"self":"https://api.unsplash.com/photos/LBI7cgq3pbM","html":"http://unsplash.com/photos/LBI7cgq3pbM","download":"http://unsplash.com/photos/LBI7cgq3pbM/download","download_location":"https://api.unsplash.com/photos/LBI7cgq3pbM/download"}
     */

    private int downloads;
    private int likes;
    private int views;
    private LinksBean links;

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public LinksBean getLinks() {
        return links;
    }

    public void setLinks(LinksBean links) {
        this.links = links;
    }

    public static class LinksBean {
        /**
         * self : https://api.unsplash.com/photos/LBI7cgq3pbM
         * html : http://unsplash.com/photos/LBI7cgq3pbM
         * download : http://unsplash.com/photos/LBI7cgq3pbM/download
         * download_location : https://api.unsplash.com/photos/LBI7cgq3pbM/download
         */

        private String self;
        private String html;
        private String download;
        private String download_location;

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public String getDownload() {
            return download;
        }

        public void setDownload(String download) {
            this.download = download;
        }

        public String getDownload_location() {
            return download_location;
        }

        public void setDownload_location(String download_location) {
            this.download_location = download_location;
        }
    }
}
