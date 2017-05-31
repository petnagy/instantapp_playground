package com.playground.instant.base_module.network;

import com.playground.instant.base_module.data.GitCommit;
import com.playground.instant.base_module.data.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by petnagy on 2017. 05. 24..
 */

public interface GitHubEndpoint {

    @GET("users/{userName}/repos")
    Call<List<GitHubRepo>> getRepos(@Path("userName") String userName);

    @GET("repos/{userName}/{repo}/commits")
    Call<List<GitCommit>> getCommits(@Path("userName") String userName, @Path("repo") String repo);
}
