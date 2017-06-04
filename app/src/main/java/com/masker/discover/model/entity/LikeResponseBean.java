package com.masker.discover.model.entity;

/**
 * CreatedBy: masker
 * Date: 2017/6/4
 * Description:
 */

public class LikeResponseBean {


    /**
     * photo : {"id":"LF8gK8-HGSg","width":5245,"height":3497,"color":"#60544D","likes":10,"liked_by_user":true,"urls":{"raw":"https://images.unsplash.com/1/type-away.jpg","full":"https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg","regular":"https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg&w=1080&fit=max","small":"https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg&w=400&fit=max","thumb":"https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg&w=200&fit=max"},"links":{"self":"http://api.unsplash.com/photos/LF8gK8-HGSg","html":"http://unsplash.com/photos/LF8gK8-HGSg","download":"http://unsplash.com/photos/LF8gK8-HGSg/download"}}
     * user : {"id":"8VpB0GYJMZQ","username":"williamnot","name":"Thomas R.","links":{"self":"http://api.unsplash.com/users/williamnot","html":"http://api.unsplash.com/williamnot","photos":"http://api.unsplash.com/users/williamnot/photos","likes":"http://api.unsplash.com/users/williamnot/likes"}}
     */

    private PhotoBean photo;
    private UserBean user;

    public PhotoBean getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoBean photo) {
        this.photo = photo;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class PhotoBean {
        /**
         * id : LF8gK8-HGSg
         * width : 5245
         * height : 3497
         * color : #60544D
         * likes : 10
         * liked_by_user : true
         * urls : {"raw":"https://images.unsplash.com/1/type-away.jpg","full":"https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg","regular":"https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg&w=1080&fit=max","small":"https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg&w=400&fit=max","thumb":"https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg&w=200&fit=max"}
         * links : {"self":"http://api.unsplash.com/photos/LF8gK8-HGSg","html":"http://unsplash.com/photos/LF8gK8-HGSg","download":"http://unsplash.com/photos/LF8gK8-HGSg/download"}
         */

        private String id;
        private int width;
        private int height;
        private String color;
        private int likes;
        private boolean liked_by_user;
        private UrlsBean urls;
        private LinksBean links;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public boolean isLiked_by_user() {
            return liked_by_user;
        }

        public void setLiked_by_user(boolean liked_by_user) {
            this.liked_by_user = liked_by_user;
        }

        public UrlsBean getUrls() {
            return urls;
        }

        public void setUrls(UrlsBean urls) {
            this.urls = urls;
        }

        public LinksBean getLinks() {
            return links;
        }

        public void setLinks(LinksBean links) {
            this.links = links;
        }

        public static class UrlsBean {
            /**
             * raw : https://images.unsplash.com/1/type-away.jpg
             * full : https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg
             * regular : https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg&w=1080&fit=max
             * small : https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg&w=400&fit=max
             * thumb : https://images.unsplash.com/1/type-away.jpg?q=80&fm=jpg&w=200&fit=max
             */

            private String raw;
            private String full;
            private String regular;
            private String small;
            private String thumb;

            public String getRaw() {
                return raw;
            }

            public void setRaw(String raw) {
                this.raw = raw;
            }

            public String getFull() {
                return full;
            }

            public void setFull(String full) {
                this.full = full;
            }

            public String getRegular() {
                return regular;
            }

            public void setRegular(String regular) {
                this.regular = regular;
            }

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }

        public static class LinksBean {
            /**
             * self : http://api.unsplash.com/photos/LF8gK8-HGSg
             * html : http://unsplash.com/photos/LF8gK8-HGSg
             * download : http://unsplash.com/photos/LF8gK8-HGSg/download
             */

            private String self;
            private String html;
            private String download;

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
        }
    }

    public static class UserBean {
        /**
         * id : 8VpB0GYJMZQ
         * username : williamnot
         * name : Thomas R.
         * links : {"self":"http://api.unsplash.com/users/williamnot","html":"http://api.unsplash.com/williamnot","photos":"http://api.unsplash.com/users/williamnot/photos","likes":"http://api.unsplash.com/users/williamnot/likes"}
         */

        private String id;
        private String username;
        private String name;
        private LinksBeanX links;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LinksBeanX getLinks() {
            return links;
        }

        public void setLinks(LinksBeanX links) {
            this.links = links;
        }

        public static class LinksBeanX {
            /**
             * self : http://api.unsplash.com/users/williamnot
             * html : http://api.unsplash.com/williamnot
             * photos : http://api.unsplash.com/users/williamnot/photos
             * likes : http://api.unsplash.com/users/williamnot/likes
             */

            private String self;
            private String html;
            private String photos;
            private String likes;

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

            public String getPhotos() {
                return photos;
            }

            public void setPhotos(String photos) {
                this.photos = photos;
            }

            public String getLikes() {
                return likes;
            }

            public void setLikes(String likes) {
                this.likes = likes;
            }
        }
    }
}
