package com.iambenbradley.chefbook.util

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

class BackdropAnimatorListener(private val frontSheet: View, private val expandedView: View) : View.OnClickListener {

    private val animatorSet = AnimatorSet()
    var backdropShown = false

    override fun onClick(v: View?) {
        backdropShown = !backdropShown
        animatorSet.removeAllListeners()
        animatorSet.end()
        animatorSet.cancel()

        val animator = ObjectAnimator.ofFloat(frontSheet, "translationY", (if (backdropShown) expandedView.height else 0).toFloat())
        animator.duration = 500
        animatorSet.play(animator)
        animator.start()

        if (!backdropShown) {
            val imm = expandedView.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(expandedView.windowToken, 0)
        }
    }
}