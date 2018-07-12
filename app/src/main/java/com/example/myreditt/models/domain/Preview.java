package com.example.myreditt.models.domain;

import java.util.List;

/**
 * Created by Sanoop
 */

public class Preview {

    private List<RedditImage> images;
    private boolean enabled;

    public List<RedditImage> getImages() {
        return images;
    }

    public boolean isEnabled() {
        return enabled;
    }

}
