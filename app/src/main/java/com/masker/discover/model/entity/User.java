package com.masker.discover.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * CreatedBy: masker
 * Date: 2017/6/4
 * Description:
 */

public class User implements Parcelable {

    private String id;
    private String userName;
    private String name;
    private String bio;
    private String location;
    private String avatarUrl;
    private String bgUrl;



    private String email;
    private int totalLikes;
    private int totalPhotos;
    private int totalCollections;

    public String getBgUrl() {
        return bgUrl;
    }

    public void setBgUrl(String bgUrl) {
        this.bgUrl = bgUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User() {
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
    }

    public int getTotalPhotos() {
        return totalPhotos;
    }

    public void setTotalPhotos(int totalPhotos) {
        this.totalPhotos = totalPhotos;
    }

    public int getTotalCollections() {
        return totalCollections;
    }

    public void setTotalCollections(int totalCollections) {
        this.totalCollections = totalCollections;
    }

    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.userName);
        dest.writeString(this.name);
        dest.writeString(this.bio);
        dest.writeString(this.location);
        dest.writeString(this.avatarUrl);
        dest.writeString(this.bgUrl);
        dest.writeString(this.email);
        dest.writeInt(this.totalLikes);
        dest.writeInt(this.totalPhotos);
        dest.writeInt(this.totalCollections);
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.userName = in.readString();
        this.name = in.readString();
        this.bio = in.readString();
        this.location = in.readString();
        this.avatarUrl = in.readString();
        this.bgUrl = in.readString();
        this.email = in.readString();
        this.totalLikes = in.readInt();
        this.totalPhotos = in.readInt();
        this.totalCollections = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
