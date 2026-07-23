/*
 * Copyright (c) 2010-2026 Mark Allen, Norbert Bartels.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.restfb.webhook;

import com.restfb.types.webhook.*;
import com.restfb.types.webhook.instagram.InstagramCommentsValue;
import com.restfb.types.webhook.instagram.InstagramMentionsValue;
import com.restfb.types.webhook.instagram.InstagramStoryInsightsValue;

/**
 * abstract class as base for custom webhook change listener, with this abstract class it is possible to implement only
 * a subset of the need methods and ignore the other ones.
 */
public abstract class AbstractWebhookChangeListener implements WebhookChangeListener {

    @Override
    public void feedCommentValue(FeedCommentValue feedCommentValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void feedPhotoAddValue(FeedPhotoAddValue feedPhotoAddValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void feedPhotoRemoveValue(FeedPhotoRemoveValue feedPhotoRemoveValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void feedVideoValue(FeedVideoValue feedVideoValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void feedVideoRemoveValue(FeedVideoRemoveValue feedVideoRemoveValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void feedStatusValue(FeedStatusValue feedStatusValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void feedAlbumAddValue(FeedAlbumAddValue feedAlbumAddValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void feedLikeValue(FeedLikeValue feedLikeValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void feedEventValue(FeedEventValue feedEventValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void feedPostValue(FeedPostValue feedPostValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void feedVideoBlockMute(FeedVideoBlockMute feedVideoBlockMute) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void feedReactionValue(FeedReactionValue feedReactionValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void feedShareValue(FeedShareValue feedShareValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void feedAlbumEditedValue(FeedAlbumEditedValue feedAlbumEditedValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void instagramCommentsValue(InstagramCommentsValue instagramCommentsValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void instagramMentionsValue(InstagramMentionsValue instagramMentionsValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void instagramStoryInsightsValue(InstagramStoryInsightsValue instagramStoryInsightsValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void pageLeadgen(PageLeadgen pageLeadgen) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void permissionChangeValue(PermissionChangeValue permissionChangeValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void mentionPostAddValue(MentionPostAddValue mentionPostAddValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void userPageValue(UserPageValue userPageValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void ratingsReactionValue(RatingsReactionValue ratingsReactionValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void ratingsLikeValue(RatingsLikeValue ratingsLikeValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void ratingsCommentValue(RatingsCommentValue ratingsCommentValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void ratingsRatingValue(RatingsRatingValue ratingsRatingValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
