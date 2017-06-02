package com.masker.discover.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/4/28
 * Description:
 */

public class CollectionListBean {

    /**
     * id : 296
     * title : I like a man with a beard.
     * description : Yeah even Santa...
     * published_at : 2016-01-27T18:47:13-05:00
     * updated_at : 2016-07-10T11:00:01-05:00
     * curated : false
     * total_photos : 12
     * private : false
     * share_key : 312d188df257b957f8b86d2ce20e4766
     * cover_photo : {"id":"C-mxLOk6ANs","width":5616,"height":3744,"color":"#E4C6A2","likes":12,"liked_by_user":false,"user":{"id":"xlt1-UPW7FE","username":"lionsdenpro","name":"Greg Raines","portfolio_url":"https://example.com/","bio":"Just an everyday Greg","location":"Montreal","total_likes":5,"total_photos":10,"total_collections":13,"profile_image":{"small":"https://images.unsplash.com/profile-1449546653256-0faea3006d34?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=32&w=32","medium":"https://images.unsplash.com/profile-1449546653256-0faea3006d34?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64","large":"https://images.unsplash.com/profile-1449546653256-0faea3006d34?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=128&w=128"},"links":{"self":"https://api.unsplash.com/users/lionsdenpro","html":"https://unsplash.com/lionsdenpro","photos":"https://api.unsplash.com/users/lionsdenpro/photos","likes":"https://api.unsplash.com/users/lionsdenpro/likes","portfolio":"https://api.unsplash.com/users/lionsdenpro/portfolio"}},"urls":{"raw":"https://images.unsplash.com/photo-1449614115178-cb924f730780","full":"https://images.unsplash.com/photo-1449614115178-cb924f730780?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy","regular":"https://images.unsplash.com/photo-1449614115178-cb924f730780?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&w=1080&fit=max","small":"https://images.unsplash.com/photo-1449614115178-cb924f730780?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&w=400&fit=max","thumb":"https://images.unsplash.com/photo-1449614115178-cb924f730780?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&w=200&fit=max"},"categories":[{"id":4,"title":"Nature","photo_count":31454,"links":{"self":"https://api.unsplash.com/categories/4","photos":"https://api.unsplash.com/categories/4/photos"}},{"id":6,"title":"People","photo_count":9844,"links":{"self":"https://api.unsplash.com/categories/6","photos":"https://api.unsplash.com/categories/6/photos"}}],"links":{"self":"https://api.unsplash.com/photos/C-mxLOk6ANs","html":"https://unsplash.com/photos/C-mxLOk6ANs","download":"https://unsplash.com/photos/C-mxLOk6ANs/download"}}
     * user : {"id":"IFcEhJqem0Q","updated_at":"2016-07-10T11:00:01-05:00","username":"fableandfolk","name":"Annie Spratt","portfolio_url":"http://mammasaurus.co.uk","bio":"Follow me on Twitter &amp; Instagram @anniespratt\r\nEmail me at hello@fableandfolk.com","location":"New Forest National Park, UK","total_likes":0,"total_photos":273,"total_collections":36,"profile_image":{"small":"https://images.unsplash.com/profile-1450003783594-db47c765cea3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=32&w=32","medium":"https://images.unsplash.com/profile-1450003783594-db47c765cea3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64","large":"https://images.unsplash.com/profile-1450003783594-db47c765cea3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=128&w=128"},"links":{"self":"https://api.unsplash.com/users/fableandfolk","html":"https://unsplash.com/fableandfolk","photos":"https://api.unsplash.com/users/fableandfolk/photos","likes":"https://api.unsplash.com/users/fableandfolk/likes","portfolio":"https://api.unsplash.com/users/fableandfolk/portfolio"}}
     * links : {"self":"https://api.unsplash.com/collections/296","html":"https://unsplash.com/collections/296","photos":"https://api.unsplash.com/collections/296/photos","related":"https://api.unsplash.com/collections/296/related"}
     */

    private int id;
    private String title;
    private String description;
    private String published_at;
    private String updated_at;
    private boolean curated;
    private int total_photos;
    @SerializedName("private")
    private boolean privateX;
    private String share_key;
    private CoverPhotoBean cover_photo;
    private UserBeanX user;
    private LinksBeanXXXX links;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isCurated() {
        return curated;
    }

    public void setCurated(boolean curated) {
        this.curated = curated;
    }

    public int getTotal_photos() {
        return total_photos;
    }

    public void setTotal_photos(int total_photos) {
        this.total_photos = total_photos;
    }

    public boolean isPrivateX() {
        return privateX;
    }

    public void setPrivateX(boolean privateX) {
        this.privateX = privateX;
    }

    public String getShare_key() {
        return share_key;
    }

    public void setShare_key(String share_key) {
        this.share_key = share_key;
    }

    public CoverPhotoBean getCover_photo() {
        return cover_photo;
    }

    public void setCover_photo(CoverPhotoBean cover_photo) {
        this.cover_photo = cover_photo;
    }

    public UserBeanX getUser() {
        return user;
    }

    public void setUser(UserBeanX user) {
        this.user = user;
    }

    public LinksBeanXXXX getLinks() {
        return links;
    }

    public void setLinks(LinksBeanXXXX links) {
        this.links = links;
    }

    public static class CoverPhotoBean {
        /**
         * id : C-mxLOk6ANs
         * width : 5616
         * height : 3744
         * color : #E4C6A2
         * likes : 12
         * liked_by_user : false
         * user : {"id":"xlt1-UPW7FE","username":"lionsdenpro","name":"Greg Raines","portfolio_url":"https://example.com/","bio":"Just an everyday Greg","location":"Montreal","total_likes":5,"total_photos":10,"total_collections":13,"profile_image":{"small":"https://images.unsplash.com/profile-1449546653256-0faea3006d34?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=32&w=32","medium":"https://images.unsplash.com/profile-1449546653256-0faea3006d34?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64","large":"https://images.unsplash.com/profile-1449546653256-0faea3006d34?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=128&w=128"},"links":{"self":"https://api.unsplash.com/users/lionsdenpro","html":"https://unsplash.com/lionsdenpro","photos":"https://api.unsplash.com/users/lionsdenpro/photos","likes":"https://api.unsplash.com/users/lionsdenpro/likes","portfolio":"https://api.unsplash.com/users/lionsdenpro/portfolio"}}
         * urls : {"raw":"https://images.unsplash.com/photo-1449614115178-cb924f730780","full":"https://images.unsplash.com/photo-1449614115178-cb924f730780?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy","regular":"https://images.unsplash.com/photo-1449614115178-cb924f730780?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&w=1080&fit=max","small":"https://images.unsplash.com/photo-1449614115178-cb924f730780?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&w=400&fit=max","thumb":"https://images.unsplash.com/photo-1449614115178-cb924f730780?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&w=200&fit=max"}
         * categories : [{"id":4,"title":"Nature","photo_count":31454,"links":{"self":"https://api.unsplash.com/categories/4","photos":"https://api.unsplash.com/categories/4/photos"}},{"id":6,"title":"People","photo_count":9844,"links":{"self":"https://api.unsplash.com/categories/6","photos":"https://api.unsplash.com/categories/6/photos"}}]
         * links : {"self":"https://api.unsplash.com/photos/C-mxLOk6ANs","html":"https://unsplash.com/photos/C-mxLOk6ANs","download":"https://unsplash.com/photos/C-mxLOk6ANs/download"}
         */

        private String id;
        private int width;
        private int height;
        private String color;
        private int likes;
        private boolean liked_by_user;
        private UserBean user;
        private UrlsBean urls;
        private LinksBeanX links;
        private List<CategoriesBean> categories;

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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public UrlsBean getUrls() {
            return urls;
        }

        public void setUrls(UrlsBean urls) {
            this.urls = urls;
        }

        public LinksBeanX getLinks() {
            return links;
        }

        public void setLinks(LinksBeanX links) {
            this.links = links;
        }

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

        public static class UserBean {
            /**
             * id : xlt1-UPW7FE
             * username : lionsdenpro
             * name : Greg Raines
             * portfolio_url : https://example.com/
             * bio : Just an everyday Greg
             * location : Montreal
             * total_likes : 5
             * total_photos : 10
             * total_collections : 13
             * profile_image : {"small":"https://images.unsplash.com/profile-1449546653256-0faea3006d34?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=32&w=32","medium":"https://images.unsplash.com/profile-1449546653256-0faea3006d34?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64","large":"https://images.unsplash.com/profile-1449546653256-0faea3006d34?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=128&w=128"}
             * links : {"self":"https://api.unsplash.com/users/lionsdenpro","html":"https://unsplash.com/lionsdenpro","photos":"https://api.unsplash.com/users/lionsdenpro/photos","likes":"https://api.unsplash.com/users/lionsdenpro/likes","portfolio":"https://api.unsplash.com/users/lionsdenpro/portfolio"}
             */

            private String id;
            private String username;
            private String name;
            private String portfolio_url;
            private String bio;
            private String location;
            private int total_likes;
            private int total_photos;
            private int total_collections;
            private ProfileImageBean profile_image;
            private LinksBean links;

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

            public String getPortfolio_url() {
                return portfolio_url;
            }

            public void setPortfolio_url(String portfolio_url) {
                this.portfolio_url = portfolio_url;
            }

            public String getBio() {
                return bio;
            }

            public void setBio(String bio) {
                this.bio = bio;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public int getTotal_likes() {
                return total_likes;
            }

            public void setTotal_likes(int total_likes) {
                this.total_likes = total_likes;
            }

            public int getTotal_photos() {
                return total_photos;
            }

            public void setTotal_photos(int total_photos) {
                this.total_photos = total_photos;
            }

            public int getTotal_collections() {
                return total_collections;
            }

            public void setTotal_collections(int total_collections) {
                this.total_collections = total_collections;
            }

            public ProfileImageBean getProfile_image() {
                return profile_image;
            }

            public void setProfile_image(ProfileImageBean profile_image) {
                this.profile_image = profile_image;
            }

            public LinksBean getLinks() {
                return links;
            }

            public void setLinks(LinksBean links) {
                this.links = links;
            }

            public static class ProfileImageBean {
                /**
                 * small : https://images.unsplash.com/profile-1449546653256-0faea3006d34?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=32&w=32
                 * medium : https://images.unsplash.com/profile-1449546653256-0faea3006d34?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64
                 * large : https://images.unsplash.com/profile-1449546653256-0faea3006d34?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=128&w=128
                 */

                private String small;
                private String medium;
                private String large;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }
            }

            public static class LinksBean {
                /**
                 * self : https://api.unsplash.com/users/lionsdenpro
                 * html : https://unsplash.com/lionsdenpro
                 * photos : https://api.unsplash.com/users/lionsdenpro/photos
                 * likes : https://api.unsplash.com/users/lionsdenpro/likes
                 * portfolio : https://api.unsplash.com/users/lionsdenpro/portfolio
                 */

                private String self;
                private String html;
                private String photos;
                private String likes;
                private String portfolio;

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

                public String getPortfolio() {
                    return portfolio;
                }

                public void setPortfolio(String portfolio) {
                    this.portfolio = portfolio;
                }
            }
        }

        public static class UrlsBean {
            /**
             * raw : https://images.unsplash.com/photo-1449614115178-cb924f730780
             * full : https://images.unsplash.com/photo-1449614115178-cb924f730780?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy
             * regular : https://images.unsplash.com/photo-1449614115178-cb924f730780?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&w=1080&fit=max
             * small : https://images.unsplash.com/photo-1449614115178-cb924f730780?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&w=400&fit=max
             * thumb : https://images.unsplash.com/photo-1449614115178-cb924f730780?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&w=200&fit=max
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

        public static class LinksBeanX {
            /**
             * self : https://api.unsplash.com/photos/C-mxLOk6ANs
             * html : https://unsplash.com/photos/C-mxLOk6ANs
             * download : https://unsplash.com/photos/C-mxLOk6ANs/download
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

        public static class CategoriesBean {
            /**
             * id : 4
             * title : Nature
             * photo_count : 31454
             * links : {"self":"https://api.unsplash.com/categories/4","photos":"https://api.unsplash.com/categories/4/photos"}
             */

            private int id;
            private String title;
            private int photo_count;
            private LinksBeanXX links;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getPhoto_count() {
                return photo_count;
            }

            public void setPhoto_count(int photo_count) {
                this.photo_count = photo_count;
            }

            public LinksBeanXX getLinks() {
                return links;
            }

            public void setLinks(LinksBeanXX links) {
                this.links = links;
            }

            public static class LinksBeanXX {
                /**
                 * self : https://api.unsplash.com/categories/4
                 * photos : https://api.unsplash.com/categories/4/photos
                 */

                private String self;
                private String photos;

                public String getSelf() {
                    return self;
                }

                public void setSelf(String self) {
                    this.self = self;
                }

                public String getPhotos() {
                    return photos;
                }

                public void setPhotos(String photos) {
                    this.photos = photos;
                }
            }
        }
    }

    public static class UserBeanX {
        /**
         * id : IFcEhJqem0Q
         * updated_at : 2016-07-10T11:00:01-05:00
         * username : fableandfolk
         * name : Annie Spratt
         * portfolio_url : http://mammasaurus.co.uk
         * bio : Follow me on Twitter &amp; Instagram @anniespratt
         Email me at hello@fableandfolk.com
         * location : New Forest National Park, UK
         * total_likes : 0
         * total_photos : 273
         * total_collections : 36
         * profile_image : {"small":"https://images.unsplash.com/profile-1450003783594-db47c765cea3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=32&w=32","medium":"https://images.unsplash.com/profile-1450003783594-db47c765cea3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64","large":"https://images.unsplash.com/profile-1450003783594-db47c765cea3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=128&w=128"}
         * links : {"self":"https://api.unsplash.com/users/fableandfolk","html":"https://unsplash.com/fableandfolk","photos":"https://api.unsplash.com/users/fableandfolk/photos","likes":"https://api.unsplash.com/users/fableandfolk/likes","portfolio":"https://api.unsplash.com/users/fableandfolk/portfolio"}
         */

        private String id;
        private String updated_at;
        private String username;
        private String name;
        private String portfolio_url;
        private String bio;
        private String location;
        private int total_likes;
        private int total_photos;
        private int total_collections;
        private ProfileImageBeanX profile_image;
        private LinksBeanXXX links;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
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

        public String getPortfolio_url() {
            return portfolio_url;
        }

        public void setPortfolio_url(String portfolio_url) {
            this.portfolio_url = portfolio_url;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getTotal_likes() {
            return total_likes;
        }

        public void setTotal_likes(int total_likes) {
            this.total_likes = total_likes;
        }

        public int getTotal_photos() {
            return total_photos;
        }

        public void setTotal_photos(int total_photos) {
            this.total_photos = total_photos;
        }

        public int getTotal_collections() {
            return total_collections;
        }

        public void setTotal_collections(int total_collections) {
            this.total_collections = total_collections;
        }

        public ProfileImageBeanX getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(ProfileImageBeanX profile_image) {
            this.profile_image = profile_image;
        }

        public LinksBeanXXX getLinks() {
            return links;
        }

        public void setLinks(LinksBeanXXX links) {
            this.links = links;
        }

        public static class ProfileImageBeanX {
            /**
             * small : https://images.unsplash.com/profile-1450003783594-db47c765cea3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=32&w=32
             * medium : https://images.unsplash.com/profile-1450003783594-db47c765cea3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64
             * large : https://images.unsplash.com/profile-1450003783594-db47c765cea3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=128&w=128
             */

            private String small;
            private String medium;
            private String large;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }
        }

        public static class LinksBeanXXX {
            /**
             * self : https://api.unsplash.com/users/fableandfolk
             * html : https://unsplash.com/fableandfolk
             * photos : https://api.unsplash.com/users/fableandfolk/photos
             * likes : https://api.unsplash.com/users/fableandfolk/likes
             * portfolio : https://api.unsplash.com/users/fableandfolk/portfolio
             */

            private String self;
            private String html;
            private String photos;
            private String likes;
            private String portfolio;

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

            public String getPortfolio() {
                return portfolio;
            }

            public void setPortfolio(String portfolio) {
                this.portfolio = portfolio;
            }
        }
    }

    public static class LinksBeanXXXX {
        /**
         * self : https://api.unsplash.com/collections/296
         * html : https://unsplash.com/collections/296
         * photos : https://api.unsplash.com/collections/296/photos
         * related : https://api.unsplash.com/collections/296/related
         */

        private String self;
        private String html;
        private String photos;
        private String related;

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

        public String getRelated() {
            return related;
        }

        public void setRelated(String related) {
            this.related = related;
        }
    }
}
