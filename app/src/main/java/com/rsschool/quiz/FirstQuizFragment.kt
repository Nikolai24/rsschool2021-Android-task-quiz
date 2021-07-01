package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding

class FirstQuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private var listener: ActionGenerate? = null

    private val answersList = listOf(
        "Париж",
        "Минск",
        "Лондон",
        "Москва",
        "Рим"
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ActionGenerate
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.let {
            it.setTheme(R.style.ThemeQuizFirst)
            it.window.statusBarColor = ContextCompat.getColor(it, R.color.deep_orange_100_dark)
        }
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.question.text = "Столица Великобритании:"
        val nextButton = binding.nextButton
        val previousButton = binding.previousButton
        previousButton.isEnabled = false
        binding.toolbar.navigationIcon  = null

        val optionOne = binding.optionOne
        val optionTwo = binding.optionTwo
        val optionThree = binding.optionThree
        val optionFour = binding.optionFour
        val optionFive = binding.optionFive
        optionOne.text = answersList[0]
        optionTwo.text = answersList[1]
        optionThree.text = answersList[2]
        optionFour.text = answersList[3]
        optionFive.text = answersList[4]

        var result = arguments?.getString(USER_ANSWER).toString()

        when (result[0].toString()) {
            "0" -> nextButton.isEnabled = false
            "1" -> optionOne.isChecked = true
            "2" -> optionTwo.isChecked = true
            "3" -> optionThree.isChecked = true
            "4" -> optionFour.isChecked = true
            "5" -> optionFive.isChecked = true
        }

        optionOne.setOnClickListener{
            nextButton.isEnabled = true
            result="1"+result[1]+result[2]+result[3]+result[4]
        }

        optionTwo.setOnClickListener{
            nextButton.isEnabled = true
            result="2"+result[1]+result[2]+result[3]+result[4]
        }

        optionThree.setOnClickListener{
            nextButton.isEnabled = true
            result="3"+result[1]+result[2]+result[3]+result[4]
        }

        optionFour.setOnClickListener{
            nextButton.isEnabled = true
            result="4"+result[1]+result[2]+result[3]+result[4]
        }

        optionFive.setOnClickListener{
            nextButton.isEnabled = true
            result="5"+result[1]+result[2]+result[3]+result[4]
        }

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        nextButton.setOnClickListener {
            listener?.goToSecondFragment(result)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface ActionGenerate {
        fun goToSecondFragment(result: String) {
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(result: String): FirstQuizFragment {
            val fragment = FirstQuizFragment()
            val args = Bundle()
            args.putString(USER_ANSWER, result)
            fragment.arguments = args
            return fragment
        }

        private const val USER_ANSWER = "USER_ANSWER"
    }
}