package com.masker.discover.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/6/2
 * Description: /collections/:id
 */

public class CollectionBean {


    /**
     * id : 899728
     * title : Sorrow and Strength
     * description : null
     * published_at : 2017-05-30T06:04:04-04:00
     * updated_at : 2017-05-30T08:25:53-04:00
     * curated : false
     * featured : true
     * total_photos : 40
     * private : false
     * share_key : 6dd8c5b9c47cfdd11d79a9c091a6d4ff
     * cover_photo : {"id":"qIk-wqAX7P0","created_at":"2016-12-10T10:51:49-05:00","updated_at":"2017-06-02T03:44:07-04:00","width":4240,"height":2384,"color":"#FCFDFD","likes":70,"liked_by_user":false,"user":{"id":"IsFaGDjTCOY","updated_at":"2017-06-02T04:02:45-04:00","username":"alex13","name":"Alex Ronsdorf","first_name":"Alex","last_name":"Ronsdorf","portfolio_url":null,"bio":"22 | germany | love jesus | love people","location":null,"total_likes":6,"total_photos":9,"total_collections":0,"profile_image":{"small":"https://images.unsplash.com/profile-1494596587432-9fbb36c51c01?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=f0b2c3faf36ac60bd4a5cecd9dc04dd2","medium":"https://images.unsplash.com/profile-1494596587432-9fbb36c51c01?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=a2a22ca2605d8db20e4cb67abfc02848","large":"https://images.unsplash.com/profile-1494596587432-9fbb36c51c01?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=1be3743f0c42b8366533381106070c9c"},"links":{"self":"https://api.unsplash.com/users/alex13","html":"http://unsplash.com/@alex13","photos":"https://api.unsplash.com/users/alex13/photos","likes":"https://api.unsplash.com/users/alex13/likes","portfolio":"https://api.unsplash.com/users/alex13/portfolio","following":"https://api.unsplash.com/users/alex13/following","followers":"https://api.unsplash.com/users/alex13/followers"}},"urls":{"raw":"https://images.unsplash.com/photo-1481385555689-454c7136cc87","full":"https://images.unsplash.com/photo-1481385555689-454c7136cc87?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=f3210ea8af4d252e61366c477881ba76","regular":"https://images.unsplash.com/photo-1481385555689-454c7136cc87?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=aa09585810bb04c29035fded7efa2137","small":"https://images.unsplash.com/photo-1481385555689-454c7136cc87?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=4b7d8342a131299e1a95764dd89ebe38","thumb":"https://images.unsplash.com/photo-1481385555689-454c7136cc87?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=e1e253b70ba87413c99dad6fd27d3fb7"},"categories":[],"links":{"self":"https://api.unsplash.com/photos/qIk-wqAX7P0","html":"http://unsplash.com/photos/qIk-wqAX7P0","download":"http://unsplash.com/photos/qIk-wqAX7P0/download","download_location":"https://api.unsplash.com/photos/qIk-wqAX7P0/download"}}
     * user : {"id":"4fpRWe06bdg","updated_at":"2017-05-30T07:23:26-04:00","username":"caronryalls","name":"Caron Ryalls","first_name":"Caron","last_name":"Ryalls","portfolio_url":null,"bio":"","location":null,"followed_by_user":false,"total_likes":1,"total_photos":0,"total_collections":7,"profile_image":{"small":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=0ad68f44c4725d5a3fda019bab9d3edc","medium":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=356bd4b76a3d4eb97d63f45b818dd358","large":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=ee8bbf5fb8d6e43aaaa238feae2fe90d"},"links":{"self":"https://api.unsplash.com/users/caronryalls","html":"http://unsplash.com/@caronryalls","photos":"https://api.unsplash.com/users/caronryalls/photos","likes":"https://api.unsplash.com/users/caronryalls/likes","portfolio":"https://api.unsplash.com/users/caronryalls/portfolio","following":"https://api.unsplash.com/users/caronryalls/following","followers":"https://api.unsplash.com/users/caronryalls/followers"}}
     * links : {"self":"https://api.unsplash.com/collections/899728","html":"http://unsplash.com/collections/899728/sorrow-and-strength","photos":"https://api.unsplash.com/collections/899728/photos","related":"https://api.unsplash.com/collections/899728/related"}
     */

    private int id;
    private String title;
    private Object description;
    private String published_at;
    private String updated_at;
    private boolean curated;
    private boolean featured;
    private int total_photos;
    @SerializedName("private")
    private boolean privateX;
    private String share_key;
    private CoverPhotoBean cover_photo;
    private UserBeanX user;
    private LinksBeanXXX links;

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

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
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

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
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

    public LinksBeanXXX getLinks() {
        return links;
    }

    public void setLinks(LinksBeanXXX links) {
        this.links = links;
    }

    public static class CoverPhotoBean {
        /**
         * id : qIk-wqAX7P0
         * created_at : 2016-12-10T10:51:49-05:00
         * updated_at : 2017-06-02T03:44:07-04:00
         * width : 4240
         * height : 2384
         * color : #FCFDFD
         * likes : 70
         * liked_by_user : false
         * user : {"id":"IsFaGDjTCOY","updated_at":"2017-06-02T04:02:45-04:00","username":"alex13","name":"Alex Ronsdorf","first_name":"Alex","last_name":"Ronsdorf","portfolio_url":null,"bio":"22 | germany | love jesus | love people","location":null,"total_likes":6,"total_photos":9,"total_collections":0,"profile_image":{"small":"https://images.unsplash.com/profile-1494596587432-9fbb36c51c01?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=f0b2c3faf36ac60bd4a5cecd9dc04dd2","medium":"https://images.unsplash.com/profile-1494596587432-9fbb36c51c01?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=a2a22ca2605d8db20e4cb67abfc02848","large":"https://images.unsplash.com/profile-1494596587432-9fbb36c51c01?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=1be3743f0c42b8366533381106070c9c"},"links":{"self":"https://api.unsplash.com/users/alex13","html":"http://unsplash.com/@alex13","photos":"https://api.unsplash.com/users/alex13/photos","likes":"https://api.unsplash.com/users/alex13/likes","portfolio":"https://api.unsplash.com/users/alex13/portfolio","following":"https://api.unsplash.com/users/alex13/following","followers":"https://api.unsplash.com/users/alex13/followers"}}
         * urls : {"raw":"https://images.unsplash.com/photo-1481385555689-454c7136cc87","full":"https://images.unsplash.com/photo-1481385555689-454c7136cc87?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=f3210ea8af4d252e61366c477881ba76","regular":"https://images.unsplash.com/photo-1481385555689-454c7136cc87?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=aa09585810bb04c29035fded7efa2137","small":"https://images.unsplash.com/photo-1481385555689-454c7136cc87?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=4b7d8342a131299e1a95764dd89ebe38","thumb":"https://images.unsplash.com/photo-1481385555689-454c7136cc87?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=e1e253b70ba87413c99dad6fd27d3fb7"}
         * categories : []
         * links : {"self":"https://api.unsplash.com/photos/qIk-wqAX7P0","html":"http://unsplash.com/photos/qIk-wqAX7P0","download":"http://unsplash.com/photos/qIk-wqAX7P0/download","download_location":"https://api.unsplash.com/photos/qIk-wqAX7P0/download"}
         */

        private String id;
        private String created_at;
        private String updated_at;
        private int width;
        private int height;
        private String color;
        private int likes;
        private boolean liked_by_user;
        private UserBean user;
        private UrlsBean urls;
        private LinksBeanX links;
        private List<?> categories;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
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

        public List<?> getCategories() {
            return categories;
        }

        public void setCategories(List<?> categories) {
            this.categories = categories;
        }

        public static class UserBean {
            /**
             * id : IsFaGDjTCOY
             * updated_at : 2017-06-02T04:02:45-04:00
             * username : alex13
             * name : Alex Ronsdorf
             * first_name : Alex
             * last_name : Ronsdorf
             * portfolio_url : null
             * bio : 22 | germany | love jesus | love people
             * location : null
             * total_likes : 6
             * total_photos : 9
             * total_collections : 0
             * profile_image : {"small":"https://images.unsplash.com/profile-1494596587432-9fbb36c51c01?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=f0b2c3faf36ac60bd4a5cecd9dc04dd2","medium":"https://images.unsplash.com/profile-1494596587432-9fbb36c51c01?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=a2a22ca2605d8db20e4cb67abfc02848","large":"https://images.unsplash.com/profile-1494596587432-9fbb36c51c01?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=1be3743f0c42b8366533381106070c9c"}
             * links : {"self":"https://api.unsplash.com/users/alex13","html":"http://unsplash.com/@alex13","photos":"https://api.unsplash.com/users/alex13/photos","likes":"https://api.unsplash.com/users/alex13/likes","portfolio":"https://api.unsplash.com/users/alex13/portfolio","following":"https://api.unsplash.com/users/alex13/following","followers":"https://api.unsplash.com/users/alex13/followers"}
             */

            private String id;
            private String updated_at;
            private String username;
            private String name;
            private String first_name;
            private String last_name;
            private Object portfolio_url;
            private String bio;
            private Object location;
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

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public Object getPortfolio_url() {
                return portfolio_url;
            }

            public void setPortfolio_url(Object portfolio_url) {
                this.portfolio_url = portfolio_url;
            }

            public String getBio() {
                return bio;
            }

            public void setBio(String bio) {
                this.bio = bio;
            }

            public Object getLocation() {
                return location;
            }

            public void setLocation(Object location) {
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
                 * small : https://images.unsplash.com/profile-1494596587432-9fbb36c51c01?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=f0b2c3faf36ac60bd4a5cecd9dc04dd2
                 * medium : https://images.unsplash.com/profile-1494596587432-9fbb36c51c01?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=a2a22ca2605d8db20e4cb67abfc02848
                 * large : https://images.unsplash.com/profile-1494596587432-9fbb36c51c01?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=1be3743f0c42b8366533381106070c9c
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
                 * self : https://api.unsplash.com/users/alex13
                 * html : http://unsplash.com/@alex13
                 * photos : https://api.unsplash.com/users/alex13/photos
                 * likes : https://api.unsplash.com/users/alex13/likes
                 * portfolio : https://api.unsplash.com/users/alex13/portfolio
                 * following : https://api.unsplash.com/users/alex13/following
                 * followers : https://api.unsplash.com/users/alex13/followers
                 */

                private String self;
                private String html;
                private String photos;
                private String likes;
                private String portfolio;
                private String following;
                private String followers;

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

                public String getFollowing() {
                    return following;
                }

                public void setFollowing(String following) {
                    this.following = following;
                }

                public String getFollowers() {
                    return followers;
                }

                public void setFollowers(String followers) {
                    this.followers = followers;
                }
            }
        }

        public static class UrlsBean {
            /**
             * raw : https://images.unsplash.com/photo-1481385555689-454c7136cc87
             * full : https://images.unsplash.com/photo-1481385555689-454c7136cc87?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=f3210ea8af4d252e61366c477881ba76
             * regular : https://images.unsplash.com/photo-1481385555689-454c7136cc87?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=aa09585810bb04c29035fded7efa2137
             * small : https://images.unsplash.com/photo-1481385555689-454c7136cc87?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=4b7d8342a131299e1a95764dd89ebe38
             * thumb : https://images.unsplash.com/photo-1481385555689-454c7136cc87?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=e1e253b70ba87413c99dad6fd27d3fb7
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
             * self : https://api.unsplash.com/photos/qIk-wqAX7P0
             * html : http://unsplash.com/photos/qIk-wqAX7P0
             * download : http://unsplash.com/photos/qIk-wqAX7P0/download
             * download_location : https://api.unsplash.com/photos/qIk-wqAX7P0/download
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

    public static class UserBeanX {
        /**
         * id : 4fpRWe06bdg
         * updated_at : 2017-05-30T07:23:26-04:00
         * username : caronryalls
         * name : Caron Ryalls
         * first_name : Caron
         * last_name : Ryalls
         * portfolio_url : null
         * bio :
         * location : null
         * followed_by_user : false
         * total_likes : 1
         * total_photos : 0
         * total_collections : 7
         * profile_image : {"small":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=0ad68f44c4725d5a3fda019bab9d3edc","medium":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=356bd4b76a3d4eb97d63f45b818dd358","large":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=ee8bbf5fb8d6e43aaaa238feae2fe90d"}
         * links : {"self":"https://api.unsplash.com/users/caronryalls","html":"http://unsplash.com/@caronryalls","photos":"https://api.unsplash.com/users/caronryalls/photos","likes":"https://api.unsplash.com/users/caronryalls/likes","portfolio":"https://api.unsplash.com/users/caronryalls/portfolio","following":"https://api.unsplash.com/users/caronryalls/following","followers":"https://api.unsplash.com/users/caronryalls/followers"}
         */

        private String id;
        private String updated_at;
        private String username;
        private String name;
        private String first_name;
        private String last_name;
        private Object portfolio_url;
        private String bio;
        private Object location;
        private boolean followed_by_user;
        private int total_likes;
        private int total_photos;
        private int total_collections;
        private ProfileImageBeanX profile_image;
        private LinksBeanXX links;

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

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public Object getPortfolio_url() {
            return portfolio_url;
        }

        public void setPortfolio_url(Object portfolio_url) {
            this.portfolio_url = portfolio_url;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public Object getLocation() {
            return location;
        }

        public void setLocation(Object location) {
            this.location = location;
        }

        public boolean isFollowed_by_user() {
            return followed_by_user;
        }

        public void setFollowed_by_user(boolean followed_by_user) {
            this.followed_by_user = followed_by_user;
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

        public LinksBeanXX getLinks() {
            return links;
        }

        public void setLinks(LinksBeanXX links) {
            this.links = links;
        }

        public static class ProfileImageBeanX {
            /**
             * small : https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=0ad68f44c4725d5a3fda019bab9d3edc
             * medium : https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=356bd4b76a3d4eb97d63f45b818dd358
             * large : https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=ee8bbf5fb8d6e43aaaa238feae2fe90d
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

        public static class LinksBeanXX {
            /**
             * self : https://api.unsplash.com/users/caronryalls
             * html : http://unsplash.com/@caronryalls
             * photos : https://api.unsplash.com/users/caronryalls/photos
             * likes : https://api.unsplash.com/users/caronryalls/likes
             * portfolio : https://api.unsplash.com/users/caronryalls/portfolio
             * following : https://api.unsplash.com/users/caronryalls/following
             * followers : https://api.unsplash.com/users/caronryalls/followers
             */

            private String self;
            private String html;
            private String photos;
            private String likes;
            private String portfolio;
            private String following;
            private String followers;

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

            public String getFollowing() {
                return following;
            }

            public void setFollowing(String following) {
                this.following = following;
            }

            public String getFollowers() {
                return followers;
            }

            public void setFollowers(String followers) {
                this.followers = followers;
            }
        }
    }

    public static class LinksBeanXXX {
        /**
         * self : https://api.unsplash.com/collections/899728
         * html : http://unsplash.com/collections/899728/sorrow-and-strength
         * photos : https://api.unsplash.com/collections/899728/photos
         * related : https://api.unsplash.com/collections/899728/related
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
