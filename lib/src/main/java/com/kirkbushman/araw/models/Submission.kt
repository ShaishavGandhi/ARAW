package com.kirkbushman.araw.models

import android.os.Parcelable
import com.kirkbushman.araw.models.general.Gildings
import com.kirkbushman.araw.models.general.Media
import com.kirkbushman.araw.models.general.MediaEmbed
import com.kirkbushman.araw.models.general.RedditMedia
import com.kirkbushman.araw.models.general.SubmissionPreview
import com.kirkbushman.araw.models.mixins.Contribution
import com.kirkbushman.araw.models.mixins.Created
import com.kirkbushman.araw.models.mixins.Distinguishable
import com.kirkbushman.araw.models.mixins.Editable
import com.kirkbushman.araw.models.mixins.Gildable
import com.kirkbushman.araw.models.mixins.Replyable
import com.kirkbushman.araw.models.mixins.Votable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * This class represents a user post in a specific Subreddit,
 * this class can be replied to, in a form of a comment section.
 *
 * The user submission can be a text post, a link to a Url or can include images/media.
 *
 * @property id This item identifier, e.g. "8xwlg"
 *
 * @property fullname Fullname of sumbission, e.g. "t1_c3v7f8u"
 *
 * @property allAwarding List of the submission's received awardings.
 *
 * @property author Name of the Submission's author.
 *
 * @property authorFlairBackgroundColor background color of the author's flair, used in the site UI.
 *
 * @property authorFlairCssClass predefined css class of the author's flair, used in the site UI.
 *
 * @property authorFlairText plain text of the author's flair.
 *
 * @property authorFlairTextColor text color of the author's flair, used in the site UI.
 *
 * @property authorFlairTemplateId template id of the author's flair, used in the site UI.
 *
 * @property authorFlairType type of author flair
 *
 */
@JsonClass(generateAdapter = true)
@Parcelize
data class Submission(

    @Json(name = "id")
    override val id: String,

    @Json(name = "name")
    override val fullname: String,

    @Json(name = "all_awardings")
    val allAwarding: List<Awarding>?,

    @Json(name = "author")
    val author: String,

    @Json(name = "author_flair_background_color")
    val authorFlairBackgroundColor: String?,

    @Json(name = "author_flair_css_class")
    val authorFlairCssClass: String?,

    @Json(name = "author_flair_text")
    val authorFlairText: String?,

    @Json(name = "author_flair_text_color")
    val authorFlairTextColor: String?,

    @Json(name = "author_flair_template_id")
    val authorFlairTemplateId: String?,

    @Json(name = "author_flair_type")
    val authorFlairType: String?,

    @Json(name = "author_fullname")
    val authorFullname: String?,

    @Json(name = "can_gild")
    override val canGild: Boolean,

    @Json(name = "clicked")
    val clicked: Boolean,

    @Json(name = "created")
    override val created: Long,

    @Json(name = "created_utc")
    override val createdUtc: Long,

    @Json(name = "crosspost_parent")
    val crosspostParentFullname: String?,

    @Json(name = "crosspost_parent_list")
    val crosspostParentList: List<Submission>?,

    @Json(name = "distinguished")
    override val distinguishedRaw: String?,

    @Json(name = "domain")
    val domain: String,

    @Json(name = "edited")
    override val editedRaw: @RawValue Any,

    @Json(name = "link_flair_background_color")
    val linkFlairBackgroundColor: String?,

    @Json(name = "link_flair_css_class")
    val linkFlairCssClass: String?,

    @Json(name = "link_flair_text")
    val linkFlairText: String?,

    @Json(name = "link_flair_text_color")
    val linkFlairTextColor: String?,

    @Json(name = "link_flair_template_id")
    val linkFlairTemplateId: String?,

    @Json(name = "link_flair_type")
    val linkFlairType: String?,

    @Json(name = "gildings")
    override val gildings: Gildings,

    @Json(name = "archived")
    val isArchived: Boolean,

    @Json(name = "is_crosspostable")
    val isCrosspostable: Boolean,

    @Json(name = "hidden")
    val isHidden: Boolean,

    @Json(name = "locked")
    val isLocked: Boolean,

    @Json(name = "media_only")
    val isMediaOnly: Boolean,

    @Json(name = "is_meta")
    val isMeta: Boolean,

    @Json(name = "pinned")
    val isPinned: Boolean,

    @Json(name = "is_reddit_media_domain")
    val isRedditMediaDomain: Boolean,

    @Json(name = "is_robot_indexable")
    val isRobotIndexable: Boolean,

    @Json(name = "saved")
    val isSaved: Boolean,

    @Json(name = "is_self")
    val isSelf: Boolean,

    @Json(name = "spoiler")
    val isSpoiler: Boolean,

    @Json(name = "stickied")
    val isStickied: Boolean,

    @Json(name = "is_video")
    val isVideo: Boolean,

    @Json(name = "likes")
    override val likes: Boolean?,

    @Json(name = "secure_media")
    val media: Media?,

    @Json(name = "secure_media_embed")
    val mediaEmbed: MediaEmbed,

    @Json(name = "media")
    val redditMedia: RedditMedia?,

    @Json(name = "num_crossposts")
    val numCrossposts: Int,

    @Json(name = "num_comments")
    val numComments: Int,

    @Json(name = "over_18")
    val over18: Boolean,

    @Json(name = "permalink")
    val permalink: String,

    @Json(name = "preview")
    val preview: SubmissionPreview?,

    @Json(name = "removed_by_category")
    val removedByCategory: String?,

    @Json(name = "score")
    override val score: Int,

    @Json(name = "selftext")
    val selfText: String?,

    @Json(name = "selftext_html")
    val selfTextHtml: String?,

    @Json(name = "subreddit")
    val subreddit: String,

    @Json(name = "subreddit_id")
    val subredditId: String,

    @Json(name = "subreddit_name_prefixed")
    val subredditNamePrefixed: String,

    @Json(name = "subreddit_subscribers")
    val subredditSubscribers: Int,

    @Json(name = "thumbnail")
    val thumbnailUrl: String?,

    @Json(name = "thumbnail_width")
    val thumbnailWidth: Int?,

    @Json(name = "thumbnail_height")
    val thumbnailHeight: Int?,

    @Json(name = "title")
    val title: String,

    @Json(name = "upvote_ratio")
    val upvoteRatio: Float?,

    @Json(name = "url")
    val url: String

) : Contribution, Votable, Created, Editable, Distinguishable, Gildable, Replyable, Parcelable
