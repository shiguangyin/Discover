package com.masker.discover.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * CreatedBy: masker
 * Date: 2017/5/12
 * Description: myinfo bean for /me
 */

public class UserInfoBean implements Parcelable {

    private String uid;
    private String id;
    private String updated_at;
    private int numeric_id;
    private String username;
    private String name;
    private String first_name;
    private String last_name;
    private String bio;
    private String location;
    private int total_likes;
    private int total_photos;
    private int total_collections;
    private boolean followed_by_user;
    private int following_count;
    private int followers_count;
    private int downloads;
    private ProfileImageBean profile_image;
    private boolean completed_onboarding;
    private int uploads_remaining;
    private String instagram_username;
    private String email;
    private LinksBean links;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

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

    public int getNumeric_id() {
        return numeric_id;
    }

    public void setNumeric_id(int numeric_id) {
        this.numeric_id = numeric_id;
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

    public boolean isFollowed_by_user() {
        return followed_by_user;
    }

    public void setFollowed_by_user(boolean followed_by_user) {
        this.followed_by_user = followed_by_user;
    }

    public int getFollowing_count() {
        return following_count;
    }

    public void setFollowing_count(int following_count) {
        this.following_count = following_count;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public ProfileImageBean getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(ProfileImageBean profile_image) {
        this.profile_image = profile_image;
    }

    public boolean isCompleted_onboarding() {
        return completed_onboarding;
    }

    public void setCompleted_onboarding(boolean completed_onboarding) {
        this.completed_onboarding = completed_onboarding;
    }

    public int getUploads_remaining() {
        return uploads_remaining;
    }

    public void setUploads_remaining(int uploads_remaining) {
        this.uploads_remaining = uploads_remaining;
    }

    public String getInstagram_username() {
        return instagram_username;
    }

    public void setInstagram_username(String instagram_username) {
        this.instagram_username = instagram_username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public LinksBean getLinks() {
        return links;
    }

    public void setLinks(LinksBean links) {
        this.links = links;
    }


    public static class ProfileImageBean implements Parcelable {
        /**
         * small : https://images.unsplash.com/profile-1494573922707-2e2c5dcbb757?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=7853c60bc812b19277356c423aa674bd
         * medium : https://images.unsplash.com/profile-1494573922707-2e2c5dcbb757?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=1b44c1bfd304201c636eb0669ed1618a
         * large : https://images.unsplash.com/profile-1494573922707-2e2c5dcbb757?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=1e2e27ec9eb29e07e2eaa924ac3941b4
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.small);
            dest.writeString(this.medium);
            dest.writeString(this.large);
        }

        public ProfileImageBean() {
        }

        protected ProfileImageBean(Parcel in) {
            this.small = in.readString();
            this.medium = in.readString();
            this.large = in.readString();
        }

        public static final Creator<ProfileImageBean> CREATOR = new Creator<ProfileImageBean>() {
            @Override
            public ProfileImageBean createFromParcel(Parcel source) {
                return new ProfileImageBean(source);
            }

            @Override
            public ProfileImageBean[] newArray(int size) {
                return new ProfileImageBean[size];
            }
        };
    }

    public static class LinksBean implements Parcelable {
        /**
         * self : https://api.unsplash.com/users/masker
         * html : http://unsplash.com/@masker
         * photos : https://api.unsplash.com/users/masker/photos
         * likes : https://api.unsplash.com/users/masker/likes
         * portfolio : https://api.unsplash.com/users/masker/portfolio
         * following : https://api.unsplash.com/users/masker/following
         * followers : https://api.unsplash.com/users/masker/followers
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.self);
            dest.writeString(this.html);
            dest.writeString(this.photos);
            dest.writeString(this.likes);
            dest.writeString(this.portfolio);
            dest.writeString(this.following);
            dest.writeString(this.followers);
        }

        public LinksBean() {
        }

        protected LinksBean(Parcel in) {
            this.self = in.readString();
            this.html = in.readString();
            this.photos = in.readString();
            this.likes = in.readString();
            this.portfolio = in.readString();
            this.following = in.readString();
            this.followers = in.readString();
        }

        public static final Creator<LinksBean> CREATOR = new Creator<LinksBean>() {
            @Override
            public LinksBean createFromParcel(Parcel source) {
                return new LinksBean(source);
            }

            @Override
            public LinksBean[] newArray(int size) {
                return new LinksBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uid);
        dest.writeString(this.id);
        dest.writeString(this.updated_at);
        dest.writeInt(this.numeric_id);
        dest.writeString(this.username);
        dest.writeString(this.name);
        dest.writeString(this.first_name);
        dest.writeString(this.last_name);
        dest.writeString(this.bio);
        dest.writeString(this.location);
        dest.writeInt(this.total_likes);
        dest.writeInt(this.total_photos);
        dest.writeInt(this.total_collections);
        dest.writeByte(this.followed_by_user ? (byte) 1 : (byte) 0);
        dest.writeInt(this.following_count);
        dest.writeInt(this.followers_count);
        dest.writeInt(this.downloads);
        dest.writeParcelable(this.profile_image, flags);
        dest.writeByte(this.completed_onboarding ? (byte) 1 : (byte) 0);
        dest.writeInt(this.uploads_remaining);
        dest.writeString(this.instagram_username);
        dest.writeString(this.email);
        dest.writeParcelable(this.links, flags);
    }

    public UserInfoBean() {
    }

    protected UserInfoBean(Parcel in) {
        this.uid = in.readString();
        this.id = in.readString();
        this.updated_at = in.readString();
        this.numeric_id = in.readInt();
        this.username = in.readString();
        this.name = in.readString();
        this.first_name = in.readString();
        this.last_name = in.readString();
        this.bio = in.readString();
        this.location = in.readString();
        this.total_likes = in.readInt();
        this.total_photos = in.readInt();
        this.total_collections = in.readInt();
        this.followed_by_user = in.readByte() != 0;
        this.following_count = in.readInt();
        this.followers_count = in.readInt();
        this.downloads = in.readInt();
        this.profile_image = in.readParcelable(ProfileImageBean.class.getClassLoader());
        this.completed_onboarding = in.readByte() != 0;
        this.uploads_remaining = in.readInt();
        this.instagram_username = in.readString();
        this.email = in.readString();
        this.links = in.readParcelable(LinksBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<UserInfoBean> CREATOR = new Parcelable.Creator<UserInfoBean>() {
        @Override
        public UserInfoBean createFromParcel(Parcel source) {
            return new UserInfoBean(source);
        }

        @Override
        public UserInfoBean[] newArray(int size) {
            return new UserInfoBean[size];
        }
    };
}
