package cloud.ffeng.uc.infra.oauth.github;

import cloud.ffeng.uc.domain.valobj.PlatformAuthUser;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class GithubOAuthUser implements Serializable {
    @JSONField(name = "gists_url")
    private String gistsUrl;
    @JSONField(name = "repos_url")
    private String reposUrl;
    @JSONField(name = "two_factor_authentication")
    private String twoFactorAuthentication;
    @JSONField(name = "following_url")
    private String followingUrl;
    @JSONField(name = "bio")
    private String bio;
    @JSONField(name = "created_at")
    private String createdAt;
    @JSONField(name = "login")
    private String login;
    @JSONField(name = "type")
    private String type;
    @JSONField(name = "blog")
    private String blog;
    @JSONField(name = "private_gists")
    private String privateGists;
    @JSONField(name = "total_private_repos")
    private String totalPrivateRepos;
    @JSONField(name = "subscriptions_url")
    private String subscriptionsUrl;
    @JSONField(name = "updated_at")
    private String updatedAt;
    @JSONField(name = "site_admin")
    private String siteAdmin;
    @JSONField(name = "disk_usage")
    private String diskUsage;
    @JSONField(name = "collaborators")
    private String collaborators;
    @JSONField(name = "company")
    private String company;
    @JSONField(name = "owned_private_repos")
    private String ownedPrivateRepos;
    @JSONField(name = "id")
    private String id;
    @JSONField(name = "public_repos")
    private String publicRepos;
    @JSONField(name = "gravatar_id")
    private String gravatarId;
    @JSONField(name = "plan")
    private String plan;
    @JSONField(name = "email")
    private String email;
    @JSONField(name = "organizations_url")
    private String organizationUrl;
    @JSONField(name = "starred_url")
    private String starredUrl;
    @JSONField(name = "followers_url")
    private String followersUrl;
    @JSONField(name = "public_gists")
    private String publicGists;
    @JSONField(name = "url")
    private String url;
    @JSONField(name = "received_events_url")
    private String receivedEventsUrl;
    @JSONField(name = "followers")
    private String followers;
    @JSONField(name = "avatar_url")
    private String avatarUrl;
    @JSONField(name = "events_url")
    private String eventsUrl;
    @JSONField(name = "html_url")
    private String htmlUrl;
    @JSONField(name = "following")
    private String following;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "location")
    private String location;
    @JSONField(name = "node_id")
    private String nodeId;

    public PlatformAuthUser toPlatformAuthUser() {
        PlatformAuthUser platformAuthUser = new PlatformAuthUser();
        platformAuthUser.setPlatformUserId(this.id);
        platformAuthUser.setAvatarUrl(this.getAvatarUrl());
        platformAuthUser.setNickname(this.name);
        platformAuthUser.setEmail(this.getEmail());
        return platformAuthUser;
    }
}
