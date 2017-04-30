package com.masker.discover.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * CreatedBy: masker
 * Date: 2017/4/30
 * Description:  photo detail infomation
 */

public class PhotoInfo {


    /**
     * id : 6VjPmyMj5KM
     * created_at : 2014-06-02T14:57:23-04:00
     * updated_at : 2017-04-30T04:55:12-04:00
     * width : 7360
     * height : 4912
     * color : #81716E
     * slug : null
     * downloads : 53405
     * likes : 354
     * views : 6795386
     * liked_by_user : false
     * exif : {"make":"","model":null,"exposure_time":null,"aperture":null,"focal_length":null,"iso":null}
     * current_user_collections : []
     * urls : {"raw":"https://images.unsplash.com/reserve/wi9yf7kTQxCNeY72cCY6_Images%20of%20Jenny%20Lace%20Plasticity%20Publish%20(4%20of%2025).jpg","full":"https://images.unsplash.com/reserve/wi9yf7kTQxCNeY72cCY6_Images%20of%20Jenny%20Lace%20Plasticity%20Publish%20(4%20of%2025).jpg?ixlib=rb-0.3.5&q=100&fm=jpg&crop=entropy&cs=tinysrgb&s=5115f8ccfd1a44472a00995f6acffdb7","regular":"https://images.unsplash.com/reserve/wi9yf7kTQxCNeY72cCY6_Images%20of%20Jenny%20Lace%20Plasticity%20Publish%20(4%20of%2025).jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=3be3636b6de52dab6948b78f82386847","small":"https://images.unsplash.com/reserve/wi9yf7kTQxCNeY72cCY6_Images%20of%20Jenny%20Lace%20Plasticity%20Publish%20(4%20of%2025).jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=5c9c0273d99c5370feae4b2fdad6c36a","thumb":"https://images.unsplash.com/reserve/wi9yf7kTQxCNeY72cCY6_Images%20of%20Jenny%20Lace%20Plasticity%20Publish%20(4%20of%2025).jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=dc779435e5e3c90d1f6dd825a3ae4380"}
     * categories : [{"id":8,"title":"Objects","photo_count":13840,"links":{"self":"https://api.unsplash.com/categories/8","photos":"https://api.unsplash.com/categories/8/photos"}},{"id":6,"title":"People","photo_count":18319,"links":{"self":"https://api.unsplash.com/categories/6","photos":"https://api.unsplash.com/categories/6/photos"}},{"id":7,"title":"Technology","photo_count":1407,"links":{"self":"https://api.unsplash.com/categories/7","photos":"https://api.unsplash.com/categories/7/photos"}}]
     * links : {"self":"https://api.unsplash.com/photos/6VjPmyMj5KM","html":"http://unsplash.com/photos/6VjPmyMj5KM","download":"http://unsplash.com/photos/6VjPmyMj5KM/download","download_location":"https://api.unsplash.com/photos/6VjPmyMj5KM/download"}
     * story : {"title":null,"description":null}
     * tags : [{"title":"touch","url":"https://images.unsplash.com/reserve/wi9yf7kTQxCNeY72cCY6_Images%20of%20Jenny%20Lace%20Plasticity%20Publish%20(4%20of%2025).jpg"},{"title":"finger","url":"https://images.unsplash.com/photo-1484747500344-fe60dc1b26d2"},{"title":"screen","url":"https://images.unsplash.com/photo-1456456496250-d5e7c0a9b44d"},{"title":"tablet","url":"https://images.unsplash.com/photo-1481207727306-1a9f151fca7d"},{"title":"hand","url":"https://images.unsplash.com/photo-1471881904296-d7fd992cb287"}]
     * related_collections : {"total":164,"type":"collected","results":[{"id":177997,"title":"Service Image Buttons","description":null,"published_at":"2016-03-31T11:07:57-04:00","updated_at":"2017-02-03T09:42:47-05:00","curated":false,"featured":false,"total_photos":10,"private":false,"share_key":"fb7565cb8825cad4125e016f23c4618c","cover_photo":{"id":"p-xSl33Wxyc","created_at":"2016-03-06T18:01:36-05:00","updated_at":"2017-04-30T01:29:17-04:00","width":4288,"height":2848,"color":"#F4EAE9","likes":473,"liked_by_user":false,"user":{"id":"AHswH5U5P2k","updated_at":"2017-04-30T01:29:17-04:00","username":"euwars","name":"Farzad Nazifi","first_name":"Farzad","last_name":"Nazifi","portfolio_url":null,"bio":"","location":null,"total_likes":1,"total_photos":2,"total_collections":0,"profile_image":{"small":"https://images.unsplash.com/profile-fb-1457305170-9e999445bee3.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=ec5cf50547fd41ed1d7147eea2fb740b","medium":"https://images.unsplash.com/profile-fb-1457305170-9e999445bee3.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=2622657a442b53d33dcd9c02baaea532","large":"https://images.unsplash.com/profile-fb-1457305170-9e999445bee3.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=4409daea357bda6a827f3c1a1522f2d7"},"links":{"self":"https://api.unsplash.com/users/euwars","html":"http://unsplash.com/@euwars","photos":"https://api.unsplash.com/users/euwars/photos","likes":"https://api.unsplash.com/users/euwars/likes","portfolio":"https://api.unsplash.com/users/euwars/portfolio","following":"https://api.unsplash.com/users/euwars/following","followers":"https://api.unsplash.com/users/euwars/followers"}},"urls":{"raw":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89","full":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=100&fm=jpg&crop=entropy&cs=tinysrgb&s=0bed7afc1c1cf459844599b5fb6f3f53","regular":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=4063ad6beb4b1939f2ed65ef3207c5d4","small":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=79798c98ccb2537d785cabf859cd0303","thumb":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=9db3f87487a9619681f99c8974848c00"},"categories":[{"id":7,"title":"Technology","photo_count":1407,"links":{"self":"https://api.unsplash.com/categories/7","photos":"https://api.unsplash.com/categories/7/photos"}}],"links":{"self":"https://api.unsplash.com/photos/p-xSl33Wxyc","html":"http://unsplash.com/photos/p-xSl33Wxyc","download":"http://unsplash.com/photos/p-xSl33Wxyc/download","download_location":"https://api.unsplash.com/photos/p-xSl33Wxyc/download"}},"user":{"id":"sbnicseOPOU","updated_at":"2016-03-31T11:15:07-04:00","username":"duanestreetdigital","name":"Elizabeth Horwitz","first_name":"Elizabeth","last_name":"Horwitz","portfolio_url":null,"bio":"","location":null,"total_likes":0,"total_photos":0,"total_collections":2,"profile_image":{"small":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=0ad68f44c4725d5a3fda019bab9d3edc","medium":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=356bd4b76a3d4eb97d63f45b818dd358","large":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=ee8bbf5fb8d6e43aaaa238feae2fe90d"},"links":{"self":"https://api.unsplash.com/users/duanestreetdigital","html":"http://unsplash.com/@duanestreetdigital","photos":"https://api.unsplash.com/users/duanestreetdigital/photos","likes":"https://api.unsplash.com/users/duanestreetdigital/likes","portfolio":"https://api.unsplash.com/users/duanestreetdigital/portfolio","following":"https://api.unsplash.com/users/duanestreetdigital/following","followers":"https://api.unsplash.com/users/duanestreetdigital/followers"}},"links":{"self":"https://api.unsplash.com/collections/177997","html":"http://unsplash.com/collections/177997/service-image-buttons","photos":"https://api.unsplash.com/collections/177997/photos","related":"https://api.unsplash.com/collections/177997/related"}},{"id":307652,"title":"Shopping","description":null,"published_at":"2016-08-08T13:09:16-04:00","updated_at":"2017-03-31T11:08:29-04:00","curated":false,"featured":true,"total_photos":63,"private":false,"share_key":"eeb8861972d4736d5a3d55774b1d9a6b","cover_photo":{"id":"oKEDtoP5YU0","created_at":"2017-03-21T10:01:52-04:00","updated_at":"2017-04-28T01:55:47-04:00","width":6000,"height":4000,"color":"#FAC86A","likes":33,"liked_by_user":false,"user":{"id":"gE-oZzlPDJg","updated_at":"2017-04-29T20:55:09-04:00","username":"glen_mccallum","name":"Glen McCallum","first_name":"Glen","last_name":"McCallum","portfolio_url":"http://www.dres.me","bio":"About as Glen-ish as any Glen can be.","location":"Detroit, MI","total_likes":18,"total_photos":65,"total_collections":0,"profile_image":{"small":"https://images.unsplash.com/profile-1488416719613-ea249b1b822f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=018566d7d36512c84087020c09e124db","medium":"https://images.unsplash.com/profile-1488416719613-ea249b1b822f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=25d8a1a288136372a335156a7ebc57ab","large":"https://images.unsplash.com/profile-1488416719613-ea249b1b822f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=c5f2a18c960b008675067628a60355f7"},"links":{"self":"https://api.unsplash.com/users/glen_mccallum","html":"http://unsplash.com/@glen_mccallum","photos":"https://api.unsplash.com/users/glen_mccallum/photos","likes":"https://api.unsplash.com/users/glen_mccallum/likes","portfolio":"https://api.unsplash.com/users/glen_mccallum/portfolio","following":"https://api.unsplash.com/users/glen_mccallum/following","followers":"https://api.unsplash.com/users/glen_mccallum/followers"}},"urls":{"raw":"https://images.unsplash.com/photo-1490104853956-854f584463c2","full":"https://images.unsplash.com/photo-1490104853956-854f584463c2?ixlib=rb-0.3.5&q=100&fm=jpg&crop=entropy&cs=tinysrgb&s=5991d0dc43b74118bdf9c4bf123a5ef7","regular":"https://images.unsplash.com/photo-1490104853956-854f584463c2?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=1449f646552b149da120ea306cb19767","small":"https://images.unsplash.com/photo-1490104853956-854f584463c2?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=954e8a44d39aeffc1671c324d3ecdc07","thumb":"https://images.unsplash.com/photo-1490104853956-854f584463c2?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=dc155009a0e6cf58281c7bb416f388b2"},"categories":[],"links":{"self":"https://api.unsplash.com/photos/oKEDtoP5YU0","html":"http://unsplash.com/photos/oKEDtoP5YU0","download":"http://unsplash.com/photos/oKEDtoP5YU0/download","download_location":"https://api.unsplash.com/photos/oKEDtoP5YU0/download"}},"user":{"id":"0XBxITCGBjI","updated_at":"2017-04-28T10:07:06-04:00","username":"micheleyamin","name":"Michele Yamin","first_name":"Michele","last_name":"Yamin","portfolio_url":null,"bio":"","location":null,"total_likes":6,"total_photos":0,"total_collections":43,"profile_image":{"small":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=0ad68f44c4725d5a3fda019bab9d3edc","medium":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=356bd4b76a3d4eb97d63f45b818dd358","large":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=ee8bbf5fb8d6e43aaaa238feae2fe90d"},"links":{"self":"https://api.unsplash.com/users/micheleyamin","html":"http://unsplash.com/@micheleyamin","photos":"https://api.unsplash.com/users/micheleyamin/photos","likes":"https://api.unsplash.com/users/micheleyamin/likes","portfolio":"https://api.unsplash.com/users/micheleyamin/portfolio","following":"https://api.unsplash.com/users/micheleyamin/following","followers":"https://api.unsplash.com/users/micheleyamin/followers"}},"links":{"self":"https://api.unsplash.com/collections/307652","html":"http://unsplash.com/collections/307652/shopping","photos":"https://api.unsplash.com/collections/307652/photos","related":"https://api.unsplash.com/collections/307652/related"}},{"id":439548,"title":"Sale","description":null,"published_at":"2016-11-28T15:17:08-05:00","updated_at":"2016-11-28T16:07:33-05:00","curated":false,"featured":false,"total_photos":2,"private":false,"share_key":"20b8a0c2b224f97f6ff4b1929e75128d","cover_photo":{"id":"vB5qtt8X4NA","created_at":"2016-08-04T07:25:51-04:00","updated_at":"2017-04-29T23:11:09-04:00","width":8688,"height":5792,"color":"#050506","likes":194,"liked_by_user":false,"user":{"id":"OCir5rnjkN0","updated_at":"2017-04-30T03:21:00-04:00","username":"artificialphotography","name":"Artificial Photography","first_name":"Artificial","last_name":"Photography","portfolio_url":"http://artificial.io","bio":"","location":"london","total_likes":4,"total_photos":22,"total_collections":0,"profile_image":{"small":"https://images.unsplash.com/profile-1471866363862-0d55d0b0dcc0?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=5495ddc3543ff4706f17fc967dbdf54e","medium":"https://images.unsplash.com/profile-1471866363862-0d55d0b0dcc0?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=70c82ee71befbc1d444ab98c3e640f45","large":"https://images.unsplash.com/profile-1471866363862-0d55d0b0dcc0?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=f97fb599b498c6b4d540a8031ef267f8"},"links":{"self":"https://api.unsplash.com/users/artificialphotography","html":"http://unsplash.com/@artificialphotography","photos":"https://api.unsplash.com/users/artificialphotography/photos","likes":"https://api.unsplash.com/users/artificialphotography/likes","portfolio":"https://api.unsplash.com/users/artificialphotography/portfolio","following":"https://api.unsplash.com/users/artificialphotography/following","followers":"https://api.unsplash.com/users/artificialphotography/followers"}},"urls":{"raw":"https://images.unsplash.com/photo-1470309864661-68328b2cd0a5","full":"https://images.unsplash.com/photo-1470309864661-68328b2cd0a5?ixlib=rb-0.3.5&q=100&fm=jpg&crop=entropy&cs=tinysrgb&s=a60b313420bf5143e685f0978b4e69a7","regular":"https://images.unsplash.com/photo-1470309864661-68328b2cd0a5?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=720183978e4ffc988f008981871d5fbd","small":"https://images.unsplash.com/photo-1470309864661-68328b2cd0a5?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=046e2a7e8b3dcd0001a3c6cb5f5ed6c2","thumb":"https://images.unsplash.com/photo-1470309864661-68328b2cd0a5?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=e271448d3c723d9553964f68076adf90"},"categories":[],"links":{"self":"https://api.unsplash.com/photos/vB5qtt8X4NA","html":"http://unsplash.com/photos/vB5qtt8X4NA","download":"http://unsplash.com/photos/vB5qtt8X4NA/download","download_location":"https://api.unsplash.com/photos/vB5qtt8X4NA/download"}},"user":{"id":"7xhOvXnNJg4","updated_at":"2017-01-23T17:33:59-05:00","username":"jamwithjaneane","name":"Janeane Leese","first_name":"Janeane","last_name":"Leese","portfolio_url":null,"bio":"","location":null,"total_likes":2,"total_photos":0,"total_collections":11,"profile_image":{"small":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=0ad68f44c4725d5a3fda019bab9d3edc","medium":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=356bd4b76a3d4eb97d63f45b818dd358","large":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=ee8bbf5fb8d6e43aaaa238feae2fe90d"},"links":{"self":"https://api.unsplash.com/users/jamwithjaneane","html":"http://unsplash.com/@jamwithjaneane","photos":"https://api.unsplash.com/users/jamwithjaneane/photos","likes":"https://api.unsplash.com/users/jamwithjaneane/likes","portfolio":"https://api.unsplash.com/users/jamwithjaneane/portfolio","following":"https://api.unsplash.com/users/jamwithjaneane/following","followers":"https://api.unsplash.com/users/jamwithjaneane/followers"}},"links":{"self":"https://api.unsplash.com/collections/439548","html":"http://unsplash.com/collections/439548/sale","photos":"https://api.unsplash.com/collections/439548/photos","related":"https://api.unsplash.com/collections/439548/related"}}]}
     * user : {"id":"XenmsNc5As4","updated_at":"2017-04-30T04:55:12-04:00","username":"timothymuza","name":"timothy muza","first_name":"timothy","last_name":"muza","portfolio_url":"http://www.timothymuza.com","bio":"Portrait & Wedding Photographer ","location":"Kitchener Waterloo Ontario Canada","total_likes":3,"total_photos":6,"total_collections":0,"profile_image":{"small":"https://images.unsplash.com/profile-1456014091359-ec39bcd1fe4b?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=da7b5fbe251548af68f50f252aa579cc","medium":"https://images.unsplash.com/profile-1456014091359-ec39bcd1fe4b?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=aa90c5220b9f461fd95db00e4c52774b","large":"https://images.unsplash.com/profile-1456014091359-ec39bcd1fe4b?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=fe0d490a695f304065863f670e2a3e33"},"links":{"self":"https://api.unsplash.com/users/timothymuza","html":"http://unsplash.com/@timothymuza","photos":"https://api.unsplash.com/users/timothymuza/photos","likes":"https://api.unsplash.com/users/timothymuza/likes","portfolio":"https://api.unsplash.com/users/timothymuza/portfolio","following":"https://api.unsplash.com/users/timothymuza/following","followers":"https://api.unsplash.com/users/timothymuza/followers"}}
     */

    private String id;
    private String created_at;
    private String updated_at;
    private int width;
    private int height;
    private String color;
    private Object slug;
    private int downloads;
    private int likes;
    private int views;
    private boolean liked_by_user;
    private ExifBean exif;
    private UrlsBean urls;
    private LinksBean links;
    private StoryBean story;
    private RelatedCollectionsBean related_collections;
    private UserBeanXX user;
    private List<?> current_user_collections;
    private List<CategoriesBeanX> categories;
    private List<TagsBean> tags;

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

    public Object getSlug() {
        return slug;
    }

    public void setSlug(Object slug) {
        this.slug = slug;
    }

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

    public boolean isLiked_by_user() {
        return liked_by_user;
    }

    public void setLiked_by_user(boolean liked_by_user) {
        this.liked_by_user = liked_by_user;
    }

    public ExifBean getExif() {
        return exif;
    }

    public void setExif(ExifBean exif) {
        this.exif = exif;
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

    public StoryBean getStory() {
        return story;
    }

    public void setStory(StoryBean story) {
        this.story = story;
    }

    public RelatedCollectionsBean getRelated_collections() {
        return related_collections;
    }

    public void setRelated_collections(RelatedCollectionsBean related_collections) {
        this.related_collections = related_collections;
    }

    public UserBeanXX getUser() {
        return user;
    }

    public void setUser(UserBeanXX user) {
        this.user = user;
    }

    public List<?> getCurrent_user_collections() {
        return current_user_collections;
    }

    public void setCurrent_user_collections(List<?> current_user_collections) {
        this.current_user_collections = current_user_collections;
    }

    public List<CategoriesBeanX> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesBeanX> categories) {
        this.categories = categories;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public static class ExifBean {
        /**
         * make :
         * model : null
         * exposure_time : null
         * aperture : null
         * focal_length : null
         * iso : null
         */

        private String make;
        private Object model;
        private Object exposure_time;
        private Object aperture;
        private Object focal_length;
        private Object iso;

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public Object getModel() {
            return model;
        }

        public void setModel(Object model) {
            this.model = model;
        }

        public Object getExposure_time() {
            return exposure_time;
        }

        public void setExposure_time(Object exposure_time) {
            this.exposure_time = exposure_time;
        }

        public Object getAperture() {
            return aperture;
        }

        public void setAperture(Object aperture) {
            this.aperture = aperture;
        }

        public Object getFocal_length() {
            return focal_length;
        }

        public void setFocal_length(Object focal_length) {
            this.focal_length = focal_length;
        }

        public Object getIso() {
            return iso;
        }

        public void setIso(Object iso) {
            this.iso = iso;
        }
    }

    public static class UrlsBean {
        /**
         * raw : https://images.unsplash.com/reserve/wi9yf7kTQxCNeY72cCY6_Images%20of%20Jenny%20Lace%20Plasticity%20Publish%20(4%20of%2025).jpg
         * full : https://images.unsplash.com/reserve/wi9yf7kTQxCNeY72cCY6_Images%20of%20Jenny%20Lace%20Plasticity%20Publish%20(4%20of%2025).jpg?ixlib=rb-0.3.5&q=100&fm=jpg&crop=entropy&cs=tinysrgb&s=5115f8ccfd1a44472a00995f6acffdb7
         * regular : https://images.unsplash.com/reserve/wi9yf7kTQxCNeY72cCY6_Images%20of%20Jenny%20Lace%20Plasticity%20Publish%20(4%20of%2025).jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=3be3636b6de52dab6948b78f82386847
         * small : https://images.unsplash.com/reserve/wi9yf7kTQxCNeY72cCY6_Images%20of%20Jenny%20Lace%20Plasticity%20Publish%20(4%20of%2025).jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=5c9c0273d99c5370feae4b2fdad6c36a
         * thumb : https://images.unsplash.com/reserve/wi9yf7kTQxCNeY72cCY6_Images%20of%20Jenny%20Lace%20Plasticity%20Publish%20(4%20of%2025).jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=dc779435e5e3c90d1f6dd825a3ae4380
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
         * self : https://api.unsplash.com/photos/6VjPmyMj5KM
         * html : http://unsplash.com/photos/6VjPmyMj5KM
         * download : http://unsplash.com/photos/6VjPmyMj5KM/download
         * download_location : https://api.unsplash.com/photos/6VjPmyMj5KM/download
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

    public static class StoryBean {
        /**
         * title : null
         * description : null
         */

        private Object title;
        private Object description;

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }
    }

    public static class RelatedCollectionsBean {
        /**
         * total : 164
         * type : collected
         * results : [{"id":177997,"title":"Service Image Buttons","description":null,"published_at":"2016-03-31T11:07:57-04:00","updated_at":"2017-02-03T09:42:47-05:00","curated":false,"featured":false,"total_photos":10,"private":false,"share_key":"fb7565cb8825cad4125e016f23c4618c","cover_photo":{"id":"p-xSl33Wxyc","created_at":"2016-03-06T18:01:36-05:00","updated_at":"2017-04-30T01:29:17-04:00","width":4288,"height":2848,"color":"#F4EAE9","likes":473,"liked_by_user":false,"user":{"id":"AHswH5U5P2k","updated_at":"2017-04-30T01:29:17-04:00","username":"euwars","name":"Farzad Nazifi","first_name":"Farzad","last_name":"Nazifi","portfolio_url":null,"bio":"","location":null,"total_likes":1,"total_photos":2,"total_collections":0,"profile_image":{"small":"https://images.unsplash.com/profile-fb-1457305170-9e999445bee3.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=ec5cf50547fd41ed1d7147eea2fb740b","medium":"https://images.unsplash.com/profile-fb-1457305170-9e999445bee3.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=2622657a442b53d33dcd9c02baaea532","large":"https://images.unsplash.com/profile-fb-1457305170-9e999445bee3.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=4409daea357bda6a827f3c1a1522f2d7"},"links":{"self":"https://api.unsplash.com/users/euwars","html":"http://unsplash.com/@euwars","photos":"https://api.unsplash.com/users/euwars/photos","likes":"https://api.unsplash.com/users/euwars/likes","portfolio":"https://api.unsplash.com/users/euwars/portfolio","following":"https://api.unsplash.com/users/euwars/following","followers":"https://api.unsplash.com/users/euwars/followers"}},"urls":{"raw":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89","full":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=100&fm=jpg&crop=entropy&cs=tinysrgb&s=0bed7afc1c1cf459844599b5fb6f3f53","regular":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=4063ad6beb4b1939f2ed65ef3207c5d4","small":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=79798c98ccb2537d785cabf859cd0303","thumb":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=9db3f87487a9619681f99c8974848c00"},"categories":[{"id":7,"title":"Technology","photo_count":1407,"links":{"self":"https://api.unsplash.com/categories/7","photos":"https://api.unsplash.com/categories/7/photos"}}],"links":{"self":"https://api.unsplash.com/photos/p-xSl33Wxyc","html":"http://unsplash.com/photos/p-xSl33Wxyc","download":"http://unsplash.com/photos/p-xSl33Wxyc/download","download_location":"https://api.unsplash.com/photos/p-xSl33Wxyc/download"}},"user":{"id":"sbnicseOPOU","updated_at":"2016-03-31T11:15:07-04:00","username":"duanestreetdigital","name":"Elizabeth Horwitz","first_name":"Elizabeth","last_name":"Horwitz","portfolio_url":null,"bio":"","location":null,"total_likes":0,"total_photos":0,"total_collections":2,"profile_image":{"small":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=0ad68f44c4725d5a3fda019bab9d3edc","medium":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=356bd4b76a3d4eb97d63f45b818dd358","large":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=ee8bbf5fb8d6e43aaaa238feae2fe90d"},"links":{"self":"https://api.unsplash.com/users/duanestreetdigital","html":"http://unsplash.com/@duanestreetdigital","photos":"https://api.unsplash.com/users/duanestreetdigital/photos","likes":"https://api.unsplash.com/users/duanestreetdigital/likes","portfolio":"https://api.unsplash.com/users/duanestreetdigital/portfolio","following":"https://api.unsplash.com/users/duanestreetdigital/following","followers":"https://api.unsplash.com/users/duanestreetdigital/followers"}},"links":{"self":"https://api.unsplash.com/collections/177997","html":"http://unsplash.com/collections/177997/service-image-buttons","photos":"https://api.unsplash.com/collections/177997/photos","related":"https://api.unsplash.com/collections/177997/related"}},{"id":307652,"title":"Shopping","description":null,"published_at":"2016-08-08T13:09:16-04:00","updated_at":"2017-03-31T11:08:29-04:00","curated":false,"featured":true,"total_photos":63,"private":false,"share_key":"eeb8861972d4736d5a3d55774b1d9a6b","cover_photo":{"id":"oKEDtoP5YU0","created_at":"2017-03-21T10:01:52-04:00","updated_at":"2017-04-28T01:55:47-04:00","width":6000,"height":4000,"color":"#FAC86A","likes":33,"liked_by_user":false,"user":{"id":"gE-oZzlPDJg","updated_at":"2017-04-29T20:55:09-04:00","username":"glen_mccallum","name":"Glen McCallum","first_name":"Glen","last_name":"McCallum","portfolio_url":"http://www.dres.me","bio":"About as Glen-ish as any Glen can be.","location":"Detroit, MI","total_likes":18,"total_photos":65,"total_collections":0,"profile_image":{"small":"https://images.unsplash.com/profile-1488416719613-ea249b1b822f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=018566d7d36512c84087020c09e124db","medium":"https://images.unsplash.com/profile-1488416719613-ea249b1b822f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=25d8a1a288136372a335156a7ebc57ab","large":"https://images.unsplash.com/profile-1488416719613-ea249b1b822f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=c5f2a18c960b008675067628a60355f7"},"links":{"self":"https://api.unsplash.com/users/glen_mccallum","html":"http://unsplash.com/@glen_mccallum","photos":"https://api.unsplash.com/users/glen_mccallum/photos","likes":"https://api.unsplash.com/users/glen_mccallum/likes","portfolio":"https://api.unsplash.com/users/glen_mccallum/portfolio","following":"https://api.unsplash.com/users/glen_mccallum/following","followers":"https://api.unsplash.com/users/glen_mccallum/followers"}},"urls":{"raw":"https://images.unsplash.com/photo-1490104853956-854f584463c2","full":"https://images.unsplash.com/photo-1490104853956-854f584463c2?ixlib=rb-0.3.5&q=100&fm=jpg&crop=entropy&cs=tinysrgb&s=5991d0dc43b74118bdf9c4bf123a5ef7","regular":"https://images.unsplash.com/photo-1490104853956-854f584463c2?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=1449f646552b149da120ea306cb19767","small":"https://images.unsplash.com/photo-1490104853956-854f584463c2?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=954e8a44d39aeffc1671c324d3ecdc07","thumb":"https://images.unsplash.com/photo-1490104853956-854f584463c2?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=dc155009a0e6cf58281c7bb416f388b2"},"categories":[],"links":{"self":"https://api.unsplash.com/photos/oKEDtoP5YU0","html":"http://unsplash.com/photos/oKEDtoP5YU0","download":"http://unsplash.com/photos/oKEDtoP5YU0/download","download_location":"https://api.unsplash.com/photos/oKEDtoP5YU0/download"}},"user":{"id":"0XBxITCGBjI","updated_at":"2017-04-28T10:07:06-04:00","username":"micheleyamin","name":"Michele Yamin","first_name":"Michele","last_name":"Yamin","portfolio_url":null,"bio":"","location":null,"total_likes":6,"total_photos":0,"total_collections":43,"profile_image":{"small":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=0ad68f44c4725d5a3fda019bab9d3edc","medium":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=356bd4b76a3d4eb97d63f45b818dd358","large":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=ee8bbf5fb8d6e43aaaa238feae2fe90d"},"links":{"self":"https://api.unsplash.com/users/micheleyamin","html":"http://unsplash.com/@micheleyamin","photos":"https://api.unsplash.com/users/micheleyamin/photos","likes":"https://api.unsplash.com/users/micheleyamin/likes","portfolio":"https://api.unsplash.com/users/micheleyamin/portfolio","following":"https://api.unsplash.com/users/micheleyamin/following","followers":"https://api.unsplash.com/users/micheleyamin/followers"}},"links":{"self":"https://api.unsplash.com/collections/307652","html":"http://unsplash.com/collections/307652/shopping","photos":"https://api.unsplash.com/collections/307652/photos","related":"https://api.unsplash.com/collections/307652/related"}},{"id":439548,"title":"Sale","description":null,"published_at":"2016-11-28T15:17:08-05:00","updated_at":"2016-11-28T16:07:33-05:00","curated":false,"featured":false,"total_photos":2,"private":false,"share_key":"20b8a0c2b224f97f6ff4b1929e75128d","cover_photo":{"id":"vB5qtt8X4NA","created_at":"2016-08-04T07:25:51-04:00","updated_at":"2017-04-29T23:11:09-04:00","width":8688,"height":5792,"color":"#050506","likes":194,"liked_by_user":false,"user":{"id":"OCir5rnjkN0","updated_at":"2017-04-30T03:21:00-04:00","username":"artificialphotography","name":"Artificial Photography","first_name":"Artificial","last_name":"Photography","portfolio_url":"http://artificial.io","bio":"","location":"london","total_likes":4,"total_photos":22,"total_collections":0,"profile_image":{"small":"https://images.unsplash.com/profile-1471866363862-0d55d0b0dcc0?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=5495ddc3543ff4706f17fc967dbdf54e","medium":"https://images.unsplash.com/profile-1471866363862-0d55d0b0dcc0?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=70c82ee71befbc1d444ab98c3e640f45","large":"https://images.unsplash.com/profile-1471866363862-0d55d0b0dcc0?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=f97fb599b498c6b4d540a8031ef267f8"},"links":{"self":"https://api.unsplash.com/users/artificialphotography","html":"http://unsplash.com/@artificialphotography","photos":"https://api.unsplash.com/users/artificialphotography/photos","likes":"https://api.unsplash.com/users/artificialphotography/likes","portfolio":"https://api.unsplash.com/users/artificialphotography/portfolio","following":"https://api.unsplash.com/users/artificialphotography/following","followers":"https://api.unsplash.com/users/artificialphotography/followers"}},"urls":{"raw":"https://images.unsplash.com/photo-1470309864661-68328b2cd0a5","full":"https://images.unsplash.com/photo-1470309864661-68328b2cd0a5?ixlib=rb-0.3.5&q=100&fm=jpg&crop=entropy&cs=tinysrgb&s=a60b313420bf5143e685f0978b4e69a7","regular":"https://images.unsplash.com/photo-1470309864661-68328b2cd0a5?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=720183978e4ffc988f008981871d5fbd","small":"https://images.unsplash.com/photo-1470309864661-68328b2cd0a5?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=046e2a7e8b3dcd0001a3c6cb5f5ed6c2","thumb":"https://images.unsplash.com/photo-1470309864661-68328b2cd0a5?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=e271448d3c723d9553964f68076adf90"},"categories":[],"links":{"self":"https://api.unsplash.com/photos/vB5qtt8X4NA","html":"http://unsplash.com/photos/vB5qtt8X4NA","download":"http://unsplash.com/photos/vB5qtt8X4NA/download","download_location":"https://api.unsplash.com/photos/vB5qtt8X4NA/download"}},"user":{"id":"7xhOvXnNJg4","updated_at":"2017-01-23T17:33:59-05:00","username":"jamwithjaneane","name":"Janeane Leese","first_name":"Janeane","last_name":"Leese","portfolio_url":null,"bio":"","location":null,"total_likes":2,"total_photos":0,"total_collections":11,"profile_image":{"small":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=0ad68f44c4725d5a3fda019bab9d3edc","medium":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=356bd4b76a3d4eb97d63f45b818dd358","large":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=ee8bbf5fb8d6e43aaaa238feae2fe90d"},"links":{"self":"https://api.unsplash.com/users/jamwithjaneane","html":"http://unsplash.com/@jamwithjaneane","photos":"https://api.unsplash.com/users/jamwithjaneane/photos","likes":"https://api.unsplash.com/users/jamwithjaneane/likes","portfolio":"https://api.unsplash.com/users/jamwithjaneane/portfolio","following":"https://api.unsplash.com/users/jamwithjaneane/following","followers":"https://api.unsplash.com/users/jamwithjaneane/followers"}},"links":{"self":"https://api.unsplash.com/collections/439548","html":"http://unsplash.com/collections/439548/sale","photos":"https://api.unsplash.com/collections/439548/photos","related":"https://api.unsplash.com/collections/439548/related"}}]
         */

        private int total;
        private String type;
        private List<ResultsBean> results;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<ResultsBean> getResults() {
            return results;
        }

        public void setResults(List<ResultsBean> results) {
            this.results = results;
        }

        public static class ResultsBean {
            /**
             * id : 177997
             * title : Service Image Buttons
             * description : null
             * published_at : 2016-03-31T11:07:57-04:00
             * updated_at : 2017-02-03T09:42:47-05:00
             * curated : false
             * featured : false
             * total_photos : 10
             * private : false
             * share_key : fb7565cb8825cad4125e016f23c4618c
             * cover_photo : {"id":"p-xSl33Wxyc","created_at":"2016-03-06T18:01:36-05:00","updated_at":"2017-04-30T01:29:17-04:00","width":4288,"height":2848,"color":"#F4EAE9","likes":473,"liked_by_user":false,"user":{"id":"AHswH5U5P2k","updated_at":"2017-04-30T01:29:17-04:00","username":"euwars","name":"Farzad Nazifi","first_name":"Farzad","last_name":"Nazifi","portfolio_url":null,"bio":"","location":null,"total_likes":1,"total_photos":2,"total_collections":0,"profile_image":{"small":"https://images.unsplash.com/profile-fb-1457305170-9e999445bee3.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=ec5cf50547fd41ed1d7147eea2fb740b","medium":"https://images.unsplash.com/profile-fb-1457305170-9e999445bee3.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=2622657a442b53d33dcd9c02baaea532","large":"https://images.unsplash.com/profile-fb-1457305170-9e999445bee3.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=4409daea357bda6a827f3c1a1522f2d7"},"links":{"self":"https://api.unsplash.com/users/euwars","html":"http://unsplash.com/@euwars","photos":"https://api.unsplash.com/users/euwars/photos","likes":"https://api.unsplash.com/users/euwars/likes","portfolio":"https://api.unsplash.com/users/euwars/portfolio","following":"https://api.unsplash.com/users/euwars/following","followers":"https://api.unsplash.com/users/euwars/followers"}},"urls":{"raw":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89","full":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=100&fm=jpg&crop=entropy&cs=tinysrgb&s=0bed7afc1c1cf459844599b5fb6f3f53","regular":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=4063ad6beb4b1939f2ed65ef3207c5d4","small":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=79798c98ccb2537d785cabf859cd0303","thumb":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=9db3f87487a9619681f99c8974848c00"},"categories":[{"id":7,"title":"Technology","photo_count":1407,"links":{"self":"https://api.unsplash.com/categories/7","photos":"https://api.unsplash.com/categories/7/photos"}}],"links":{"self":"https://api.unsplash.com/photos/p-xSl33Wxyc","html":"http://unsplash.com/photos/p-xSl33Wxyc","download":"http://unsplash.com/photos/p-xSl33Wxyc/download","download_location":"https://api.unsplash.com/photos/p-xSl33Wxyc/download"}}
             * user : {"id":"sbnicseOPOU","updated_at":"2016-03-31T11:15:07-04:00","username":"duanestreetdigital","name":"Elizabeth Horwitz","first_name":"Elizabeth","last_name":"Horwitz","portfolio_url":null,"bio":"","location":null,"total_likes":0,"total_photos":0,"total_collections":2,"profile_image":{"small":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=0ad68f44c4725d5a3fda019bab9d3edc","medium":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=356bd4b76a3d4eb97d63f45b818dd358","large":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=ee8bbf5fb8d6e43aaaa238feae2fe90d"},"links":{"self":"https://api.unsplash.com/users/duanestreetdigital","html":"http://unsplash.com/@duanestreetdigital","photos":"https://api.unsplash.com/users/duanestreetdigital/photos","likes":"https://api.unsplash.com/users/duanestreetdigital/likes","portfolio":"https://api.unsplash.com/users/duanestreetdigital/portfolio","following":"https://api.unsplash.com/users/duanestreetdigital/following","followers":"https://api.unsplash.com/users/duanestreetdigital/followers"}}
             * links : {"self":"https://api.unsplash.com/collections/177997","html":"http://unsplash.com/collections/177997/service-image-buttons","photos":"https://api.unsplash.com/collections/177997/photos","related":"https://api.unsplash.com/collections/177997/related"}
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
            private LinksBeanXXXXX links;

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

            public LinksBeanXXXXX getLinks() {
                return links;
            }

            public void setLinks(LinksBeanXXXXX links) {
                this.links = links;
            }

            public static class CoverPhotoBean {
                /**
                 * id : p-xSl33Wxyc
                 * created_at : 2016-03-06T18:01:36-05:00
                 * updated_at : 2017-04-30T01:29:17-04:00
                 * width : 4288
                 * height : 2848
                 * color : #F4EAE9
                 * likes : 473
                 * liked_by_user : false
                 * user : {"id":"AHswH5U5P2k","updated_at":"2017-04-30T01:29:17-04:00","username":"euwars","name":"Farzad Nazifi","first_name":"Farzad","last_name":"Nazifi","portfolio_url":null,"bio":"","location":null,"total_likes":1,"total_photos":2,"total_collections":0,"profile_image":{"small":"https://images.unsplash.com/profile-fb-1457305170-9e999445bee3.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=ec5cf50547fd41ed1d7147eea2fb740b","medium":"https://images.unsplash.com/profile-fb-1457305170-9e999445bee3.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=2622657a442b53d33dcd9c02baaea532","large":"https://images.unsplash.com/profile-fb-1457305170-9e999445bee3.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=4409daea357bda6a827f3c1a1522f2d7"},"links":{"self":"https://api.unsplash.com/users/euwars","html":"http://unsplash.com/@euwars","photos":"https://api.unsplash.com/users/euwars/photos","likes":"https://api.unsplash.com/users/euwars/likes","portfolio":"https://api.unsplash.com/users/euwars/portfolio","following":"https://api.unsplash.com/users/euwars/following","followers":"https://api.unsplash.com/users/euwars/followers"}}
                 * urls : {"raw":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89","full":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=100&fm=jpg&crop=entropy&cs=tinysrgb&s=0bed7afc1c1cf459844599b5fb6f3f53","regular":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=4063ad6beb4b1939f2ed65ef3207c5d4","small":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=79798c98ccb2537d785cabf859cd0303","thumb":"https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=9db3f87487a9619681f99c8974848c00"}
                 * categories : [{"id":7,"title":"Technology","photo_count":1407,"links":{"self":"https://api.unsplash.com/categories/7","photos":"https://api.unsplash.com/categories/7/photos"}}]
                 * links : {"self":"https://api.unsplash.com/photos/p-xSl33Wxyc","html":"http://unsplash.com/photos/p-xSl33Wxyc","download":"http://unsplash.com/photos/p-xSl33Wxyc/download","download_location":"https://api.unsplash.com/photos/p-xSl33Wxyc/download"}
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
                private UrlsBeanX urls;
                private LinksBeanXX links;
                private List<CategoriesBean> categories;

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

                public UrlsBeanX getUrls() {
                    return urls;
                }

                public void setUrls(UrlsBeanX urls) {
                    this.urls = urls;
                }

                public LinksBeanXX getLinks() {
                    return links;
                }

                public void setLinks(LinksBeanXX links) {
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
                     * id : AHswH5U5P2k
                     * updated_at : 2017-04-30T01:29:17-04:00
                     * username : euwars
                     * name : Farzad Nazifi
                     * first_name : Farzad
                     * last_name : Nazifi
                     * portfolio_url : null
                     * bio :
                     * location : null
                     * total_likes : 1
                     * total_photos : 2
                     * total_collections : 0
                     * profile_image : {"small":"https://images.unsplash.com/profile-fb-1457305170-9e999445bee3.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=ec5cf50547fd41ed1d7147eea2fb740b","medium":"https://images.unsplash.com/profile-fb-1457305170-9e999445bee3.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=2622657a442b53d33dcd9c02baaea532","large":"https://images.unsplash.com/profile-fb-1457305170-9e999445bee3.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=4409daea357bda6a827f3c1a1522f2d7"}
                     * links : {"self":"https://api.unsplash.com/users/euwars","html":"http://unsplash.com/@euwars","photos":"https://api.unsplash.com/users/euwars/photos","likes":"https://api.unsplash.com/users/euwars/likes","portfolio":"https://api.unsplash.com/users/euwars/portfolio","following":"https://api.unsplash.com/users/euwars/following","followers":"https://api.unsplash.com/users/euwars/followers"}
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
                    private LinksBeanX links;

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

                    public LinksBeanX getLinks() {
                        return links;
                    }

                    public void setLinks(LinksBeanX links) {
                        this.links = links;
                    }

                    public static class ProfileImageBean {
                        /**
                         * small : https://images.unsplash.com/profile-fb-1457305170-9e999445bee3.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=ec5cf50547fd41ed1d7147eea2fb740b
                         * medium : https://images.unsplash.com/profile-fb-1457305170-9e999445bee3.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=2622657a442b53d33dcd9c02baaea532
                         * large : https://images.unsplash.com/profile-fb-1457305170-9e999445bee3.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=4409daea357bda6a827f3c1a1522f2d7
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

                    public static class LinksBeanX {
                        /**
                         * self : https://api.unsplash.com/users/euwars
                         * html : http://unsplash.com/@euwars
                         * photos : https://api.unsplash.com/users/euwars/photos
                         * likes : https://api.unsplash.com/users/euwars/likes
                         * portfolio : https://api.unsplash.com/users/euwars/portfolio
                         * following : https://api.unsplash.com/users/euwars/following
                         * followers : https://api.unsplash.com/users/euwars/followers
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

                public static class UrlsBeanX {
                    /**
                     * raw : https://images.unsplash.com/photo-1457305237443-44c3d5a30b89
                     * full : https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=100&fm=jpg&crop=entropy&cs=tinysrgb&s=0bed7afc1c1cf459844599b5fb6f3f53
                     * regular : https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=4063ad6beb4b1939f2ed65ef3207c5d4
                     * small : https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=79798c98ccb2537d785cabf859cd0303
                     * thumb : https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=9db3f87487a9619681f99c8974848c00
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

                public static class LinksBeanXX {
                    /**
                     * self : https://api.unsplash.com/photos/p-xSl33Wxyc
                     * html : http://unsplash.com/photos/p-xSl33Wxyc
                     * download : http://unsplash.com/photos/p-xSl33Wxyc/download
                     * download_location : https://api.unsplash.com/photos/p-xSl33Wxyc/download
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

                public static class CategoriesBean {
                    /**
                     * id : 7
                     * title : Technology
                     * photo_count : 1407
                     * links : {"self":"https://api.unsplash.com/categories/7","photos":"https://api.unsplash.com/categories/7/photos"}
                     */

                    private int id;
                    private String title;
                    private int photo_count;
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

                    public int getPhoto_count() {
                        return photo_count;
                    }

                    public void setPhoto_count(int photo_count) {
                        this.photo_count = photo_count;
                    }

                    public LinksBeanXXX getLinks() {
                        return links;
                    }

                    public void setLinks(LinksBeanXXX links) {
                        this.links = links;
                    }

                    public static class LinksBeanXXX {
                        /**
                         * self : https://api.unsplash.com/categories/7
                         * photos : https://api.unsplash.com/categories/7/photos
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
                 * id : sbnicseOPOU
                 * updated_at : 2016-03-31T11:15:07-04:00
                 * username : duanestreetdigital
                 * name : Elizabeth Horwitz
                 * first_name : Elizabeth
                 * last_name : Horwitz
                 * portfolio_url : null
                 * bio :
                 * location : null
                 * total_likes : 0
                 * total_photos : 0
                 * total_collections : 2
                 * profile_image : {"small":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=0ad68f44c4725d5a3fda019bab9d3edc","medium":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=356bd4b76a3d4eb97d63f45b818dd358","large":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=ee8bbf5fb8d6e43aaaa238feae2fe90d"}
                 * links : {"self":"https://api.unsplash.com/users/duanestreetdigital","html":"http://unsplash.com/@duanestreetdigital","photos":"https://api.unsplash.com/users/duanestreetdigital/photos","likes":"https://api.unsplash.com/users/duanestreetdigital/likes","portfolio":"https://api.unsplash.com/users/duanestreetdigital/portfolio","following":"https://api.unsplash.com/users/duanestreetdigital/following","followers":"https://api.unsplash.com/users/duanestreetdigital/followers"}
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
                private ProfileImageBeanX profile_image;
                private LinksBeanXXXX links;

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

                public ProfileImageBeanX getProfile_image() {
                    return profile_image;
                }

                public void setProfile_image(ProfileImageBeanX profile_image) {
                    this.profile_image = profile_image;
                }

                public LinksBeanXXXX getLinks() {
                    return links;
                }

                public void setLinks(LinksBeanXXXX links) {
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

                public static class LinksBeanXXXX {
                    /**
                     * self : https://api.unsplash.com/users/duanestreetdigital
                     * html : http://unsplash.com/@duanestreetdigital
                     * photos : https://api.unsplash.com/users/duanestreetdigital/photos
                     * likes : https://api.unsplash.com/users/duanestreetdigital/likes
                     * portfolio : https://api.unsplash.com/users/duanestreetdigital/portfolio
                     * following : https://api.unsplash.com/users/duanestreetdigital/following
                     * followers : https://api.unsplash.com/users/duanestreetdigital/followers
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

            public static class LinksBeanXXXXX {
                /**
                 * self : https://api.unsplash.com/collections/177997
                 * html : http://unsplash.com/collections/177997/service-image-buttons
                 * photos : https://api.unsplash.com/collections/177997/photos
                 * related : https://api.unsplash.com/collections/177997/related
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
    }

    public static class UserBeanXX {
        /**
         * id : XenmsNc5As4
         * updated_at : 2017-04-30T04:55:12-04:00
         * username : timothymuza
         * name : timothy muza
         * first_name : timothy
         * last_name : muza
         * portfolio_url : http://www.timothymuza.com
         * bio : Portrait & Wedding Photographer
         * location : Kitchener Waterloo Ontario Canada
         * total_likes : 3
         * total_photos : 6
         * total_collections : 0
         * profile_image : {"small":"https://images.unsplash.com/profile-1456014091359-ec39bcd1fe4b?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=da7b5fbe251548af68f50f252aa579cc","medium":"https://images.unsplash.com/profile-1456014091359-ec39bcd1fe4b?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=aa90c5220b9f461fd95db00e4c52774b","large":"https://images.unsplash.com/profile-1456014091359-ec39bcd1fe4b?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=fe0d490a695f304065863f670e2a3e33"}
         * links : {"self":"https://api.unsplash.com/users/timothymuza","html":"http://unsplash.com/@timothymuza","photos":"https://api.unsplash.com/users/timothymuza/photos","likes":"https://api.unsplash.com/users/timothymuza/likes","portfolio":"https://api.unsplash.com/users/timothymuza/portfolio","following":"https://api.unsplash.com/users/timothymuza/following","followers":"https://api.unsplash.com/users/timothymuza/followers"}
         */

        private String id;
        private String updated_at;
        private String username;
        private String name;
        private String first_name;
        private String last_name;
        private String portfolio_url;
        private String bio;
        private String location;
        private int total_likes;
        private int total_photos;
        private int total_collections;
        private ProfileImageBeanXX profile_image;
        private LinksBeanXXXXXX links;

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

        public ProfileImageBeanXX getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(ProfileImageBeanXX profile_image) {
            this.profile_image = profile_image;
        }

        public LinksBeanXXXXXX getLinks() {
            return links;
        }

        public void setLinks(LinksBeanXXXXXX links) {
            this.links = links;
        }

        public static class ProfileImageBeanXX {
            /**
             * small : https://images.unsplash.com/profile-1456014091359-ec39bcd1fe4b?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=da7b5fbe251548af68f50f252aa579cc
             * medium : https://images.unsplash.com/profile-1456014091359-ec39bcd1fe4b?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=aa90c5220b9f461fd95db00e4c52774b
             * large : https://images.unsplash.com/profile-1456014091359-ec39bcd1fe4b?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=fe0d490a695f304065863f670e2a3e33
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

        public static class LinksBeanXXXXXX {
            /**
             * self : https://api.unsplash.com/users/timothymuza
             * html : http://unsplash.com/@timothymuza
             * photos : https://api.unsplash.com/users/timothymuza/photos
             * likes : https://api.unsplash.com/users/timothymuza/likes
             * portfolio : https://api.unsplash.com/users/timothymuza/portfolio
             * following : https://api.unsplash.com/users/timothymuza/following
             * followers : https://api.unsplash.com/users/timothymuza/followers
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

    public static class CategoriesBeanX {
        /**
         * id : 8
         * title : Objects
         * photo_count : 13840
         * links : {"self":"https://api.unsplash.com/categories/8","photos":"https://api.unsplash.com/categories/8/photos"}
         */

        private int id;
        private String title;
        private int photo_count;
        private LinksBeanXXXXXXX links;

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

        public LinksBeanXXXXXXX getLinks() {
            return links;
        }

        public void setLinks(LinksBeanXXXXXXX links) {
            this.links = links;
        }

        public static class LinksBeanXXXXXXX {
            /**
             * self : https://api.unsplash.com/categories/8
             * photos : https://api.unsplash.com/categories/8/photos
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

    public static class TagsBean {
        /**
         * title : touch
         * url : https://images.unsplash.com/reserve/wi9yf7kTQxCNeY72cCY6_Images%20of%20Jenny%20Lace%20Plasticity%20Publish%20(4%20of%2025).jpg
         */

        private String title;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
