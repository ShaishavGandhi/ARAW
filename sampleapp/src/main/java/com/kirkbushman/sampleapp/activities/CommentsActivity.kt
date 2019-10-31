package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.araw.models.Comment
import com.kirkbushman.araw.models.MoreComments
import com.kirkbushman.araw.models.Submission
import com.kirkbushman.araw.models.general.Vote
import com.kirkbushman.araw.models.mixins.CommentData
import com.kirkbushman.araw.utils.toCommentSequence
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.TestApplication
import com.kirkbushman.sampleapp.controllers.CommentController
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.sampleapp.fragments.ReplyBottomFragment
import kotlinx.android.synthetic.main.activity_comments.*

class CommentsActivity : AppCompatActivity() {

    private val client by lazy { TestApplication.instance.getClient() }

    private val comments = ArrayList<CommentData>()
    private val controller by lazy {
        CommentController(object : CommentController.CommentCallback {

            override fun onUpvoteClick(submission: Submission) {

                doAsync(doWork = {
                    client?.contributionClient?.vote(Vote.UPVOTE, submission)
                })
            }

            override fun onNoneClick(submission: Submission) {

                doAsync(doWork = {
                    client?.contributionClient?.vote(Vote.NONE, submission)
                })
            }

            override fun onDownClick(submission: Submission) {

                doAsync(doWork = {
                    client?.contributionClient?.vote(Vote.DOWNVOTE, submission)
                })
            }

            override fun onSaveClick(submission: Submission) {

                doAsync(doWork = {
                    client?.contributionClient?.save(!submission.isSaved, submission)
                })
            }

            override fun onHideClick(submission: Submission) {

                doAsync(doWork = {
                    client?.contributionClient?.hide(submission)
                })
            }

            override fun onLockClick(submission: Submission) {

                doAsync(doWork = {
                    client?.contributionClient?.lock(submission)
                })
            }

            override fun onLoadMoreClick(moreComments: MoreComments, submission: Submission) {

                val addendum = ArrayList<CommentData>()

                doAsync(doWork = {

                    val more = client?.contributionClient?.moreChildren(moreComments, submission)
                    addendum.addAll(more ?: listOf())
                }, onPost = {

                    replaceMoreComments(moreComments, addendum)
                })
            }

            override fun onReplyClick(comment: Comment) {

                val fr = ReplyBottomFragment.instance(comment)
                fr.show(supportFragmentManager, "Reply Bottom Fragment")
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        list.setHasFixedSize(true)
        list.setController(controller)

        search_bttn.setOnClickListener {

            val submissionId = search.text.toString().trim()

            doAsync(doWork = {

                val fetcher = client?.contributionClient?.comments(submissionId)
                val temp = fetcher?.fetchNext() ?: listOf()

                if (fetcher!!.getSubmission() != null) {
                    controller.setSubmission(fetcher.getSubmission()!!)
                }

                comments.clear()
                comments.addAll(temp.toCommentSequence())
            }, onPost = {

                controller.setComments(comments)
            })
        }
    }

    private fun replaceMoreComments(moreComments: MoreComments, addendum: List<CommentData>) {

        val index = comments.indexOf(moreComments)
        comments.remove(moreComments)
        comments.addAll(index, addendum)

        controller.setComments(comments)
    }
}
