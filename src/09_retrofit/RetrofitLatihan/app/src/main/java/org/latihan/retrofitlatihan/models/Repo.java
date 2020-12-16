package org.latihan.retrofitlatihan.models;

import com.google.gson.annotations.SerializedName;

public class Repo {
    @SerializedName("id")
    private Integer id;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("full_name")
    private String fullName;

    public Integer getId() {
        return id;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getFullName() {
        return fullName;
    }
}
