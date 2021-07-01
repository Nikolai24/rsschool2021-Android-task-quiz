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

class ThirdQuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private var listener: ActionBack? = null
    private var listener2: ActionGenerate? = null

    private val answersList = listOf(
        "Фернан Магеллан",
        "Христофор Колумб",
        "Васко да Гама",
        "Джеймс Кук",
        "Витус Беринг"
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ActionBack
        listener2 = context as ActionGenerate
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.let {
            it.setTheme(R.style.ThemeQuizThird)
            it.window.statusBarColor = ContextCompat.getColor(it, R.color.light_green_100_dark)
        }
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.question.text = "Мореплаватель открывший Америку:"
        val nextButton = binding.nextButton
        val previousButton = binding.previousButton
        binding.toolbar.title = "Question 3"

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

        var result = arguments?.getString(ThirdQuizFragment.USER_ANSWER).toString()

        when (result[2].toString()) {
            "0" -> nextButton.isEnabled = false
            "1" -> optionOne.isChecked = true
            "2" -> optionTwo.isChecked = true
            "3" -> optionThree.isChecked = true
            "4" -> optionFour.isChecked = true
            "5" -> optionFive.isChecked = true
        }

        optionOne.setOnClickListener{
            nextButton.isEnabled = true
            result=result[0]+result[1].toString()+"1"+result[3]+result[4]
        }

        optionTwo.setOnClickListener{
            nextButton.isEnabled = true
            result=result[0]+result[1].toString()+"2"+result[3]+result[4]
        }

        optionThree.setOnClickListener{
            nextButton.isEnabled = true
            result=result[0]+result[1].toString()+"3"+result[3]+result[4]
        }

        optionFour.setOnClickListener{
            nextButton.isEnabled = true
            result=result[0]+result[1].toString()+"4"+result[3]+result[4]
        }

        optionFive.setOnClickListener{
            nextButton.isEnabled = true
            result=result[0]+result[1].toString()+"5"+result[3]+result[4]
        }

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        nextButton.setOnClickListener {
            listener2?.goToFourthFragment(result)
        }

        previousButton.setOnClickListener {
            listener?.backToSecondFragment(result)
        }

        binding.toolbar.setNavigationOnClickListener{
            listener?.backToSecondFragment(result)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface ActionGenerate {
        fun goToFourthFragment(result: String) {
        }
    }

    interface ActionBack {
        fun backToSecondFragment(result: String)
    }

    companion object {

        @JvmStatic
        fun newInstance(result: String): ThirdQuizFragment {
            val fragment = ThirdQuizFragment()
            val args = Bundle()
            args.putString(USER_ANSWER, result)
            fragment.arguments = args
            return fragment
        }

        private const val USER_ANSWER = "USER_ANSWER"
    }
}