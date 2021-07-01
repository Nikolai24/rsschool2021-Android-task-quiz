package com.rsschool.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentResultBinding
import kotlin.system.exitProcess

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private var listener: ActionBack? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ActionBack
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userResult = binding.userResult
        val shareButton = binding.shareButton
        val backButton = binding.backButton
        val closeButton = binding.closeButton
        val questionsList = listOf(
            "Столица Великобритании:",
            "Автор романа Война и Мир:",
            "Мореплаватель открывший Америку:",
            "Режиссёр фильма Аватар:",
            "Создатель оперы Севильский цирюльник:"
        )
        val answersList = listOf(
            "Париж",
            "Минск",
            "Лондон",
            "Москва",
            "Рим",
            "Александр Пушкин",
            "Виктор Гюго",
            "Артур Конан Дойль",
            "Лев Толстой",
            "Джоан Роулинг",
            "Фернан Магеллан",
            "Христофор Колумб",
            "Васко да Гама",
            "Джеймс Кук",
            "Витус Беринг",
            "Квентин Тарантино",
            "Гай Ричи",
            "Стивен Спилберг",
            "Кристофер Нолан",
            "Джеймс Кэмерон",
            "Россини",
            "Моцарт",
            "Чайковский",
            "Бетховен",
            "Шуберт"
        )


        val result = arguments?.getString(ResultFragment.USER_ANSWER).toString()
        val trueAnswer = "34251"
        var score = 0
        var message = ""
        for (i in 0..4) {
            message = message + "${i+1}) "+ questionsList[i] + System.lineSeparator()
            if (result[i] == trueAnswer[i]) {
                score +=20
            }
            message = message + "Your answer: " + answersList[(i*5-1)+result[i].toString().toInt()] + System.lineSeparator()
        }
        userResult.text = "$score %"
        message = "Your result: $score %" + System.lineSeparator() + message

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    listener?.backToFifthFragment(result)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        shareButton.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, message)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        backButton.setOnClickListener {
            listener?.backToFifthFragment(result)
        }

        closeButton.setOnClickListener {
            activity?.finish()
            exitProcess(0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface ActionBack {
        fun backToFifthFragment(result: String)
    }

    companion object {

        @JvmStatic
        fun newInstance(result: String): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle()
            args.putString(USER_ANSWER, result)
            fragment.arguments = args
            return fragment
        }

        private const val USER_ANSWER = "USER_ANSWER"
    }
}