package com.iambenbradley.chefbook.util

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

class BackdropAnimatorListener(val frontSheet: View, val expandedView: View) : View.OnClickListener {

    private val animatorSet = AnimatorSet()
    private var backdropShown = false

    override fun onClick(v: View?) {
        backdropShown = !backdropShown
        animatorSet.removeAllListeners()
        animatorSet.end()
        animatorSet.cancel()

        val animator = ObjectAnimator.ofFloat(frontSheet, "translationY", (if (backdropShown) expandedView.height else 0).toFloat())
        animator.duration = 500
        animatorSet.play(animator)
        animator.start()
    }
}