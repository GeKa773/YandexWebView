package com.geka.radchenko.yandexwebview

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.geka.radchenko.yandexwebview.databinding.BackDialogBinding

class BackPressedDialog(private val clickYes: () -> Unit) : DialogFragment(R.layout.back_dialog) {
    lateinit var binding: BackDialogBinding

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.back_dialog, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val attributes = dialog?.window?.attributes
        attributes?.let {
            it.gravity = Gravity.CENTER
            it.width = ViewGroup.LayoutParams.MATCH_PARENT
            it.windowAnimations = R.style.TextDialog
        }
        dialog?.window?.attributes = attributes
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setStyle(STYLE_NORMAL, R.style.TextDialog)

        binding.btYes.setOnClickListener {
            dismiss()
            clickYes.invoke()
        }
        binding.btNo.setOnClickListener { dismiss() }
    }
}