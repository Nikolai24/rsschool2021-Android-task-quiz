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

class FourthQuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private var listener: ActionBack? = null
    private var listener2: ActionGenerate? = null

    private val answersList = listOf(
        "Квентин Тарантино",
        "Гай Ричи",
        "Стивен Спилберг",
        "Кристофер Нолан",
        "Джеймс Кэмерон"
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
            it.setTheme(R.style.ThemeQuizFourth)
            it.window.statusBarColor = ContextCompat.getColor(it, R.color.cyan_100_dark)
        }
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.question.text = "Режиссёр фильма Аватар:"
        val nextButton = binding.nextButton
        val previousButton = binding.previousButton
        binding.toolbar.title = "Question 4"

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

        var result = arguments?.getString(FourthQuizFragment.USER_ANSWER).toString()

        when (result[3].toString()) {
            "0" -> nextButton.isEnabled = false
            "1" -> optionOne.isChecked = true
            "2" -> optionTwo.isChecked = true
            "3" -> optionThree.isChecked = true
            "4" -> optionFour.isChecked = true
            "5" -> optionFive.isChecked = true
        }

        optionOne.setOnClickListener{
            nextButton.isEnabled = true
            result=result[0]+result[1].toString()+result[2]+"1"+result[4]
        }

        optionTwo.setOnClickListener{
            nextButton.isEnabled = true
            result=result[0]+result[1].toString()+result[2]+"2"+result[4]
        }

        optionThree.setOnClickListener{
            nextButton.isEnabled = true
            result=result[0]+result[1].toString()+result[2]+"3"+result[4]
        }

        optionFour.setOnClickListener{
            nextButton.isEnabled = true
            result=result[0]+result[1].toString()+result[2]+"4"+result[4]
        }

        optionFive.setOnClickListener{
            nextButton.isEnabled = true
            result=result[0]+result[1].toString()+result[2]+"5"+result[4]
        }

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        nextButton.setOnClickListener {
            listener2?.goToFifthFragment(result)
        }

        previousButton.setOnClickListener {
            listener?.backToThirdFragment(result)
        }

        binding.toolbar.setNavigationOnClickListener{
            listener?.backToThirdFragment(result)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface ActionGenerate {
        fun goToFifthFragment(result: String) {
        }
    }

    interface ActionBack {
        fun backToThirdFragment(result: String)
    }

    companion object {

        @JvmStatic
        fun newInstance(result: String): FourthQuizFragment {
            val fragment = FourthQuizFragment()
            val args = Bundle()
            args.putString(USER_ANSWER, result)
            fragment.arguments = args
            return fragment
        }

        private const val USER_ANSWER = "USER_ANSWER"
    }
}