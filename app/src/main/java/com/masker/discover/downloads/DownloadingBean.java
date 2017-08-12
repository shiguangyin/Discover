package com.masker.discover.downloads;

/**
 * Author: masker
 * Date: 2017/8/12
 * Description:
 */

public class DownloadingBean {
    private long id;
    private String title;
    private String localUri;
    private String uri;
    private long totalBytes;
    private long curBytes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocalUri() {
        return localUri;
    }

    public void setLocalUri(String localUri) {
        this.localUri = localUri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public long getTotalBytes() {
        return totalBytes;
    }

    public void setTotalBytes(long totalBytes) {
        this.totalBytes = totalBytes;
    }

    public long getCurBytes() {
        return curBytes;
    }

    public void setCurBytes(long curBytes) {
        this.curBytes = curBytes;
    }
}
