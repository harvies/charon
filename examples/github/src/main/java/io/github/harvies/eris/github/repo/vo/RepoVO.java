package io.github.harvies.eris.github.repo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;

/**
 * @author harvies
 */
@Data
@Builder
public class RepoVO implements Serializable {

    private static final long serialVersionUID = -1L;

    private String description;

    private String homepage;

    private String name;

    private String fullName;

    private String htmlUrl;

    private Boolean fork;
    @JsonProperty("private")
    private Boolean _private;

    private Integer forkCount;

    private Integer stargazersCount;

    private Integer watcherCount;

    private Integer size;

    private String defaultBranch;

    private String language;

    private URL url;

    private Long id;

    private Date createdAt;

    private Date updateAt;
}
